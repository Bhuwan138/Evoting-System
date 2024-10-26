package com.evoting.evotingsystem.Controller;

import com.evoting.evotingsystem.DAO.CandidateGroupPositionDAO;
import com.evoting.evotingsystem.Entity.Group;
import com.evoting.evotingsystem.Entity.Position;
import com.evoting.evotingsystem.Handler.FactoryHandler;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONArray;
import org.json.JSONObject;

public class AdminGroupGetData extends HttpServlet {

  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    response.setContentType("application/json");
    PrintWriter out = response.getWriter();
    HttpSession session = request.getSession();
    String userId = (String) session.getId();
      if (userId == null) {
        session.invalidate();
        response.sendRedirect("accessdenied.html");
        return;
      }
    CandidateGroupPositionDAO dao = new CandidateGroupPositionDAO(FactoryHandler.getFactory());
    List<Group> groups = dao.getAllGroups();

    // Construct a JSON object for the response
    JSONObject jsonResponse = new JSONObject();
    JSONArray jsonGroups = new JSONArray();

    for (Group group : groups) {
      JSONObject jsonGroup = new JSONObject();
      jsonGroup.put("groupName", group.getGroupName());

      JSONArray jsonPositions = new JSONArray();
      for (Position position : group.getPositions()) {
        jsonPositions.put(position.getPositionName());
      }
      jsonGroup.put("positions", jsonPositions);

      jsonGroups.put(jsonGroup);
    }

    jsonResponse.put("groups", jsonGroups);
    getServletContext().log("=========>" + jsonResponse.toString());;
    // Send the JSON response
    out.print(jsonResponse.toString());
    out.flush();
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
