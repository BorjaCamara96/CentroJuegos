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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class Insertar extends HttpServlet {

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
            out.println("<title>Servlet Insertar</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Insertar at " + request.getContextPath() + "</h1>");
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
            Boolean empty = false;
            Boolean insert = false;
            Boolean userExist = false;
            Boolean passDistinct = false;
            String message = null;
            if(misession.getAttribute("UserActive") != null || request.getParameterNames() != null) {
                userActive = (User) misession.getAttribute("UserActive");
                if(!userActive.isEsAdm()){
                    response.sendRedirect("index.jsp");
                }
                miConexion = new Conexion();   
                if(request.getParameter("botonInsertar") != null){
                    switch(request.getParameter("insertar")){
                        case "Videojuego":
                            try{
                                String nombre_juego = request.getParameter("nombre_juego").trim();
                                String compania = request.getParameter("compania").trim();
                                String genero = request.getParameter("genero").trim();
                                int puntuacion = 0;
                                if(nombre_juego.equals("") || compania.equals("") || genero.equals("") || request.getParameter("puntuacion").equals("")){
                                    empty = true;
                                }                            
                                else{
                                    puntuacion = Integer.parseInt(request.getParameter("puntuacion"));
                                }
                                if(!empty){
                                    insert = miConexion.insertarJuego(nombre_juego, compania, genero, puntuacion);
                                }
                            } catch(Exception e){
                                out.println(e.getMessage());
                            }
                            break;
                        case "Stock":
                            try{
                                String nombre_juego = request.getParameter("nombre_juego").trim();
                                String generacion = request.getParameter("generacion").trim();                                
                                String imagen_juego = request.getParameter("imagen_juego").trim();
                                int stock_juego = 0;
                                float precio_juego = 0; 
                                if(nombre_juego.equals("") || generacion.equals("") || imagen_juego.equals("") || request.getParameter("stock_juego").equals("") || !request.getParameter("precio_juego").matches("\\d+\\.\\d{1,2}")){
                                    empty = true;
                                }                            
                                else{
                                    precio_juego = Float.parseFloat(request.getParameter("precio_juego").trim());
                                    stock_juego = Integer.parseInt(request.getParameter("stock_juego"));
                                }
                                if(!empty){
                                    insert = miConexion.insertarStock(nombre_juego, generacion, precio_juego, stock_juego, imagen_juego);
                                }
                            } catch(Exception e){
                                out.println(e.getMessage());
                            }                            
                            break;
                        case "Consola":
                            try{
                                String nombre_consola = request.getParameter("nombre_consola").trim();
                                String generacion = request.getParameter("generacion").trim();
                                String compania = request.getParameter("compania").trim();
                                String pot_cpu = request.getParameter("pot_cpu").trim();
                                String pot_gpu = request.getParameter("pot_gpu").trim();
                                String imagen_consola = request.getParameter("imagen_consola").trim();
                                float precio_consola = 0; 
                                int stock_consola = 0;
                                if(nombre_consola.equals("") || generacion.equals("") || compania.equals("") || pot_cpu.equals("") || pot_gpu.equals("") || imagen_consola.equals("") || request.getParameter("stock_consola").equals("") || !request.getParameter("precio_consola").matches("\\d+\\.\\d{1,2}")){
                                    empty = true;
                                }                            
                                else{
                                    precio_consola = Float.parseFloat(request.getParameter("precio_consola").trim());
                                    stock_consola = Integer.parseInt(request.getParameter("stock_consola"));
                                }
                                if(!empty){
                                    insert = miConexion.insertarConsola(nombre_consola, generacion, compania, pot_cpu, pot_gpu, precio_consola, stock_consola, imagen_consola);
                                }
                            } catch(Exception e){
                                out.println(e.getMessage());
                            }  
                            break;
                        case "Usuario":
                            String user = request.getParameter("user").trim();
                            String pass = request.getParameter("pass").trim();
                            String passRepeat = request.getParameter("passRepeat").trim();
                            Boolean esAdm = request.getParameter("isAdm") != null;
                            if(user.equals("") || pass.equals("") || passRepeat.equals("")){
                                empty = true;
                            }
                            else{
                                try{
                                    if(pass.equals(passRepeat)){
                                        miConexion = new Conexion();
                                        ResultSet miResultSet = miConexion.comprobarUsuario(user);
                                        if(!miResultSet.absolute(1)){
                                            message = miConexion.registroUsuario(user, pass, esAdm);
                                        }
                                        else{
                                            userExist = true;
                                        }       
                                    }   
                                    else{
                                        passDistinct = true;
                                    }                           
                                } catch(Exception e){
                                    out.println(e.getMessage());
                                } 
                            }
                            break;
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
            out.println("<title>Insertar "+request.getParameter("insertar")+"</title>");
            out.println("<link href=\"css/styles.css\" rel=\"stylesheet\" type=\"text/css\"/>");
            out.println("<link rel=\"shortcut icon\" href=\"images/favicon.png\">");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='cont'>");
            out.println("<h1>Insertar "+request.getParameter("insertar")+"</h1>");
            out.println("<form action='?' method='post'>");
            out.println("<input type='hidden' name='insertar' value='"+request.getParameter("insertar")+"'/>");
            out.println("<table class='form'>");
            switch(request.getParameter("insertar")){
                case "Videojuego":
                    ArrayList<String> opcionesGenero = new ArrayList<>();
                    ResultSet miResultSet = miConexion.listarCampoEnum("videojuegos", "genero");
                    while(miResultSet.next()){
                        String columnType = miResultSet.getString(1);
                        String[] valores = columnType.replace("enum(", "").replace(")", "").replace("'", "").split(",");
                        for (String valor : valores){ 
                            opcionesGenero.add(valor); 
                        }
                    }
                    out.println("<tr>");
                        out.println("<td>Ingrese nombre de juego</td>");
                        out.println("<td><input type='text' name='nombre_juego'/></td>");
                    out.println("</tr>"); 
                    out.println("<tr>");
                        out.println("<td>Ingrese compañia:</td>");
                        out.println("<td><input type='text' name='compania'/></td>");
                    out.println("</tr>"); 
                    out.println("<tr>");
                        out.println("<td>Seleccione genero:</td>");
                        out.println("<td>");
                            out.println("<select name='genero'>");
                                for(String opcion:opcionesGenero){
                                    out.println("<option value='"+opcion+"'>"+opcion+"</option>");
                                }
                            out.println("</select>");
                        out.println("</td>");
                    out.println("</tr>");  
                    out.println("<tr>");
                        out.println("<td>Ingrese puntuación:</td>");
                        out.println("<td><input type='number' name='puntuacion'/></td>");
                    out.println("</tr>");   
                    break;
                case "Stock":
                    out.println("<tr>");
                        out.println("<td>Seleccione videojuego:</td>");
                        out.println("<td>");
                            out.println("<select name='nombre_juego'>");
                                miResultSet = miConexion.listarTabla("videojuegos");
                                while(miResultSet.next()){
                                    out.println("<option value='"+miResultSet.getString(1)+"'>"+miResultSet.getString(1)+"</option>");
                                }
                            out.println("</select>");
                        out.println("</td>");
                    out.println("</tr>");
                    out.println("<tr>");
                        out.println("<td>Seleccione generación:</td>");
                        out.println("<td>");
                            out.println("<select name='generacion'>");
                                miResultSet = miConexion.listarTabla("generaciones");
                                while(miResultSet.next()){
                                    out.println("<option value='"+miResultSet.getString(1)+"'>"+miResultSet.getString(1)+"</option>");
                                }
                            out.println("</select>");
                        out.println("</td>");
                    out.println("</tr>");
                    out.println("<tr>");
                        out.println("<td>Ingrese precio:</td>");
                        out.println("<td><input type='text' name='precio_juego'/></td>");
                    out.println("</tr>"); 
                    out.println("<tr>");
                        out.println("<td>Ingrese stock:</td>");
                        out.println("<td><input type='number' name='stock_juego'/></td>");
                    out.println("</tr>"); 
                    out.println("<tr>");
                        out.println("<td>Ingrese ruta de imagen:</td>");
                        out.println("<td><input type='text' name='imagen_juego'/></td>");
                    out.println("</tr>");
                    break;
                case "Consola":
                    out.println("<tr>");
                        out.println("<td>Ingrese nombre de consola:</td>");
                        out.println("<td><input type='text' name='nombre_consola'/></td>");
                    out.println("</tr>"); 
                    out.println("<tr>");
                        out.println("<td>Seleccione generación:</td>");
                        out.println("<td>");
                            out.println("<select name='generacion'>");
                                miResultSet = miConexion.listarTabla("generaciones");
                                while(miResultSet.next()){
                                    out.println("<option value='"+miResultSet.getString(1)+"'>"+miResultSet.getString(1)+"</option>");
                                }
                            out.println("</select>");
                        out.println("</td>");
                    out.println("</tr>");   
                    out.println("<tr>");
                        out.println("<td>Ingrese compañia:</td>");
                        out.println("<td><input type='text' name='compania'/></td>");
                    out.println("</tr>"); 
                    out.println("<tr>");
                        out.println("<td>Ingrese potencia CPU:</td>");
                        out.println("<td><input type='text' name='pot_cpu'/></td>");
                    out.println("</tr>"); 
                    out.println("<tr>");
                        out.println("<td>Ingrese potencia GPU:</td>");
                        out.println("<td><input type='text' name='pot_gpu'/></td>");
                    out.println("</tr>"); 
                    out.println("<tr>");
                        out.println("<td>Ingrese precio:</td>");
                        out.println("<td><input type='text' name='precio_consola'/></td>");
                    out.println("</tr>"); 
                    out.println("<tr>");
                        out.println("<td>Ingrese stock:</td>");
                        out.println("<td><input type='number' name='stock_consola'/></td>");
                    out.println("</tr>"); 
                    out.println("<tr>");
                        out.println("<td>Ingrese ruta de imagen:</td>");
                        out.println("<td><input type='text' name='imagen_consola'/></td>");
                    out.println("</tr>"); 
                    break;
                case "Usuario":
                    out.println("<tr>");
                        out.println("<td>Ingrese usuario:</td>");
                        out.println("<td><input type='text' name='user'/></td>");
                    out.println("</tr>");        
                    out.println("<tr>");
                        out.println("<td>Ingrese contraseña:</td>");
                        out.println("<td><input type='password' name='pass'/></td>");
                    out.println("</tr>");        
                    out.println("<tr>");
                        out.println("<td>Repita contraseña:</td>");
                        out.println("<td><input type='password' name='passRepeat'/></td>");
                    out.println("</tr>");        
                    out.println("<tr>");
                        out.println("<td>Permisos de administrador:</td>");
                        out.println("<td><input type='checkbox' name='isAdm' value='adm'/></td>");
                    out.println("</tr>");
                    break;
            }            
            out.println("</table>");
            out.println("<input type='submit' name='botonInsertar' value='Insertar'/>");
            out.println("<input type='reset' name='reset' value='Resetear'/>");
            out.println("<input type='submit' name='volver' value='Volver'/>");
            out.println("</form>");
            if(empty) out.println("<p>No puede haber campos vacios</p>");
            else if(insert) out.println("<p>"+request.getParameter("insertar")+" insertado correctamente</p>");
            else if(userExist) out.println("<p>El usuario que ha introducido ya esta registrado</p>");
            else if(passDistinct) out.println("<p>Las contraseñas introducidas no coinciden</p>");
            else if(message != null) out.println(message);
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
