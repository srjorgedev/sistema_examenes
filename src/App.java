import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        String regex = "\\.-\\.";

        // Inicializar arrays
        String[] usuariosArray = new String[99];
        String[] historialUsuariosArray = new String[99];
        String[] examenInfoArray = new String[99];
        String[] examenPreguntasArray = new String[99];
        String[] examenReactivosArray = new String[99];
        String[] examenRespuestasArray = new String[99];
        String[] cursosArray = new String[100];
        int cursosIndiceActual = 0;
        String[] temasArray = new String[100];
        int temasIndiceActual = 0;

        int[] indice = {
                0, // Usuarios
                0, // Historial usuarios
                0, // Examen - Informacion
                0, // Examen - Preguntas
                0, // Examen - Reactivos
                0 // Examen - Respuestas
        };

        // Array de uso para el apartado de Docente
        char[] reactivos = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n' };

        // Leer los archivos guardados
        String[] usuariosArchivo = FuncionesServidor.leerArchivo("Usuarios");

        if (usuariosArchivo.length == 0) {
            Datos.crearUsuarios(usuariosArray, indice);
        } else {
            for (String usuario : usuariosArchivo) {
                Array.agregarUsuario(usuariosArray, usuario, indice);
            }
        }

        // Crear datos de ejemplo
        Datos.crearExamenInformacion(examenInfoArray);
        Datos.crearExamenPreguntas(examenPreguntasArray);
        Datos.crearExamenReactivos(examenReactivosArray);
        Datos.crearExamenRespuestas(examenRespuestasArray);

        // Variables de gestion del usuario actual
        String usuarioSesionActual = "", usuarioSesionClave = "", usuarioSesionID = "", usuarioSesionNombre = "",
                usuarioSesionTipo = "";

        // Instancia del Scanner
        Scanner scan = new Scanner(System.in);

        // Variables para gestionar el flujo del sistema
        int opcionInicio = 0, opcionPanel = 0, opcionPanelCrear = 0, opcionAdmin = 0, opcionEstudiante = 0,
                opcionProfesor = 0, opcionAdministrar = 0, opcionIniciarSesion = 0, opcionBorrar = 0;
        int nombreMasLargo = 0;

        while (true) {
            while (opcionInicio == 0
                    || !(opcionInicio == 1 || opcionInicio == 2 || opcionInicio == 3 || opcionInicio == 4)) {
                System.out.println();

                Interfaz.imprimirTitulo("Bienvenido al sistema de examenes");
                Interfaz.imprimirTextoLineaSalto("Seleccione su usuario:");
                Interfaz.imprimirBordeIzqDer();
                Interfaz.imprimirTextoLineaSalto("1. Administrador");
                Interfaz.imprimirTextoLineaSalto("2. Estudiante");
                Interfaz.imprimirTextoLineaSalto("3. Profesor");
                Interfaz.imprimirBordeIzqDer();
                Interfaz.imprimirTextoLineaSalto("4. Salir del sistema");
                Interfaz.imprimirBordeIzqDer();
                Interfaz.imprimirLineaInfIzqDer();

                System.out.print("  Ingrese su opcion: ");
                opcionInicio = scan.nextInt();
                scan.nextLine();

                System.out.println();
            }
            ;

            if (opcionInicio == 4)
                break;

            while (usuarioSesionActual.isEmpty()) {
                boolean usuarioEncontrado = false;

                System.out.println();
                Interfaz.imprimirTitulo("Iniciar sesion");

                Interfaz.imprimirTextoLineaSalto("Nota: El formato de usuarios son 4 digitos numericos");
                Interfaz.imprimirTextoLineaSalto("Por ejemplo: 0000");
                Interfaz.imprimirLineaConexion();
                Interfaz.imprimirBordeIzqDer();

                do {
                    System.out.print("    Usuario: ");
                    usuarioSesionID = scan.nextLine();
                } while (!usuarioSesionID.matches("^\\d{4}$"));

                do {
                    System.out.print("    Clave: ");
                    usuarioSesionClave = scan.nextLine();
                } while (usuarioSesionClave.isBlank());

                for (int i = 0; i < indice[0]; i++) {
                    String[] datos = usuariosArray[i].split(regex);

                    if (datos[1].equals(usuarioSesionID) && datos[3].equals(usuarioSesionClave)) {
                        usuarioEncontrado = true;

                        usuarioSesionTipo = datos[0];
                        usuarioSesionActual = datos[1];
                        usuarioSesionNombre = datos[2];
                        break;
                    }
                }

                Interfaz.imprimirBordeIzqDer();
                Interfaz.imprimirLineaConexion();

                if (usuarioEncontrado) {
                    Interfaz.imprimirTextoLineaSalto("Sesion iniciada.");
                    Interfaz.imprimirLineaInfIzqDer();
                    break;
                }

                Interfaz.imprimirTextoLineaSalto("Usuario o clave incorrectos.");
                Interfaz.imprimirLineaConexion();
                Interfaz.imprimirTextoLineaSalto("1. Intentar de nuevo");
                Interfaz.imprimirTextoLineaSalto("2. Volver al inicio");
                Interfaz.imprimirLineaInfIzqDer();

                System.out.print("  Ingrese su opcion: ");
                opcionIniciarSesion = scan.nextInt();
                scan.nextLine();

                System.out.println();

                if (opcionIniciarSesion == 2) {
                    opcionInicio = 0;
                    opcionIniciarSesion = 0;
                    break;
                }
            }

            if (opcionInicio == 1) {
                opcionPanel = Administrador.menuAdmin(scan);

                if (opcionPanel == 3)
                    opcionInicio = 0;
            }

            if (opcionPanel == 1) {
                opcionPanelCrear = Administrador.crearUsuario(scan, usuariosArray, indice);

                if (opcionPanelCrear == 2) {
                    opcionPanel = 0;
                    opcionPanelCrear = 0;
                }

                if (opcionPanelCrear == 3) {
                    opcionInicio = 0;
                    opcionPanel = 0;
                    opcionPanelCrear = 0;
                }
            }

            if (opcionPanel == 2) {
                opcionAdministrar = Administrador.mostrarUsuarios(scan, usuariosArray, indice);

                if (opcionAdministrar == 3) {
                    opcionPanel = 0;
                    opcionAdministrar = 0;
                }
                if (opcionAdministrar == 4) {
                    opcionInicio = 0;
                    opcionPanel = 0;
                    opcionAdministrar = 0;
                }

                if (opcionAdministrar == 1) {
                    opcionAdministrar = Administrador.modificarUsuario(scan, usuariosArray, indice);

                    if (opcionAdministrar == 2) {
                        opcionInicio = 1;
                        opcionPanel = 0;
                        opcionAdministrar = 0;
                    }
                    if (opcionAdministrar == 3) {
                        opcionInicio = 0;
                        opcionPanel = 0;
                        opcionAdministrar = 0;
                    }
                }

                if (opcionAdministrar == 2) {
                    opcionBorrar = Administrador.borrarUsuario(scan, usuariosArray, indice);

                    if (opcionBorrar == 2) {
                        opcionInicio = 1;
                        opcionPanel = 0;
                        opcionBorrar = 0;
                    }
                    if (opcionBorrar == 3) {
                        opcionInicio = 0;
                        opcionPanel = 0;
                    }
                }
            }
            if (opcionInicio == 3) {
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

                    if (opcionDocente == 1) {
                        String examenInformacion = "";
                        String examenPreguntas = "";
                        String examenRespuestas = "";
                        String examenReactivos = "";

                        int opcionCrearExamen = 0;

                        Interfaz.imprimirTitulo("Crear Examen");
                        String examenID = "EX0" + Array.examenInfoIndiceActual;

                        System.out.print("    Nombre del examen: ");
                        String nombre = scan.nextLine();

                        System.out.print("    Fecha (ej: 29/7/25): ");
                        String fecha = scan.nextLine();

                        Interfaz.imprimirTextoLineaSalto("Tipos -> (O)rdinario (R)emedial (E)xtra");
                        System.out.print("    Tipo: ");
                        String tipo = scan.nextLine();

                        System.out.print("    Materia: ");
                        String materia = scan.nextLine();

                        System.out.println("    Nombre del docente: " + usuarioSesionNombre);

                        examenInformacion = examenID + ".-." + nombre + ".-." + fecha + ".-." + tipo.toUpperCase()
                                + ".-." + materia + ".-." + usuarioSesionNombre + ".-." + usuarioSesionActual;
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

                                examenReactivos += examenID + ".-." + reactivos[indiceReactivo] + ")" + respuesta;
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

                        Array.agregarExamenInformacion(examenInfoArray, examenInformacion);
                        Array.agregarExamenPreguntas(examenPreguntasArray, examenPreguntas);
                        Array.agregarExamenReactivos(examenReactivosArray, examenReactivos);
                        Array.agregarExamenRespuestas(examenRespuestasArray, examenRespuestas);

                        System.out.println(examenInfoArray[0]);
                        System.out.println(examenPreguntasArray[0]);
                        System.out.println(examenReactivosArray[0]);
                        System.out.println(examenRespuestasArray[0]);

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
                        Interfaz.imprimirTextoLineaSalto("4. Guardar exámenes en archivo"); // NUEVA OPCIÓN
                        Interfaz.imprimirLineaInfIzqDer();

                        System.out.print("  Ingrese su opcion: ");
                        opcionCrearExamen = scan.nextInt();
                        scan.nextLine();

                        System.out.println();

                        if (opcionCrearExamen == 2) {
                            opcionInicio = 3;
                            opcionDocente = 1;
                            opcionCrearExamen = 0;
                        }
                        if (opcionCrearExamen == 3) {
                            opcionInicio = 0;
                            opcionDocente = 0;
                            opcionCrearExamen = 0;
                        }
                        if (opcionCrearExamen == 4) {
                            System.out.print(
                                    "Ingrese el nombre del archivo para guardar los exámenes (ejemplo: examenes.txt): ");
                            String nombreArchivo = scan.nextLine();

                            try (
                                    Socket socket = new Socket("localhost", 5000);
                                    PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
                                    BufferedReader entrada = new BufferedReader(
                                            new InputStreamReader(socket.getInputStream()))) {
                                salida.println("REGISTRO_EXAMENES");
                                for (int i = 0; i < Array.examenInfoIndiceActual; i++) {
                                    if (!examenInfoArray[i].isEmpty()) {
                                        salida.println(examenInfoArray[i]);
                                        salida.println(examenPreguntasArray[i]);
                                        salida.println(examenReactivosArray[i]);
                                        salida.println(examenRespuestasArray[i]);
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
                        }

                    }
                }

                if (opcionDocente == 2) {
                    int opcionVerExamenes;

                    Interfaz.imprimirTitulo("Examenes creados");
                    Interfaz.imprimirBordeIzqDer();

                    for (int i = 0; i < Array.examenInfoIndiceActual; i++) {
                        String[] examenInfo = examenInfoArray[i].split(regex);

                        if (!(examenInfo[examenInfo.length - 1].equals(usuarioSesionActual)))
                            continue;

                        String[] examenPreguntas = examenPreguntasArray[i].split(regex);
                        String[] examenReactivos = examenReactivosArray[i].split(regex);
                        String[] examenRespuestas = examenRespuestasArray[i].split(regex);

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

                        for (int j = 1; j < examenPreguntas.length; j++) {
                            Interfaz.imprimirTextoLineaSalto(examenPreguntas[j]);
                            Interfaz.imprimirTextoLineaSalto(examenReactivos[j]);

                            String respuestas = "";
                            for (int k = 0; k < examenRespuestas[j].length(); k++) {
                                if (examenRespuestas[k].isBlank())
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

                    if (opcionDocente == 3) {
                        System.out.print("Nombre del curso: ");
                        String nuevoCurso = scan.nextLine();

                        if (cursosIndiceActual < cursosArray.length) {
                            cursosArray[cursosIndiceActual] = nuevoCurso;
                            cursosIndiceActual++;
                            System.out.println("Curso agregado correctamente.");
                        } else {
                            System.out.println("No hay espacio para más cursos.");
                        }
                    }

                    if (opcionDocente == 4) {
                        System.out.print("Nombre del tema: ");
                        String nuevoTema = scan.nextLine();

                        if (temasIndiceActual < temasArray.length) {
                            temasArray[temasIndiceActual] = nuevoTema;
                            temasIndiceActual++;
                            System.out.println("Tema agregado correctamente.");
                        } else {
                            System.out.println("No hay espacio para más temas.");
                        }
                    }
                }

                if (opcionDocente == 5) {
                    opcionDocente = 0; // Regresa al inicio
                    break;
                }
            }
        }
    }
} //
