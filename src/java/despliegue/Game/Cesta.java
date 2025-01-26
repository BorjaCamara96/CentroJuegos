/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package despliegue.Game;

import java.util.*;

/**
 *
 * @author Administrator
 */
public class Cesta {
    private ArrayList<Producto> cesta;
    private boolean compraRealizada;
    private ArrayList<String> productoEmpty;
      
    public Cesta(){
        this.cesta = new ArrayList<>();
        this.compraRealizada = false;
        this.productoEmpty = new ArrayList<>();
    }
    
    public void agregarCarro(Producto producto){
        this.cesta.add(producto);
    }
    
    public void quitarCarro(String categoria, int id){
        Producto producto = null;
        for(Producto elemento:cesta){
            if(elemento.getId() == id && elemento.getCategoria().equals(categoria)){
                producto = elemento;
            }
        }
       cesta.remove(producto);
    }
    
    public void vaciarCarro(){
        this.cesta.clear();
    }
    
    public ArrayList<Producto> getCesta(){
        return cesta;
    }
    
    public float precioCarro(){
        float suma = 0;
        for(Producto elemento:cesta){
            suma += elemento.getPrecio();
        }
        return suma;
    }
    
    public boolean getCompraRealizada(){
        return this.compraRealizada;
    }
    
    public void setCompraRealizada(boolean compra){
        this.compraRealizada = compra;
    }
    
    public ArrayList<String> getProductoEmpty(){
        return this.productoEmpty;
    }
    
    public void añadirProductoEmpty(String producto){
        this.productoEmpty.add(producto);
    }
    
    public void vaciarProductosEmpty(){
        this.productoEmpty.clear();
    }
}
