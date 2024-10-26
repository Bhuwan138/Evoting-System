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

public class NewUserServlet extends HttpServlet {

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
    String fullName = request.getParameter("fullname");
    String ctznNo = request.getParameter("ctznno");
    String email = request.getParameter("email");
    String password = request.getParameter("password");
    String rePassword = request.getParameter("repassword");
    String address = request.getParameter("address");
    String city = request.getParameter("city");
    String phone = request.getParameter("phone");

    // Check if password matches rePassword
    if (!password.equals(rePassword)) {
      request.setAttribute("errorMessage", "Passwords do not match");
      request.getRequestDispatcher("newUser.jsp").forward(request, response);
      return;
    }

    // Create a UserDetails object with the user data
    UserDetails user = new UserDetails();
    user.setUserName(fullName);
    user.setCtznNo(ctznNo);
    user.setEmail(email);
    user.setPassword(password);
    user.setAddress(address);
    user.setCity(city);
    user.setMobileNo(phone);
    user.setUserType("User");

    // Use Hibernate to insert the user into the database
    UserDAO userDAO = new UserDAO(FactoryHandler.getFactory());

    try {
      boolean result = userDAO.registerUser(user);
      if (result) {
        // Redirect to a success page or show a success message
        request.setAttribute("successMessage", "User registration successful");
      }else{
        request.setAttribute("errorMessage", "An error occurred while registering the user");
      
      }
        request.getRequestDispatcher("newUser.jsp").forward(request, response);
    } catch (Exception e) {
      e.printStackTrace();
      // Handle any database or Hibernate errors
      request.setAttribute("errorMessage", "An error occurred while registering the user");
      request.getRequestDispatcher("newUser.jsp").forward(request, response);
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
