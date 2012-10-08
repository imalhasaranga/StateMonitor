<%@page import="com.dean.statemonitor.email.pageReader"%>
<%@page import="com.dean.statemonitor.email.emailClient"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="scripts/jquery-1.8.2.min.js" ></script>
        <script type="text/javascript">
            $(document).ready(function() {
                

                   var k = document.getElementById("mp3");
                   k.play();
                  
                
            });
            
            
        </script>

    </head>
    <body>
        <audio src="files/al1.wav" id="mp3" loop="loop" preload="auto" />
    </body>
</html>
