
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

public class DeleteUserServlet extends HttpServlet {

  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    String userIdString = request.getParameter("userId");
       HttpSession session = request.getSession();
       String userId = (String) session.getId();
      if (userId == null) {
        session.invalidate();
        response.sendRedirect("accessdenied.html");
        return;
      }
    if (userIdString != null && !userIdString.isEmpty()) {
        try {
            // Parse the user ID to an integer (you can use other data types if needed)

            // Initialize a UserDetailsDAO (your data access object)
            UserDAO userDetailsDAO = new UserDAO(FactoryHandler.getFactory());

            // Retrieve the user by ID
            UserDetails user = userDetailsDAO.getUserById(userIdString);

            if (user != null) {
                // Delete the user
                userDetailsDAO.deleteUser(user);

                // Set a success message
                request.setAttribute("successMessage", "User deleted successfully!");
            } else {
                // Handle the case where the user with the provided ID doesn't exist
                request.setAttribute("errorMessage", "User not found.");
            }
        } catch (NumberFormatException e) {
            // Handle the case where the user ID parameter is not a valid integer
            request.setAttribute("errorMessage", "Invalid user ID.");
        }
    } else {
        // Handle the case where the user ID parameter is missing
        request.setAttribute("errorMessage", "User ID is missing.");
    }

    // Forward the request to the manageUser.jsp page
    request.getRequestDispatcher("manageUsers.jsp").forward(request, response);
    
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
