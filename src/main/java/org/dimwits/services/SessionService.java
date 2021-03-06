package org.dimwits.services;

import java.math.BigInteger;
import java.security.SecureRandom;
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

  private String generateHash() {
    return new BigInteger(128, new SecureRandom()).toString(32);
  }
  public String addUserToSession (User user) {
    String hash = generateHash();
    sessionIdToUser.put(hash, user);
    return hash;
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
