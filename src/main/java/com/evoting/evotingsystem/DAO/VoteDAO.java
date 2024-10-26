package com.evoting.evotingsystem.DAO;

import com.evoting.evotingsystem.Entity.Candidate;
import com.evoting.evotingsystem.Entity.UserDetails;
import com.evoting.evotingsystem.Entity.Voting;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class VoteDAO {

  SessionFactory factory;

  public VoteDAO(SessionFactory factory) {
    this.factory = factory;
  }

  public boolean addVote(String userId, int candidateId) {
    Session session = factory.openSession();
    try {
      session.beginTransaction();

      // Retrieve UserDetails and Candidate entities based on their IDs
      UserDetails userDetails = session.get(UserDetails.class, userId);
      Candidate candidate = session.get(Candidate.class, candidateId);

      if (userDetails != null && candidate != null) {
        Voting voting = new Voting(candidate, userDetails);

        // Save the Voting object to the database
        session.save(voting);

        // Commit the transaction
        session.getTransaction().commit();
        return true;
      }

      // Rollback the transaction if something is wrong
      session.getTransaction().rollback();
      return false;
    } catch (Exception e) {
      // Handle exceptions, log them, and rollback the transaction
      if (session.getTransaction() != null && session.getTransaction().isActive()) {
        session.getTransaction().rollback();
      }
      e.printStackTrace(); // Log the exception
      return false;
    } finally {
      // Close the session
      session.close();
    }
  }

  public Map<String, Integer> getCandidateVoteCounts() {
    try ( Session session = factory.openSession()) {
//            String hql = "SELECT v.candidate.candidateId, COUNT(v) FROM Voting v GROUP BY v.candidate.candidateId ORDER BY COUNT(v) DESC";
      String hql = "SELECT v.userDetails.ctznNo, COUNT(*) "
              + "FROM Voting v "
              + "GROUP BY v.userDetails.ctznNo";

      Query query = session.createQuery(hql);
      List<Object[]> results = query.getResultList();

      Map<String, Integer> voteCounts = new LinkedHashMap<>();
      for (Object[] result : results) {
        String candidateId = (String) result[0];
        Long count = (Long) result[1];
        voteCounts.put(candidateId, count.intValue());
      }

      return voteCounts;
    }
  }

  public int getVoteCount() {
    try ( Session session = factory.openSession()) {
      String hql = "SELECT COUNT(*) FROM Voting";

      Query query = session.createQuery(hql);
      Long result = (Long) query.getSingleResult();

      return result.intValue();
    }
  }
}
