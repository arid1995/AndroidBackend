package org.dimwits.database.dao;

import java.util.List;
import org.dimwits.services.HibernateSessionService;
import org.dimwits.database.entities.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * Created by farid on 5/7/17.
 */
@SuppressWarnings({"JpaQlInspection", "unchecked"})
@Repository
public class UserDaoImpl implements UserDao {

  @Autowired
  private HibernateSessionService hibernateSessionService;

  @Override
  public User save(User user) throws DataIntegrityViolationException {
    try (final Session session = hibernateSessionService.getSessionFactory().openSession()) {
      final Transaction transaction = session.beginTransaction();
      System.out.println(user.getFirstName());
      user.setUserId((Integer) session.save(user));
      transaction.commit();
      return user;
    }
  }

  @Override
  public User findByLogin(String login) {
    try (final Session session = hibernateSessionService.getSessionFactory().openSession()) {
      final Transaction transaction = session.beginTransaction();
      final Query query = session.createQuery("from User user where user.login = :login");
      query.setParameter("login", login);
      final User user = (User) query.uniqueResult();
      transaction.commit();
      return user;
    }
  }

  @Override
  public User findByUserId(Integer userId) {
    try(final Session session = hibernateSessionService.getSessionFactory().openSession()) {
      final Transaction transaction = session.beginTransaction();
      final Query query = session.createQuery("from User user where user.userId = :userId");
      query.setParameter("userId", userId);
      final User user = (User) query.uniqueResult();
      transaction.commit();
      return user;
    }
  }

  @Override
  public List<User> findAll() {
    try (final Session session = hibernateSessionService.getSessionFactory().openSession()) {
      final Transaction transaction = session.beginTransaction();
      final List users = session.createQuery("from User user").list();
      transaction.commit();
      return users;
    }
  }

  @Override
  public void update(User user) throws DataIntegrityViolationException {
    try (final Session session = hibernateSessionService.getSessionFactory().openSession()) {
      final Transaction transaction = session.beginTransaction();
      session.update(user);
      transaction.commit();
    }
  }

  @Override
  public void delete(User user) {
    try (final Session session = hibernateSessionService.getSessionFactory().openSession()) {
      final Transaction transaction = session.beginTransaction();
      session.remove(user);
      transaction.commit();
      session.close();
    }
  }
}
