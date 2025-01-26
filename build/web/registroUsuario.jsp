<%-- 
    Document   : registroUsuario
    Created on : 23 dic 2024, 18:04:16
    Author     : Administrator
--%>

<%@page contentType="text/html" import="despliegue.Game.*, java.sql.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registro usuario</title>
        <link href="css/styles.css" rel="stylesheet" type="text/css"/>
        <link rel="shortcut icon" href="images/favicon.png">
        <%
            HttpSession misession = request.getSession();
            Conexion miConexion = null;
            User userActive = null;
            Boolean empty = false;
            Boolean userExist = false;
            Boolean passDistinct = false;
            String error = null;
            String message = null;
            if(misession.getAttribute("UserActive") != null) {
                response.sendRedirect("index.jsp");
            }
            else{
                if(request.getParameter("registro") != null){
                    String user = request.getParameter("user").trim();
                    String pass = request.getParameter("pass").trim();
                    String passRepeat = request.getParameter("passRepeat").trim();
                    if(user.equals("") || pass.equals("") || passRepeat.equals("")){
                        empty = true;
                    }
                    else{
                        try{
                            if(pass.equals(passRepeat)){
                                miConexion = new Conexion();
                                ResultSet miResultSet = miConexion.comprobarUsuario(user);
                                if(!miResultSet.absolute(1)){
                                    message = miConexion.registroUsuario(user, pass, false);
                                }
                                else{
                                    userExist = true;
                                }       
                            }   
                            else{
                                passDistinct = true;
                            }                           
                        } catch(Exception e){
                            error = "Error: "+e.getMessage();
                        } 
                    }
                } 
                else if(request.getParameter("volver") != null){
                    response.sendRedirect("inicioSesion.jsp");
                }
            }
        %>
    </head>
    <body>
        <div class="cont">
            <h1>Registro</h1>
            <form action="?" method="post">
                <table class="form">
                    <tr>
                        <td>Ingrese usuario:</td>
                        <td><input type="text" name="user"/></td>
                    </tr>
                    <tr>
                        <td>Ingrese contraseña:</td>
                        <td><input type="password" name="pass"/></td>
                    </tr>
                    <tr>
                        <td>Repita contraseña:</td>
                        <td><input type="password" name="passRepeat"/></td>
                    </tr>
                </table>   
                <input type="submit" name="registro" value="Registrar"/>
                <input type="reset" name="reset" value="Resetear"/>
                <input type="submit" name="volver" value="Volver"/>        
            </form>
            <%
                if(empty) out.println("<p>Los campos no pueden estar vacios</p>");
                if(userExist) out.println("<p>El usuario que ha introducido ya esta registrado</p>");
                if(passDistinct) out.println("<p>Las contraseñas introducidas no coinciden</p>");
                if(error != null) out.println(error);
                if(message != null) out.println(message);
            %>
        </div>
    </body>
</html>
