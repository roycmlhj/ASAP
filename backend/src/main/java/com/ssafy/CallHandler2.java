/*
 * (C) Copyright 2014 Kurento (http://kurento.org/)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.ssafy;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

import org.kurento.client.EventListener;
import org.kurento.client.IceCandidate;
import org.kurento.client.IceCandidateFoundEvent;
import org.kurento.client.KurentoClient;
import org.kurento.client.MediaPipeline;
import org.kurento.client.WebRtcEndpoint;
import org.kurento.jsonrpc.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

/**
 * Protocol handler for 1 to N video call communication.
 *
 * @author Boni Garcia (bgarcia@gsyc.es)
 * @since 5.0.0
 */
public class CallHandler2 extends TextWebSocketHandler {

  private static final Logger log = LoggerFactory.getLogger(CallHandler2.class);
  private static final Gson gson = new GsonBuilder().create();

  private final ConcurrentHashMap<String, UserSession2> viewers = new ConcurrentHashMap<>();

  @Autowired
  private KurentoClient kurento;

  private MediaPipeline pipeline;
  private UserSession2 presenterUserSession2;

  @Override
  public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
    JsonObject jsonMessage = gson.fromJson(message.getPayload(), JsonObject.class);
    log.debug("Incoming message from session '{}': {}", session.getId(), jsonMessage);

