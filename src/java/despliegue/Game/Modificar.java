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
public class Modificar extends HttpServlet {

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
            out.println("<title>Servlet Modificar</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Modificar at " + request.getContextPath() + "</h1>");
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
            ResultSet miResultSet2 = null;
            Boolean select = false;
            Boolean empty = false;
            Boolean modify = false;
            Boolean userExist = false;
            Boolean passDistinct = false;
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Modificar "+request.getParameter("modificar")+"</title>");
            out.println("<link href=\"css/styles.css\" rel=\"stylesheet\" type=\"text/css\"/>");
            out.println("<link rel=\"shortcut icon\" href=\"images/favicon.png\">");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='cont'>");
            out.println("<h1>Modificar "+request.getParameter("modificar")+"</h1>");
            
            if(misession.getAttribute("UserActive") != null || request.getParameterNames() != null) {
                userActive = (User) misession.getAttribute("UserActive");
                if(!userActive.isEsAdm()){
                    response.sendRedirect("index.jsp");
                }
                if(request.getParameter("volver") != null){
                    response.sendRedirect("panelAdministrador.jsp");
                }
                miConexion = new Conexion();   
                if(request.getParameter("botonModificar") != null){
                    if(request.getParameter("id") != null){
                        if(request.getParameter("botonPress") != null){
                            switch(request.getParameter("modificar")){
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
                                            modify = miConexion.modificarJuegos(nombre_juego, compania, genero, puntuacion, request.getParameter("id"));
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
                                            precio_juego = Float.parseFloat(request.getParameter("precio_juego"));
                                            stock_juego = Integer.parseInt(request.getParameter("stock_juego"));
                                        }
                                        if(!empty){
                                            modify = miConexion.modificarStock(nombre_juego, generacion, imagen_juego, stock_juego, precio_juego, Integer.parseInt(request.getParameter("id")));
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
                                            precio_consola = Float.parseFloat(request.getParameter("precio_consola"));
                                            stock_consola = Integer.parseInt(request.getParameter("stock_consola"));
                                        }
                                        if(!empty){
                                            modify = miConexion.modificarConsola(nombre_consola, generacion, compania, pot_cpu, pot_gpu, precio_consola, stock_consola, imagen_consola, Integer.parseInt(request.getParameter("id")));
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
                                                miResultSet = miConexion.comprobarUsuario(user);
                                                if(miResultSet.absolute(1)){
                                                    if(!miResultSet.getString(2).equals(user)){
                                                        userExist = true;
                                                    }                                                    
                                                }    
                                                if(!userExist) modify = miConexion.modificarUsuario(user, pass, esAdm, Integer.parseInt(request.getParameter("id")));
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
                        try{
                            out.println("<form action='?' method='post'>");
                            out.println("<input type='hidden' name='modificar' value='"+request.getParameter("modificar")+"'/>");
                            out.println("<input type='hidden' name='id' value='"+request.getParameter("id")+"'/>");
                            if(request.getParameter("botonModificar") != null)  out.println("<input type='hidden' name='botonModificar' value='Modificar'/>");
                            out.println("<table class='form'>");
                            switch(request.getParameter("modificar")){
                                case "Videojuego":
                                    ArrayList<String> opcionesGenero = new ArrayList<>();
                                    miResultSet = miConexion.listarCampoEnum("videojuegos", "genero");
                                    while(miResultSet.next()){
                                        String columnType = miResultSet.getString(1);
                                        String[] valores = columnType.replace("enum(", "").replace(")", "").replace("'", "").split(",");
                                        for (String valor : valores){ 
                                            opcionesGenero.add(valor); 
                                        }
                                    }
                                    miResultSet = miConexion.listarTabla("videojuegos", "nombre_juego", request.getParameter("id"));
                                    if(miResultSet.absolute(1)){
                                        out.println("<tr>");
                                            out.println("<td>Ingrese nombre de juego</td>");
                                            out.println("<td><input type='text' name='nombre_juego' value='"+miResultSet.getString(1)+"'/></td>");
                                        out.println("</tr>"); 
                                        out.println("<tr>");
                                            out.println("<td>Ingrese compañia:</td>");
                                            out.println("<td><input type='text' name='compania' value='"+miResultSet.getString(2)+"'/></td>");
                                        out.println("</tr>"); 
                                        out.println("<tr>");
                                            out.println("<td>Seleccione genero:</td>");
                                            out.println("<td>");
                                                out.println("<select name='genero'>");
                                                    for(String opcion:opcionesGenero){
                                                        out.println("<option value='"+opcion+"'");
                                                            if(opcion.equals(miResultSet.getString(3))) out.println(" selected");
                                                        out.println(">"+opcion+"</option>");
                                                    }
                                                out.println("</select>");
                                            out.println("</td>");
                                        out.println("</tr>");  
                                        out.println("<tr>");
                                            out.println("<td>Ingrese puntuación:</td>");
                                            out.println("<td><input type='number' name='puntuacion' value='"+miResultSet.getString(4)+"'/></td>");
                                        out.println("</tr>"); 
                                    }                                    
                                    break;
                                case "Stock":  
                                    miResultSet = miConexion.listarTabla("stock", "id_juego", request.getParameter("id"));
                                    if(miResultSet.absolute(1)){                                        
                                        out.println("<tr>");
                                            out.println("<td>Seleccione videojuego:</td>");
                                            out.println("<td>");
                                                out.println("<select name='nombre_juego'>");
                                                    miResultSet2 = miConexion.listarTabla("videojuegos");
                                                    while(miResultSet2.next()){
                                                        out.println("<option value='"+miResultSet2.getString(1)+"'");
                                                        if(miResultSet2.getString(1).equals(miResultSet.getString(2))) out.println(" selected");
                                                        out.println(">"+miResultSet2.getString(1)+"</option>");
                                                    }
                                                out.println("</select>");
                                            out.println("</td>");
                                        out.println("</tr>");
                                        out.println("<tr>");
                                            out.println("<td>Seleccione generación:</td>");
                                            out.println("<td>");
                                                out.println("<select name='generacion'>");
                                                    miResultSet2 = miConexion.listarTabla("generaciones");
                                                    while(miResultSet2.next()){
                                                        out.println("<option value='"+miResultSet2.getString(1)+"'");
                                                            if(miResultSet2.getString(1).equals(miResultSet.getString(3))) out.println(" selected");
                                                        out.println(">"+miResultSet2.getString(1)+"</option>");
                                                    }
                                                out.println("</select>");
                                            out.println("</td>");
                                        out.println("</tr>");
                                        out.println("<tr>");
                                            out.println("<td>Ingrese precio:</td>");
                                            out.println("<td><input type='text' name='precio_juego' value='"+miResultSet.getString(4)+"'/></td>");
                                        out.println("</tr>"); 
                                        out.println("<tr>");
                                            out.println("<td>Ingrese stock:</td>");
                                            out.println("<td><input type='number' name='stock_juego' value='"+miResultSet.getString(5)+"'/></td>");
                                        out.println("</tr>"); 
                                        out.println("<tr>");
                                            out.println("<td>Ingrese ruta de imagen:</td>");
                                            out.println("<td><input type='text' name='imagen_juego' value='"+miResultSet.getString(6)+"'/></td>");
                                        out.println("</tr>");
                                    }
                                    break;
                                case "Consola":
                                    miResultSet = miConexion.listarTabla("consolas", "id_consola", request.getParameter("id"));
                                    if(miResultSet.absolute(1)){
                                        out.println("<tr>");
                                            out.println("<td>Ingrese nombre de consola:</td>");
                                            out.println("<td><input type='text' name='nombre_consola' value='"+miResultSet.getString(2)+"'/></td>");
                                        out.println("</tr>"); 
                                        out.println("<tr>");
                                            out.println("<td>Seleccione generación:</td>");
                                            out.println("<td>");
                                                out.println("<select name='generacion'>");
                                                    miResultSet2 = miConexion.listarTabla("generaciones");
                                                    while(miResultSet2.next()){
                                                        out.println("<option value='"+miResultSet2.getString(1)+"'");
                                                            if(miResultSet2.getString(1).equals(miResultSet.getString(3))) out.println(" selected");
                                                        out.println(">"+miResultSet2.getString(1)+"</option>");                                                        
                                                    }
                                                out.println("</select>");
                                            out.println("</td>");
                                        out.println("</tr>");   
                                        out.println("<tr>");
                                            out.println("<td>Ingrese compañia:</td>");
                                            out.println("<td><input type='text' name='compania' value='"+miResultSet.getString(4)+"'/></td>");
                                        out.println("</tr>"); 
                                        out.println("<tr>");
                                            out.println("<td>Ingrese potencia CPU:</td>");
                                            out.println("<td><input type='text' name='pot_cpu' value='"+miResultSet.getString(5)+"'/></td>");
                                        out.println("</tr>"); 
                                        out.println("<tr>");
                                            out.println("<td>Ingrese potencia GPU:</td>");
                                            out.println("<td><input type='text' name='pot_gpu' value='"+miResultSet.getString(6)+"'/></td>");
                                        out.println("</tr>"); 
                                        out.println("<tr>");
                                            out.println("<td>Ingrese precio:</td>");
                                            out.println("<td><input type='text' name='precio_consola' value='"+miResultSet.getString(7)+"'/></td>");
                                        out.println("</tr>"); 
                                        out.println("<tr>");
                                            out.println("<td>Ingrese stock:</td>");
                                            out.println("<td><input type='number' name='stock_consola' value='"+miResultSet.getString(8)+"'/></td>");
                                        out.println("</tr>"); 
                                        out.println("<tr>");
                                            out.println("<td>Ingrese ruta de imagen:</td>");
                                            out.println("<td><input type='text' name='imagen_consola' value='"+miResultSet.getString(9)+"'/></td>");
                                        out.println("</tr>"); 
                                    }
                                    break;
                                case "Usuario":
                                    miResultSet = miConexion.listarTabla("usuarios", "id_usuario", request.getParameter("id"));
                                    if(miResultSet.absolute(1)){
                                        out.println("<tr>");
                                            out.println("<td>Ingrese usuario:</td>");
                                            out.println("<td><input type='text' name='user' value='"+miResultSet.getString(2)+"'/></td>");
                                        out.println("</tr>");        
                                        out.println("<tr>");
                                            out.println("<td>Ingrese contraseña:</td>");
                                            out.println("<td><input type='password' name='pass' value='"+miResultSet.getString(3)+"'/></td>");
                                        out.println("</tr>");        
                                        out.println("<tr>");
                                            out.println("<td>Repita contraseña:</td>");
                                            out.println("<td><input type='password' name='passRepeat' value='"+miResultSet.getString(3)+"'/></td>");
                                        out.println("</tr>");        
                                        out.println("<tr>");
                                            out.println("<td>Permisos de administrador:</td>");
                                            out.println("<td><input type='checkbox' name='isAdm' value='adm'");
                                                if(miResultSet.getBoolean(4)) out.println(" checked");
                                            out.println("/></td>");
                                        out.println("</tr>");
                                    }
                                    break;
                            }
                            out.println("</table>");
                            out.println("<input type='submit' name='botonPress' value='Modificar'/>");
                            out.println("<input type='submit' name='volver' value='Volver'/>");
                            out.println("</form>");
                            
                        } catch(Exception e) {
                            out.println(e.getMessage());
                        }
                    }
                    else{
                        select = true;
                    }
                }
            }
            else{
                response.sendRedirect("index.jsp");
            }           
            
            if(request.getParameter("botonModificar") == null || request.getParameter("id") == null){
                out.println("<form action='?' method='post'>");
                out.println("<input type='hidden' name='modificar' value='"+request.getParameter("modificar")+"'/>");
                out.println("<table>");
                switch(request.getParameter("modificar")){
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
                                out.println("<td><input type='radio' name='id' value='"+miResultSet.getString(1)+"'/></td>");
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
                                out.println("<td><input type='radio' name='id' value='"+miResultSet.getString(1)+"'/></td>");                            
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
                                out.println("<td><input type='radio' name='id' value='"+miResultSet.getString(1)+"'/></td>");                            
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
                                out.println("<td><input type='radio' name='id' value='"+miResultSet.getString(1)+"'/></td>");                            
                            out.println("</tr>");
                        }
                        break;
                }            
                out.println("</table>"); 
                out.println("<input type='submit' name='botonModificar' value='Modificar'/>");
                out.println("<input type='reset' name='reset' value='Resetear'/>");
                out.println("<input type='submit' name='volver' value='Volver'/>");
                out.println("</form>"); 
            }
                     
            if(select) out.println("<p>Debe seleccionar una opcion</p>");
            else if(empty) out.println("<p>No puede haber campos vacios</p>");
            else if(userExist) out.println("<p>El usuario que ha introducido ya esta registrado</p>");
            else if(passDistinct) out.println("<p>Las contraseñas introducidas no coinciden</p>");
            else if(modify) out.println("<p>"+request.getParameter("modificar")+" modificado correctamente</p>");
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
