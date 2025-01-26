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
public class ActualizarCarro extends HttpServlet {

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
            out.println("<title>Servlet ActualizarCarro</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ActualizarCarro at " + request.getContextPath() + "</h1>");
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
        HttpSession misession = request.getSession();
        Cesta cesta = (Cesta) misession.getAttribute("CestaCompra");
        if(request.getParameter("agregarCarro") != null){
            String  categoria = request.getParameter("categoria");
            int id_producto = Integer.parseInt(request.getParameter("id_producto"));
            String nombre_producto = request.getParameter("nombre_producto");
            String generacion = request.getParameter("generacion");
            float precio_producto = Float.parseFloat(request.getParameter("precio_producto"));
            String imagen_producto = request.getParameter("imagen_producto");
            cesta.agregarCarro(new Producto(categoria, id_producto, nombre_producto, generacion,  precio_producto, imagen_producto)); 
        }
        else if(request.getParameter("vaciar") != null){
            cesta.vaciarCarro();
        }
        else if(request.getParameter("comprar") != null){
            try {
                for(Producto elemento:cesta.getCesta()){
                    Conexion miConexion = new Conexion();
                    ResultSet miResultSet = miConexion.listarCesta(elemento.getCategoria(), elemento.getId()); 
                    if(!miResultSet.absolute(1) || miResultSet.getInt(2) <= 0){
                        cesta.añadirProductoEmpty(miResultSet.getString(1));
                    }
                    else{
                        miConexion.restarStock(elemento.getCategoria(), elemento.getId());
                        miConexion.close();
                        cesta.setCompraRealizada(true);
                    }
                }   
                cesta.vaciarCarro();
            } catch (SQLException ex) {
                Logger.getLogger(ActualizarCarro.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ActualizarCarro.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(ActualizarCarro.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if(request.getParameter("eliminar") != null){
            String categoria = request.getParameter("categoria");
            int id_producto = Integer.parseInt(request.getParameter("id_producto")); 
            cesta.quitarCarro(categoria, id_producto);
        }
        if(request.getParameter("volver") != null){
            response.sendRedirect("index.jsp");
        }     
        else{
             response.sendRedirect("cestaCompra.jsp");
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
