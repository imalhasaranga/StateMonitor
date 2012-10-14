
package com.dean.statemonitor.Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;

/**
 *
 * @author Imal
 */
@WebServlet(name = "LoadCharts", urlPatterns = {"/LoadCharts"})
public class LoadCharts extends HttpServlet {

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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        Iterator i1 = new TotalSensorReadings().getLatestReadingsCharts(request.getParameter("ido")).iterator();

        JSONArray reding = new JSONArray();
        JSONArray reding1 = new JSONArray();
        reding1.add("Time");
        reding1.add("Sensor Reading");
        
        reding.add(reding1);

        while (i1.hasNext()) {
            TotalSensorReadings red = (TotalSensorReadings) i1.next();
            JSONArray re = new JSONArray();
            re.add(red.getReadingTime());
            re.add(Integer.parseInt(red.getReading()));
           
            reding.add(re);

        }

       out.print(reding);
//        try {
//            JSONArray reding = new JSONArray();
//            JSONArray reding1 = new JSONArray();
//            reding1.add("Year");
//            reding1.add("Sales");
//            reding1.add("Expenses");
//            reding.add(reding1);
//            for (int i = 0; i < 3; i++) {
//                JSONArray re = new JSONArray();
//                re.add("Year"+i+"");
//                re.add(i);
//                re.add(i);
//                reding.add(re);
//            }
//
//            out.print(reding);
//            System.out.println(reding);
//        } catch (Exception e) {
//            System.out.println(e);
//        }
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
