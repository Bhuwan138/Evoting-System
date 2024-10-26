
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    boolean result = (boolean)request.getAttribute("result");
    boolean isUserFound = (boolean)request.getAttribute("isUserFound");
    String userName = (String)request.getAttribute("userName");
    
    if(isUserFound) out.println("uap");
    else if(result) out.println("success");
    else out.println("error");
%>
