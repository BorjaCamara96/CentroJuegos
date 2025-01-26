<%-- 
    Document   : inicioSesion
    Created on : 21 dic 2024, 13:42:06
    Author     : Administrator
--%>

<%@page contentType="text/html" import="despliegue.Game.*, java.sql.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inicio de sesion</title>
        <link href="css/styles.css" rel="stylesheet" type="text/css"/>
        <link rel="shortcut icon" href="images/favicon.png">
        <%
            Boolean empty = false;
            Boolean login = true;
            String error = null;
            HttpSession misession = request.getSession();
            Conexion miConexion = null;
            User userActive = null;
            if(misession.getAttribute("UserActive") != null) {
                response.sendRedirect("index.jsp");
            }
            else{
                if(request.getParameter("login") != null){
                    String user = request.getParameter("user").trim();
                    String pass = request.getParameter("pass").trim();
                    if(user == null || user.equals("") || pass == null || pass.equals("")) empty = true;
                    try{
                        miConexion = new Conexion();
                        ResultSet miResultset = miConexion.comprobarUsuario(user, pass);
                        if(miResultset.absolute(1)){
                            misession = request.getSession(true);
                            userActive = new User(user, miResultset.getBoolean(4));
                            misession.setAttribute("UserActive", userActive);
                            misession.setAttribute("CestaCompra", new Cesta());
                            response.sendRedirect("index.jsp");
                        }
                        else{
                            login = false;
                        }
                    } catch(Exception e){
                        error = "<p>Error: "+e.getMessage()+"</p>";
                    } 
                }
                else if(request.getParameter("registro") != null){
                    response.sendRedirect("registroUsuario.jsp");
                }
                else if(request.getParameter("volver") != null){
                    response.sendRedirect("index.jsp");
                }
            }
        %>
    </head>
    <body>
        <div class="cont">
            <h1>Login</h1>
            <form action="?" method="post">
                <table class="form">
                    <tr>
                        <td>Usuario:</td>
                        <td><input type="text" name="user"/></td>
                    </tr>
                    <tr>
                        <td>Contraseña:</td>
                        <td><input type="password" name="pass"/></td>
                    </tr>
                </table>
                <input type="submit" name="login" value="Acceder"/>
                <input type="submit" name="registro"value="Registro"/>
                <input type="submit" name="volver" value="Volver"/> 
            </form>
            <%
                if(empty) out.println("<p>Los campos no pueden estar vacios</p>");
                else if(!login) out.println("<p>Usuario o contraseña no correctos</p>");
                else if(error != null) out.println(error);
            %>
        </div>
    </body>
</html>
