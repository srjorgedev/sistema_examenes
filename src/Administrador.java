public class Administrador {
    public static void imprimirMenuAdministrar() {
        System.out.println();
        Interfaz.imprimirLineaSupIzqDer();
        Interfaz.imprimirTextoLineaSalto("Panel de Administrador");
        Interfaz.imprimirLineaConexion();
        Interfaz.imprimirTextoLineaSalto("Acciones disponibles:");
        Interfaz.imprimirBordeIzqDer();
        Interfaz.imprimirTextoLineaSalto("1. Crear usuarios");
        Interfaz.imprimirTextoLineaSalto("2. Administrar usuarios");
        Interfaz.imprimirBordeIzqDer();
        Interfaz.imprimirTextoLineaSalto("3. Volver al inicio");

        Interfaz.imprimirBordeIzqDer();
        Interfaz.imprimirLineaInfIzqDer();
    }
}
