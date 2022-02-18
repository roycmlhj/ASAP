package com.ssafy;


import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

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



public class ChatHandler extends TextWebSocketHandler {

  private static final Logger log = LoggerFactory.getLogger(CallHandler2.class);
  private static final Gson gson = new GsonBuilder().create();

  private final ConcurrentHashMap<String, User> chatters = new ConcurrentHashMap<>();

  @Override
  public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
    JsonObject jsonMessage = gson.fromJson(message.getPayload(), JsonObject.class);
    log.debug("Incoming message from session '{}': {}", session.getId(), jsonMessage);

    switch (jsonMessage.get("id").getAsString()) {
      case "joinRoom" :
        User join = new User(session);
        chatters.put(session.getId(), join);
        log.info("new view join into viewers");
        break;
      case "newMessage":
        try {
          log.info("callhandler2-presenter in "+session);
          chatter(session, jsonMessage);
        } catch (Throwable t) {
          handleErrorResponse(t, session, "presenterResponse");
        }
        break;
     default :
        break;
    }
  }

  private void handleErrorResponse(Throwable throwable, WebSocketSession session, String responseId)
      throws IOException {
    //stop(session);
    log.error(throwable.getMessage(), throwable);
    JsonObject response = new JsonObject();
    response.addProperty("id", responseId);
    response.addProperty("response", "rejected");
    response.addProperty("message", throwable.getMessage());
    session.sendMessage(new TextMessage(response.toString()));
  }

 
  private void chatter(WebSocketSession session, JsonObject jsonMessage) throws IOException{
    for(User chat : chatters.values()){
      
        String sender = jsonMessage.getAsJsonPrimitive("sender").getAsString();
        String content = jsonMessage.getAsJsonPrimitive("content").getAsString();
        JsonObject response = new JsonObject();
        response.addProperty("id", "MessageEvent");
        response.addProperty("content", content);
        response.addProperty("sender", sender);
        chat.sendMessage(response);
      }
    
  }

  @Override
  public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
    //stop(session);
  }

}