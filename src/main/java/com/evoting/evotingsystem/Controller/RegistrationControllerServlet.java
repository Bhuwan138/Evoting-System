/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.evoting.evotingsystem.Controller;

import com.evoting.evotingsystem.DAO.RegistrationDAO;
import com.evoting.evotingsystem.Entity.UserDetails;
import com.evoting.evotingsystem.Handler.FactoryHandler;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RegistrationControllerServlet extends HttpServlet {

  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    RequestDispatcher rd = null;
    HttpSession session = request.getSession();
    String userId = (String) session.getId();
    if (userId == null) {
      session.invalidate();
      response.sendRedirect("accessdenied.html");
      return;
    }
    UserDetails users = new UserDetails();
    users.setCtznNo(request.getParameter("userId"));
    users.setAddress(request.getParameter("address"));
    users.setCity(request.getParameter("city"));
    users.setEmail(request.getParameter("email"));
    users.setMobileNo(request.getParameter("mobileNo"));
    users.setPassword(request.getParameter("password"));
    users.setUserName(request.getParameter("userName"));
    users.setUserType("User");

    try {
      RegistrationDAO registrationDAO = new RegistrationDAO(FactoryHandler.getFactory());
      boolean result = false, isUserFound = false;
      if (!registrationDAO.searchUser(users.getCtznNo())) {
        result = registrationDAO.registerUser(users);
      } else {
        isUserFound = true;
      }

      request.setAttribute("result", result);
      request.setAttribute("isUserFound", isUserFound);
      request.setAttribute("userName", users.getUserName());
      rd = request.getRequestDispatcher("RegistrationResponse.jsp");

    } catch (Exception e) {
      e.printStackTrace();
      request.setAttribute("errorPage", e.getMessage());
      rd = request.getRequestDispatcher("ShowError.jsp");
    } finally {
      rd.forward(request, response);
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
