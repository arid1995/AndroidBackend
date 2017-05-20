package org.dimwits.database.dao;

import java.util.List;
import org.dimwits.database.entities.User;
import org.springframework.dao.DataIntegrityViolationException;

/**
 * Created by farid on 5/7/17.
 */
public interface UserDao {
  User save(User user) throws DataIntegrityViolationException;

  User findByLogin(String login);

  User findByUserId(Integer userId);

  List<User> findAll();

  void update(User user) throws DataIntegrityViolationException;

  void delete(User user);
}
