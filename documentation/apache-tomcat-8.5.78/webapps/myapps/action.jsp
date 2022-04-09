<html>
    <head>
    <title>Using POST Method to Read Form Data</title>
    </head>
    <body>
        <h3>Returning the user Form Data back</h3>
        <br>
            <p><b>Login name:</b>
            <%= request.getParameter("username")%>
            </p>
            <p><b>Password:</b>
            <%= request.getParameter("password")%>

            </p>
    </body>


</html>