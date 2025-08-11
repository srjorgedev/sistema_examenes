public class Interfaz {
    static int ancho = 60;

    public static void imprimirTitulo(String titulo) {
        imprimirLineaSupIzqDer();
        imprimirTextoLineaSalto(titulo);
        imprimirLineaConexion();
    }

    public static void imprimirLineaSupIzqDer() {
        System.out.print("  \u250C" + "\u2500".repeat(ancho) + "\u2510\n");
    }

    public static void imprimirLineaInfIzqDer() {
        System.out.print("  \u2514" + "\u2500".repeat(ancho) + "\u2518\n");
    }

    public static void imprimirLineaConexion() {
        System.out.print("  \u251C" + "\u2500".repeat(ancho) + "\u2524\n");
    }

    public static void imprimirBordeIzqDer() {
        System.out.print("  \u2502" + " ".repeat(ancho) + "\u2502\n");
    }

    public static void imprimirTextoLineaSalto(String texto) {
        System.out.print("  \u2502 " + texto + " ".repeat(ancho - texto.length() - 1) + "\u2502\n");
    }

    public static void imprimirTextoLinea(String texto) {
        System.out.print("  \u2502 " + texto + " ".repeat(ancho - texto.length() - 1) + "\u2502");
    }
}
