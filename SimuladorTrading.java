import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

public class SimuladorTrading {
     
    // Configuracion de la billetera virtual
    private double saldoUSD = 1000.0;
    private Map<String, Double> portafolio = new HashMap<>();

    // Conexion HTTP nativa con Binance
    public static double obtenerPreciosEnVivo(String parCripto) {
        String url = "https://api.binance.com/api/v1/ticker/price?symbol=" + parCripto;
        try {
            // 1. Crear el cliente HTTP y la solicitud
            HttpClient cliente = HttpClient.newHttpClient();
            HttpRequest solicitud = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            // 2. Enviar la solicitud y recibir la respuesta en texto JSON
            HttpResponse<String> respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());
            String json = respuesta.body();

            // Ejemplo de lo que responde Binance: {"symbol":"BTCUSDT","price":"63250.42000000"}
            // 3. Extracción rápida del precio sin usar librerías externas
            String buscarPrecio = "\"price\":\"";
            int inicio = json.indexOf(buscarPrecio) + buscarPrecio.length();
            int fin = json.indexOf("\"", inicio);

            String precioTexto = json.substring(inicio, fin);

            // Convierte el texto a numero decimal
            return Double.parseDouble(precioTexto);

        } catch (Exception e) {
            System.out.println("!!Error al conectar con Binance: " + e.getMessage());
            return 0.0; // Retorna si falla la conexion
        }
    }
}
