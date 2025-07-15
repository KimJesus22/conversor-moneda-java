import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UtilFechas {
    public static String obtenerMarcaTiempo() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return LocalDateTime.now().format(formatter);
    }
}
