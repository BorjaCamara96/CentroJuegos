/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package despliegue.Game;

import java.sql.*;

/**
 *
 * @author Administrator
 */
public class Conexion {
    private final Connection miConexion;
    private Statement miStatement;
    private PreparedStatement miPreparedStatement;
    private ResultSet miResultset;
    
    public Conexion() throws SQLException, ClassNotFoundException{
        Class.forName("com.mysql.jdbc.Driver");
        this.miConexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/centro_game","user_game","1234");
    }   
    
    public void close() throws SQLException{
        if(miResultset != null)miResultset.close();
        if(miStatement != null) miStatement.close();
        if(miPreparedStatement != null) miPreparedStatement.close();
        if(miConexion != null) miConexion.close();
    }
    
    public ResultSet comprobarUsuario(String user, String pass) throws SQLException{
        String consulta = "SELECT * FROM usuarios WHERE user LIKE ? AND pass LIKE ?";
        miPreparedStatement = miConexion.prepareStatement(consulta,
                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        miPreparedStatement.setString(1, user);
        miPreparedStatement.setString(2, pass);
        miResultset = miPreparedStatement.executeQuery();
        return miResultset;
    }
    
    public ResultSet comprobarUsuario(String user) throws SQLException{
        String consulta = "SELECT * FROM usuarios WHERE user LIKE ?";
        miPreparedStatement = miConexion.prepareStatement(consulta,
                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        miPreparedStatement.setString(1, user);
        miResultset = miPreparedStatement.executeQuery();
        return miResultset;
    }
    
    public ResultSet listarTabla(String tabla) throws SQLException{
        String consulta = "SELECT * FROM "+tabla;
        miStatement = miConexion.createStatement();
        miResultset = miStatement.executeQuery(consulta);
        return miResultset;
    }
    
    public ResultSet listarTabla(String tabla, String columna, String id) throws SQLException{
        String consulta = "SELECT * FROM "+tabla+" WHERE "+columna+" = ?";
        miPreparedStatement = miConexion.prepareStatement(consulta,
                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        miPreparedStatement.setString(1, id);
        miResultset = miPreparedStatement.executeQuery();
        return miResultset;
    }
            
    public ResultSet listarTodosJuegos() throws SQLException{
        String consulta = """
                          SELECT stock.id_juego, stock.nombre_juego, stock.generacion, videojuegos.compania, videojuegos.genero, videojuegos.puntuacion, stock.precio_juego, stock.stock_juego, stock.imagen_juego
                          FROM stock 
                          INNER JOIN videojuegos ON stock.nombre_juego = videojuegos.nombre_juego
                          """;
        miStatement = miConexion.createStatement();
        miResultset = miStatement.executeQuery(consulta);
        return miResultset;
    }
    
    public ResultSet listarJuegos(String generacion) throws SQLException{
        String consulta = """
                          SELECT stock.id_juego, stock.nombre_juego, stock.generacion, videojuegos.compania, videojuegos.genero, videojuegos.puntuacion, stock.precio_juego, stock.stock_juego, stock.imagen_juego
                          FROM stock 
                          INNER JOIN videojuegos ON stock.nombre_juego = videojuegos.nombre_juego
                          WHERE stock.generacion LIKE ?
                          """;
        miPreparedStatement = miConexion.prepareStatement(consulta);
        miPreparedStatement.setString(1, generacion);
        miResultset = miPreparedStatement.executeQuery();
        return miResultset;
    }
    
    public ResultSet listarCesta(String categoria, int id) throws SQLException{
        String consulta = "";
        if(categoria.equals("consolas")){
            consulta = """
                          SELECT nombre_consola, stock_consola
                          FROM consolas 
                          WHERE id_consola = ?
                          """;
        }
        else if(categoria.equals("juegos")){
            consulta = """
                          SELECT nombre_juego, stock_juego
                          FROM stock 
                          WHERE stock.id_juego = ?
                          """;
        }
        miPreparedStatement = miConexion.prepareStatement(consulta,
                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        miPreparedStatement.setInt(1, id);
        miResultset = miPreparedStatement.executeQuery();
        return miResultset;
    }
    
    public String registroUsuario(String user, String pass, boolean adm) throws SQLException{
        String insercion = "INSERT INTO usuarios (user, pass, admin) VALUES (?, ?, ?)";
        miPreparedStatement = miConexion.prepareStatement(insercion);
        miPreparedStatement.setString(1, user);
        miPreparedStatement.setString(2, pass);
        miPreparedStatement.setBoolean(3, adm);
        miPreparedStatement.executeUpdate();
        return "<p>Usuario "+user+" registrado</p>";
    }
    
    public void restarStock(String categoria, int id) throws SQLException{
        String update = null;
        if(categoria.equals("consolas")){
            update = "UPDATE consolas SET consolas.stock_consola = consolas.stock_consola - 1 WHERE consolas.id_consola = ? AND consolas.stock_consola > 0";
        }
        else if(categoria.equals("juegos")){
            update = "UPDATE stock SET stock.stock_juego = stock.stock_juego - 1 WHERE stock.id_juego = ? AND stock.stock_juego > 0";
        }
        miPreparedStatement = miConexion.prepareStatement(update);
        miPreparedStatement.setInt(1, id);
        miPreparedStatement.executeUpdate();
    }
    
    public ResultSet listarCampoEnum(String tabla, String columna) throws SQLException{
        String consulta = """
                          SELECT COLUMN_TYPE FROM information_schema.COLUMNS 
                          WHERE TABLE_NAME = ? AND COLUMN_NAME = ?;
                          """;
        this.miPreparedStatement = this.miConexion.prepareStatement(consulta);
        this.miPreparedStatement.setString(1, tabla);
        this.miPreparedStatement.setString(2, columna);
        this.miResultset = this.miPreparedStatement.executeQuery();
        return this.miResultset;        
    }
    public boolean insertarJuego(String nombre_juego, String compania, String genero, int puntuacion) throws SQLException{
        String insercion = """
                           INSERT INTO videojuegos (nombre_juego, compania, genero, puntuacion) 
                           VALUES (?, ?, ?, ?)
                           """;
        if(puntuacion < 0) puntuacion = 0;
        miPreparedStatement = miConexion.prepareStatement(insercion);
        miPreparedStatement.setString(1, nombre_juego);
        miPreparedStatement.setString(2, compania);
        miPreparedStatement.setString(3, genero);
        miPreparedStatement.setInt(4, puntuacion);        
        miPreparedStatement.executeUpdate();
        return true;
    }
    public boolean insertarStock(String nombre_juego, String generacion, float precio_juego, int stock_juego, String imagen_juego) throws SQLException{
        String insercion = """
                           INSERT INTO stock (nombre_juego, generacion, precio_juego, stock_juego, imagen_juego) 
                           VALUES (?, ?, ?, ?, ?)
                           """;
        if(stock_juego < 0) stock_juego = 0;
        if(precio_juego < 0) precio_juego = 0;
        miPreparedStatement = miConexion.prepareStatement(insercion);
        miPreparedStatement.setString(1, nombre_juego);
        miPreparedStatement.setString(2, generacion);
        miPreparedStatement.setFloat(3, precio_juego);
        miPreparedStatement.setInt(4, stock_juego);  
        miPreparedStatement.setString(5, imagen_juego);
        miPreparedStatement.executeUpdate();
        return true;
    }
    public boolean insertarConsola(String nombre_consola, String generacion, String compania, String pot_cpu, String pot_gpu, float precio_consola, int stock_consola, String imagen_consola) throws SQLException{
        String insercion = """
                           INSERT INTO consolas (nombre_consola, generacion, compania, pot_cpu, pot_gpu, precio_consola, stock_consola, imagen_consola) 
                           VALUES (?, ?, ?, ?, ?, ?, ?, ?)
                           """;
        if(stock_consola < 0) stock_consola = 0;
        if(precio_consola < 0) precio_consola = 0;
        miPreparedStatement = miConexion.prepareStatement(insercion);
        miPreparedStatement.setString(1, nombre_consola);
        miPreparedStatement.setString(2, generacion);
        miPreparedStatement.setString(3, compania);
        miPreparedStatement.setString(4, pot_cpu);
        miPreparedStatement.setString(5, pot_gpu);
        miPreparedStatement.setFloat(6, precio_consola);
        miPreparedStatement.setInt(7, stock_consola);
        miPreparedStatement.setString(8, imagen_consola);
        miPreparedStatement.executeUpdate();
        return true;
    }
    public boolean modificarJuegos(String nombre_juego, String compania, String genero, int puntuacion, String id) throws SQLException{
        String modify = """
                        UPDATE videojuegos
                        SET nombre_juego = ?, compania = ?, genero = ?, puntuacion = ?
                        WHERE nombre_juego LIKE ?
                        """;
        if(puntuacion < 0) puntuacion = 0;
        miPreparedStatement = miConexion.prepareStatement(modify);
        miPreparedStatement.setString(1, nombre_juego);
        miPreparedStatement.setString(2, compania);
        miPreparedStatement.setString(3, genero);
        miPreparedStatement.setInt(4, puntuacion);
        miPreparedStatement.setString(5, id);
        miPreparedStatement.executeUpdate();
        return true;
    }
    public boolean modificarStock(String nombre_juego, String generacion, String imagen_juego, int stock_juego, float precio_juego, int id) throws SQLException{
        String modify = """
                        UPDATE stock
                        SET nombre_juego = ?, generacion = ?, precio_juego = ?, stock_juego = ?, imagen_juego = ?
                        WHERE id_juego = ?
                        """;
        if(stock_juego < 0) stock_juego = 0;
        if(precio_juego < 0) precio_juego = 0;
        miPreparedStatement = miConexion.prepareStatement(modify);
        miPreparedStatement.setString(1, nombre_juego);
        miPreparedStatement.setString(2, generacion);
        miPreparedStatement.setFloat(3, precio_juego);
        miPreparedStatement.setInt(4, stock_juego);  
        miPreparedStatement.setString(5, imagen_juego);
        miPreparedStatement.setInt(6, id);
        miPreparedStatement.executeUpdate();
        return true;
    }
    public boolean modificarConsola(String nombre_consola, String generacion, String compania, String pot_cpu, String pot_gpu, float precio_consola, int stock_consola, String imagen_consola, int id) throws SQLException{
        String modify = """
                        UPDATE consolas
                        SET nombre_consola = ?, generacion = ?, compania = ?, pot_cpu = ?, 
                        pot_gpu = ?, precio_consola = ?, stock_consola = ?, imagen_consola = ? 
                        WHERE id_consola = ?
                        """;
        if(stock_consola < 0) stock_consola = 0;
        if(precio_consola < 0) precio_consola = 0;
        miPreparedStatement = miConexion.prepareStatement(modify);
        miPreparedStatement.setString(1, nombre_consola);
        miPreparedStatement.setString(2, generacion);
        miPreparedStatement.setString(3, compania);
        miPreparedStatement.setString(4, pot_cpu);
        miPreparedStatement.setString(5, pot_gpu);
        miPreparedStatement.setFloat(6, precio_consola);
        miPreparedStatement.setInt(7, stock_consola);
        miPreparedStatement.setString(8, imagen_consola);
        miPreparedStatement.setInt(9, id);
        miPreparedStatement.executeUpdate();
        return true;
    }
    public boolean modificarUsuario(String user, String pass, boolean esAdm, int id) throws SQLException{
        String modify = """
                        UPDATE usuarios
                        SET user = ?, pass = ?, admin = ?
                        WHERE id_usuario = ?
                        """;
        miPreparedStatement = miConexion.prepareStatement(modify);
        miPreparedStatement.setString(1, user);
        miPreparedStatement.setString(2, pass);
        miPreparedStatement.setBoolean(3, esAdm);
        miPreparedStatement.setInt(4, id);
        miPreparedStatement.executeUpdate();
        return true;
    }
    public void eliminarVideojuego(String nombre_juego) throws SQLException{
        String delete = """
                        DELETE FROM videojuegos WHERE nombre_juego LIKE ?
                        """;
        miPreparedStatement = miConexion.prepareStatement(delete);
        miPreparedStatement.setString(1, nombre_juego);
        miPreparedStatement.executeUpdate();
    }
    
    public String eliminarStockConsolaUsuario(int id, String opcion) throws SQLException{
        String x = null, y = null;
        switch(opcion){
            case "Stock":
                x = "stock";
                y = "id_juego";
                break;
            case "Consola":
                x = "consolas";
                y = "id_consola";
                break;
            case "Usuario":
                x = "usuarios";
                y = "id_usuario";
                break;
        }                
        String nombre = this.listarTablaEliminar(x, y, id);        
        String delete = "DELETE FROM "+x+" WHERE "+y+" LIKE ?";
        miPreparedStatement = miConexion.prepareStatement(delete);
        miPreparedStatement.setInt(1, id);
        miPreparedStatement.executeUpdate();
        return nombre;
    }
    
    public String listarTablaEliminar(String tabla, String columna, int id) throws SQLException{
        String consulta = "SELECT * FROM "+tabla+" WHERE "+columna+" = ?";
        miPreparedStatement = miConexion.prepareStatement(consulta,
                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        miPreparedStatement.setInt(1, id);
        miResultset = miPreparedStatement.executeQuery();
        String nombre = "";
        if(miResultset.absolute(1)){
            nombre = tabla.equals("stock") ? miResultset.getString(2)+" - "+miResultset.getString(3) : miResultset.getString(2);
        }
        return nombre;
    }
 
    public ResultSet buscarCoincidenciaStock(String texto) throws SQLException{
        texto = "%"+texto+"%";
        String consulta = """
                        SELECT stock.id_juego, stock.nombre_juego, stock.generacion, videojuegos.compania, videojuegos.genero, videojuegos.puntuacion, stock.precio_juego, stock.stock_juego, stock.imagen_juego
                        FROM stock 
                        INNER JOIN videojuegos ON stock.nombre_juego = videojuegos.nombre_juego
                        WHERE stock.nombre_juego LIKE ? OR stock.generacion LIKE ? OR videojuegos.compania LIKE ? OR videojuegos.genero LIKE ? 
                        """;
        miPreparedStatement = miConexion.prepareStatement(consulta,
                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        miPreparedStatement.setString(1, texto);
        miPreparedStatement.setString(2, texto);
        miPreparedStatement.setString(3, texto);
        miPreparedStatement.setString(4, texto);
        miResultset = miPreparedStatement.executeQuery();
        return miResultset;
    }
    
    public ResultSet buscarCoincidenciaConsolas(String texto) throws SQLException{
        texto = "%"+texto+"%";
        String consulta = """
                        SELECT *
                        FROM consolas 
                        WHERE consolas.nombre_consola LIKE ? OR consolas.generacion LIKE ? OR consolas.compania LIKE ?
                        """;
        miPreparedStatement = miConexion.prepareStatement(consulta,
                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        miPreparedStatement.setString(1, texto);
        miPreparedStatement.setString(2, texto);
        miPreparedStatement.setString(3, texto);
        miResultset = miPreparedStatement.executeQuery();
        return miResultset;
    }
}
