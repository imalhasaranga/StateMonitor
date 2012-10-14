/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dean.statemonitor.Servlets;

import com.dean.statemonitor.Model.Sensor;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;

/**
 *
 * @author Imal
 */
@WebServlet(name = "AjaxLoader", urlPatterns = {"/AjaxLoader"})
public class AjaxLoader extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String id = request.getParameter("id");
        JSONObject obj = new JSONObject();
        try {
            Sensor sen = new Sensor();
            sen.setSensor_type(Integer.parseInt(id));
            ArrayList<Sensor> sensor = sen.getAllSensorsWithinId();
            Iterator i = sensor.iterator();
            int count = 0;
            while (i.hasNext()) {
                Sensor sensr = (Sensor) i.next();
                LinkedList l1 = new LinkedList();
                l1.add(sensr.getSensor_id());
                l1.add(sensr.getDescription());
                obj.put((count++), l1);
            }
            out.print(obj);
        } catch (Exception e) {
            System.out.println(e);
        }




    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
    }

    @Override
    public String getServletInfo() {
        return "ajax jquery";
    }// </editor-fold>
}
