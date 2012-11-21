
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
import org.json.simple.JSONObject;

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
       

        while (i1.hasNext()) {
            TotalSensorReadings red = (TotalSensorReadings) i1.next();
            JSONObject re = new JSONObject();
            re.put("x", red.getReadingTime());
            re.put("y", Integer.parseInt(red.getReading()));
            
           
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
       
       
//       [{ x: '1790', y: 3.9 }, 
//                                { x: '1810', y: 7.2 }, 
//                                { x: '1830', y: 12.8 }, 
//                                { x: '1850', y: 23.1 },
//                                { x: '1870', y: 36.5 },
//                                { x: '1890', y: 62.9 }, 
//                                { x: '1910', y: 92.2 },
//                                { x: '1930', y: 123.2 },
//                                { x: '1950', y: 151.3 }, 
//                                { x: '1970', y: 203.2 },
//                                { x: '1990', y: 248.7 }, 
//                                { x: '2010', y: 308.7}]
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
