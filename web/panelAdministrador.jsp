<%-- 
    Document   : panelAdministrador
    Created on : 24 dic 2024, 0:01:21
    Author     : Administrator
--%>

<%@page contentType="text/html" import="despliegue.Game.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Panel de administrador</title>
        <link href="css/styles.css" rel="stylesheet" type="text/css"/>
        <link rel="shortcut icon" href="images/favicon.png">
        <%
            HttpSession misession = request.getSession();
            User userActive = null;  
            if(misession.getAttribute("UserActive") != null) {
                userActive = (User) misession.getAttribute("UserActive");
                if(!userActive.isEsAdm()){
                    response.sendRedirect("index.jsp");
                }
            } 
            else{
                response.sendRedirect("index.jsp");
            }
            if(request.getParameter("volver") != null){
                response.sendRedirect("index.jsp");
            }
        %>
    </head>
    <body>
        <div class="cont">
            <h1>Panel de administrador</h1>
            <div class="contAdms">
                <form action="insertar" method="post">
                    <h3>Insertar</h3>
                    <ul>
                        <li><input type="submit" name="insertar" value="Videojuego"/></li>
                        <li><input type="submit" name="insertar" value="Stock"/></li>
                        <li><input type="submit" name="insertar" value="Consola"/></li>
                        <li><input type="submit" name="insertar" value="Usuario"/></li>
                    </ul>
                </form>
            </div>
            <div class="contAdms">
                <form action="modificar" method="post">
                    <h3>Modificar</h3>
                    <ul>
                        <li><input type="submit" name="modificar" value="Videojuego"/></li>
                        <li><input type="submit" name="modificar" value="Stock"/></li>
                        <li><input type="submit" name="modificar" value="Consola"/></li>
                        <li><input type="submit" name="modificar" value="Usuario"/></li>           
                    </ul>
                </form>
            </div>
            <div class="contAdms">
                <form action="eliminar" method="post">
                    <h3>Eliminar</h3>
                    <ul>
                        <li><input type="submit" name="eliminar" value="Videojuego"/></li>
                        <li><input type="submit" name="eliminar" value="Stock"/></li>
                        <li><input type="submit" name="eliminar" value="Consola"/></li>
                        <li><input type="submit" name="eliminar" value="Usuario"/></li>
                    </ul>
                </form>
            </div>
            <form action="?" method="post">
                <input type="submit" name="volver" value="Volver"/>
            </form>
        </div>
    </body>
</html>
