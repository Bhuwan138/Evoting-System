package com.evoting.evotingsystem.Controller;

import com.evoting.evotingsystem.DAO.CandidateDAO;
import com.evoting.evotingsystem.DAO.UserDAO;
import com.evoting.evotingsystem.DAO.VoteDAO;
import com.evoting.evotingsystem.Entity.Candidate;
import com.evoting.evotingsystem.Entity.UserDetails;
import com.evoting.evotingsystem.Handler.FactoryHandler;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

public class VoteCandidateServlet extends HttpServlet {

  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        // Parse JSON data sent from the JavaScript
        JSONObject requestData = new JSONObject(request.getReader().lines().reduce("", (accumulator, actual) -> accumulator + actual));

        // Retrieve candidateCtznNumber and userCtznNumber from the JSON data
        String candidateCtznNumber = requestData.getString("candidateCtznNumber");
        String userCtznNumber = requestData.getString("userCtznNumber");
        VoteDAO vote = new VoteDAO(FactoryHandler.getFactory());
        UserDAO userDao = new UserDAO(FactoryHandler.getFactory());
        CandidateDAO cDao = new CandidateDAO(FactoryHandler.getFactory());
        Candidate c = cDao.getCandidateByCtzn(candidateCtznNumber.trim());
        boolean voteSaved = vote.addVote(userCtznNumber, c.getCandidateId());

            getServletContext().log("==== after dao====" + voteSaved);
        if (voteSaved) {
            // Send a success response
            getServletContext().log("==== in success====" + voteSaved);
            JSONObject jsonResponse = new JSONObject();
            jsonResponse.put("message", "Vote saved successfully");
            out.print(jsonResponse.toString());
        } else {
            // Send an error response
            JSONObject jsonResponse = new JSONObject();
            jsonResponse.put("message", "Failed to save vote");
            getServletContext().log("==== in error====" + voteSaved);
            out.print(jsonResponse.toString());
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
  }

  // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
  /**
   * Handles
   * the
   * HTTP
   * <code>GET</code>
   * method.
   *
   * @param
   * request
   * servlet
   * request
   * @param
   * response
   * servlet
   * response
   * @throws
   * ServletException
   * if a
   * servlet-specific
   * error
   * occurs
   * @throws
   * IOException
   * if an
   * I/O
   * error
   * occurs
   */
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    processRequest(request, response);
  }

  /**
   * Handles
   * the
   * HTTP
   * <code>POST</code>
   * method.
   *
   * @param
   * request
   * servlet
   * request
   * @param
   * response
   * servlet
   * response
   * @throws
   * ServletException
   * if a
   * servlet-specific
   * error
   * occurs
   * @throws
   * IOException
   * if an
   * I/O
   * error
   * occurs
   */
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    processRequest(request, response);
  }

  /**
   * Returns
   * a
   * short
   * description
   * of
   * the
   * servlet.
   *
   * @return
   * a
   * String
   * containing
   * servlet
   * description
   */
  @Override
  public String getServletInfo() {
    return "Short description";
  }// </editor-fold>

}
