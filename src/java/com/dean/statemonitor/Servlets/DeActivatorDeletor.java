/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dean.statemonitor.Servlets;

import com.dean.statemonitor.Model.Sensor;
import com.dean.statemonitor.Model.Sensor_type;
import com.dean.statemonitor.Model.User;
import com.dean.statemonitor.Model.sensors_inspectors;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Imal
 */
@WebServlet(name = "DeActivatorDeletor", urlPatterns = {"/DeActivatorDeletor"})
public class DeActivatorDeletor extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int delid = Integer.parseInt(request.getParameter("delid"));
        switch (delid) {
            case 1:
                String inspectrid = request.getParameter("idi");
                String sensorid = request.getParameter("senid");
                sensors_inspectors senins = new sensors_inspectors();
                senins.setInspectorID(Integer.parseInt(inspectrid));
                senins.setSensorID(Integer.parseInt(sensorid));
                senins.deleteSensor_inspectorsRecord();
                response.sendRedirect("inspectormgt2.jsp?id=" + inspectrid);
                break;
            case 2:
                int sentypeid = Integer.parseInt(request.getParameter("sentyp"));
                Sensor_type type = new Sensor_type();
                type.setSensor_type_id(sentypeid);
                type.deactivateSensor();
                response.sendRedirect("sensortype.jsp");
                break;
            case 3:
                int uid = Integer.parseInt(request.getParameter("uid"));
                User user = new User();
                user.setUserId(uid);
                user.SetAvailability();
                response.sendRedirect("usermgt.jsp");
                break;
                
            case 4:
                Sensor senor = new Sensor();
                senor.setSensor_id(Integer.parseInt(request.getParameter("senid")));
                senor.deActivate();
                response.sendRedirect("sensormgt.jsp");
                break;
        }
        
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
