package com.evoting.evotingsystem.Controller;

import com.evoting.evotingsystem.DAO.CandidateDAO;
import com.evoting.evotingsystem.Entity.Candidate;
import com.evoting.evotingsystem.Handler.FactoryHandler;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateCandidateStatusServlet extends HttpServlet {

  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    String candidateIdStr = request.getParameter("candidateId");
    String statusStr = request.getParameter("status");

    if (candidateIdStr != null && statusStr != null) {
      try {
        int candidateId = Integer.parseInt(candidateIdStr);
        int status = Integer.parseInt(statusStr);

        CandidateDAO candidateDAO = new CandidateDAO(FactoryHandler.getFactory());
        Candidate candidate = candidateDAO.getCandidateById(candidateId);

        if (candidate != null) {
          if (status == 1) {
            candidate.setIsCandidate(1);
          } else if (status == -1) {
            candidate.setIsCandidate(-1);
          }

          candidateDAO.updateCandidate(candidate);

          // Send a success response back to the client
          response.getWriter().write("success");
        } else {
          // Candidate not found
          response.getWriter().write("error: Candidate not found");
        }
      } catch (NumberFormatException e) {
        // Invalid parameter values
        response.getWriter().write("error: Invalid parameters");
      }
    } else {
      // Missing parameters
      response.getWriter().write("error: Missing parameters");
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
