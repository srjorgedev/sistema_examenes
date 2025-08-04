public class Interfaz {
    static int ancho = 60;

    public static void imprimirLineaSupIzqDer() {
        System.out.print("  \u250C");

        for (int i = 0; i < ancho; i++) {
            System.out.print("\u2500");
        }

        System.out.print("\u2510\n");
    }

    public static void imprimirLineaInfIzqDer() {
        System.out.print("  \u2514");

        for (int i = 0; i < ancho; i++) {
            System.out.print("\u2500");
        }

        System.out.print("\u2518\n");
    }

    public static void imprimirLineaConexion() {
        System.out.print("  \u251C");

        for (int i = 0; i < ancho; i++) {
            System.out.print("\u2500");
        }

        System.out.print("\u2524\n");
    }

    public static void imprimirBordeIzqDer() {
        System.out.print("  \u2502");

        for (int i = 0; i < ancho; i++) {
            System.out.print(" ");
        }

        System.out.print("\u2502\n");
    }

    public static void imprimirTextoLineaSalto(String texto) {
        System.out.print("  \u2502 " + texto + " ".repeat(ancho - texto.length() - 1) + "\u2502\n");
    }

    public static void imprimirTextoLinea(String texto) {
        System.out.print("  \u2502 " + texto + " ".repeat(ancho - texto.length() - 1) + "\u2502");
    }
}
