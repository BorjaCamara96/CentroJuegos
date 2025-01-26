/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package despliegue.Game;

/**
 *
 * @author Administrator
 */
public class Producto {
    private String categoria;
    private int id;
    private String nombre;
    private String generacion;
    private float precio;
    private String imagen;

    public Producto(String categoria, int id, String nombre, String generacion, float precio, String imagen) {
        this.categoria = categoria;
        this.id = id;
        this.nombre = nombre;
        this.generacion = generacion;
        this.precio = precio;
        this.imagen = imagen;
    }

    public String getCategoria() {
        return categoria;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }
    
    public String getGeneracion(){
        return generacion;
    }

    public float getPrecio() {
        return precio;
    }
    
    public String getImagen(){
        return imagen;
    }
    
}
