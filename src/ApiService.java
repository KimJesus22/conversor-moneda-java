import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import java.time.Duration;
import com.google.gson.Gson;

public class ApiService {

    private static final String API_KEY = "1970fb8ef9a2e843696eb749";
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/";

    public static ApiResponse obtenerApiResponse(String base) throws Exception {
        String url = BASE_URL + API_KEY + "/latest/" + base;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Accept", "application/json")
                .timeout(Duration.ofSeconds(5))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            Gson gson = new Gson();
            // Usa la clase externa ApiResponse
            ApiResponse apiResponse = gson.fromJson(response.body(), ApiResponse.class);

            if (!"success".equalsIgnoreCase(apiResponse.getResult())) {
                throw new RuntimeException("Error en la respuesta API");
            }

            return apiResponse;
        } else {
            throw new RuntimeException("Error HTTP: " + response.statusCode());
        }
    }

    public static double convertirMoneda(double cantidad, double tasaOrigen, double tasaDestino) {
        return cantidad * (tasaDestino / tasaOrigen);
    }
}






