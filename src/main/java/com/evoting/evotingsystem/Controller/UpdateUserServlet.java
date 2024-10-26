package com.evoting.evotingsystem.Controller;

import com.evoting.evotingsystem.DAO.UserDAO;
import com.evoting.evotingsystem.Entity.UserDetails;
import com.evoting.evotingsystem.Handler.FactoryHandler;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UpdateUserServlet extends HttpServlet {

  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    HttpSession session = request.getSession();
    String userId = (String) session.getId();
    if (userId == null) {
      session.invalidate();
      response.sendRedirect("accessdenied.html");
      return;
    }
    String ctznNo = request.getParameter("ctznNo");
    String userName = request.getParameter("userName");
    String address = request.getParameter("address");
    String city = request.getParameter("city");
    String email = request.getParameter("email");
    String mobileNo = request.getParameter("mobileNo");

    // Initialize a UserDetailsDAO (your data access object)
    UserDAO userDetailsDAO = new UserDAO(FactoryHandler.getFactory());

    // Retrieve the user by ctznNo
    UserDetails user = userDetailsDAO.getUserByCtznNo(ctznNo);

    if (user != null) {
      // Update the user details (excluding userType and ctznNo)
      user.setUserName(userName);
      user.setAddress(address);
      user.setCity(city);
      user.setEmail(email);
      user.setMobileNo(mobileNo);

      // Save the updated user
      userDetailsDAO.updateUser(user);

      // Redirect to a success page or a user list page
      request.setAttribute("successUser", "User Updated Sucessfully");
    } else {
      request.setAttribute("errorUser", "User Not Found");
    }
    response.sendRedirect("manageUsers.jsp");
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
