/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cafeteria;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.Locale;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;


public class VentanaCafeteria extends JFrame {
    private final JTextField totalField = new JTextField(18);
    private final JTextArea salida = new JTextArea(10, 42);
    private final JButton calcularButton = new JButton("Calcular descuento");
    private final CalculadoreDeDescuento calculadora = new CalculadoreDeDescuento();
    private final Pedido pedidoDAO = new Pedido();
    private final NumberFormat formato =
            NumberFormat.getCurrencyInstance(new Locale("es", "CO"));

    public VentanaCafeteria() {
        configurarVentana();
        construirInterfaz();
        comprobarConexion();
    }

    private void configurarVentana() {
        setTitle("Sistema de descuentos - Cafeteria");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(620, 440);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void construirInterfaz() {
        JPanel principal = new JPanel(new BorderLayout(12, 12));
        principal.setBorder(BorderFactory.createEmptyBorder(20, 24, 20, 24));

        JLabel titulo = new JLabel("Gestion de descuentos de pedidos");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 20));
        principal.add(titulo, BorderLayout.NORTH);

        JPanel formulario = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(8, 5, 8, 5);
        c.anchor = GridBagConstraints.WEST;

        c.gridx = 0;
        c.gridy = 0;
        formulario.add(new JLabel("Monto total del pedido:"), c);

        c.gridx = 1;
        formulario.add(totalField, c);

        c.gridx = 1;
        c.gridy = 1;
        c.anchor = GridBagConstraints.EAST;
        formulario.add(calcularButton, c);

        salida.setEditable(false);
        salida.setLineWrap(true);
        salida.setWrapStyleWord(true);
        salida.setFont(new Font("Monospaced", Font.PLAIN, 12));

        JPanel centro = new JPanel(new BorderLayout(8, 8));
        centro.add(formulario, BorderLayout.NORTH);
        centro.add(new JScrollPane(salida), BorderLayout.CENTER);
        principal.add(centro, BorderLayout.CENTER);

        calcularButton.addActionListener(evento -> calcularYGuardar());
        totalField.addActionListener(evento -> calcularYGuardar());

        setContentPane(principal);
    }

    private void comprobarConexion() {
        try {
            Cafeteria.inicializar();
            salida.append("Conexion con H2 establecida correctamente.\n");
            salida.append("La tabla de pedidos esta lista.");
        } catch (SQLException ex) {
            salida.append("ERROR DE CONEXION CON H2:");
            salida.append(ex.getMessage() + "\n\n");
        }
    }

    private void calcularYGuardar() {
        String texto = totalField.getText().trim().replace(",", ".");
        if (texto.isEmpty()) {
            salida.append("Ingrese el monto total del pedido.");
            return;
        }

        try {
            double subtotal = Double.parseDouble(texto);
            if (!Double.isFinite(subtotal) || subtotal < 0) {
                salida.append("Ingrese un monto numerico valido");
                return;
            }

            double descuento = calculadora.calcularDescuento(subtotal);
            double totalFinal = calculadora.calcularTotalFinal(subtotal);

            salida.append("Resultado del pedido");
            salida.append("Subtotal: " + formato.format(subtotal));
            salida.append("Descuento: " + formato.format(descuento));
            salida.append("Total a pagar: " + formato.format(totalFinal));

            try {
                pedidoDAO.guardar(subtotal, descuento, totalFinal);
                salida.append("Pedido guardado correctamente en H2.");
            } catch (SQLException ex) {
                salida.append("No se pudo guardar el pedido: "
                        + ex.getMessage());
            }
        } catch (NumberFormatException ex) {
            salida.append("Monto invalido. Escriba un numero, por ejemplo 15000.");
        } catch (IllegalArgumentException ex) {
            salida.append(ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaCafeteria().setVisible(true));
    }
}
