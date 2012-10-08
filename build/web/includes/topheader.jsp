<%@page import="com.dean.statemonitor.Model.*" %>
<%
    User user1 = new User();
 
        if (session.getAttribute("User") != null) {
            user1 = (User) session.getAttribute("User");
        } else {
            response.sendRedirect("index.jsp");
        }
    

%>

