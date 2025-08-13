import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Docente {
    public static int mostrarPanel(Scanner scan) {
        int opcionDocente = 0;

        while (opcionDocente == 0 || !(opcionDocente == 1 || opcionDocente == 2 || opcionDocente == 3)) {
            Interfaz.imprimirTitulo("Panel Docente");
            Interfaz.imprimirTextoLineaSalto("1. Crear examen");
            Interfaz.imprimirTextoLineaSalto("2. Ver examenes");
            Interfaz.imprimirTextoLineaSalto("3. Agregar curso");
            Interfaz.imprimirTextoLineaSalto("4. Agregar tema");
            Interfaz.imprimirBordeIzqDer();
            Interfaz.imprimirTextoLineaSalto("5. Volver al inicio");
            Interfaz.imprimirLineaInfIzqDer();

            System.out.print("  Seleccionar opcion: ");
            opcionDocente = scan.nextInt();
            scan.nextLine();

            System.out.println();
        }

        return opcionDocente;
    }

    public static int crearExamen(Scanner scan, String[][] examen, String[] usuario, int[] indice) {
        String examenInformacion = "";
        String examenPreguntas = "";
        String examenRespuestas = "";
        String examenReactivos = "";
        String fecha;

        char[] reactivos = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n' };

        int opcionCrearExamen = 0;

        Interfaz.imprimirTitulo("Crear Examen");
        String examenID = "EX0" + indice[2];

        System.out.print("    Nombre del examen: ");
        String nombre = scan.nextLine();

        do {
            System.out.print("    Fecha (ej: 29/7/25): ");
            fecha = scan.nextLine();
        } while (!fecha.matches("^\\d{1,2}/\\d{1,2}/\\d{2}$"));

        Interfaz.imprimirTextoLineaSalto("Tipos -> (O)rdinario (R)emedial (E)xtra");
        System.out.print("    Tipo: ");
        String tipo = scan.nextLine();

        System.out.print("    Materia: ");
        String materia = scan.nextLine();

        System.out.println("    Nombre del docente: " + usuario[1]);

        examenInformacion = examenID + ".-." + nombre + ".-." + fecha + ".-." + tipo.toUpperCase()
                + ".-." + materia + ".-." + usuario[1] + ".-." + usuario[0];
        examenPreguntas += examenID;
        examenReactivos += examenID;
        examenRespuestas += examenID;

        int preguntaIndice = 1;

        Interfaz.imprimirLineaConexion();

        Interfaz.imprimirTextoLineaSalto("Nota: Para dejar de agregar preguntas,");
        Interfaz.imprimirTextoLineaSalto("deje un reactivo en blanco");
        Interfaz.imprimirLineaConexion();

        Interfaz.imprimirTextoLineaSalto("Ingresar preguntas");
        Interfaz.imprimirBordeIzqDer();

        while (true) {
            if (preguntaIndice > 1)
                Interfaz.imprimirLineaSupIzqDer();

            System.out.print("    Pregunta " + preguntaIndice + ": ");
            String pregunta = scan.nextLine();
            Interfaz.imprimirBordeIzqDer();

            if (pregunta.isEmpty())
                break;

            Interfaz.imprimirTextoLineaSalto("Tipos -> (O)pcion multiple (S)eleccion multiple");
            System.out.print("    Tipo de pregunta: ");
            String tipoExamen = scan.nextLine();

            examenPreguntas += ".-." + tipoExamen + "-" + pregunta;

            Interfaz.imprimirLineaConexion();

            Interfaz.imprimirTextoLineaSalto("Nota: Para dejar de agregar respuestas,");
            Interfaz.imprimirTextoLineaSalto("deje un reactivo en blanco");
            Interfaz.imprimirBordeIzqDer();

            Interfaz.imprimirTextoLineaSalto("Ingrese respuestas:");

            int indiceReactivo = 0;

            while (true) {
                System.out.print("    " + reactivos[indiceReactivo] + ") ");
                String respuesta = scan.nextLine();

                if (respuesta.isEmpty())
                    break;

                examenReactivos += ".-." + reactivos[indiceReactivo] + ")" + respuesta;
                indiceReactivo++;
            }

            String reactivosDisponibles = "";
            for (int i = 0; i < indiceReactivo; i++) {
                reactivosDisponibles += "(" + reactivos[i] + ") ";
            }

            Interfaz.imprimirLineaConexion();

            Interfaz.imprimirTextoLineaSalto("Seleccione la(s) respuesta(s)");

            Interfaz.imprimirTextoLineaSalto("Respuestas disponibles");
            Interfaz.imprimirTextoLineaSalto(reactivosDisponibles);

            Interfaz.imprimirBordeIzqDer();

            if (tipoExamen.toUpperCase().equals("O")) {
                String respuestasCorrectas = "";

                System.out.print("    Respuesta correcta: ");
                String respuesta = scan.nextLine();

                respuestasCorrectas += respuesta;
                examenRespuestas = examenID + ".-. " + respuesta + ".-.";
            }

            if (tipoExamen.toUpperCase().equals("S")) {
                String respuestasCorrectas = "";

                Interfaz.imprimirTextoLineaSalto("Nota: Para dejar de agregar respuestas");
                Interfaz.imprimirTextoLineaSalto("deje el texto vacio.");
                for (int i = 1; i <= indiceReactivo; i++) {
                    System.out.print("    Respuesta correcta (" + i + "): ");
                    String respuesta = scan.nextLine();

                    if (respuesta.isEmpty())
                        break;

                    respuestasCorrectas += respuesta;

                }
                examenRespuestas = examenID + ".-. " + respuestasCorrectas + ".-.";
            }

            Interfaz.imprimirLineaConexion();
            Interfaz.imprimirTextoLineaSalto("Pregunta creada.");
            Interfaz.imprimirLineaConexion();

            Interfaz.imprimirTextoLineaSalto("1. Agregar otra pregunta.");
            Interfaz.imprimirTextoLineaSalto("2. Guardar el examen y salir");
            Interfaz.imprimirLineaInfIzqDer();

            System.out.print("  Ingrese su opcion: ");
            opcionCrearExamen = scan.nextInt();
            scan.nextLine();

            System.out.println();

            if (opcionCrearExamen == 2)
                break;

            preguntaIndice++;
        }

        Array.agregarExamen(examen, new String[] {
                examenInformacion,
                examenPreguntas,
                examenReactivos,
                examenRespuestas
        }, indice);

        System.out.println(examen[indice[2] - 1][0]);
        System.out.println(examen[indice[2] - 1][1]);
        System.out.println(examen[indice[2] - 1][2]);
        System.out.println(examen[indice[2] - 1][3]);

        try (
                Socket socket = new Socket("localhost", 5000);
                PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader entrada = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()))) {
            salida.println("REGISTRO_EXAMENES");
            for (int i = 0; i < indice[2]; i++) {
                if (!examen[i][0].isEmpty()) {
                    salida.println(examen[i][0]);
                    salida.println(examen[i][1]);
                    salida.println(examen[i][2]);
                    salida.println(examen[i][3]);
                    salida.println("---");
                }
            }

            salida.println("");
            String respuesta;
            while ((respuesta = entrada.readLine()) != null) {
                System.out.println(respuesta);
                if (respuesta.contains("Examen guardado correctamente")) {
                    break;
                }
            }

        } catch (Exception e) {
            System.out.println("Error al conectar con el servidor: " + e.getMessage());
        }

        // OUTPUT
        // EX00.-.Prueba.-.29/07/27.-.O.-.Materia.-.admin.-.0000
        // EX00.-.o-Pregunta 1.-.s-Pregunta 2
        // EX00.-.a)A b)B c)C d)D.-.a)A b)V c)B d)C e)D.-.
        // EX00.-.a.-.abc.-.

        Interfaz.imprimirTextoLineaSalto("Examen creado.");

        Interfaz.imprimirLineaConexion();
        Interfaz.imprimirTextoLineaSalto("Acciones disponibles");

        Interfaz.imprimirTextoLineaSalto("1. Crear otro examen");
        Interfaz.imprimirTextoLineaSalto("2. Volver al panel docente");
        Interfaz.imprimirTextoLineaSalto("3. Volver al inicio");

        Interfaz.imprimirLineaInfIzqDer();

        System.out.print("  Ingrese su opcion: ");
        opcionCrearExamen = scan.nextInt();
        scan.nextLine();

        System.out.println();

        return opcionCrearExamen;
    }
}
