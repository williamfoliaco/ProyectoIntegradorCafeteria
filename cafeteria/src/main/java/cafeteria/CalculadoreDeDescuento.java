/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cafeteria;


public class CalculadoreDeDescuento {
    private static final double UMBRAL = 10.0;
    private static final double PORCENTAJE = 0.15;

    private final ReglaDescuento regla;

    public CalculadoreDeDescuento() {
       
        this.regla = total -> total > UMBRAL ? total * PORCENTAJE : 0.0;
    }

    public double calcularDescuento(double total) {
        if (total < 0) {
            throw new IllegalArgumentException("El total no puede ser negativo.");
        }
        return regla.calcularDescuento(total);
    }

    public double calcularTotalFinal(double total) {
        return total - calcularDescuento(total);
    }
}

