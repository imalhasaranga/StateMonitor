/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dean.statemonitor.Servlets;

import com.dean.statemonitor.Model.User;
import com.dean.statemonitor.Properties.DB;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "changeuser", urlPatterns = {"/changeuser"})
public class changeuser extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String userid = request.getParameter("id");
        User user = new User();
        user.setUserId(Integer.parseInt(userid));

        if ((user = user.getUser()) != null) {
            String password = Base64.encode(request.getParameter("pass").getBytes());
            if (password.equals(user.getPassword())) {
                try {
                    String username = request.getParameter("username");
                    String newpass = Base64.encode(request.getParameter("newpass").getBytes());
                    DB.setData("UPDATE USER SET username = '" + username + "' , PASSWORD = '" + newpass + "' WHERE user_id = '" + userid + "' ");
                    out.print("User details has successfully edited");
                } catch (Exception ex) {
                    Logger.getLogger(changeuser.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                out.print("Sorry your old password is wrong");
            }

        } else {
            out.print("Sorry user is not avialable");
        }
    }

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
        processRequest(request, response);
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
        processRequest(request, response);
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
