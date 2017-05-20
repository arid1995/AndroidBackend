package org.dimwits.services;

import org.dimwits.database.dao.UserDaoImpl;
import org.dimwits.database.entities.User;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

/**
 * Created by farid on 5/19/17.
 */
@Service
public class AccountService {
  @Autowired
  UserDaoImpl userDao;

  public User addUser(User user) {
    try {
      return userDao.save(user);
    } catch (DataIntegrityViolationException ex) {
      ex.printStackTrace();
      return null;
    }
  }

  public User getUser(String login) {
    try {
      return userDao.findByLogin(login);
    } catch (HibernateException ex) {
      ex.printStackTrace();
      return null;
    }
  }

  public User getUser(int userId) {
    try {
      return userDao.findByUserId(userId);
    } catch (HibernateException ex) {
      ex.printStackTrace();
      return null;
    }
  }

  public Iterable<User> getAllUsers() {
    try {
      return userDao.findAll();
    } catch (HibernateException ex) {
      ex.printStackTrace();
      return null;
    }
  }

  public void updateUser(User user) throws DataIntegrityViolationException {
    try {
      userDao.update(user);
    } catch (HibernateException ex) {
      ex.printStackTrace();
    }
  }

  public void deleteUser(User user) {
    try {
      userDao.delete(user);
    } catch (HibernateException ex) {
      ex.printStackTrace();
    }
  }
}
