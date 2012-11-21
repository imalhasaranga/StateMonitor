<%@page import="com.dean.statemonitor.Servlets.TotalSensorReadings"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="includes/topheader.jsp" %>
<%@page import="com.dean.statemonitor.Model.Sensor_type" %>
<%@page import="java.util.*" %>
<%@page import="java.text.*" %>
<%
    SimpleDateFormat frmt = new SimpleDateFormat("yyyy-MM-dd hh:mm");
    String nowd = frmt.format(new Date());
    Sensor_type sentype = new Sensor_type().getSensorTypes(Integer.parseInt(request.getParameter("sentyid")));
    TotalSensorReadings read = new TotalSensorReadings();
%>



<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>TATA</title>

        <link href="styles/sensormore.css" rel="stylesheet" type="text/css"/>
        <script type="text/javascript" src="https://www.google.com/jsapi"></script>
        <script src="scripts/jquery-1.8.2.min.js" ></script>
        <script type="text/javascript">
            google.load("visualization", "1", {packages:["corechart"]});
            //google.setOnLoadCallback(drawChart);
            function drawChart(serial,description,senid) {
                document.getElementById("charthed1").innerHTML = " Chart for "+description+" Sensor (Serial No: "+serial+")";
                var atag = document.getElementById("chartmore");
                atag.href = "#?"+senid;
                atag.innerHTML ="More Detailed Report";
                $.ajax({url:"LoadCharts?ido="+senid,success:function(result){  
                
                var obj = jQuery.parseJSON(result);
                if(obj.lenght >1){
                    document.getElementById("charthed2").innerHTML = obj[0][0];
                }
                var data = google.visualization.arrayToDataTable(obj);
                var options = {
                    title: ''
                };
                var chart = new google.visualization.LineChart(document.getElementById('chartspace'));
                chart.draw(data, options);
                }});
            }
          
        </script>
    </head>

    <body>

        <%@include file="includes/HeaderMenu.jsp" %>

        <div id="container" align="center">

            <div id="content" align="left">

                <div id="title"><%=sentype.getSensor_type()%>: <%=nowd%></div>

                <div id="tbl">
                    <table id="tblsensors" cellpadding="0" cellspacing="0">
                        <tr>
                            <th>Serial No</th>
                            <th>Sensor Name</th>
                            <th>Value(C)</th>
                            <th>Chart Details</th>
                        </tr>
                        <%
                            Iterator i = read.getLatestReadings(sentype.getSensor_type_id()).iterator();
                            while (i.hasNext()) {
                                read = (TotalSensorReadings) i.next();

                        %>

                        <tr>
                            <td width="80"><%=read.getSen_serial()%></td>
                            <td width="180"><%=read.getSen_description()%></td>
                            <td width="40" bgcolor="<%=read.getColor()%>" align="center" style="color:#FFF"><%=read.getReading()%></td>
                            <td width="100" align="center"><a href="#" id="viewchart2" onclick="drawChart('<%=read.getSen_serial()%>','<%=read.getSen_description()%>','<%=read.getSensor_id()%>')"><span id="viewchart">View Chart</span></a></td>
                        </tr>
                        <%

                            }
                        %>
                    </table>


                </div>

                <div id="chart" align="center">
                    <div id="chartheading" align="center">
                        <span id="charthed1"></span>
                        <br/>
                        <span id="charthed2"></span>
                    </div>

                    <div id="chartspace" align="center">

                    </div>


                    <a href="#" id="chartmore"></a>


                </div>




            </div>




        </div>

    </body>
</html>
