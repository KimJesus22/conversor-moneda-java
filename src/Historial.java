import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Historial {
    private static final String ARCHIVO = "historial.txt";
    private static final List<String> registros = new ArrayList<>();

    // Cargar historial desde archivo
    public static void cargarHistorial() {
        File file = new File(ARCHIVO);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                registros.add(linea);
            }
        } catch (IOException e) {
            System.out.println("⚠ No se pudo cargar el historial: " + e.getMessage());
        }
    }

    // Agregar registro y guardarlo en archivo
    public static void agregarRegistro(String registro) {
        registros.add(registro);
        guardarEnArchivo(registro);
    }

    private static void guardarEnArchivo(String registro) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARCHIVO, true))) {
            writer.write(registro);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("⚠ Error al guardar historial: " + e.getMessage());
        }
    }

    // ✅ NUEVO: permite obtener la lista de registros
    public static List<String> getRegistros() {
        return registros;
    }

    public static void mostrarHistorial() {
        System.out.println("\n=== Historial de Conversiones ===");
        if (registros.isEmpty()) {
            System.out.println("No hay conversiones registradas aún.");
        } else {
            registros.forEach(System.out::println);
        }
        System.out.println("--------------------------------\n");
    }
}



