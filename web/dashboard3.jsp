
<%@page import="java.util.Iterator"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="includes/topheader.jsp" %>
<%@page import="com.dean.statemonitor.Model.*" %>
<%@page import="java.util.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>TATA</title>
        <link href="styles/dashboard.css" rel="stylesheet" type="text/css"/>
        <script src="scripts/jquery-1.8.2.min.js" ></script>
        <script type="text/javascript">
            var idss = new Array();
            $(document).ready(function() {
                
                jquerrequest();
                setInterval(function(){jquerrequest()},<%=System.getProperty("ReadingFreqInClientSide")%>); 
            });
            
            function jquerrequest(){
                var prob = "Problem in: ";
                $.ajax({url:"ReadingMonitor?ido=1",success:function(result){                  
                        for(var i = 0; i < idss.length; i++){
                            var divel = document.getElementById("d"+idss[i]);
                            var aeli = document.getElementById("a"+idss[i]);
                            
                            divel.style.backgroundColor ="#d1fc86";
                            divel.style.color = "#666666";
                            divel.innerHTML = "All Sensors Work Fine";
                            aeli.href = "#";
                            
                        }
                       
                        var obj = jQuery.parseJSON(result);
                        var flag = 0;
                        for(var i = 0; i < obj.length; i++){
                            flag = 1;
                            var divel =  document.getElementById("d"+obj[i].SenTypeID);
                            var link =  document.getElementById("a"+obj[i].SenTypeID); 
                           
                            divel.style.backgroundColor ="#C30";
                            divel.style.color = "white";                            
                            link.href = "sensorproblem.html";
                            if(prob != ""){
                                divel.innerHTML = "";
                                divel.innerHTML = prob+divel.innerHTML+obj[i].SenName;
                                prob = "";
                            }else{                                
                                divel.innerHTML = divel.innerHTML+","+obj[i].SenName;
                            }
                           
                        }
                        
                        if(flag == 1){
                            document.getElementById("mp3").play();
                        }else{
                            document.getElementById("mp3").pause();
                        }
                        
                    }});
                
            }
            
           
                
           
        </script>
    </head>

    <body>

        <%@include file="includes/HeaderMenu.jsp" %>

        <div id="container" align="center">

            <div id="content" align="left">
                <%
                    Sensor_type sentype = new Sensor_type();
                    Sensor sen1 = new Sensor();
                    Iterator i = sentype.getAllSensorTypes(Sensor_type.ACTIVE).iterator();
                    int cou1 = 0;
                    while (i.hasNext()) {
                        sentype = (Sensor_type) i.next();
                %>

                <div id="sensor">
                    <div class="sensor_left">
                        <div class="sensor_left">
                            <a href="sensormore.jsp?sentyid=<%=sentype.getSensor_type_id()%>"><div class="sensor_name" align="center"><%=sentype.getSensor_type()%></div></a>
                            <div class="sensor_nums" align="center">
                                No of Sensors: <%=sen1.SensorCount(sentype.getSensor_type_id())%>
                            </div>
                        </div>
                    </div>
                    <a href="#" id="a<%=sentype.getSensor_type_id()%>"><div  class="sensor_right" align="center" id="d<%=sentype.getSensor_type_id()%>" >All Sensors Work Fine</div></a>
                </div>
                <br/>
                <script type="text/javascript">               
                    idss[<%=(cou1++)%>] = '<%=sentype.getSensor_type_id()%>';  
                </script>
                <%  
                    }
                %>

            </div>

        </div>
        <audio src="files/al1.wav" id="mp3" loop="loop" preload="auto"></audio>
    </body>
</html>
