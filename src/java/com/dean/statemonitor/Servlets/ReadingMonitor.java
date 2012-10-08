/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dean.statemonitor.Servlets;

import com.dean.statemonitor.Model.Reading;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Imal
 */
@WebServlet(name = "ReadingMonitor", urlPatterns = {"/ReadingMonitor"})
public class ReadingMonitor extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            
           Reading read = new Reading();
           read.StartReading();
           ArrayList<TotalSensorReadings> over = Reading.getTotSenReadOver();
           if(over !=null){
           Iterator totread = over.iterator();
           JSONArray reding = new JSONArray();
           while(totread.hasNext()){
               
               TotalSensorReadings overread = (TotalSensorReadings)totread.next();
               JSONObject obj = new JSONObject();
               obj.put("SenTypeID", overread.getSen_type_id());
               obj.put("SenName", overread.getSen_description());
               obj.put("Reading", overread.getReading());
               reding.add(obj);
               
           }          
           out.print(reding);
           System.out.println(reding+" "+(new Date()));
           }

        } catch (Exception e) {
            System.out.println(e);
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
