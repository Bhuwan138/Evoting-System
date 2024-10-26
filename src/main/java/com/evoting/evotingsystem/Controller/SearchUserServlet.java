package com.evoting.evotingsystem.Controller;

import com.evoting.evotingsystem.DAO.UserDAO;
import com.evoting.evotingsystem.Entity.UserDetails;
import com.evoting.evotingsystem.Handler.FactoryHandler;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

public class SearchUserServlet extends HttpServlet {

  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    response.setContentType("application/json");
        String searchInput = request.getParameter("searchInput");

        UserDAO userDao = new UserDAO(FactoryHandler.getFactory());
        List<UserDetails> searchResults = userDao.searchUser(searchInput);

        JSONArray jsonArray = new JSONArray();
        for (UserDetails userDetails : searchResults) {
            JSONObject userJson = new JSONObject();
            userJson.put("ctznNo", userDetails.getCtznNo());
            userJson.put("userName", userDetails.getUserName());
            userJson.put("city", userDetails.getCity());
            userJson.put("address", userDetails.getAddress());

            jsonArray.put(userJson);
        }
        getServletContext().log("=== json search===" + jsonArray.toString());
        // Send the JSON response
        PrintWriter out = response.getWriter();
        out.println(jsonArray.toString());
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
