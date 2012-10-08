package com.dean.statemonitor.Servlets;

import com.dean.statemonitor.Model.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.catalina.util.Base64;

/**
 *
 * @author Imal
 */



@WebServlet(name = "login", urlPatterns = {"/login"})
public class login extends HttpServlet {
  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username").replace("'","");
        String password = request.getParameter("password").replace("'","");;
        password = Base64.encode(password.getBytes());
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.getLoggedIN();

        if (user.isAvilable()) {

            HttpSession session = request.getSession();
            session.setAttribute("User", user);
            session.setAttribute("status", "logged");
            response.sendRedirect("dashboard.jsp");
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("status", "failled");
            session.setAttribute("User", null);
            response.sendRedirect("index.jsp");
        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "login servelt";
    }// </editor-fold>
}
