package com.evoting.evotingsystem.Controller;

import com.evoting.evotingsystem.DAO.CandidateGroupPositionDAO;
import com.evoting.evotingsystem.Entity.Group;
import com.evoting.evotingsystem.Entity.Position;
import com.evoting.evotingsystem.Handler.FactoryHandler;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONArray;
import org.json.JSONObject;

public class AdminGroupServlet extends HttpServlet {

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
    String jsonData = request.getReader().lines().reduce("", (accumulator, actual) -> accumulator + actual);

    getServletContext().log("=========>" + jsonData);
    CandidateGroupPositionDAO dao = new CandidateGroupPositionDAO(FactoryHandler.getFactory());

    if (jsonData != null && !jsonData.isEmpty()) {
      try {
        JSONObject jsonObject = new JSONObject(jsonData);
        String groupName = jsonObject.getString("groupName");
        JSONArray positionsArray = jsonObject.getJSONArray("positions");
        Group existingGroup = dao.getGroupByName(groupName);

        if (existingGroup == null) {
          Group newGroup = new Group(groupName);

          List<Position> newPositions = new ArrayList<>();
          for (int i = 0; i < positionsArray.length(); i++) {
            String positionName = positionsArray.getString(i);
            Position newPosition = new Position();
            newPosition.setPositionName(positionName);
            newPosition.setGroup(newGroup);
            newPositions.add(newPosition);
          }

          newGroup.setPositions(newPositions);

          // Save the new group to the database
          dao.addGroup(newGroup);

          for (Position p : newGroup.getPositions()) {
            dao.addPosition(p);
          }

        } else {
          List<Position> existingPositions = existingGroup.getPositions();

          for (int i = 0; i < positionsArray.length(); i++) {
            String positionName = positionsArray.getString(i);

            // Check if the position is already present in the group
            boolean positionExists = existingPositions.stream()
                    .anyMatch(p -> p.getPositionName().equalsIgnoreCase(positionName));

            if (!positionExists) {
              // If the position is not already present, add it
              Position newPosition = new Position();
              newPosition.setPositionName(positionName);
              newPosition.setGroup(existingGroup);
              dao.addPosition(newPosition);
              existingPositions.add(newPosition);

            }
          }

          // Update the group's positions
          existingGroup.setPositions(existingPositions);

          // Update the existing group in the database
          dao.updateGroup(existingGroup);

        }
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println("Group positions updated successfully.");

      } catch (Exception e) {
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        response.getWriter().println("Error updating group positions.");
        e.printStackTrace();
      }
    } else {
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      response.getWriter().println("Problem in fetching group or positions.");
    }


  }

  // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
  
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
