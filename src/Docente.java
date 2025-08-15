import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Docente {
    public static int mostrarPanel(Scanner scan) {
        int opcionDocente = 0;

        while (opcionDocente == 0 || !(opcionDocente >= 1 && opcionDocente <= 5)) {
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

    public static int crearExamen(Scanner scan, String[] usuario, PrintWriter out, BufferedReader in)
            throws IOException {
        String examenInformacion = "";
        String examenPreguntas = "";
        String examenRespuestas = "";
        String examenReactivos = "";
        String fecha;

        char[] reactivos = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n' };

        int opcionCrearExamen = 0;

        int examenIndiceActual = Integer
                .parseInt(FuncionesServidor.obtenerDelServidor(out, in, "OBTENER_EXAMEN_INDICE_ACTUAL"));

        Interfaz.imprimirTitulo("Crear Examen");
        String examenID = "EX0" + examenIndiceActual;

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
        examenReactivos += examenID + ".-.";
        examenRespuestas += examenID + ".-.";

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

            examenPreguntas += ".-." + tipoExamen + " - " + pregunta;

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

                examenReactivos += reactivos[indiceReactivo] + ")" + respuesta + " ";
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
                examenRespuestas += respuesta.trim();
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

                    respuestasCorrectas += respuesta.trim();

                }
                examenRespuestas += respuestasCorrectas;
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

            if (opcionCrearExamen == 1) {
                examenReactivos += ".-.";
                examenRespuestas += ".-.";
            }

            if (opcionCrearExamen == 2)
                break;

            preguntaIndice++;
        }

        String crearExamen = FuncionesServidor.subirAlServidor(out, in, "CREAR_EXAMEN", new String[] {
                examenInformacion,
                examenPreguntas,
                examenReactivos,
                examenRespuestas
        });

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

    public static int mostrarExamenes(Scanner scan, String[] usuario, PrintWriter out, BufferedReader in)
            throws IOException {

        int opcionVerExamenes = 0;
        String regex = "\\.-\\.";

        int examenIndiceActual = Integer
                .parseInt(FuncionesServidor.obtenerDelServidor(out, in, "OBTENER_EXAMEN_INDICE_ACTUAL"));

        String respuesta = FuncionesServidor.obtenerDelServidor(out, in, "OBTENER_EXAMENES");
        String[] filas = respuesta.split("\n");

        String[][] examenMatriz = new String[filas.length][];
        for (int i = 0; i < filas.length; i++) {
            examenMatriz[i] = filas[i].split(";");
        }

        Interfaz.imprimirTitulo("Examenes creados");
        Interfaz.imprimirBordeIzqDer();

        for (int i = 0; i < examenIndiceActual; i++) {
            String[] examenInfo = examenMatriz[i][0].split(regex);

            if (!(examenInfo[examenInfo.length - 1].equals(usuario[0])) && !usuario[0].equals("0000"))
                continue;

            String[] examenPreguntas = examenMatriz[i][1].split(regex);
            String[] examenReactivos = examenMatriz[i][2].split(regex);
            String[] examenRespuestas = examenMatriz[i][3].split(regex);

            String tipo = "";
            if (examenInfo[3].toUpperCase().equals("O"))
                tipo = "Ordinario";
            if (examenInfo[3].toUpperCase().equals("R"))
                tipo = "Remedial";
            if (examenInfo[3].toUpperCase().equals("E"))
                tipo = "Extraordinario";

            Interfaz.imprimirTextoLineaSalto("Examen: " + examenInfo[0] + " - " + tipo);
            Interfaz.imprimirTextoLineaSalto("Creado por: " + examenInfo[5]);
            Interfaz.imprimirTextoLineaSalto(examenInfo[1] + " - " + examenInfo[4]);

            Interfaz.imprimirBordeIzqDer();
            Interfaz.imprimirTextoLineaSalto("Preguntas");
            Interfaz.imprimirBordeIzqDer();

            System.out.println(examenRespuestas.length);

            for (int j = 1; j < examenPreguntas.length; j++) {
                Interfaz.imprimirTextoLineaSalto(examenPreguntas[j]);
                Interfaz.imprimirTextoLineaSalto(examenReactivos[j]);

                String respuestas = "";
                for (int k = 0; k < examenRespuestas[j].length(); k++) {
                    if (examenRespuestas[k].isEmpty())
                        continue;

                    respuestas += examenRespuestas[j].charAt(k) + ") ";
                }

                Interfaz.imprimirBordeIzqDer();
                Interfaz.imprimirTextoLineaSalto("Respuestas");
                Interfaz.imprimirTextoLineaSalto(respuestas);
                Interfaz.imprimirBordeIzqDer();
            }

            Interfaz.imprimirLineaConexion();
        }

        Interfaz.imprimirTextoLineaSalto("Acciones disponibles:");
        Interfaz.imprimirBordeIzqDer();

        Interfaz.imprimirTextoLineaSalto("1. Finalizar examen");
        Interfaz.imprimirTextoLineaSalto("2. Borrar examen");
        Interfaz.imprimirTextoLineaSalto("3. Volver al panel docente");
        Interfaz.imprimirTextoLineaSalto("4. Volver al inicio");

        Interfaz.imprimirLineaInfIzqDer();

        System.out.print("  Ingrese su opcion: ");
        opcionVerExamenes = scan.nextInt();
        scan.nextLine();

        System.out.println();

        return opcionVerExamenes;
    }

    public static int crearCurso(Scanner scan, PrintWriter out, BufferedReader in)
            throws IOException {

        int opcionCrearCursos = 0;

        while (opcionCrearCursos == 0 || opcionCrearCursos == 1
                || !(opcionCrearCursos >= 2 || opcionCrearCursos <= 3)) {
            String cursoIndiceActual = FuncionesServidor.obtenerDelServidor(out, in, "OBTENER_CURSO_INDICE_ACTUAL");
            String cursoID = "C" + " ".repeat(4 - cursoIndiceActual.length()) + cursoIndiceActual;
            String curso;

            Interfaz.imprimirTitulo("Crear Curso");
            Interfaz.imprimirBordeIzqDer();

            do {
                System.out.print("    Nombre: ");
                curso = scan.nextLine();
            } while (curso.isBlank());

            Interfaz.imprimirBordeIzqDer();
            Interfaz.imprimirLineaConexion();

            Interfaz.imprimirTextoLineaSalto("Curso - " + curso + " - creado.");

            Interfaz.imprimirLineaConexion();

            Interfaz.imprimirBordeIzqDer();
            Interfaz.imprimirTextoLineaSalto("Acciones dispinibles:");
            Interfaz.imprimirBordeIzqDer();
            Interfaz.imprimirTextoLineaSalto("1. Crear otro curso");
            Interfaz.imprimirTextoLineaSalto("2. Volver al panel docente");
            Interfaz.imprimirTextoLineaSalto("3. Volver al inicio");
            Interfaz.imprimirLineaInfIzqDer();

            FuncionesServidor.subirAlServidor(out, in, "CREAR_CURSO", cursoID + ".-." + curso);

            System.out.print("  Ingrese su opcion: ");
            opcionCrearCursos = scan.nextInt();
            scan.nextLine();

            System.out.println();
        }

        return opcionCrearCursos;
    }

    public static int crearTema(Scanner scan, PrintWriter out, BufferedReader in)
            throws IOException {

        int opcionCrearCursos = 0;

        while (opcionCrearCursos == 0 || opcionCrearCursos == 1
                || !(opcionCrearCursos >= 2 || opcionCrearCursos <= 3)) {
            String cursoIndiceActual = FuncionesServidor.obtenerDelServidor(out, in, "OBTENER_TEMA_INDICE_ACTUAL");
            String cursoID = "T" + " ".repeat(4 - cursoIndiceActual.length()) + cursoIndiceActual;
            String curso;

            Interfaz.imprimirTitulo("Crear Tema");
            Interfaz.imprimirBordeIzqDer();

            do {
                System.out.print("    Nombre: ");
                curso = scan.nextLine();
            } while (curso.isBlank());

            Interfaz.imprimirBordeIzqDer();
            Interfaz.imprimirLineaConexion();

            Interfaz.imprimirTextoLineaSalto("Tema - " + curso + " - creado.");

            Interfaz.imprimirLineaConexion();

            Interfaz.imprimirBordeIzqDer();
            Interfaz.imprimirTextoLineaSalto("Acciones dispinibles:");
            Interfaz.imprimirBordeIzqDer();
            Interfaz.imprimirTextoLineaSalto("1. Crear otro tema");
            Interfaz.imprimirTextoLineaSalto("2. Volver al panel docente");
            Interfaz.imprimirTextoLineaSalto("3. Volver al inicio");
            Interfaz.imprimirLineaInfIzqDer();

            FuncionesServidor.subirAlServidor(out, in, "CREAR_TEMA", cursoID + ".-." + curso);

            System.out.print("  Ingrese su opcion: ");
            opcionCrearCursos = scan.nextInt();
            scan.nextLine();

            System.out.println();
        }

        return opcionCrearCursos;
    }
}
