<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>TATA</title>

        <link href="styles/main.css" rel="stylesheet" type="text/css"/>

    </head>

    <body>

        <div id="container" align="center">


            <div id="loginholder">
                <%
                    if (session.getAttribute("status") != null) {
                        if(session.getAttribute("status").toString().equals("logged") ){
                       // response.sendRedirect("dashboard.jsp");
                        }else{
                        out.print("<b>" + session.getAttribute("status").toString() + "<b/>");
                        session.setAttribute("status", null);
                                               }
                    }
                %>
                <div id="loginholder_top">Authorized Users Only</div>

                <form action="login" method="post">
                    <table id="tbl_login">
                        <tr>
                            <td>Username</td>
                            <td><input type="text" name="username" id="txt_username"  class="userenter"/></td>
                        </tr>
                        <tr>
                            <td>Password</td>
                            <td><input type="password" name="password" id="password"  class="userenter"/></td>
                        </tr>
                        <tr>
                            <td>&nbsp;</td>
                            <td><input type="submit" name="login" value="Login" id="btn_login"  class="btn"/></td>
                        </tr>
                    </table>
                </form>

                <div id="loginholder_bottom">TATA India Ltd.</div>

            </div>






        </div>














    </body>
</html>
