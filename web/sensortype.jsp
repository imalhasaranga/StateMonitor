<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="includes/topheader.jsp" %>
<%@page import="com.dean.statemonitor.Model.Sensor_type" %>
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
            
            function validate(){
                
                var stype = document.getElementById("stype").value;
                var fcode = document.getElementById("fcode").value;
                if(stype == '' && fcode == ''){
                     
                    return false;
                }
            }
            
        </script>

    </head>

    <body>

        <%@include file="includes/HeaderMenu.jsp" %>
        <div id="container" align="center">

            <div id="content" align="left">

                <div id="title">Sensor Type Management...</div>

                <form action="SensorType" method="post" onsubmit="return validate()">
                    <table id="tbl_login">
                        <tr>
                            <td>Sensor Type</td>
                            <td><input type="text" name="stype" id="stype"  class="userenter"/></td>
                        </tr>
                        <tr>
                            <td>Family Code</td>
                            <td><input type="text" name="fcode" id="fcode"  class="userenter"/></td>
                        </tr>
                        <tr>
                            <td>&nbsp;</td>
                            <td><input type="submit" name="login" value="Add Sensor Type" id="btn_login"  class="btn"/></td>
                        </tr>
                    </table>
                </form>

                <div id="tbl">
                    <table id="tblsensors" cellpadding="0" cellspacing="0">
                        <tr>
                            <th>S.Type.Id</th>
                            <th>Sensor Type</th>
                            <th>F.Code</th>
                            <th>Isactive</th>
                        </tr>
                        <%
                            Iterator ito = sensorty.getAllSensorTypes(Sensor_type.ALL).iterator();
                            
                            while (ito.hasNext()) {
                               
                                Sensor_type sen = (Sensor_type) ito.next();
                        %>
                        <tr>
                            <td width="80"><%=sen.getSensor_type_id() %></td>
                            <td width="180"><%=sen.getSensor_type()%></td>
                            <td width="40"><%=sen.getSensor_family_code()%></td>
                            <td width="100" align="center"><a href="javascript:void(0)" id="viewchart2" onclick = "document.getElementById('light1').style.display='block';document.getElementById('fade').style.display='block';document.getElementById('deactive').href='DeActivatorDeletor?delid=2&sentyp=<%=sen.getSensor_type_id() %>'"><span id="viewchart"><%=sen.getState()%></span></a></td>
                        </tr>
                        <% }%>
                    </table>

                    <div id="light1" class="white_box" align="center">		
                        <p>Sure to Deactivate this Sensor Type?</p>
                        <a href="" id="deactive"><input type="submit" name="yes" value="Yes" id="btn_login"  class="btn"/></a>
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
