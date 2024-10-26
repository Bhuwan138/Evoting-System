package com.evoting.evotingsystem.DAO;

import com.evoting.evotingsystem.Entity.Group;
import com.evoting.evotingsystem.Entity.Position;
import java.util.Collections;
import java.util.List;
import javax.persistence.TypedQuery;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class CandidateGroupPositionDAO {

  private SessionFactory factory;

  public CandidateGroupPositionDAO(SessionFactory factory) {
    this.factory = factory;
  }

  public void addGroup(Group group) {
    Transaction transaction = null;
    try {
      Session session = factory.openSession();
      // Begin a transaction
      transaction = session.beginTransaction();

      // Save the group to the database
      session.save(group);

      // Commit the transaction
      transaction.commit();
    } catch (HibernateException e) {
      if (transaction != null) {
        transaction.rollback();
      }
      e.printStackTrace();
    }
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

  public void addPosition(Position position) {
    Transaction transaction = null;
    try {
      Session session = factory.openSession();
      // Begin a transaction
      transaction = session.beginTransaction();

      // Save the position to the database
      session.save(position);

      // Commit the transaction
      transaction.commit();
    } catch (HibernateException e) {
      if (transaction != null) {
        transaction.rollback();
      }
      e.printStackTrace();
    }
  }

  public Group getGroupByName(String groupName) {
    try {
      Session session = factory.openSession();
      String hql = "FROM Group WHERE lower(groupName) = :groupName";
      Query<Group> query = session.createQuery(hql, Group.class);
      query.setParameter("groupName", groupName.toLowerCase());

      List<Group> results = query.getResultList();

      if (!results.isEmpty()) {
        return results.get(0);
      }
    } catch (HibernateException e) {
      e.printStackTrace();
    }

    return null;
  }

  public List<Group> getAllGroups() {
    try ( Session session = factory.openSession()) {
      String jpql = "SELECT DISTINCT g FROM Group g LEFT JOIN FETCH g.positions";
        TypedQuery<Group> query = session.createQuery(jpql, Group.class);
        return query.getResultList();
    } catch (HibernateException e) {
      e.printStackTrace();
    }
    return Collections.emptyList();
  }

  public void updateGroup(Group group) {
    Transaction transaction = null;
    try {
      Session session = factory.openSession();
      // Begin a transaction
      transaction = session.beginTransaction();

      // Merge the updated group entity into the session
      session.merge(group);

      // Commit the transaction
      transaction.commit();
    } catch (HibernateException e) {
      if (transaction != null) {
        transaction.rollback();
      }
      e.printStackTrace();
    }

  }

}
