package com.evoting.evotingsystem.Controller;

import com.evoting.evotingsystem.DAO.CandidateDAO;
import com.evoting.evotingsystem.DAO.UserDAO;
import com.evoting.evotingsystem.Entity.Candidate;
import com.evoting.evotingsystem.Entity.UserDetails;
import com.evoting.evotingsystem.Handler.FactoryHandler;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

@MultipartConfig
public class AddCandidateServlet extends HttpServlet {

  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    CandidateDAO cd = new CandidateDAO(FactoryHandler.getFactory());

    String party = request.getParameter("party");
    String city = request.getParameter("city");
    String group = request.getParameter("group");
    String position = request.getParameter("position");
    String ctznNo = request.getParameter("citizenship");
    UserDAO ud = new UserDAO(FactoryHandler.getFactory());
    UserDetails userDetails = ud.getUserByCtznNo(ctznNo);

    Candidate candidate = new Candidate();
    candidate.setParty(party);
    candidate.setCity(city);
    candidate.setCandidateGroup(group);
    candidate.setPosition(position);
    candidate.setIsCandidate(0);
    candidate.setUserDetails(userDetails);

    Part filePart = request.getPart("symbol");
    getServletContext().log("===After part ======>" );
//    String fileName = extractFileName(filePart);
    String fileName = filePart.getSubmittedFileName();
    candidate.setSymbol(fileName);
    getServletContext().log("===File Name======>" + fileName);

    if (fileName != null && !fileName.isEmpty()) {
      String uploadDir = request.getRealPath("Images");
      String filePath = uploadDir + File.separator + "Candidate" + File.separator + fileName;

//      try ( InputStream fileContent = filePart.getInputStream();  FileOutputStream fileOutputStream = new FileOutputStream(filePath)) {
//        byte[] buffer = new byte[1024];
//        int bytesRead;
//
//        while ((bytesRead = fileContent.read(buffer)) != -1) {
//          fileOutputStream.write(buffer, 0, bytesRead);
//        }
//      }
      try {
        InputStream is = filePart.getInputStream();
        byte[] data = new byte[is.available()];
        is.read(data);

        FileOutputStream fos = new FileOutputStream(filePath);
        fos.write(data);

        fos.close();
      } catch (IOException ex) {
        ex.printStackTrace();
      }

      // Set the file path in the Candidate object
      
    }else{
      getServletContext().log("===Error in if======>" );
    }

    cd.addCandidate(candidate);
    HttpSession session = request.getSession();
    session.setAttribute("message", "Candidate Added Successfully");
    response.sendRedirect("User_AddCandidate.jsp");

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
