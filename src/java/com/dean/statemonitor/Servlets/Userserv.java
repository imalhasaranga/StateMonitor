/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dean.statemonitor.Servlets;

import com.dean.statemonitor.Model.User;
import com.dean.statemonitor.email.emailClient;
import com.dean.statemonitor.email.pageReader;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.catalina.util.Base64;

/**
 *
 * @author Imal
 */
@WebServlet(name = "Userserv", urlPatterns = {"/Userserv"})
public class Userserv extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User usr = new User();
        usr.setname(request.getParameter("name"));
        usr.setemail(request.getParameter("email"));
        usr.setUsername(request.getParameter("username"));
        usr.setType(Integer.parseInt(request.getParameter("typeu")));
        usr.setPostion(request.getParameter("position"));
        usr.setPassword(Base64.encode(request.getParameter("password2").getBytes()));
        int id = usr.addUserToDB();
        
        
        emailClient clint = new emailClient();
        clint.to = usr.getemail();
        clint.message = "use this page to change your logine";
        clint.subject = "Logine information";
        pageReader readr = new pageReader();
        readr.setUsername(usr.getUsername());
        readr.setPage(System.getProperty("pageUrl")+"/changeuser");
        readr.setParameters("id="+id);
        readr.setMessage("Please use this page to change your user details ");  
        clint.setHtml(readr.getContent());
        clint.startSendingThread();
        
        response.sendRedirect("usermgt.jsp");


    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
