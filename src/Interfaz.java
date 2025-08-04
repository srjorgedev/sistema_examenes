public class Interfaz {
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_RESET = "\u001B[0m";
    static int ancho = 60;

    public static void imprimirTitulo(String titulo) {
        imprimirLineaSupIzqDer();
        imprimirTextoLineaSalto(titulo);
        imprimirLineaConexion();
    }

    public static void imprimirLineaSupIzqDer() {
        System.out.print(ANSI_BLUE + "  \u250C");
        for (int i = 0; i < ancho; i++) {
            System.out.print("\u2500");
        }
        System.out.print("\u2510\n" + ANSI_RESET);
    }

    public static void imprimirLineaInfIzqDer() {
        System.out.print(ANSI_BLUE + "  \u2514");
        for (int i = 0; i < ancho; i++) {
            System.out.print("\u2500");
        }
        System.out.print("\u2518\n" + ANSI_RESET);
    }

    public static void imprimirLineaConexion() {
        System.out.print(ANSI_BLUE + "  \u251C");
        for (int i = 0; i < ancho; i++) {
            System.out.print("\u2500");
        }
        System.out.print("\u2524\n" + ANSI_RESET);
    }

    public static void imprimirBordeIzqDer() {
        System.out.print(ANSI_BLUE + "  \u2502");
        for (int i = 0; i < ancho; i++) {
            System.out.print(" ");
        }
        System.out.print("\u2502\n" + ANSI_RESET);
    }

    public static void imprimirTextoLineaSalto(String texto) {
        System.out.print(ANSI_BLUE + "  \u2502 " + texto + " ".repeat(ancho - texto.length() - 1) + "\u2502\n" + ANSI_RESET);
    }

    public static void imprimirTextoLinea(String texto) {
        System.out.print(ANSI_BLUE + "  \u2502 " + texto + " ".repeat(ancho - texto.length() - 1) + "\u2502" + ANSI_RESET);
    }
}
