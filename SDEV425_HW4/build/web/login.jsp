<%-- 
    Document   : login
    Created on : Aug 10, 2015, 7:53:14 PM
    Author     : jim
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>



<!DOCTYPE html>
<html>
    <head>
        <title>SDEV425 Login</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="styles.css" rel="stylesheet" type="text/css">

    </head>
    <body>
        <div id="main">
            <%@include file="WEB-INF/jspf/menus.jspf" %>
            <p></p>
            <p></p>
            <h2>Login</h2>

            <% if (session.getAttribute("UMUCUserEmail") == null) {

            %>



            <input type="hidden" id="reset"/>

            <form action="Authenticate" method="post" id ='form-id'>
                <table class="center">
                    <tr>
                        <td>Email: </td><td><input type="text" id="email"  name="emailAddress"  size="50" autofocus ${ sessionScope.attempts eq 6 ? 'disabled="disabled"' : ''}> </td>
                    </tr>
                    <tr>
                        <td>Password: </td><td><input type="password" id="password" name="pfield" size="50" autocomplete="off" ${ sessionScope.attempts eq 6 ? 'disabled="disabled"' : ''}></td>
                    </tr>
                    <tr>
                        <td>
                            &nbsp;
                        </td>
                        <td>
                            <input type="button"  id='sub-id'  name="SignIn" value="Sign In" ${ sessionScope.attempts eq 6? 'disabled="disabled"' : ''}>
                        </td>
                    </tr>
                </table>

                <input type="hidden" id="counter"/>


                Attempt <%= session.getAttribute("attempts")%> : 

                <!-- Print Error Message if any -->
                <% String e = (String) request.getAttribute("ErrorMessage");
                    if (e != null) {
                        out.print(e);
                    }
                %>

            </form>
            <%
                } else {

                    request.setAttribute("ErrorMessage", "You are already logged in.");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("welcome.jsp");
                    dispatcher.forward(request, response);
                }
            %>
        </div>
    </body>
</html>
<script type='text/javascript'><!--

    window.onload = function () {
        document.getElementById('sub-id').onclick = function () {
            document.getElementById('form-id').submit();
            return false;
        };
    };

    setTimeout(function () {
        document.getElementById('sub-id').disabled = false;
        document.getElementById('email').disabled = false;
        document.getElementById('password').disabled = false;
        document.getElementById("counter").value = 0;

    }, 30000);
</script>