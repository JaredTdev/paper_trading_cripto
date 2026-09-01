public class ValidadorPrecios {
    public static double obtenerPreciosEnVivo(String parCripto) {
        String url = "https://binance.com" + parCripto;
        return 0.0;
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
