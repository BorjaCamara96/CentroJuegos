<%-- 
    Document   : cestaCompra
    Created on : 24 dic 2024, 0:38:23
    Author     : Administrator
--%>

<%@page contentType="text/html" import="despliegue.Game.*, java.sql.*, java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cesta de compra</title>
        <link href="css/styles.css" rel="stylesheet" type="text/css"/>
        <link rel="shortcut icon" href="images/favicon.png">
        <%
            HttpSession misession = request.getSession();
            User userActive = null;  
            Cesta cesta = (Cesta) misession.getAttribute("CestaCompra");                
            if(misession.getAttribute("UserActive") != null) {
                userActive = (User) misession.getAttribute("UserActive");
            }
            else{
                response.sendRedirect("index.jsp");
            }
        %>
    </head>
    <body>
        <div class="cont">
        <h1>Cesta de compra</h1>
            <%  
                try{
                    out.println("<form action='actualizarCarro' method='post'>");
                    if(cesta.getCesta().isEmpty()) {
                        out.println("<p>Cesta vacia</p>");
                        if(cesta.getCompraRealizada()){
                            out.println("<p>Pedido realizado.</p>");
                            cesta.setCompraRealizada(false);
                        }
                        if(!cesta.getProductoEmpty().isEmpty()){
                            for(String elemento:cesta.getProductoEmpty()){
                                out.println("<p>Producto "+elemento+" sin stock disponible.</p>");
                            }
                            cesta.vaciarProductosEmpty();
                        }
                    }
                    else{
                        out.println("<table>");
                        out.println("<tr>");
                        out.println("</tr>");
                        for(Producto elemento:cesta.getCesta()){
                            out.println("<form action='actualizarCarro' method='post'>");
                            out.println("<tr>");
                                out.println("<td>"+elemento.getNombre()+"</td>");
                                out.println("<td>"+elemento.getGeneracion()+"</td>");
                                out.println("<td>"+elemento.getPrecio()+" €</td>");
                                out.println("<td><img class='imgJuegosPequeña' src='images/"+elemento.getImagen()+".png'/></td>");
                                out.println("<input type='hidden' name='categoria' value='"+elemento.getCategoria()+"'/>");
                                out.println("<input type='hidden' name='id_producto' value='"+elemento.getId()+"'/>");
                                out.println("<td><input type='submit' name='eliminar' value='Eliminar'/></td>");
                            out.println("</tr>");
                            out.println("</form>");
                        }
                        out.println("</table>");
                        float f = cesta.precioCarro();
                        String precioString = String.format("%.2f", f);
                        out.println("<p>Precio total: "+precioString+" €</p>");
                        out.println("<input type='submit' name='comprar' value='Comprar'/>");
                        out.println("<input type='submit' name='vaciar' value='Vaciar cesta'/>");
                    }
                    out.println("<input type='submit' name='volver' value='Volver'/>");
                    out.println("</form>");
                } catch(Exception e){
                    out.println(e.getMessage());
                }
            %>
        </div>
    </body>
</html>
