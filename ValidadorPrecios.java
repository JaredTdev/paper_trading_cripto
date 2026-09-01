import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ValidadorPrecios {
    public static double obtenerPreciosEnVivo(String parCripto) {
        String url = "https://binance.com" + parCripto;
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

    public static void main(String[] args) {
        System.out.println("Consultando precios en tiempo real...");

        // Prueba con Bitcoin y Ethereum
        double precioBTC = obtenerPreciosEnVivo("BTCUSDT");
        double precioETH = obtenerPreciosEnVivo("ETHUSDT");

        System.out.printf("Precio actual de BTC: $%.2f USD\n", precioBTC);
        System.out.printf("Precio actual de ETH: $%.2f USD\n", precioETH);
    }
}
