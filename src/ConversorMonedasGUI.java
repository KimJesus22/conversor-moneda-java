import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConversorMonedasGUI extends JFrame {

    private JTextField txtCantidad, txtMonedaOrigen, txtMonedaDestino;
    private JTextArea txtResultado;
    private ApiResponse apiResponse;

    public ConversorMonedasGUI() {
        setTitle("Conversor de Monedas");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel superior para ingresar datos
        JPanel panelDatos = new JPanel(new GridLayout(4, 2));
        panelDatos.add(new JLabel("Cantidad:"));
        txtCantidad = new JTextField();
        panelDatos.add(txtCantidad);

        panelDatos.add(new JLabel("Moneda origen (USD, EUR, etc.):"));
        txtMonedaOrigen = new JTextField();
        panelDatos.add(txtMonedaOrigen);

        panelDatos.add(new JLabel("Moneda destino (ARS, BRL, etc.):"));
        txtMonedaDestino = new JTextField();
        panelDatos.add(txtMonedaDestino);

        JButton btnConvertir = new JButton("Convertir");
        panelDatos.add(btnConvertir);

        add(panelDatos, BorderLayout.NORTH);

        // Area para mostrar resultado e historial
        txtResultado = new JTextArea();
        txtResultado.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(txtResultado);
        add(scrollPane, BorderLayout.CENTER);

        // Intentar cargar API al iniciar
        try {
            apiResponse = ApiService.obtenerApiResponse("USD");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error cargando datos de la API: " + e.getMessage());
        }

        // Acción del botón
        btnConvertir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realizarConversion();
            }
        });

        // Cargar historial existente
        Historial.cargarHistorial();
        mostrarHistorial();
    }

    private void realizarConversion() {
        if (apiResponse == null) {
            JOptionPane.showMessageDialog(this, "No se pudo cargar las tasas de cambio.");
            return;
        }

        try {
            double cantidad = Double.parseDouble(txtCantidad.getText());
            String monedaOrigen = txtMonedaOrigen.getText().toUpperCase();
            String monedaDestino = txtMonedaDestino.getText().toUpperCase();

            Double tasaOrigen = apiResponse.getConversion_rates().get(monedaOrigen);
            Double tasaDestino = apiResponse.getConversion_rates().get(monedaDestino);

            if (tasaOrigen == null || tasaDestino == null) {
                JOptionPane.showMessageDialog(this, "Moneda no encontrada.");
                return;
            }

            double resultado = ApiService.convertirMoneda(cantidad, tasaOrigen, tasaDestino);

            String registro = String.format("[%s] %.2f %s -> %.2f %s",
                    UtilFechas.obtenerMarcaTiempo(),
                    cantidad,
                    monedaOrigen,
                    resultado,
                    monedaDestino);

            // Mostrar resultado y guardar en historial
            txtResultado.append(registro + "\n");
            Historial.agregarRegistro(registro);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Cantidad inválida.");
        }
    }

    private void mostrarHistorial() {
        txtResultado.append("=== Historial de conversiones previas ===\n");
        for (String reg : Historial.getRegistros()) {
            txtResultado.append(reg + "\n");
        }
        txtResultado.append("--------------------------------------\n");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ConversorMonedasGUI().setVisible(true);
        });
    }
}
