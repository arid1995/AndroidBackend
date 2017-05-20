package org.dimwits.services;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Service;

/**
 * Created by farid on 5/7/17.
 */
@Service
public class HibernateSessionService {

  private SessionFactory sessionFactory;

  public HibernateSessionService() {
    sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
  }

  public SessionFactory getSessionFactory() {
    return sessionFactory;
  }
}