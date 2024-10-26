package com.evoting.evotingsystem.DAO;

import com.evoting.evotingsystem.Entity.Candidate;
import com.evoting.evotingsystem.Entity.Position;
import java.util.Collections;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class CandidateDAO {

  SessionFactory factory;

  public CandidateDAO(SessionFactory factory) {
    this.factory = factory;
  }

  public void addCandidate(Candidate candidate) {
    Transaction transaction = null;
    try {
      Session session = factory.openSession();
      transaction = session.beginTransaction();
      session.save(candidate);
      transaction.commit();
    } catch (HibernateException e) {
      if (transaction != null) {
        transaction.rollback();
      }
      e.printStackTrace();
    }
  }

  public List<Candidate> getAllNonCandidates() {
    try {
      Session session = factory.openSession();
      String hql = "FROM Candidate WHERE isCandidate = :isCandidate";
      Query<Candidate> query = session.createQuery(hql, Candidate.class);
      query.setParameter("isCandidate", 0);

      return query.getResultList();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
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

  public List<Candidate> getAllCandidatesAcceptedRejected() {
    try {
      Session session = factory.openSession();
      String hql = "FROM Candidate WHERE isCandidate <> :isCandidate";
      Query<Candidate> query = session.createQuery(hql, Candidate.class);
      query.setParameter("isCandidate", 0);

      return query.getResultList();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public Candidate getCandidateById(int candidateId) {
    Candidate candidate = null;
    Transaction tx = null;
    try ( Session session = factory.openSession()) {
      tx = session.beginTransaction();
      candidate = session.get(Candidate.class, candidateId);
      tx.commit();
    } catch (Exception e) {
      if (tx != null) {
        tx.rollback();
      }
      e.printStackTrace();
    }
    return candidate;
  }

  public Candidate getCandidateByCtzn(String citizenShipNumber) {
    Session session = factory.openSession();
    try {
      session.beginTransaction();

      // Query for the candidate based on citizenship number
      Query<Candidate> query = session.createQuery("FROM Candidate WHERE ctznNo = :ctznNumber", Candidate.class);
      query.setParameter("ctznNumber", citizenShipNumber);
      Candidate candidate = query.uniqueResult();

      // Commit the transaction
      session.getTransaction().commit();

      return candidate;
    } catch (Exception e) {
      // Handle exceptions, log them, and rollback the transaction
      if (session.getTransaction() != null && session.getTransaction().isActive()) {
        session.getTransaction().rollback();
      }
      e.printStackTrace(); // Log the exception
      return null;
    } finally {
      // Close the session
      session.close();
    }
  }

  public List<Candidate> getAllCandidatesByCity(String city) {
    try ( Session session = factory.openSession()) {
      String hql = "FROM Candidate WHERE city = :city";
      Query<Candidate> query = session.createQuery(hql, Candidate.class);
      query.setParameter("city", city);
      return query.list();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return Collections.emptyList(); // Return an empty list in case of an error
  }

  public void updateCandidate(Candidate candidate) {
    Session session = factory.openSession();
    Transaction tx = null;

    try {
      tx = session.beginTransaction();

      // Load the candidate from the database
      Candidate existingCandidate = session.get(Candidate.class, candidate.getCandidateId());

      if (existingCandidate != null) {
        // Update the candidate's properties with the new values
        existingCandidate.setParty(candidate.getParty());
        existingCandidate.setCity(candidate.getCity());
        existingCandidate.setCandidateGroup(candidate.getCandidateGroup());
        existingCandidate.setPosition(candidate.getPosition());
        existingCandidate.setIsCandidate(candidate.isIsCandidate());
        existingCandidate.setSymbol(candidate.getSymbol());
        existingCandidate.setUserDetails(candidate.getUserDetails());

        // Save the updated candidate to the database
        session.update(existingCandidate);
        tx.commit();
      } else {
        // Candidate not found in the database
        // Handle this case as needed
      }
    } catch (Exception e) {
      if (tx != null) {
        tx.rollback();
      }
      e.printStackTrace();
    } finally {
      session.close();
    }
  }

  public List<String> getAllDistinctPositions() {
    try ( Session session = factory.openSession()) {
      String hql = "SELECT DISTINCT c.position FROM Candidate c";
      Query<String> query = session.createQuery(hql, String.class);
      return query.getResultList();
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

}
