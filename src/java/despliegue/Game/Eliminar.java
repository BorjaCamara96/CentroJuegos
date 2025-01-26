/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package despliegue.Game;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class Eliminar extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Eliminar</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Eliminar at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        response.sendRedirect("index.jsp");
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession misession = request.getSession();
            User userActive = null;  
            Conexion miConexion = null;
            ResultSet miResultSet = null;
            Boolean empty = false;
            Boolean idForeign = false;
            String messages = "";
            if(misession.getAttribute("UserActive") != null || request.getParameterNames() != null) {
                userActive = (User) misession.getAttribute("UserActive");
                if(!userActive.isEsAdm()){
                    response.sendRedirect("index.jsp");
                }
                miConexion = new Conexion();   
                if(request.getParameter("botonEliminar") != null){
                    if(request.getParameterValues("id") != null){
                        try{
                            switch(request.getParameter("eliminar")){
                                case "Videojuego":
                                    for(String opcion:request.getParameterValues("id")){                                        
                                        miConexion.eliminarVideojuego(opcion);
                                        messages += "<p>Eliminado con exito: "+opcion+"</p>\n";
                                    }
                                    break;
                                case "Stock":  
                                case "Consola":
                                case "Usuario":
                                    for(String opcion:request.getParameterValues("id")){
                                        int opcionInt = Integer.parseInt(opcion);
                                        String nombre = miConexion.eliminarStockConsolaUsuario(opcionInt, request.getParameter("eliminar"));
                                        messages += "<p>Eliminado con exito: "+nombre+"</p>";
                                    }
                                    break;
                            }
                        } catch(Exception e) {
                            out.println(e.getMessage());
                        }
                    }
                    else{
                        empty = true;
                    }
                }
                else if(request.getParameter("volver") != null){
                    response.sendRedirect("panelAdministrador.jsp");
                }
            }
            else{
                response.sendRedirect("index.jsp");
            }
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Eliminar "+request.getParameter("eliminar")+"</title>");
            out.println("<link href=\"css/styles.css\" rel=\"stylesheet\" type=\"text/css\"/>");
            out.println("<link rel=\"shortcut icon\" href=\"images/favicon.png\">");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='cont'>");
            out.println("<h1>Eliminar "+request.getParameter("eliminar")+"</h1>");
            out.println("<form action='?' method='post'>");
            out.println("<input type='hidden' name='eliminar' value='"+request.getParameter("eliminar")+"'/>");
            out.println("<table>");            
            switch(request.getParameter("eliminar")){
                case "Videojuego":
                    miResultSet = miConexion.listarTabla("videojuegos");
                    out.println("<tr>");
                        out.println("<td>Nombre</td>");
                        out.println("<td>Compañia</td>");
                        out.println("<td>Genero</td>");
                        out.println("<td>Puntuación</td>");
                    out.println("</tr>");
                    while(miResultSet.next()){
                        out.println("<tr>");
                            out.println("<td>"+miResultSet.getString(1)+"</td>");
                            out.println("<td>"+miResultSet.getString(2)+"</td>");
                            out.println("<td>"+miResultSet.getString(3)+"</td>");
                            out.println("<td>"+miResultSet.getString(4)+"</td>");
                            out.println("<td><input type='checkbox' name='id' value='"+miResultSet.getString(1)+"'/></td>");
                        out.println("</tr>");
                    }
                    break;
                case "Stock":
                    miResultSet = miConexion.listarTodosJuegos();
                    out.println("<tr>");
                        out.println("<td>Nombre</td>");
                        out.println("<td>Plataforma</td>");
                        out.println("<td>Compañia</td>");
                        out.println("<td>Genero</td>");
                        out.println("<td>Puntuación</td>");
                        out.println("<td>Precio</td>");
                        out.println("<td>Stock</td>");
                        out.println("<td>Imagen</td>");
                    out.println("</tr>");
                    while(miResultSet.next()){
                        out.println("<tr>");
                            out.println("<td>"+miResultSet.getString(2)+"</td>");
                            out.println("<td>"+miResultSet.getString(3)+"</td>");
                            out.println("<td>"+miResultSet.getString(4)+"</td>");
                            out.println("<td>"+miResultSet.getString(5)+"</td>");
                            out.println("<td>"+miResultSet.getString(6)+"</td>");
                            out.println("<td>"+miResultSet.getString(7)+"</td>");
                            out.println("<td>"+miResultSet.getString(8)+"</td>");
                            out.println("<td><img class='imgJuegosPequeña' src='images/"+miResultSet.getString(9)+".png'/></td>");                            
                            out.println("<td><input type='checkbox' name='id' value='"+miResultSet.getString(1)+"'/></td>");                            
                        out.println("</tr>");
                    }
                    break;
                case "Consola":
                    miResultSet = miConexion.listarTabla("consolas");
                    out.println("<tr>");
                        out.println("<td>Nombre</td>");
                        out.println("<td>Compañia</td>");
                        out.println("<td>Precio</td>");
                        out.println("<td>Stock</td>");
                        out.println("<td>Imagen</td>");
                    out.println("</tr>");
                    while(miResultSet.next()){
                        out.println("<tr>");
                            out.println("<td>"+miResultSet.getString(2)+"</td>");
                            out.println("<td>"+miResultSet.getString(4)+"</td>");
                            out.println("<td>"+miResultSet.getString(7)+"</td>");
                            out.println("<td>"+miResultSet.getString(8)+"</td>");
                            out.println("<td><img class='imgJuegosPequeña' src='images/"+miResultSet.getString(9)+".png'/></td>");
                            out.println("<td><input type='checkbox' name='id' value='"+miResultSet.getString(1)+"'/></td>");                            
                        out.println("</tr>");
                    }
                    break;
                case "Usuario":
                    miResultSet = miConexion.listarTabla("usuarios");
                        out.println("<tr>");
                            out.println("<td>Usuario</td>");
                            out.println("<td>Fuciones de administrador</td>");
                        out.println("</tr>");
                    while(miResultSet.next()){
                        out.println("<tr>");
                            out.println("<td>"+miResultSet.getString(2)+"</td>");
                            String adm = miResultSet.getBoolean(4) ? "Si" : "No";
                            out.println("<td>"+adm+"</td>");
                            out.println("<td><input type='checkbox' name='id' value='"+miResultSet.getString(1)+"'/></td>");                            
                        out.println("</tr>");
                    }
                    break;
            }            
            out.println("</table>");
            out.println("<input type='submit' name='botonEliminar' value='Eliminar'/>");
            out.println("<input type='reset' name='reset' value='Resetear'/>");
            out.println("<input type='submit' name='volver' value='Volver'/>");
            if(empty) out.println("<p>Debe seleccionar al menos una opcion</p>");
            else if(!messages.equals("")) out.println(messages);
            out.println("</form>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
            miConexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(Insertar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Insertar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
