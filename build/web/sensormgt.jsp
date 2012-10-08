
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="includes/topheader.jsp" %>
<%@page import="com.dean.statemonitor.Model.*" %>
<%@page import="java.util.*" %>
<%
    Sensor_type sensorty = new Sensor_type();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>TATA</title>

        <link href="styles/main2.css" rel="stylesheet" type="text/css"/>
        <link href="styles/sensortype.css" rel="stylesheet" type="text/css"/>
        <link href="styles/message.css" rel="stylesheet" type="text/css"/>
        <script type="text/javascript">
            
            function move(id,minred,maxread){
              
                minred = document.getElementById(minred).value;
                maxread = document.getElementById(maxread).value;
                window.location = "sensorBoundary?id="+id+"&min="+minred+"&max="+maxread;
              
            }
            
        </script>

    </head>

    <body>

        <%@include file="includes/HeaderMenu.jsp" %>

        <div id="container" align="center">

            <div id="content" align="left">

                <div id="title">Sensor Management...</div>

                <form action="SenSorManagement" method="post">
                    <table id="tbl_login">
                        <tr>
                            <td>Sensor Serial</td>
                            <td><input type="text" name="serial" id="txt_username"  class="userenter"/></td>
                        </tr>
                        <tr>
                            <td>Sensor Type</td>
                            <td>
                                <select name="stype" id="txt_username"  class="userselect"/>
                                <%
                                    Iterator alsernros = sensorty.getAllSensorTypes(Sensor_type.ACTIVE).iterator();
                                    while (alsernros.hasNext()) {
                                        Sensor_type sens = (Sensor_type) alsernros.next();
                                %>
                                <option value="<%=sens.getSensor_type_id()%>"><%=sens.getSensor_type()%></option>
                                <% }%>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td>Sensor Name</td>
                            <td><input type="text" name="sname" id="password"  class="userenter"/></td>
                        </tr>
                        <tr>
                            <td>&nbsp;</td>
                            <td><input type="submit" name="login" value="Add Sensor" id="btn_login"  class="btn"/></td>
                        </tr>
                    </table>
                </form>

                <div id="tbl2">
                    <table width="100" cellpadding="0" cellspacing="0" id="tblsensors2">
                        <tr>
                            <th>S.Id</th>
                            <th>S.Serial</th>
                            <th>S.Type</th>
                            <th>Sensor Name</th>
                            <th>Min Reading</th>
                            <th>Max Reading</th>
                            <th></th>
                            <th></th>
                        </tr>

                        <%
                            Sensor senro = new Sensor();
                            Iterator sensors = senro.getAllSensors().iterator();
                            int ic = 0;
                            while (sensors.hasNext()) {
                                ++ic;
                                senro = (Sensor) sensors.next();
                                Sensor_type senrtyp = new Sensor_type().getSensorTypes(senro.getSensor_type());
                        %>
                        <tr>
                            <td width="36"><%=senro.getSensor_id() %></td>
                            <td width="63"><%=senro.getSensor_serial()%></td>
                            <td width="60"><%=senrtyp.getSensor_type()%></td>
                            <td width="124"><%=senro.getDescription()%></td>
                            <td width="107"><input type="text" name="minread" style="width:90px;" id="minread<%=ic %>" value="<%=senro.getMinimum()%>"/></td>
                            <td width="97"><input type="text" name="maxread" style="width:90px;" id="maxread<%=ic %>" value="<%=senro.getMaximum()%>"/></td>
                            <td width="48">&nbsp;&nbsp;<a href="DeActivatorDeletor?delid=4&senid=<%=senro.getSensor_id() %>"><%=senro.getActiveState() %></a></td>
                            <td width="63">&nbsp;&nbsp;&nbsp;<a href="#" onclick="move('<%=senro.getSensor_id()%>','minread<%=ic %>','maxread<%=ic %>')">Update</a></td>
                            <!-- <td width="100" align="center"><a href="javascript:void(0)" id="viewchart2" onclick = "document.getElementById('light1').style.display='block';document.getElementById('fade').style.display='block'"><span id="viewchart">Active</span></a></td> -->
                        </tr>
                        <%  }%>
                    </table>

                    <div id="light1" class="white_box" align="center">		
                    <p>Sure to Deactivate this Sensor Type?</p>
                        <input type="submit" name="yes" value="Yes" id="btn_login"  class="btn"/>
                        <input type="submit" name="no" value="No" id="btn_login"  class="btn"/>
                    </div>

                    <a onclick="document.getElementById('light2').style.display='none';document.getElementById('fade').style.display='none'" href="javascript:void(0)">
                        <div id="fade" class="black_overlay1" onclick = "document.getElementById('light1').style.display='none';document.getElementById('fade').style.display='none'">
                        </div>
                    </a>


                </div>



            </div>




        </div>

    </body>
</html>
