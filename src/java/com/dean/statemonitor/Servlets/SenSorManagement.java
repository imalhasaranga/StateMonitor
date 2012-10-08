/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dean.statemonitor.Servlets;

import com.dean.statemonitor.Model.Sensor;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 *
 * @author Imal
 */
@WebServlet(name = "SenSorManagement", urlPatterns = {"/SenSorManagement"})
public class SenSorManagement extends HttpServlet {

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Sensor sensor = new Sensor();
        sensor.setSensor_serial(request.getParameter("serial"));
        sensor.setSensor_type(Integer.parseInt(request.getParameter("stype")));
        sensor.setDescription(request.getParameter("sname"));
        sensor.addSensorToDb();
        response.sendRedirect("sensormgt.jsp");
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
