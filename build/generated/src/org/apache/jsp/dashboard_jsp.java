package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.Iterator;
import com.dean.statemonitor.Model.*;
import com.dean.statemonitor.Model.*;
import java.util.*;

public final class dashboard_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.Vector _jspx_dependants;

  static {
    _jspx_dependants = new java.util.Vector(2);
    _jspx_dependants.add("/includes/topheader.jsp");
    _jspx_dependants.add("/includes/HeaderMenu.jsp");
  }

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write('\n');

    User user1 = new User();
 
        if (session.getAttribute("User") != null) {
            user1 = (User) session.getAttribute("User");
        } else {
            response.sendRedirect("index.jsp");
        }
    


      out.write('\n');
      out.write('\n');
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\r\n");
      out.write("<html xmlns=\"http://www.w3.org/1999/xhtml\">\r\n");
      out.write("    <head>\r\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\r\n");
      out.write("        <title>TATA</title>\r\n");
      out.write("        <link href=\"styles/dashboard.css\" rel=\"stylesheet\" type=\"text/css\"/>\r\n");
      out.write("        <script src=\"scripts/jquery-1.8.2.min.js\" ></script>\r\n");
      out.write("        <script type=\"text/javascript\">\r\n");
      out.write("            var idss = new Array();\r\n");
      out.write("            $(document).ready(function() {\r\n");
      out.write("                \r\n");
      out.write("                jquerrequest();\r\n");
      out.write("                setInterval(function(){jquerrequest()},");
      out.print(System.getProperty("ReadingFreqInClientSide"));
      out.write("); \r\n");
      out.write("            });\r\n");
      out.write("            \r\n");
      out.write("            function jquerrequest(){\r\n");
      out.write("                var prob = \"Problem in: \";\r\n");
      out.write("                $.ajax({url:\"ReadingMonitor?ido=1\",success:function(result){                  \r\n");
      out.write("                        for(var i = 0; i < idss.length; i++){\r\n");
      out.write("                            var divel = document.getElementById(\"d\"+idss[i]);\r\n");
      out.write("                            var aeli = document.getElementById(\"a\"+idss[i]);\r\n");
      out.write("                            \r\n");
      out.write("                            divel.style.backgroundColor =\"#d1fc86\";\r\n");
      out.write("                            divel.style.color = \"#666666\";\r\n");
      out.write("                            divel.innerHTML = \"All Sensors Work Fine\";\r\n");
      out.write("                            aeli.href = \"#\";\r\n");
      out.write("                            \r\n");
      out.write("                        }\r\n");
      out.write("                       \r\n");
      out.write("                        var obj = jQuery.parseJSON(result);\r\n");
      out.write("                        var flag = 0;\r\n");
      out.write("                        for(var i = 0; i < obj.length; i++){\r\n");
      out.write("                            flag = 1;\r\n");
      out.write("                            var divel =  document.getElementById(\"d\"+obj[i].SenTypeID);\r\n");
      out.write("                            var link =  document.getElementById(\"a\"+obj[i].SenTypeID); \r\n");
      out.write("                           \r\n");
      out.write("                            divel.style.backgroundColor =\"#C30\";\r\n");
      out.write("                            divel.style.color = \"white\";                            \r\n");
      out.write("                            link.href = \"sensorproblem.html\";\r\n");
      out.write("                            if(prob != \"\"){\r\n");
      out.write("                                divel.innerHTML = \"\";\r\n");
      out.write("                                divel.innerHTML = prob+divel.innerHTML+obj[i].SenName;\r\n");
      out.write("                                prob = \"\";\r\n");
      out.write("                            }else{                                \r\n");
      out.write("                                divel.innerHTML = divel.innerHTML+\",\"+obj[i].SenName;\r\n");
      out.write("                            }\r\n");
      out.write("                           \r\n");
      out.write("                        }\r\n");
      out.write("                        \r\n");
      out.write("                        if(flag == 1){\r\n");
      out.write("                            document.getElementById(\"mp3\").play();\r\n");
      out.write("                        }else{\r\n");
      out.write("                            document.getElementById(\"mp3\").pause();\r\n");
      out.write("                        }\r\n");
      out.write("                        \r\n");
      out.write("                    }});\r\n");
      out.write("                \r\n");
      out.write("            }\r\n");
      out.write("            \r\n");
      out.write("           \r\n");
      out.write("                \r\n");
      out.write("           \r\n");
      out.write("        </script>\r\n");
      out.write("    </head>\r\n");
      out.write("\r\n");
      out.write("    <body>\r\n");
      out.write("\r\n");
      out.write("        ");
      out.write("<div id=\"top\" align=\"center\">\n");
      out.write("            <div id=\"top1\" align=\"left\">\n");
      out.write("                Dash Board of Managemet Room\n");
      out.write("                <span id=\"userdetails\">Current User: ");
      out.print(user1.getUsername());
      out.write("</span>\n");
      out.write("                <a href=\"SignOut\"><span id=\"logout\">Logout</span></a>\n");
      out.write("            </div>\n");
      out.write("\n");
      out.write("            <div id=\"topbrd\">\n");
      out.write("                <div id=\"top2\">\n");
      out.write("                    <a href=\"dashboard.jsp\"><div class=\"mainlinks\">Dash Board</div></a>\n");
      out.write("                    <a href=\"reportsensor.html\"><div class=\"mainlinks\">Sensor Reports</div></a>\n");
      out.write("                    <a href=\"sensortype.jsp\"><div class=\"mainlinks\">Sensor Types</div></a>\n");
      out.write("                    <a href=\"sensormgt.jsp\"><div class=\"mainlinks\">Sensor Management</div></a>\n");
      out.write("                    <a href=\"inspectormgt.jsp\"><div class=\"mainlinks\">Inspector Management</div></a>\n");
      out.write("                    <a href=\"usermgt.jsp\"><div class=\"mainlinks\">User Management</div></a>\n");
      out.write("\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("\n");
      out.write("\n");
      out.write("        </div>");
      out.write("\r\n");
      out.write("\r\n");
      out.write("        <div id=\"container\" align=\"center\">\r\n");
      out.write("\r\n");
      out.write("            <div id=\"content\" align=\"left\">\r\n");
      out.write("                ");

                    Sensor_type sentype = new Sensor_type();
                    Sensor sen1 = new Sensor();
                    Iterator i = sentype.getAllSensorTypes(Sensor_type.ACTIVE).iterator();
                    int cou1 = 0;
                    while (i.hasNext()) {
                        sentype = (Sensor_type) i.next();
                
      out.write("\r\n");
      out.write("\r\n");
      out.write("                <div id=\"sensor\">\r\n");
      out.write("                    <div class=\"sensor_left\">\r\n");
      out.write("                        <div class=\"sensor_left\">\r\n");
      out.write("                            <a href=\"sensormore.jsp?sentyid=");
      out.print(sentype.getSensor_type_id());
      out.write("\"><div class=\"sensor_name\" align=\"center\">");
      out.print(sentype.getSensor_type());
      out.write("</div></a>\r\n");
      out.write("                            <div class=\"sensor_nums\" align=\"center\">\r\n");
      out.write("                                No of Sensors: ");
      out.print(sen1.SensorCount(sentype.getSensor_type_id()));
      out.write("\r\n");
      out.write("                            </div>\r\n");
      out.write("                        </div>\r\n");
      out.write("                    </div>\r\n");
      out.write("                    <a href=\"#\" id=\"a");
      out.print(sentype.getSensor_type_id());
      out.write("\"><div  class=\"sensor_right\" align=\"center\" id=\"d");
      out.print(sentype.getSensor_type_id());
      out.write("\" >All Sensors Work Fine</div></a>\r\n");
      out.write("                </div>\r\n");
      out.write("                <br/>\r\n");
      out.write("                <script type=\"text/javascript\">               \r\n");
      out.write("                    idss[");
      out.print((cou1++));
      out.write("] = '");
      out.print(sentype.getSensor_type_id());
      out.write("';  \r\n");
      out.write("                </script>\r\n");
      out.write("                ");
  
                    }
                
      out.write("\r\n");
      out.write("\r\n");
      out.write("            </div>\r\n");
      out.write("\r\n");
      out.write("        </div>\r\n");
      out.write("        <audio src=\"files/al1.wav\" id=\"mp3\" loop=\"loop\" preload=\"auto\"></audio>\r\n");
      out.write("    </body>\r\n");
      out.write("</html>\r\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