    switch (jsonMessage.get("id").getAsString()) {
      case "joinRoom" :
        UserSession2 join = new UserSession2(session);
        viewers.put(session.getId(), join);
        log.info("new view join into viewers");
        break;
      case "presenter":
        try {
          log.info("callhandler2-presenter in "+session);
          presenter(session, jsonMessage);
        } catch (Throwable t) {
          handleErrorResponse(t, session, "presenterResponse");
        }
        break;
      case "viewer":
        try {
          log.info("callhandler2-viewer in "+session);
          viewer(session, jsonMessage);
        } catch (Throwable t) {
          handleErrorResponse(t, session, "viewerResponse");
        }
        break;
      case "onIceCandidate": {
        JsonObject candidate = jsonMessage.get("candidate").getAsJsonObject();

        UserSession2 user = null;
        if (presenterUserSession2 != null) {
            log.info("{} + {}",presenterUserSession2.getSession(),session);
          if (presenterUserSession2.getSession() == session) {
            user = presenterUserSession2;
          } else {
            user = viewers.get(session.getId());
          }
        }
        if (user != null) {
          IceCandidate cand =
              new IceCandidate(candidate.get("candidate").getAsString(), candidate.get("sdpMid")
                  .getAsString(), candidate.get("sdpMLineIndex").getAsInt());
          user.addCandidate(cand);
          log.info("newIceCandidate");
        }
        break;
      }
      case "stop":
        stop(session);
        break;
      case "test":
        log.info("test 도착 with session : " + session);
        JsonObject response = new JsonObject();
        response.addProperty("id", "testResponse");
        session.sendMessage(new TextMessage(response.toString()));
      default:
        break;
    }
  }

  private void handleErrorResponse(Throwable throwable, WebSocketSession session, String responseId)
      throws IOException {
    stop(session);
    log.error(throwable.getMessage(), throwable);
    JsonObject response = new JsonObject();
    response.addProperty("id", responseId);
    response.addProperty("response", "rejected");
    response.addProperty("message", throwable.getMessage());
    session.sendMessage(new TextMessage(response.toString()));
  }

  private synchronized void presenter(final WebSocketSession session, JsonObject jsonMessage)
      throws IOException {
    if (presenterUserSession2 == null) {
      presenterUserSession2 = new UserSession2(session);

      pipeline = kurento.createMediaPipeline();
      presenterUserSession2.setWebRtcEndpoint(new WebRtcEndpoint.Builder(pipeline).build());

      WebRtcEndpoint presenterWebRtc = presenterUserSession2.getWebRtcEndpoint();

      presenterWebRtc.addIceCandidateFoundListener(new EventListener<IceCandidateFoundEvent>() {

        @Override
        public void onEvent(IceCandidateFoundEvent event) {
          JsonObject response = new JsonObject();
          //response.addProperty("id", "iceOne2ManyCandidate");
          response.addProperty("id", "iceCandidate");
          response.addProperty("type", "screenvideo");
          response.add("candidate", JsonUtils.toJsonObject(event.getCandidate()));
          try {
            synchronized (session) {
              log.info("presenter synch + {}",session);
              session.sendMessage(new TextMessage(response.toString()));
            }
          } catch (IOException e) {
            log.debug(e.getMessage());
          }
        }
      });

      String sdpOffer = jsonMessage.getAsJsonPrimitive("sdpOffer").getAsString();
      String sdpAnswer = presenterWebRtc.processOffer(sdpOffer);

      JsonObject response = new JsonObject();
      response.addProperty("id", "presenterResponse");
      response.addProperty("response", "accepted");
      response.addProperty("sdpAnswer", sdpAnswer);

      synchronized (session) {
        log.info("asdasdasdasdasdasdasdasdasd");
        presenterUserSession2.sendMessage(response);
      }
      presenterWebRtc.gatherCandidates();
      for (UserSession2 viewer : viewers.values()) {
        //if(viewer.getSession().equals(presenterUserSession2.getSession())) continue;
        JsonObject responseforv = new JsonObject();
        responseforv.addProperty("id", "newPresenter");
        viewer.sendMessage(responseforv);
      }

    } else {
      JsonObject response = new JsonObject();
      response.addProperty("id", "presenterResponse");
      response.addProperty("response", "rejected");
      response.addProperty("message",
          "Another user is currently acting as sender. Try again later ...");
      session.sendMessage(new TextMessage(response.toString()));
    }
  }

  private synchronized void viewer(final WebSocketSession session, JsonObject jsonMessage)
      throws IOException {
      String sessionId = session.getId();
      UserSession2 viewer = viewers.get(sessionId);
      
      WebRtcEndpoint nextWebRtc = new WebRtcEndpoint.Builder(pipeline).build();

      nextWebRtc.addIceCandidateFoundListener(new EventListener<IceCandidateFoundEvent>() {

        @Override
        public void onEvent(IceCandidateFoundEvent event) {
          JsonObject response = new JsonObject();
          response.addProperty("id", "iceCandidate");
          //response.addProperty("id", "iceOne2ManyCandidate");
          response.addProperty("type", "screenvideo");
          response.add("candidate", JsonUtils.toJsonObject(event.getCandidate()));
          try {
            synchronized (session) {
              session.sendMessage(new TextMessage(response.toString()));
            }
          } catch (IOException e) {
            log.debug(e.getMessage());
          }
        }
      });

      log.info("viewer candidate message send");
      viewer.setWebRtcEndpoint(nextWebRtc);
      presenterUserSession2.getWebRtcEndpoint().connect(nextWebRtc);
      String sdpOffer = jsonMessage.getAsJsonPrimitive("sdpOffer").getAsString();
      log.info("viewer sdpOffer received");
      String sdpAnswer = nextWebRtc.processOffer(sdpOffer);
    
      JsonObject response = new JsonObject();
      response.addProperty("id", "viewerResponse");
      response.addProperty("response", "accepted");
      response.addProperty("sdpAnswer", sdpAnswer);
      
      synchronized (session) {
        viewer.sendMessage(response);
        log.info("viewerResponse Message sended");
      }
      nextWebRtc.gatherCandidates();
    
  }

  private synchronized void stop(WebSocketSession session) throws IOException {
    String sessionId = session.getId();
    log.info("session : " + session);
    log.info("sessionId : " + sessionId);
    if (presenterUserSession2 != null && presenterUserSession2.getSession().getId().equals(sessionId)) {
      for (UserSession2 viewer : viewers.values()) {
        JsonObject response = new JsonObject();
        response.addProperty("id", "stopCommunication");
        viewer.sendMessage(response);
      }

      log.info("Releasing media pipeline");
      if (pipeline != null) {
        pipeline.release();
      }
      pipeline = null;
      presenterUserSession2 = null;
    } else if (viewers.containsKey(sessionId)) {
      if (viewers.get(sessionId).getWebRtcEndpoint() != null) {
        viewers.get(sessionId).getWebRtcEndpoint().release();
      }
      viewers.remove(sessionId);
    }
  }

  @Override
  public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
    stop(session);
  }

}
