<%-- 
    Document   : index
    Created on : 23 dic 2024, 12:46:41
    Author     : Administrator
--%>

<%@page contentType="text/html" import="despliegue.Game.*, java.sql.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inicio</title>
        <link href="css/styles.css" rel="stylesheet" type="text/css"/>
        <link rel="shortcut icon" href="images/favicon.png"> 
        <%
            HttpSession misession = request.getSession();
            Conexion miConexion = null;
            User userActive = null;
            try{
                miConexion = new Conexion();
            } catch(Exception e){
                out.println("Error: "+e.getMessage());
            }
            if(misession.getAttribute("UserActive") != null) {
                userActive = (User) misession.getAttribute("UserActive");
            }
        %>
    </head>
    <body>
        <div id="header">
            <div id="logo">
                <img src="images/logo_game.png">
            </div>
            <div id="sesion">
                <form action="sesion" method="post">
                    <%
                        if(userActive == null) out.println("<input type='submit' name='inicioSesion' value='Iniciar sesion'/>");
                        else if(userActive != null){
                            out.println("<span id='userActiveName'>"+userActive.getUserName()+"</span>");
                            if(userActive.isEsAdm()){
                               out.println("<input type='submit' name='panelAdm' value='Panel de administrador'/>");
                            }
                            out.println("<input type='submit' name='cesta' value='Cesta de compra'/>");
                            out.println("<input type='submit' name='cerrarSesion' value='Cerrar sesion'/>");
                        }
                    %>
                </form>
            </div>
        </div>
        <div id="contenido">
            <div id="categorias"> 
                <div id="buscarCont">
                    <form action="?" method="post">
                        <input type="text" name="texto"/>
                        <input id="buscarBottom" type="submit" name="buscarBottom" value="Buscar"/>
                    </form>
                </div>
                <ul>
                    <li><a href="index.jsp?producto=Consolas">Todas las consolas</a></li>
                    <%
                        try{
                            ResultSet miResultSet = miConexion.listarTabla("generaciones");
                            String generacion;
                            while(miResultSet.next()){
                                generacion = miResultSet.getString(1).replace(" ","_");
                                out.println("<li><a href='index.jsp?producto=Juegos-"+generacion+"'>Juegos de "+miResultSet.getString(1)+"</a></li>");
                            }
                        } catch(Exception e){
                            out.println("Error: "+e.getMessage());
                        }
                    %>
                    <li><a href="index.jsp?producto=Juegos">Todos los juegos</a></li>
                    <li><a href="index.jsp?producto=Todos">Todos los productos</a></li>
                </ul>
            </div>
            <div id="productos">
                <%
                    try{
                        if(request.getParameter("buscarBottom") == null){
                            String valor;   
                            if(request.getParameter("producto") != null) valor = request.getParameter("producto"); 
                            else valor = "";
                            if(valor.equals("Consolas")){
                                ResultSet miResultSet = miConexion.listarTabla("consolas");
                                out.println("<table>"
                                        + "<tr class='headerTable'>"
                                            + "<td>Nombre</td>"
                                            + "<td>Compañia</td>"
                                            + "<td>Potencia CPU</td>"
                                            + "<td>Potencia GPU</td>"
                                            + "<td>Precio</td>"
                                            + "<td>Stock</td>"
                                            + "<td>Imagen</td>"
                                        + "</tr>");
                                while(miResultSet.next()){
                                    out.print("<form action='actualizarCarro' method='post'>");
                                    out.print("<tr>");
                                        out.println("<td>"+miResultSet.getString(2)+"</td>");
                                        out.println("<td>"+miResultSet.getString(4)+"</td>");
                                        out.println("<td>"+miResultSet.getString(5)+"</td>");
                                        out.println("<td>"+miResultSet.getString(6)+"</td>");
                                        out.println("<td>"+miResultSet.getString(7)+" €</td>");
                                        out.println("<td>"+miResultSet.getString(8)+" ud</td>");
                                        out.println("<td><img class='imgJuegos' src='images/"+miResultSet.getString(9)+".png'></td>");
                                        if(userActive != null && Integer.parseInt(miResultSet.getString(8)) > 0){
                                            out.println("<input type='hidden' name='categoria' value='consolas'/>");
                                            out.println("<input type='hidden' name='id_producto' value='"+miResultSet.getString(1)+"'/>");
                                            out.println("<input type='hidden' name='nombre_producto' value='"+miResultSet.getString(2)+"'/>");
                                            out.println("<input type='hidden' name='generacion' value='"+miResultSet.getString(3)+"'/>");
                                            out.println("<input type='hidden' name='precio_producto' value='"+miResultSet.getString(7)+"'/>");
                                            out.println("<input type='hidden' name='imagen_producto' value='"+miResultSet.getString(9)+"'/>");
                                            out.println("<td><input class='botonAgregar' type='submit' name='agregarCarro' value='Añadir a cesta'");
                                            if(Integer.parseInt(miResultSet.getString(8)) <= 0 || userActive == null){
                                                out.println(" disabled='disabled'");
                                            }
                                            out.println("/></td>");
                                        }
                                    out.print("</tr>");
                                    out.println("</form>");
                                }
                                out.println("</table>");
                            }
                            else if(valor.equals("Juegos")){
                                ResultSet miResultSet = miConexion.listarTodosJuegos();
                                out.println("<table>"
                                        + "<tr class='headerTable'>"
                                            + "<td>Nombre</td>"
                                            + "<td>Plataforma</td>"
                                            + "<td>Compañia</td>"
                                            + "<td>Genero</td>"
                                            + "<td>Metacritic Score</td>"
                                            + "<td>Precio</td>"
                                            + "<td>Stock</td>"
                                            + "<td>Imagen</td>"
                                        + "</tr>");
                                while(miResultSet.next()){
                                    out.print("<form action='actualizarCarro' method='post'>");
                                    out.print("<tr>");
                                        out.println("<td>"+miResultSet.getString(2)+"</td>");
                                        out.println("<td>"+miResultSet.getString(3)+"</td>");
                                        out.println("<td>"+miResultSet.getString(4)+"</td>");
                                        out.println("<td>"+miResultSet.getString(5)+"</td>");
                                        out.println("<td>"+miResultSet.getString(6)+"</td>");
                                        out.println("<td>"+miResultSet.getString(7)+" €</td>");
                                        out.println("<td>"+miResultSet.getString(8)+" ud</td>");
                                        out.println("<td><img class='imgJuegos' src='images/"+miResultSet.getString(9)+".png'></td>");
                                        if(userActive != null && Integer.parseInt(miResultSet.getString(8)) > 0){
                                            out.println("<input type='hidden' name='categoria' value='juegos'/>");
                                            out.println("<input type='hidden' name='id_producto' value='"+miResultSet.getString(1)+"'/>");
                                            out.println("<input type='hidden' name='nombre_producto' value='"+miResultSet.getString(2)+"'/>");
                                            out.println("<input type='hidden' name='generacion' value='"+miResultSet.getString(3)+"'/>");
                                            out.println("<input type='hidden' name='precio_producto' value='"+miResultSet.getString(7)+"'/>");
                                            out.println("<input type='hidden' name='imagen_producto' value='"+miResultSet.getString(9)+"'/>");
                                            out.println("<td><input class='botonAgregar' type='submit' name='agregarCarro' value='Añadir a cesta'");
                                            if(Integer.parseInt(miResultSet.getString(8)) <= 0 || userActive == null){
                                                out.println(" disabled='disabled'");
                                            }
                                            out.println("/></td>");
                                        }
                                    out.print("</tr>");
                                    out.println("</form>");
                                }
                                out.println("</table>");
                            }
                            else if(valor.equals("Todos") || valor.equals("")){
                                ResultSet miResultSet = miConexion.listarTodosJuegos();
                                out.println("<table>"
                                        + "<tr class='headerTable'>"
                                            + "<td>Nombre</td>"
                                            + "<td>Precio</td>"
                                            + "<td>Stock</td>"
                                            + "<td>Imagen</td>"
                                        + "</tr>");
                                while(miResultSet.next()){
                                    out.print("<form action='actualizarCarro' method='post'>");
                                    out.print("<tr>");
                                        out.println("<td>"+miResultSet.getString(2)+"</td>");
                                        out.println("<td>"+miResultSet.getString(7)+" €</td>");
                                        out.println("<td>"+miResultSet.getString(8)+" ud</td>");
                                        out.println("<td><img class='imgJuegos' src='images/"+miResultSet.getString(9)+".png'></td>");
                                        if(userActive != null && Integer.parseInt(miResultSet.getString(8)) > 0){
                                            out.println("<input type='hidden' name='categoria' value='juegos'/>");
                                            out.println("<input type='hidden' name='id_producto' value='"+miResultSet.getString(1)+"'/>");
                                            out.println("<input type='hidden' name='nombre_producto' value='"+miResultSet.getString(2)+"'/>");
                                            out.println("<input type='hidden' name='generacion' value='"+miResultSet.getString(3)+"'/>");
                                            out.println("<input type='hidden' name='precio_producto' value='"+miResultSet.getString(7)+"'/>");
                                            out.println("<input type='hidden' name='imagen_producto' value='"+miResultSet.getString(9)+"'/>");
                                            out.println("<td><input class='botonAgregar' type='submit' name='agregarCarro' value='Añadir a cesta'");
                                            if(Integer.parseInt(miResultSet.getString(8)) <= 0 || userActive == null){
                                                out.println(" disabled='disabled'");
                                            }
                                            out.println("/></td>");
                                        }
                                    out.print("</tr>");
                                    out.println("</form>");
                                }
                                miResultSet = miConexion.listarTabla("consolas");
                                while(miResultSet.next()){
                                    out.print("<form action='actualizarCarro' method='post'>");
                                    out.print("<tr>");
                                        out.println("<td>"+miResultSet.getString(2)+"</td>");
                                        out.println("<td>"+miResultSet.getString(7)+" €</td>");
                                        out.println("<td>"+miResultSet.getString(8)+" ud</td>");
                                        out.println("<td><img class='imgJuegos' src='images/"+miResultSet.getString(9)+".png'></td>");
                                        if(userActive != null && Integer.parseInt(miResultSet.getString(8)) > 0){
                                            out.println("<input type='hidden' name='categoria' value='consolas'/>");
                                            out.println("<input type='hidden' name='id_producto' value='"+miResultSet.getString(1)+"'/>");
                                            out.println("<input type='hidden' name='nombre_producto' value='"+miResultSet.getString(2)+"'/>");
                                            out.println("<input type='hidden' name='generacion' value='"+miResultSet.getString(3)+"'/>");
                                            out.println("<input type='hidden' name='precio_producto' value='"+miResultSet.getString(7)+"'/>");
                                            out.println("<input type='hidden' name='imagen_producto' value='"+miResultSet.getString(9)+"'/>");
                                            out.println("<td><input class='botonAgregar' type='submit' name='agregarCarro' value='Añadir a cesta'");
                                            if(Integer.parseInt(miResultSet.getString(8)) <= 0 || userActive == null){
                                                out.println(" disabled='disabled'");
                                            }
                                            out.println("/></td>");
                                        }
                                    out.print("</tr>");
                                    out.println("</form>");
                                }
                                out.println("</table>");
                            }
                            else if(valor.contains("Juegos")){
                                String vectorValor[] = valor.split("-");
                                valor = vectorValor[1].replace("_", " ");
                                ResultSet miResultSet = miConexion.listarJuegos(valor);
                                out.println("<table>"
                                        + "<tr class='headerTable'>"
                                            + "<td>Nombre</td>"
                                            + "<td>Compañia</td>"
                                            + "<td>Genero</td>"
                                            + "<td>Metacritic Score</td>"
                                            + "<td>Precio</td>"
                                            + "<td>Stock</td>"
                                            + "<td>Imagen</td>"
                                        + "</tr>");
                                while(miResultSet.next()){
                                    out.print("<form action='actualizarCarro' method='post'>");
                                    out.print("<tr>");
                                        out.println("<td>"+miResultSet.getString(2)+"</td>");
                                        out.println("<td>"+miResultSet.getString(4)+"</td>");
                                        out.println("<td>"+miResultSet.getString(5)+"</td>");
                                        out.println("<td>"+miResultSet.getString(6)+"</td>");
                                        out.println("<td>"+miResultSet.getString(7)+" €</td>");
                                        out.println("<td>"+miResultSet.getString(8)+" ud</td>");
                                        out.println("<td><img class='imgJuegos' src='images/"+miResultSet.getString(9)+".png'></td>");
                                        if(userActive != null && Integer.parseInt(miResultSet.getString(8)) > 0){
                                            out.println("<input type='hidden' name='categoria' value='juegos'/>");
                                            out.println("<input type='hidden' name='id_producto' value='"+miResultSet.getString(1)+"'/>");
                                            out.println("<input type='hidden' name='nombre_producto' value='"+miResultSet.getString(2)+"'/>");
                                            out.println("<input type='hidden' name='generacion' value='"+miResultSet.getString(3)+"'/>");
                                            out.println("<input type='hidden' name='precio_producto' value='"+miResultSet.getString(7)+"'/>");
                                            out.println("<input type='hidden' name='imagen_producto' value='"+miResultSet.getString(9)+"'/>");
                                            out.println("<td><input class='botonAgregar' type='submit' name='agregarCarro' value='Añadir a cesta'");
                                            if(Integer.parseInt(miResultSet.getString(8)) <= 0 || userActive == null){
                                                out.println(" disabled='disabled'");
                                            }
                                            out.println("/></td>");
                                        }
                                    out.print("</tr>");
                                    out.println("</form>");
                                }
                                out.println("</table>");
                            }
                        }
                        else{
                            String texto = request.getParameter("texto").trim();
                            if(texto.equals("")) out.println("<h1>No ha introducido ningun valor</h1>");
                            else{
                                ResultSet miResultSetStock = miConexion.buscarCoincidenciaStock(texto);
                                ResultSet miResultSetConsolas = miConexion.buscarCoincidenciaConsolas(texto);                            
                                if(miResultSetStock.absolute(1) || miResultSetConsolas.absolute(1)){
                                    if(miResultSetStock.absolute(1)){
                                        miResultSetStock.absolute(0);
                                        out.println("<h1>Videojuegos</h1>");
                                        out.println("<table>"
                                                + "<tr class='headerTable'>"
                                                    + "<td>Nombre</td>"
                                                    + "<td>Plataforma</td>"
                                                    + "<td>Compañia</td>"
                                                    + "<td>Genero</td>"
                                                    + "<td>Metacritic Score</td>"
                                                    + "<td>Precio</td>"
                                                    + "<td>Stock</td>"
                                                    + "<td>Imagen</td>"
                                                + "</tr>");
                                        while(miResultSetStock.next()){
                                            out.print("<form action='actualizarCarro' method='post'>");
                                            out.print("<tr>");
                                                out.println("<td>"+miResultSetStock.getString(2)+"</td>");
                                                out.println("<td>"+miResultSetStock.getString(3)+"</td>");
                                                out.println("<td>"+miResultSetStock.getString(4)+"</td>");
                                                out.println("<td>"+miResultSetStock.getString(5)+"</td>");
                                                out.println("<td>"+miResultSetStock.getString(6)+"</td>");
                                                out.println("<td>"+miResultSetStock.getString(7)+" €</td>");
                                                out.println("<td>"+miResultSetStock.getString(8)+" ud</td>");
                                                out.println("<td><img class='imgJuegos' src='images/"+miResultSetStock.getString(9)+".png'></td>");
                                                if(userActive != null && Integer.parseInt(miResultSetStock.getString(8)) > 0){
                                                    out.println("<input type='hidden' name='categoria' value='juegos'/>");
                                                    out.println("<input type='hidden' name='id_producto' value='"+miResultSetStock.getString(1)+"'/>");
                                                    out.println("<input type='hidden' name='nombre_producto' value='"+miResultSetStock.getString(2)+"'/>");
                                                    out.println("<input type='hidden' name='generacion' value='"+miResultSetStock.getString(3)+"'/>");
                                                    out.println("<input type='hidden' name='precio_producto' value='"+miResultSetStock.getString(7)+"'/>");
                                                    out.println("<input type='hidden' name='imagen_producto' value='"+miResultSetStock.getString(9)+"'/>");
                                                    out.println("<td><input class='botonAgregar' type='submit' name='agregarCarro' value='Añadir a cesta'");
                                                    if(Integer.parseInt(miResultSetStock.getString(8)) <= 0 || userActive == null){
                                                        out.println(" disabled='disabled'");
                                                    }
                                                    out.println("/></td>");
                                                }
                                            out.print("</tr>");
                                            out.println("</form>");
                                        }
                                        out.println("</table>");
                                    }
                                    if(miResultSetConsolas.absolute(1)){
                                        miResultSetConsolas.absolute(0);
                                        out.println("<h1>Consolas</h1>");
                                        out.println("<table>"
                                                + "<tr class='headerTable'>"
                                                    + "<td>Nombre</td>"
                                                    + "<td>Compañia</td>"
                                                    + "<td>Potencia CPU</td>"
                                                    + "<td>Potencia GPU</td>"
                                                    + "<td>Precio</td>"
                                                    + "<td>Stock</td>"
                                                    + "<td>Imagen</td>"
                                                + "</tr>");
                                        while(miResultSetConsolas.next()){
                                            out.print("<form action='actualizarCarro' method='post'>");
                                            out.print("<tr>");
                                                out.println("<td>"+miResultSetConsolas.getString(2)+"</td>");
                                                out.println("<td>"+miResultSetConsolas.getString(4)+"</td>");
                                                out.println("<td>"+miResultSetConsolas.getString(5)+"</td>");
                                                out.println("<td>"+miResultSetConsolas.getString(6)+"</td>");
                                                out.println("<td>"+miResultSetConsolas.getString(7)+" €</td>");
                                                out.println("<td>"+miResultSetConsolas.getString(8)+" ud</td>");
                                                out.println("<td><img class='imgJuegos' src='images/"+miResultSetConsolas.getString(9)+".png'></td>");
                                                if(userActive != null && Integer.parseInt(miResultSetConsolas.getString(8)) > 0){
                                                    out.println("<input type='hidden' name='categoria' value='consolas'/>");
                                                    out.println("<input type='hidden' name='id_producto' value='"+miResultSetConsolas.getString(1)+"'/>");
                                                    out.println("<input type='hidden' name='nombre_producto' value='"+miResultSetConsolas.getString(2)+"'/>");
                                                    out.println("<input type='hidden' name='generacion' value='"+miResultSetConsolas.getString(3)+"'/>");
                                                    out.println("<input type='hidden' name='precio_producto' value='"+miResultSetConsolas.getString(7)+"'/>");
                                                    out.println("<input type='hidden' name='imagen_producto' value='"+miResultSetConsolas.getString(9)+"'/>");
                                                    out.println("<td><input class='botonAgregar' type='submit' name='agregarCarro' value='Añadir a cesta'");
                                                    if(Integer.parseInt(miResultSetConsolas.getString(8)) <= 0 || userActive == null){
                                                        out.println(" disabled='disabled'");
                                                    }
                                                    out.println("/></td>");
                                                }
                                            out.print("</tr>");
                                            out.println("</form>");
                                        }
                                        out.println("</table>");
                                    }
                                }
                                else{
                                    out.println("<h1>No se encontraron coincidencias en la busqueda.</h1>");
                                }
                            }
                        }
                        miConexion.close();
                    } catch(Exception e){
                        out.println("Error: "+e.getMessage());
                    }
                %>
            </div>        
        </div>
    </body>
</html>
