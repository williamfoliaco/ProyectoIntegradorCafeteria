/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cafeteria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Acceso a datos de los pedidos.
 */
public class Pedido {
    public void guardar(double subtotal, double descuento, double totalFinal)
            throws SQLException {
        String sql = "INSERT INTO pedidos (subtotal, descuento, total_final) "
                + "VALUES (?, ?, ?)";

        try (Connection conexion = Cafeteria.conectar();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {
            sentencia.setDouble(1, subtotal);
            sentencia.setDouble(2, descuento);
            sentencia.setDouble(3, totalFinal);
            sentencia.executeUpdate();
        }
    }
}
