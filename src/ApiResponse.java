import java.util.Map;

public class ApiResponse {
    private String result;
    private Map<String, Double> conversion_rates;

    public String getResult() {
        return result;
    }

    public Map<String, Double> getConversion_rates() {
        return conversion_rates;
    }
}
