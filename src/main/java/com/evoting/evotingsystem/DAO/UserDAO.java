package com.evoting.evotingsystem.DAO;

import com.evoting.evotingsystem.Entity.UserDetails;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class UserDAO {

  private SessionFactory factory;

  public UserDAO(SessionFactory factory) {
    this.factory = factory;
  }

  public String validateUser(UserDetails user) {
    String userType = null;
    try {
      Session session = factory.openSession();
      Query<String> query = session.createQuery(
              "SELECT ud.userType FROM UserDetails ud WHERE ud.ctznNo = :ctznNo AND ud.password = :password",
              String.class
      );
      query.setParameter("ctznNo", user.getCtznNo());
      query.setParameter("password", user.getPassword());

      userType = query.uniqueResult();
    } catch (HibernateException e) {
      e.printStackTrace();
    }
    return userType;
  }

  public long countTotalRecords(String tableName) {
    try ( Session session = factory.openSession()) {
      String hql = "SELECT COUNT(*) FROM " + tableName;
      Query<Long> query = session.createQuery(hql, Long.class);
      Long totalCount = query.uniqueResult();
      if (totalCount != null) {
        return totalCount;
      } else {
        return 0L; 
      }
    } catch (Exception e) {
      e.printStackTrace();
      return 0L;
    }
  }

  public List<UserDetails> searchUser(String searchInput) {
    try ( Session session = factory.openSession()) {
      // Use HQL to create a query that searches for users by username
      String hql = "FROM UserDetails u WHERE lower(u.userName) LIKE :searchInput";
      Query<UserDetails> query = session.createQuery(hql, UserDetails.class);
      query.setParameter("searchInput", "%" + searchInput.toLowerCase() + "%");

      List<UserDetails> results = query.getResultList();
      return results;
    } catch (Exception e) {
      // Handle exceptions as needed
      e.printStackTrace();
      return Collections.emptyList();
    }
  }

  public List<UserDetails> getUserDetails() {
    List<UserDetails> list = new ArrayList<>();
    try ( Session session = factory.openSession()) {
      String hql = "FROM UserDetails WHERE LOWER(usertype) <> 'admin'";
      Query<UserDetails> query = session.createQuery(hql, UserDetails.class);
      list = query.list();
    } catch (HibernateException e) {
      // Handle any exceptions, log or rethrow as needed
      e.printStackTrace();
    }
    return list;
  }

  public UserDetails getUserById(String userId) {
    Session session = factory.openSession();
    UserDetails user = null;

    try {
      user = session.get(UserDetails.class, userId);
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      session.close();
    }

    return user;
  }

  public void deleteUser(UserDetails user) {
    Session session = factory.openSession();
    Transaction transaction = null;

    try {
      transaction = session.beginTransaction();
      session.delete(user);
      transaction.commit();
    } catch (Exception e) {
      if (transaction != null) {
        transaction.rollback();
      }
      e.printStackTrace();
    } finally {
      session.close();
    }
  }

  public UserDetails getUserByCtznNo(String ctznNo) {
    try ( Session session = factory.openSession()) {
      String hql = "FROM UserDetails WHERE ctznNo = :ctznNumber";
      Query<UserDetails> query = session.createQuery(hql, UserDetails.class);
      query.setParameter("ctznNumber", ctznNo);
      return query.uniqueResult();
    } catch (HibernateException e) {
      e.printStackTrace();
    }
    return null;
  }

  public void updateUser(UserDetails updatedUser) {
    try ( Session session = factory.openSession()) {
      Transaction transaction = session.beginTransaction();

      // Retrieve the existing user by ctznNo
      UserDetails existingUser = session.get(UserDetails.class, updatedUser.getCtznNo());

      if (existingUser != null) {
        // Update the user's details
        existingUser.setUserName(updatedUser.getUserName());
        existingUser.setAddress(updatedUser.getAddress());
        existingUser.setCity(updatedUser.getCity());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setMobileNo(updatedUser.getMobileNo());

        // Save the updated user
        session.update(existingUser);

        transaction.commit();
      }
    } catch (HibernateException e) {
      e.printStackTrace();
    }
  }

  public boolean registerUser(UserDetails user) {
    try ( Session session = factory.openSession()) {
      Transaction tx = session.beginTransaction();
      session.save(user); // Save the user details to the database
      tx.commit();
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  public List<String> getAllCities() {
    try {
      Session session = factory.openSession();
      String hql = "SELECT DISTINCT ud.city FROM UserDetails ud";
      Query<String> query = session.createQuery(hql, String.class);
      return query.getResultList();
    } catch (HibernateException e) {
      e.printStackTrace();
      return null;
    }
  }

}
