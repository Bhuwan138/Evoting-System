package com.evoting.evotingsystem.Controller;

import com.evoting.evotingsystem.DAO.CandidateDAO;
import com.evoting.evotingsystem.DAO.VoteDAO;
import com.evoting.evotingsystem.Entity.Candidate;
import com.evoting.evotingsystem.Handler.FactoryHandler;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

public class ElectionResultControllerServlet extends HttpServlet {

  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    response.setContentType("application/json;charset=UTF-8");
    
    try {
            VoteDAO voteDao = new VoteDAO(FactoryHandler.getFactory());
            CandidateDAO candidateDAO = new CandidateDAO(FactoryHandler.getFactory());
            getServletContext().log("Before dao in servlet======");
            Map<String, Integer> result = voteDao.getCandidateVoteCounts();
            getServletContext().log("Get candidate" + result);
            Set s = result.entrySet();
            Iterator it = s.iterator();
            Map<Candidate, Integer> resultDetails = new LinkedHashMap<>();
            while (it.hasNext()) {
                Map.Entry<String, Integer> e = (Map.Entry) it.next();
                Candidate cd = candidateDAO.getCandidateByCtzn(e.getKey());
                resultDetails.put(cd, e.getValue());
            }

            // Create JSON objects to represent the voting details
            JSONArray jsonArray = new JSONArray();
            for (Map.Entry<Candidate, Integer> entry : resultDetails.entrySet()) {
                Candidate candidate = entry.getKey();
                Integer voteCount = entry.getValue();
                float votePercentage = (entry.getValue()*100.0F)/voteDao.getVoteCount(); 

                JSONObject candidateJson = new JSONObject();
                candidateJson.put("candidateName", candidate.getUserDetails().getUserName());
                candidateJson.put("city", candidate.getCity());
                candidateJson.put("partyName", candidate.getParty());
                candidateJson.put("partySymbol", candidate.getSymbol());
                candidateJson.put("position", candidate.getPosition());
                candidateJson.put("totalVote", voteCount);
                candidateJson.put("votePercentage", votePercentage);

                jsonArray.put(candidateJson);
                getServletContext().log("======= candidate result===" + candidateJson );
            }

            // Send the JSON response to the client
            PrintWriter out = response.getWriter();
            out.print(jsonArray.toString());
            out.flush();
        } catch (Exception ex) {
            ex.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    
//    RequestDispatcher rd = null;
//    try{
//      VoteDAO voteDao = new VoteDAO(FactoryHandler.getFactory());
//      CandidateDAO candidateDAO = new CandidateDAO(FactoryHandler.getFactory());
//            Map<String,Integer> result = voteDao.getCandidateVoteCounts();
//            Set s = result.entrySet();
//            Iterator it = s.iterator();
//            Map<Candidate,Integer> resultDetails = new LinkedHashMap<>();
//            while(it.hasNext()){
//                Map.Entry<String,Integer> e = (Map.Entry) it.next();
//                Candidate cd = candidateDAO.getCandidateByCtzn(e.getKey());
//                resultDetails.put(cd, e.getValue());
//            }
//            request.setAttribute("voteCount", voteDao.getVoteCount());
//            request.setAttribute("result", resultDetails);
//            rd = request.getRequestDispatcher("User_result.jsp");
//        }catch(Exception ex){
//            ex.printStackTrace();
//            request.setAttribute("errorPage", ex);
//            rd = request.getRequestDispatcher("User_result.jsp");
//        }
//        
//        finally{
//            rd.forward(request, response);
//        }
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
