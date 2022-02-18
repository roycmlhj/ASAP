package com.ssafy;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.google.gson.JsonObject;


public class User{
  private static final Logger log = LoggerFactory.getLogger(UserSession2.class);

  private final WebSocketSession session;
  
  public User(WebSocketSession session) {
    this.session = session;
  }

  public WebSocketSession getSession() {
    return session;
  }
  
  public void sendMessage(JsonObject message) throws IOException {
    log.debug("Sending message from user with session Id '{}': {}", session.getId(), message);
    log.info("Sending message from user with session Id '{}': {}", session.getId(), message);
    //session.sendMessage(new TextMessage(message.toString()));
    synchronized (session) {
      session.sendMessage(new TextMessage(message.toString()));
    }
  }
}