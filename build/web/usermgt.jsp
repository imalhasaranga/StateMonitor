<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="includes/topheader.jsp" %>
<%@page import="com.dean.statemonitor.Model.Sensor_type" %>
<%@page import="java.util.*" %>
<%
    if (user1.getType() == 2) {
        
        response.sendRedirect("includes/nopermission.html");
    }

%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>TATA</title>

        <link href="styles/main2.css" rel="stylesheet" type="text/css"/>
        <link href="styles/sensortype.css" rel="stylesheet" type="text/css"/>
        <link href="styles/message.css" rel="stylesheet" type="text/css"/>

    </head>

    <body>

        <%@include file="includes/HeaderMenu.jsp" %>

        <div id="container" align="center">

            <div id="content" align="left">

                <div id="title">User Profile Management...</div>

                <form action="Userserv" method="post">
                    <table width="800"  id="tbl_login_user">
                        <tr>
                            <td width="86">Name</td>
                            <td width="130"><input type="text" name="name" id="txt_username"  class="userenter"/></td>
                            <td width="68">&nbsp;</td>
                        </tr>
                        <tr>
                            <td>email</td>
                            <td><input type="text" name="email" id="txt_username"  class="userenter"/></td>
                            <td>&nbsp;</td>
                        </tr>
                        <tr>
                            <td>Designation</td>
                            <td><input type="text" name="position" id="txt_username"  class="userenter"/></td>
                        </tr>
                        <tr>
                            <td>User Type</td>
                            <td>
                                <select name="typeu" id="txt_username"  class="userselect">
                                    <option value="0">Select Type</option>
                                    <option value="1">Administrator</option>
                                    <option value="2">Super User</option>
                                    <option value="3">Normal User</option>
                                </select>
                                
                            </td>
                        </tr>
                        <tr>
                            <td>User Level</td>
                            <td>
                                <select name="typeu" id="txt_username"  class="userselect">
                                    <option value="0">Select Level</option>
                                    <option value="1">Level 1</option>
                                    <option value="2">Level 2</option>
                                    <option value="3">Level 3</option>
                                </select>
                                
                            </td>
                        </tr>
                        <tr>
                            <td>Username</td>
                            <td><input type="text" name="username" id="txt_username"  class="userenter"/></td>
                            <td>&nbsp;</td>
                        </tr>
                        <tr>
                            <td>Password</td>
                            <td><input type="password" name="password1" id="password"  class="userenter"/></td>
                            <td>&nbsp;</td>
                        </tr>
                        <tr>
                            <td>C.Password</td>
                            <td><input type="password" name="password2" id="password"  class="userenter"/></td>
                            <td>&nbsp;</td>
                        </tr>
                        <tr>
                            <td></td>
                            <td><input type="submit" name="login" value="Add User" id="btn_login"  class="btn"/></td>
                            <td>&nbsp;</td>
                        </tr>
                    </table>
                </form>
                
                <div id="userarea" align="center">
                    Responsibilities & Privileges<br></br>
                    
                    <table>
                        <tr>
                            <td><input type="checkbox" name="option1" value="Milk"/> Add New Users</td>
                            <td><input type="checkbox" name="option1" value="Milk"/> Change User Privileges</td>
                        </tr>
                        <tr>
                            <td><input type="checkbox" name="option1" value="Milk"/> Add New Sensors</td>
                            <td><input type="checkbox" name="option1" value="Milk"/> Sensor Manage</td>
                        </tr>
                        <tr>
                            <td><input type="checkbox" name="option1" value="Milk"/> Stop Alarms</td>
                            <td><input type="checkbox" name="option1" value="Milk"/> Acknowledge</td>
                        </tr>
                        <tr>
                            <td><input type="checkbox" name="option1" value="Milk"/> Problem Solved Report</td>
                            <td><input type="checkbox" name="option1" value="Milk"/> View Incident History</td>
                        </tr>
                    </table>
                    
                    
                    
                </div>
                
                <div id="tbl2">
                    <table id="tblsensors2" cellpadding="0" cellspacing="0">
                        <tr>
                            <th>U.Id</th>
                            <th>Username</th>
                            <th>U.Type</th>
                            <th>User Name</th>
                            <th>Isactive</th>
                        </tr>
                        <%
                            User uer = new User();
                            Iterator i = uer.getAllUsers().iterator();
                           
                            while (i.hasNext()) {
                                
                                uer = (User) i.next();
                        %>  
                        <tr>
                            <td width="30"><%=uer.getUserId() %></td>
                            <td width="70"><%=uer.getUsername()%></td>
                            <td width="80"><%=(uer.getType() == 1 ? "Admin" : "User")%></td>
                            <td width="330"><%=uer.getname()%></td>
                            <td width="100" align="center"><a href="javascript:void(0)" id="viewchart2" onclick = "document.getElementById('light1').style.display='block';document.getElementById('fade').style.display='block';document.getElementById('deactive').href='DeActivatorDeletor?delid=3&uid=<%=uer.getUserId() %>'"><span id="viewchart"><%=uer.getActiveState()%></span></a></td>
                        </tr>
                        <%
                            }
                        %> 
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
