<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="includes/topheader.jsp" %>
<%@page import="com.dean.statemonitor.Model.Inspector" %>
<%@page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>TATA</title>
        <link href="styles/main2.css" rel="stylesheet" type="text/css"/>
        <link href="styles/sensortype.css" rel="stylesheet" type="text/css"/>
        <link href="styles/message.css" rel="stylesheet" type="text/css"/>
        
        <script type="text/javascript">
            function chck(){
                var name = document.getElementById("inname").value;
                var email = document.getElementById("inemail").value;
                var phone = document.getElementById("inphone").value;
                if(name !='' &&(email !='' || phone !='')){
                    
                    return true;
                }else{
                    return false;
                }
            }
            
        </script>

    </head>

    <body>

        <%@include file="includes/HeaderMenu.jsp" %>

        <div id="container" align="center">

            <div id="content" align="left">

                <div id="title">Inspector Profile Management...</div>

                <form action="InspectorSe" method="post" onsubmit="return chck()">
                    <table id="tbl_login">
                        <tr>
                            <td>Name</td>
                            <td><input type="text" name="name" id="inname"  class="userenter"/></td>
                        </tr>
                        <tr>
                            <td>Post</td>
                            <td><input type="text" name="post" id="inpost"  class="userenter"/></td>
                        </tr>
                        <tr>
                            <td>Email</td>
                            <td><input type="text" name="email" id="inemail"  class="userenter"/></td>
                        </tr>
                        <tr>
                            <td>SMS Mobile No</td>
                            <td><input type="text" name="mobile" id="inphone"  class="userenter"/></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td><input type="submit" name="login" value="Add Inspector" id="btn_login"  class="btn"/></td>
                        </tr>
                    </table>
                </form>

                <div id="tbl3">
                    <table id="tblsensors2" cellpadding="0" cellspacing="0" >
                        <tr>
                            <th>I.Id</th>
                            <th>I. Name</th>
                            <th>Post</th>
                            <th>Email Add</th>
                            <th>SMS Mobile</th>
                            <th>Isactive</th>
                            <th>Add Sensors</th>
                        </tr>
                        <%
                            ArrayList<Inspector> ins = new Inspector().getAllInspectors();
                            Iterator i = ins.iterator();
                            
                            while(i.hasNext()){
                                
                                Inspector inspec = (Inspector)i.next();
%>
                        
                        <tr>
                            <td width="30"><%=inspec.getInspectorID() %></td>
                            <td width="200"><%=inspec.getInspectorName() %></td>
                            <td width="100"><%=inspec.getPost() %></td>
                            <td width="200"><%=inspec.getEmail() %></td>
                            <td width="100"><%=inspec.getPhone() %></td>
                            <td width="100" align="center"><a href="javascript:void(0)" id="viewchart2" onclick = "document.getElementById('light1').style.display='block';document.getElementById('fade').style.display='block'"><span id="viewchart"><%=inspec.getActives() %></span></a></td>
                            <td width="100"><a href="inspectormgt2.jsp?id=<%=inspec.getInspectorID() %>" id="viewchart2">Add Sensors</a></td>
                        </tr>
                                               <%
                        

                                              }
%>
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
