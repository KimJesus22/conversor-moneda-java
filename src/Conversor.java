import java.util.Scanner;

public class Conversor {

    public static void mostrarMenuPrincipal() {
        System.out.println("=== Bienvenido/a al Conversor de Moneda ===");
        System.out.println("1. Realizar conversión");
        System.out.println("2. Ver historial");
        System.out.println("0. Salir");
        System.out.print("Elija una opción: ");
    }

    public static void mostrarMenuMonedas() {
        System.out.println("\nOpciones de monedas disponibles:");
        System.out.println("1. ARS - Peso argentino");
        System.out.println("2. BOB - Boliviano");
        System.out.println("3. BRL - Real brasileño");
        System.out.println("4. CLP - Peso chileno");
        System.out.println("5. COP - Peso colombiano");
        System.out.println("6. USD - Dólar estadounidense");
        System.out.print("Elija una opción: ");
    }

    public static String opcionAMoneda(int opcion) {
        switch(opcion) {
            case 1: return "ARS";
            case 2: return "BOB";
            case 3: return "BRL";
            case 4: return "CLP";
            case 5: return "COP";
            case 6: return "USD";
            default: return null;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ApiResponse apiResponse;

        try {
            apiResponse = ApiService.obtenerApiResponse("USD");
        } catch (Exception e) {
            System.out.println("Error al obtener datos de la API: " + e.getMessage());
            scanner.close();
            return;
        }

        boolean salir = false;
        while (!salir) {
            mostrarMenuPrincipal();
            int opcionMenu = scanner.nextInt();

            switch(opcionMenu) {
                case 1: // Realizar conversión
                    mostrarMenuMonedas();
                    int opcionOrigen = scanner.nextInt();
                    String monedaOrigen = opcionAMoneda(opcionOrigen);

                    mostrarMenuMonedas();
                    int opcionDestino = scanner.nextInt();
                    String monedaDestino = opcionAMoneda(opcionDestino);

                    if (monedaOrigen == null || monedaDestino == null) {
                        System.out.println("Opción inválida, intenta de nuevo.");
                        break;
                    }

                    System.out.print("Ingrese la cantidad a convertir: ");
                    double cantidad = scanner.nextDouble();

                    Double tasaOrigen = apiResponse.getConversion_rates().get(monedaOrigen);
                    Double tasaDestino = apiResponse.getConversion_rates().get(monedaDestino);

                    if (tasaOrigen == null || tasaDestino == null) {
                        System.out.println("Moneda no disponible en la API.");
                        break;
                    }

                    double resultado = ApiService.convertirMoneda(cantidad, tasaOrigen, tasaDestino);
                    System.out.printf("%.2f %s equivalen a %.2f %s%n", cantidad, monedaOrigen, resultado, monedaDestino);

                    // Guardar en historial con fecha y hora
                    String registro = String.format("[%s] %.2f %s -> %.2f %s",
                            UtilFechas.obtenerMarcaTiempo(),
                            cantidad,
                            monedaOrigen,
                            resultado,
                            monedaDestino
                    );
                    Historial.agregarRegistro(registro);

                    System.out.println("------------------------------------------");
                    break;

                case 2: // Ver historial
                    Historial.mostrarHistorial();
                    break;

                case 0: // Salir
                    salir = true;
                    break;

                default:
                    System.out.println("Opción no válida, intenta de nuevo.");
            }
        }

        System.out.println("Gracias por usar el conversor. ¡Hasta luego!");
        scanner.close();
    }
}

