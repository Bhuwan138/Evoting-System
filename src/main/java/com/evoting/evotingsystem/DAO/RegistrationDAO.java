package com.evoting.evotingsystem.DAO;

import com.evoting.evotingsystem.Entity.UserDetails;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class RegistrationDAO {

  private SessionFactory factory;

  public RegistrationDAO(SessionFactory factory) {
    this.factory = factory;
  }

  public boolean searchUser(String userId) {
    try {
      Session session = factory.openSession();
      UserDetails user = session.get(UserDetails.class, userId);
      return user != null;
    } catch (HibernateException e) {
      e.printStackTrace();
    }
    return false;
  }

  public boolean registerUser(UserDetails user) {
    try {
      Session session = factory.openSession();
      Transaction transaction = session.beginTransaction();
      session.save(user); // Save the user object
      transaction.commit();
      return true;
    }catch (HibernateException e) {
      e.printStackTrace();
    }

    return false;
  }

}
