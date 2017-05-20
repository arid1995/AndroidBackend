package org.dimwits.services;

import java.util.HashMap;
import java.util.Map;
import org.dimwits.database.entities.User;
import org.springframework.stereotype.Service;

/**
 * Created by farid on 5/19/17.
 */
@Service
public class SessionService {
  private Map<String, User> sessionIdToUser = new HashMap<>();

  public void addUserToSession (String sessionId, User user) {
    sessionIdToUser.put(sessionId, user);
  }

  public void endSession (String sessionId) {
    sessionIdToUser.remove(sessionId);
  }

  public User getUserBySessionId(String sessionId) {
    return sessionIdToUser.get(sessionId);
  }

  public long getLength() {
    return sessionIdToUser.size();
  }
}
