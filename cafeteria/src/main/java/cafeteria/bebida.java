/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cafeteria;

/**
 * Producto de tipo bebida.
 */
public class bebida extends Producto {
    public bebida(String nombre, double precio) {
        super(nombre, precio);
    }

    @Override
    public String getTipo() {
        return "Bebida";
    }
}
