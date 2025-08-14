import java.util.Scanner;

public class Estudiante {
    public static int mostrarMenu(Scanner scan) {
        int opcionEstudiante = 0;

        while (opcionEstudiante == 0 || !(opcionEstudiante >= 1 && opcionEstudiante <= 5)) {
            Interfaz.imprimirTitulo("Panel de Estudiante");
            Interfaz.imprimirBordeIzqDer();

            Interfaz.imprimirTextoLineaSalto("Acciones disponibles:");
            Interfaz.imprimirBordeIzqDer();
            Interfaz.imprimirTextoLineaSalto("1. Administrar perfil");
            Interfaz.imprimirTextoLineaSalto("2. Participar en examen");
            Interfaz.imprimirTextoLineaSalto("3. Ver resultados");
            Interfaz.imprimirTextoLineaSalto("4. Historial de examenes");

            Interfaz.imprimirBordeIzqDer();

            Interfaz.imprimirTextoLineaSalto("5. Volver al inicio");
            Interfaz.imprimirLineaInfIzqDer();

            System.out.print("  Ingrese su opcion: ");
            opcionEstudiante = scan.nextInt();
            scan.nextLine();

            System.out.println();
        }

        return opcionEstudiante;
    }

    public static int administrarPerfil(Scanner scan) {
        int opcionAdministrarPerfil = 0;

        while (opcionAdministrarPerfil == 0 || !(opcionAdministrarPerfil >= 1 && opcionAdministrarPerfil <= 3)) {
            Interfaz.imprimirTitulo("Administrar Perfil");

            Interfaz.imprimirBordeIzqDer();
            Interfaz.imprimirTextoLineaSalto("Acciones disponibles: ");
            Interfaz.imprimirTextoLineaSalto("1. Modificar datos");
            Interfaz.imprimirTextoLineaSalto("2. Volver al panel de estudiante");
            Interfaz.imprimirTextoLineaSalto("3. Volver al inicio");
            Interfaz.imprimirLineaInfIzqDer();

            System.out.print("  Ingrese su opcion: ");
            opcionAdministrarPerfil = scan.nextInt();

            System.out.println();
        }

        return opcionAdministrarPerfil;
    }

    public static void participarExamen(String[][] examen, int[] indices) {
                        
    }
}
