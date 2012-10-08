<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="includes/topheader.jsp" %>
<%@page import="com.dean.statemonitor.Model.*" %>
<%@page import="java.util.*" %>
<%
    Sensor_type sensorty = new Sensor_type();
    Inspector inspect = new Inspector();
    inspect.setInspectorID(Integer.parseInt(request.getParameter("id")));
    inspect = inspect.getInspector();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>TATA</title>

        <link href="styles/main2.css" rel="stylesheet" type="text/css"/>
        <link href="styles/sensortype.css" rel="stylesheet" type="text/css"/>
        <link href="styles/inspectors.css" rel="stylesheet" type="text/css"/>
        <link href="styles/message.css" rel="stylesheet" type="text/css"/>
        <script src="scripts/jquery-1.8.2.min.js" ></script>
        <script>
            
            $(document).ready(function() {
                $("#sentypes").change(function() {
                    var stype = document.getElementById("sentypes");
                    var id =stype.options[stype.selectedIndex].value;
                    
                    $.ajax({url:"AjaxLoader?id="+id,success:function(result){
                            //$("#doo").html(result);
                            var obj = jQuery.parseJSON(result);
                            var ddl = document.getElementById( "sensors" );
                            while(ddl.hasChildNodes())
                            {
                                ddl.removeChild(ddl.lastChild);
                            }
                            for (var i = 0; i < result.length; i++) {
                                var theOption = new Option;
                                theOption.value = obj[""+i][0];
                                theOption.text = obj[""+i][1];
                                ddl.options[i] = theOption  
                            }
                        }});
                });
            });
                
                
            
            
        </script>
    </head>

    <body>
        <%@include file="includes/HeaderMenu.jsp" %>

        <div id="container" align="center">

            <div id="content" align="left">

                <div id="title"><%=inspect.getInspectorName()%> (Id: <%=inspect.getInspectorID()%>)</div>

                <form action="sensor_inspect?idi=<%=inspect.getInspectorID() %>" method="post">
                    <table id="tbl_login2">
                        <tr>
                            <td width="100">Sensor Type</td>
                            <td width="200">
                                <select name="stype1" id="sentypes"  class="userselect"/>
                                <%
                                    Iterator alsernros = sensorty.getAllSensorTypes(Sensor_type.ALL).iterator();
                                    int count1 = 0;
                                    int sensorTypiD = 0;
                                    while (alsernros.hasNext()) {
                                        Sensor_type sens = (Sensor_type) alsernros.next();
                                        if (count1++ == 0) {
                                            sensorTypiD = sens.getSensor_type_id();
                                        }
                                %>
                                <option value="<%=sens.getSensor_type_id()%>"><%=sens.getSensor_type()%></option>
                                <% }%>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td>Sensor</td>
                            <td>
                                <select name="sensors1" id="sensors"  class="userselect"/>
                                <%
                                    Sensor sensor = new Sensor();
                                    sensor.setSensor_type(sensorTypiD);
                                    Iterator i = sensor.getAllSensorsWithinId().iterator();
                                    while (i.hasNext()) {
                                        sensor = (Sensor) i.next();
                                %>
                                <option value="<%=sensor.getSensor_id()%>"><%=sensor.getDescription()%></option>

                                <%
                                    }
                                %>

                                </select>
                            </td>
                            <td width="200"><span id="serialvalue"><!--Serial: ABC22336D --></span></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td><input type="submit" name="login" value="Add to Inspector" id="btn_login"  class="btn"/></td>
                        </tr>
                    </table>
                </form>

                <div id="title">Current Sensors for <%=inspect.getInspectorName()%> (Id: <%=inspect.getInspectorID()%>)</div>

                <div id="tb2">

                    <table id="tblsensors2" cellpadding="0" cellspacing="0">
                        <tr>
                            <th>S.Id</th>
                            <th>S.Serial</th>
                            <th>S.Type</th>
                            <th>Sensor Name</th>
                            <th>Remove</th>
                        </tr>
                        <%
                        
                        sensors_inspectors ins = new sensors_inspectors();
                        ins.setInspectorID(inspect.getInspectorID());
                        Iterator ir = ins.retrive_inspector_sensors().iterator();
                       
                        while(ir.hasNext()){
                            ins = (sensors_inspectors)ir.next();
                            Sensor sensor1 = ins.getSensor();
                            Sensor_type sentype = ins.getSensorType();
                            
%>

                        
                        <tr>
                            <td width="30"><%=sensor1.getSensor_id() %></td>
                            <td width="70"><%=sensor1.getSensor_serial() %></td>
                            <td width="80"><%=sentype.getSensor_type() %></td>
                            <td width="330"><%=sensor1.getDescription() %></td>
                            <td width="100" align="center"><a href="javascript:void(0)" id="viewchart2" onclick = "document.getElementById('light1').style.display='block';document.getElementById('fade').style.display='block';document.getElementById('linkremove').href='DeActivatorDeletor?delid=1&idi=<%=inspect.getInspectorID() %>&senid=<%=sensor1.getSensor_id() %>'"><span id="viewchart">Remove</span></a></td>
                        </tr>
                                                <%

                                                }
                                             
%>

             
                    </table>

                    <div id="light1" class="white_box" align="center">		
                        <p>Sure to Deactivate this Sensor Type?</p>
                        <a href="#" id="linkremove"><input type="submit" name="yes" value="Yes" id="btn_login"  class="btn"/></a>
                        <input type="submit" name="no" value="No" id="btn_login"  class="btn"/>
                    </div>

                    <a onclick="document.getElementById('light2').style.display='none';document.getElementById('fade').style.display='none'" href="javascript:void(0)">
                        <div id="fade" class="black_overlay1" onclick = "document.getElementById('light1').style.display='none';document.getElementById('fade').style.display='none'">
                        </div>
                    </a>


                </div>

                <div id="stopalarmdiv"><a href="inspectormgt.jsp" id="stopalarm">Back to Inspector Management</a></div>

            </div>




        </div>

    </body>
</html>
