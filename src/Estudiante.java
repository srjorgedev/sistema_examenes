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
}
