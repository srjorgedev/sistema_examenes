import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Estudiante {
    public static int mostrarMenu(Scanner scan) {
        int opcionEstudiante = 0;
//F
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

    public static int participarExamen(Scanner scan, String[] usuario, PrintWriter out, BufferedReader in)
            throws IOException {

        int examenIndice = 0;

        Interfaz.imprimirTitulo("Participar en examen");

        String respuesta = FuncionesServidor.obtenerDelServidor(out, in, "OBTENER_EXAMENES");
        String[] filas = respuesta.split("\n");

        String[][] examenMatriz = new String[filas.length][];
        for (int i = 0; i < filas.length; i++) {
            examenMatriz[i] = filas[i].split(";");
        }

        for (int i = 0; i < examenMatriz.length; i++) {
            String[] examenInfo = examenMatriz[i][0].split("\\.-\\.");

            String tipo = "";
            if (examenInfo[3].toUpperCase().equals("O"))
                tipo = "Ordinario";
            if (examenInfo[3].toUpperCase().equals("R"))
                tipo = "Remedial";
            if (examenInfo[3].toUpperCase().equals("E"))
                tipo = "Extraordinario";

            Interfaz.imprimirTextoLineaSalto("Examen: " + examenInfo[0] + " - " + tipo);
            Interfaz.imprimirTextoLineaSalto("Docente: " + examenInfo[5]);
            Interfaz.imprimirTextoLineaSalto(examenInfo[1] + " - " + examenInfo[4]);

            Interfaz.imprimirLineaConexion();
        }

        Interfaz.imprimirBordeIzqDer();
        Interfaz.imprimirTextoLineaSalto("Acciones disponibles: ");
        Interfaz.imprimirTextoLineaSalto("1. Participar");
        Interfaz.imprimirTextoLineaSalto("2. Volver al panel de estudiante");
        Interfaz.imprimirTextoLineaSalto("3. Volver al inicio");
        Interfaz.imprimirLineaInfIzqDer();

        System.out.print("  Ingrese su opcion: ");
        int opcionParticiparExamen = scan.nextInt();
        scan.nextLine();

        System.out.println();

        String idExamen = "";
        if (opcionParticiparExamen == 1) {
            Interfaz.imprimirTitulo("Seleccionar examen");
            Interfaz.imprimirBordeIzqDer();
            do {
                System.out.print("    ID: ");
                idExamen = scan.nextLine();
            } while (!idExamen.matches("^EX\\d{2}$"));

            for (int i = 0; i < examenMatriz.length; i++) {
                if (examenMatriz[i][0].equals(idExamen))
                    examenIndice = i;
            }
            Interfaz.imprimirLineaInfIzqDer();

            responderExamen(scan, examenMatriz[examenIndice], usuario, out, in);
        }

        return opcionParticiparExamen;
    }

    public static void responderExamen(Scanner scan, String[] examen, String[] usuario, PrintWriter out,
            BufferedReader in)
            throws IOException {
        String regex = "\\.-\\.";

        String[] examenInfo = examen[0].split(regex);
        String[] examenPreguntas = examen[1].split(regex);
        String[] examenReactivos = examen[2].split(regex);
        String[] examenRespuestas = examen[3].split(regex);

        String tipo = "";
        if (examenInfo[3].toUpperCase().equals("O"))
            tipo = "Ordinario";
        if (examenInfo[3].toUpperCase().equals("R"))
            tipo = "Remedial";
        if (examenInfo[3].toUpperCase().equals("E"))
            tipo = "Extraordinario";

        Interfaz.imprimirTitulo("Responder examen");
        Interfaz.imprimirBordeIzqDer();

        Interfaz.imprimirTextoLineaSalto("Examen: " + examenInfo[0] + " - " + tipo);
        Interfaz.imprimirTextoLineaSalto("Creado por: " + examenInfo[5]);
        Interfaz.imprimirTextoLineaSalto(examenInfo[1] + " - " + examenInfo[4]);

        Interfaz.imprimirBordeIzqDer();
        Interfaz.imprimirTextoLineaSalto("Preguntas");
        Interfaz.imprimirBordeIzqDer();

        String[] respuestas = new String[examenPreguntas.length];

        for (int i = 1; i < examenPreguntas.length; i++) {
            Interfaz.imprimirTextoLineaSalto((i + 1) + " -> " + examenPreguntas[i]);
            Interfaz.imprimirTextoLineaSalto(examenReactivos[i]);
            Interfaz.imprimirBordeIzqDer();

            if (examenPreguntas[i].contains("o -")) {
                System.out.print("    Respuesta: ");
                respuestas[i] = scan.nextLine();
            }
            if (examenPreguntas[i].contains("s -")) {
                while (true) {
                    System.out.print("    Respuestas (Dejar en blanco para parar): ");
                    String respuesta = scan.nextLine();
                    if (respuesta.isEmpty())
                        break;
                    respuestas[i] += respuesta;
                }
            }
        }

        String historial = usuario[0] + ".-." + examenInfo[0];
        String responderExamen = FuncionesServidor.subirAlServidor(out, in, "REGISTRO_HISTORIAL", historial);

        Interfaz.imprimirLineaConexion();
        Interfaz.imprimirTextoLineaSalto("Examen contestado");
        Interfaz.imprimirLineaConexion();

        Interfaz.imprimirTextoLineaSalto("Acciones disponibles: ");
        Interfaz.imprimirBordeIzqDer();

        Interfaz.imprimirTextoLineaSalto("1. Volver a la seleccion de examen");
        Interfaz.imprimirTextoLineaSalto("1. Volver al panel de estudiantes");
        Interfaz.imprimirTextoLineaSalto("1. Volver al inicio");

        Interfaz.imprimirLineaInfIzqDer();

        System.out.print("  Ingrese su opcion: ");
        int opcionResponderExamen = scan.nextInt();
        scan.nextLine();

        System.out.println();
    }

    public static void verHistorial(Scanner scan, PrintWriter out, BufferedReader in) throws IOException {
        String historial = FuncionesServidor.obtenerDelServidor(out, in, "OBTENER_HISTORIAL");
        String[] historialUsuariosArray = historial.split("\n");
    }
}
