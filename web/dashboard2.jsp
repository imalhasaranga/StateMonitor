

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>TATA</title>

        <link href="styles/dashboard2.css" rel="stylesheet" type="text/css"/>
        <script src="scripts/jquery-1.8.2.min.js" ></script>
        <script type="text/javascript">
            var idss = new Array();
            var time1;
            $(document).ready(function() {
                
                jquerrequest();
                setInterval(function(){jquerrequest()},<%=System.getProperty("ReadingFreqInClientSide")%>); 
            });
            window.onload = function() {
                if(!window.location.hash) {
                    window.location = window.location + '#loaded';
                    window.location.reload();
                }
            }
            
            function jquerrequest(){
                var prob = ": Increasing";
                $.ajax({url:"ReadingMonitor?ido=1",success:function(result){                  
                        for(var i = 0; i < idss.length; i++){
                            var divel = document.getElementById("d"+idss[i]);
                            var aeli = document.getElementById("a"+idss[i]);
                            
                            divel.style.backgroundColor ="#d1fc86";
                            divel.style.color = "#666666";
                            divel.innerHTML = "<br><br><br><br><br><img src='images/sensor_ok.png' border='0'></img>";
                            aeli.href = "sensormore.jsp?sentyid="+idss[i];
                            
                        }
                       
                        var obj = jQuery.parseJSON(result);
                        var flag = 0;
                        var dg1 = 0;
                        var nodg = 0;
                        for(var i = 0; i < obj.length; i++){
                            flag = 1;
                            var divel =  document.getElementById("d"+obj[i].SenTypeID);
                            //var link =  document.getElementById("a"+obj[i].SenTypeID); 
                            
                            divel.style.backgroundColor ="#C30";
                            divel.style.color = "white"; 
                            divel.style.fontFamily = "arial";
                            divel.style.fontSize = "12px";
                            divel.style.fontWeight = "normal";
                            // link.href = "sensorproblem.html";
                            if(prob != ""){
                                divel.innerHTML = "";
                                divel.innerHTML = divel.innerHTML + obj[i].SenName + prob;
                                //divel.innerHTML = divel.innerHTML + "<br><br><br><img src='images/sensor_crt.png' border='0'></img>";
                                prob = "";
                            }else{
                                divel.innerHTML = divel.innerHTML + "<br>" + obj[i].SenName + ": Increasing";
                            }
                            
                           if(obj[i].dg == 1){
                               dg1 = 1;
                           }else{
                               
                               ++nodg;
                           }
                        }
                        divel.innerHTML = divel.innerHTML + "<br><br><img src='images/sensor_crt.png' border='0'></img>";
                        if((nodg == obj.length) && (nodg != 0)){
                            
                            divel.style.backgroundColor ="#FC6";
                        }
                        
                        if(flag == 1 && dg1 ==1){
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
                <div id="dbheading"></div>
                <div id="sensor">
                    <div id="sensor_heading"  align="center">
                        Temp
                    </div>
                    <a href="sensormore.jsp?sentyid=" id="" style="font-family: Verdana; font-size: 9px; font-weight:bold" target="_blank">
                        <div id="" class="sensor_middle"  align="center"  style="padding-top: 10px">
                        </div></a>
                    <div id="sensor_bottom" align="center">
                        No. of Sensors: 

                    </div>
                </div>
                
                <div id="sensor">
                    <div id="sensor_heading"  align="center">
                        Temp
                    </div>
                    <a href="sensormore.jsp?sentyid=" id="" style="font-family: Verdana; font-size: 9px; font-weight:bold" target="_blank">
                        <div id="" class="sensor_middle"  align="center"  style="padding-top: 10px">
                        </div></a>
                    <div id="sensor_bottom" align="center">
                        No. of Sensors: 

                    </div>
                </div>
                <div id="sensor">
                    <div id="sensor_heading"  align="center">
                        Temp
                    </div>
                    <a href="sensormore.jsp?sentyid=" id="" style="font-family: Verdana; font-size: 9px; font-weight:bold" target="_blank">
                        <div id="" class="sensor_middle"  align="center"  style="padding-top: 10px">
                        </div></a>
                    <div id="sensor_bottom" align="center">
                        No. of Sensors: 

                    </div>
                </div>
                <div id="sensor">
                    <div id="sensor_heading"  align="center">
                        Temp
                    </div>
                    <a href="sensormore.jsp?sentyid=" id="" style="font-family: Verdana; font-size: 9px; font-weight:bold" target="_blank">
                        <div id="" class="sensor_middle"  align="center"  style="padding-top: 10px">
                        </div></a>
                    <div id="sensor_bottom" align="center">
                        No. of Sensors: 

                    </div>
                </div>
                <div id="sensor">
                    <div id="sensor_heading"  align="center">
                        Temp
                    </div>
                    <a href="sensormore.jsp?sentyid=" id="" style="font-family: Verdana; font-size: 9px; font-weight:bold" target="_blank">
                        <div id="" class="sensor_middle"  align="center"  style="padding-top: 10px">
                        </div></a>
                    <div id="sensor_bottom" align="center">
                        No. of Sensors: 

                    </div>
                </div>
                
                <div id="sensor">
                    <div id="sensor_heading"  align="center">
                        Temp
                    </div>
                    <a href="sensormore.jsp?sentyid=" id="" style="font-family: Verdana; font-size: 9px; font-weight:bold" target="_blank">
                        <div id="" class="sensor_middle"  align="center"  style="padding-top: 10px">
                        </div></a>
                    <div id="sensor_bottom" align="center">
                        No. of Sensors: 

                    </div>
                </div>

            </div>
        </div>
        
    </body>
</html>
