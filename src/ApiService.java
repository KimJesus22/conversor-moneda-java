import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ApiService {

    private static final String API_KEY = "1970fb8ef9a2e843696eb749";
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/";

    public static double obtenerTasa(String base, String destino) throws Exception {
        String url = BASE_URL + API_KEY + "/latest/" + base;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        JsonObject json = JsonParser.parseString(response.body()).getAsJsonObject();
        JsonObject conversionRates = json.getAsJsonObject("conversion_rates");

        return conversionRates.get(destino).getAsDouble();
    }

    public static void main(String[] args) throws Exception {
        // Prueba convertir de USD a MXN
        double tasa = obtenerTasa("USD", "MXN");
        System.out.println("1 USD = " + tasa + " MXN");
    }
}
