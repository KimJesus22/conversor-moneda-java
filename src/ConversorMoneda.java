import java.util.Scanner;

public class ConversorMoneda {

    private static final String[] MONEDAS = {"USD", "EUR", "MXN", "BRL"};

    public static void mostrarMenu() {
        System.out.println("=== Conversor de Monedas ===");
        System.out.println("Monedas disponibles:");
        for (String moneda : MONEDAS) {
            System.out.println("- " + moneda);
        }
        System.out.println();
    }

    public static boolean esMonedaValida(String moneda) {
        for (String m : MONEDAS) {
            if (m.equalsIgnoreCase(moneda)) {
                return true;
            }
        }
        return false;
    }

    public static void iniciarConversor() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            mostrarMenu();

            System.out.print("Ingresa la moneda base (o 'salir' para terminar): ");
            String base = scanner.nextLine().toUpperCase();
            if (base.equalsIgnoreCase("salir")) {
                System.out.println("¡Gracias por usar el conversor!");
                break;
            }

            if (!esMonedaValida(base)) {
                System.out.println("Moneda base no válida. Intenta de nuevo.");
                continue;
            }

            System.out.print("Ingresa la moneda destino: ");
            String destino = scanner.nextLine().toUpperCase();

            if (!esMonedaValida(destino)) {
                System.out.println("Moneda destino no válida. Intenta de nuevo.");
                continue;
            }

            System.out.print("Ingresa la cantidad a convertir: ");
            double cantidad = 0;
            try {
                cantidad = Double.parseDouble(scanner.nextLine());
                if (cantidad <= 0) {
                    System.out.println("Cantidad debe ser mayor a 0. Intenta de nuevo.");
                    continue;
                }
            } catch (NumberFormatException e) {
                System.out.println("Cantidad inválida. Intenta de nuevo.");
                continue;
            }

            try {
                double tasa = ApiService.obtenerTasa(base, destino);
                double resultado = cantidad * tasa;
                System.out.printf("%.2f %s son %.2f %s\n\n", cantidad, base, resultado, destino);
            } catch (Exception e) {
                System.out.println("Error al obtener la tasa de conversión. Intenta más tarde.");
            }
        }

        scanner.close();
    }

    public static void main(String[] args) {
        iniciarConversor();
    }
}
