import java.net.Socket;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
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

        // Array de uso para el apartado de Docente
        char[] reactivos = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n' };

        // // Crear datos de ejemplo
        Datos.crearUsuarios(usuariosArray);
        Datos.crearExamenInformacion(examenInfoArray);
        Datos.crearExamenPreguntas(examenPreguntasArray);
        Datos.crearExamenReactivos(examenReactivosArray);
        Datos.crearExamenRespuestas(examenRespuestasArray);

        // Variables de captura de datos del usuario
        String usuarioNombre, usuarioPass, usuarioId, usuarioTipo;

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
                Interfaz.imprimirBordeIzqDer();

                System.out.print("    Usuario: ");
                usuarioSesionID = scan.nextLine();

                System.out.print("    Clave: ");
                usuarioSesionClave = scan.nextLine();

                for (int i = 0; i < Array.usuariosIndiceActual; i++) {
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
                opcionPanelCrear = Administrador.crearUsuario(scan, usuariosArray);
              
                }

                if (opcionPanelCrear == 2) {
                    opcionPanel = 0;
                    opcionPanelCrear = 0;
                }

                if (opcionPanelCrear == 3) {
                    opcionInicio = 0;
                    opcionPanel = 0;
                    opcionPanelCrear = 0;
                }
                if (opcionPanel == 4){
                      // ...ya tienes tipo, id, nombre, clave capturados...
                String usuarioCompleto = usuariosArray[Array.usuariosIndiceActual - 1];

                // Pedir el nombre del archivo para guardar en el servidor
                System.out.print("Ingrese el nombre de archivo para guardar el usuario (ejemplo: usuario1.txt): ");
                String nombreArchivo = scan.nextLine();

                // Enviar al servidor
                try (
                        Socket socket = new Socket("localhost", 5000);
                        PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
                        BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                    salida.println("REGISTRO_USUARIO");
                    salida.println(nombreArchivo); // Nombre del archivo a crear
                    salida.println(usuarioCompleto); // Datos del usuario

                    String respuesta;
                    while ((respuesta = entrada.readLine()) != null) {
                        System.out.println(respuesta);
                        if (respuesta.contains("Usuario guardado"))
                            break;
                    }
                } catch (Exception e) {
                    System.out.println("Error al conectar con el servidor: " + e.getMessage());
                }
            }

            if (opcionPanel == 2) {
                while (opcionAdministrar == 0
                        || !(opcionAdministrar == 1 || opcionAdministrar == 2 || opcionAdministrar == 3)) {
                    System.out.println();
                    Interfaz.imprimirLineaSupIzqDer();
                    Interfaz.imprimirTextoLineaSalto("Administrar Usuarios");
                    Interfaz.imprimirLineaConexion();

                    Interfaz.imprimirTextoLineaSalto("Tipo    ID       Nombre                 Clave");
                    Interfaz.imprimirLineaInfIzqDer();

                    System.out.println();

                    for (int i = 0; i < Array.usuariosIndiceActual; i++) {
                        if (usuariosArray[i].isEmpty())
                            continue; // Si el valor del array esta vacio pasa a la siguiente iteracion, para que no
                                      // de error.

                        String[] datos = usuariosArray[i].split(regex);
                        if (datos[2].length() > nombreMasLargo)
                            nombreMasLargo = datos[2].length() + 2; // Almacena la longitud del nombre mas largo de un
                        // usuario
                    }

                    for (int i = 0; i < Array.usuariosIndiceActual; i++) {
                        if (usuariosArray[i].isEmpty())
                            continue; // Si el valor del array esta vacio pasa a la siguiente iteracion, para que no
                                      // de error.

                        String[] datos = usuariosArray[i].split(regex);
                        String usuarioTexto = "     " + datos[0] + "      " + datos[1] + " "
                                + datos[2] + " ".repeat(nombreMasLargo - datos[2].length()) + "\t" + datos[3];
                        System.out.println(usuarioTexto);
                    }

                    System.out.println();

                    Interfaz.imprimirLineaSupIzqDer();
                    Interfaz.imprimirTextoLineaSalto("Acciones disponibles:");
                    Interfaz.imprimirTextoLineaSalto("1. Modificar usuario");
                    Interfaz.imprimirTextoLineaSalto("2. Eliminar usuario");

                    Interfaz.imprimirBordeIzqDer();
                    Interfaz.imprimirTextoLineaSalto("3. Volver al panel de administrador");
                    Interfaz.imprimirTextoLineaSalto("4. Volver al inicio");

                    Interfaz.imprimirLineaInfIzqDer();

                    System.out.print("  Ingrese su opcion: ");
                    opcionAdministrar = scan.nextInt();
                    scan.nextLine();

                    System.out.println();

                    if (opcionAdministrar == 3) {
                        opcionPanel = 0;
                        opcionAdministrar = 0;
                        break;
                    }
                    if (opcionAdministrar == 4) {
                        opcionInicio = 0;
                        opcionPanel = 0;
                        opcionAdministrar = 0;
                        break;
                    }
                }

                if (opcionAdministrar == 1) {
                    Interfaz.imprimirTitulo("Modificar Usuario");
                    Interfaz.imprimirTextoLineaSalto("Nota: Si un campo no sera modificado, dejar en blanco.");
                    System.out.print("    ID: ");

                    String idModificar = scan.nextLine();

                    boolean usuarioEncontrado = false;

                    for (int i = 0; i < Array.usuariosIndiceActual; i++) {
                        String[] datos = usuariosArray[i].split(regex);

                        if (datos[1].equals(idModificar)) {
                            usuarioEncontrado = true;
                            Interfaz.imprimirLineaConexion();

                            Interfaz.imprimirTextoLineaSalto("Usuario encontrado, datos: ");
                            Interfaz.imprimirTextoLineaSalto("Nombre: " + datos[2]);
                            Interfaz.imprimirTextoLineaSalto("Clave: " + datos[3]);
                            Interfaz.imprimirTextoLineaSalto("Tipo: " + datos[0].toUpperCase());
                            Interfaz.imprimirLineaConexion();

                            System.out.print("    Nuevo nombre: ");
                            String nuevoNombre = scan.nextLine();

                            System.out.print("    Nueva clave: ");
                            String nuevaClave = scan.nextLine();

                            if (!nuevoNombre.isEmpty())
                                datos[2] = nuevoNombre;
                            if (!nuevaClave.isEmpty())
                                datos[3] = nuevaClave;

                            String nuevoUsuario = datos[0] + ".-." + datos[1] + ".-." + datos[2] + ".-." + datos[3];
                            Array.modificar(usuariosArray, i, nuevoUsuario);

                            Interfaz.imprimirLineaConexion();
                            Interfaz.imprimirTextoLineaSalto("Usuario modificado correctamente.");
                            break;
                        }

                    }

                    Interfaz.imprimirLineaConexion();
                    if (!usuarioEncontrado) {
                        Interfaz.imprimirTextoLineaSalto("Usuario no encontrado.");
                        Interfaz.imprimirLineaConexion();
                        Interfaz.imprimirTextoLineaSalto("1. Intentar otra vez");
                        Interfaz.imprimirTextoLineaSalto("2. Salir");
                        Interfaz.imprimirLineaInfIzqDer();

                        System.out.print("  Ingrese su opcion: ");
                        opcionAdministrar = scan.nextInt();
                        scan.nextLine();

                        System.out.println();

                        if (opcionAdministrar == 2) {
                            opcionInicio = 1;
                            opcionPanel = 0;
                            opcionAdministrar = 0;
                        }
                    }
                    if (usuarioEncontrado) {
                        Interfaz.imprimirTextoLineaSalto("1. Modificar otro usuario");
                        Interfaz.imprimirTextoLineaSalto("2. Volver al panel de administrador");
                        Interfaz.imprimirTextoLineaSalto("3. Volver al inicio");

                        Interfaz.imprimirLineaInfIzqDer();

                        System.out.print("  Ingrese su opcion: ");
                        opcionAdministrar = scan.nextInt();
                        scan.nextLine();

                        System.out.println();

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
                }

                if (opcionAdministrar == 2) {
                    int indiceBorrar = 0;

                    Interfaz.imprimirTitulo("Borrar Usuario");

                    System.out.print("    ID: ");
                    String idBorrar = scan.nextLine();

                    boolean usuarioEncontrado = false;
                    String[] datos = null;

                    for (int i = 0; i < Array.usuariosIndiceActual; i++) {
                        if (usuariosArray[i].isEmpty())
                            continue;

                        datos = usuariosArray[i].split(regex);
                        indiceBorrar = i;

                        if (datos[1].equals(idBorrar)) {
                            usuarioEncontrado = true;
                            break;
                        }
                    }

                    Interfaz.imprimirLineaConexion();

                    if (usuarioEncontrado) {
                        Interfaz.imprimirTextoLineaSalto("Usuario encontrado, datos: ");
                        Interfaz.imprimirTextoLineaSalto("Nombre: " + datos[2]);
                        Interfaz.imprimirTextoLineaSalto("Clave: " + datos[3]);
                        Interfaz.imprimirTextoLineaSalto("Tipo: " + datos[0].toUpperCase());
                        Interfaz.imprimirLineaConexion();

                        Interfaz.imprimirTextoLineaSalto("Esta seguro de borrar el usuario?");
                        Interfaz.imprimirTextoLineaSalto("1. Si");
                        Interfaz.imprimirTextoLineaSalto("2. No");
                        Interfaz.imprimirLineaInfIzqDer();

                        System.out.print("  Ingrese su opcion: ");
                        int borrarOpcion = scan.nextInt();
                        scan.nextLine();

                        System.out.println();

                        if (borrarOpcion == 1)
                            Array.borrar(usuariosArray, indiceBorrar);

                        Interfaz.imprimirLineaConexion();
                        Interfaz.imprimirTextoLineaSalto("Usuario borrado correctamente.");

                        Interfaz.imprimirLineaConexion();
                        Interfaz.imprimirTextoLineaSalto("Acciones disponibles:");
                        Interfaz.imprimirBordeIzqDer();

                        Interfaz.imprimirTextoLineaSalto("1. Borrar a otro usuario");
                        Interfaz.imprimirTextoLineaSalto("2. Volver al panel de administrador");
                        Interfaz.imprimirTextoLineaSalto("3. Volver al inicio");

                        Interfaz.imprimirLineaInfIzqDer();

                        System.out.print("  Ingrese su opcion: ");
                        opcionBorrar = scan.nextInt();
                        scan.nextLine();

                        System.out.println();

                        if (opcionBorrar == 2) {
                            opcionInicio = 1;
                            opcionPanel = 0;
                            opcionBorrar = 0;
                            opcionAdministrar = 0;
                        }
                        if (opcionBorrar == 3) {
                            opcionInicio = 0;
                            opcionPanel = 0;
                            opcionBorrar = 0;
                            opcionAdministrar = 0;
                        }
                    }

                    if (!usuarioEncontrado) {
                        Interfaz.imprimirTextoLineaSalto("Usuario no encontrado.");
                        Interfaz.imprimirLineaConexion();
                        Interfaz.imprimirTextoLineaSalto("1. Intentar otra vez");
                        Interfaz.imprimirTextoLineaSalto("2. Salir");
                        Interfaz.imprimirLineaInfIzqDer();

                        System.out.print("  Ingrese su opcion: ");
                        opcionBorrar = scan.nextInt();
                        scan.nextLine();

                        System.out.println();

                        if (opcionBorrar == 2) {
                            opcionInicio = 1;
                            opcionPanel = 0;
                            opcionBorrar = 0;
                            opcionAdministrar = 0;
                        }
                    }
                }
            }
            if (opcionInicio == 3) {
                int opcionDocente = 0;

                while (opcionDocente == 0 || !(
                    opcionDocente == 1 || opcionDocente == 2 || opcionDocente == 3
                )) {
                    Interfaz.imprimirTitulo("Panel Docente");
                    Interfaz.imprimirTextoLineaSalto("1. Crear examen");
                    Interfaz.imprimirTextoLineaSalto("2. Ver examenes");
                    Interfaz.imprimirBordeIzqDer();
                    Interfaz.imprimirTextoLineaSalto("3. Volver al inicio");
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

                            // Une todos los exámenes en un solo String, separados por salto de línea
                            StringBuilder todosExamenes = new StringBuilder();
                            for (int i = 0; i < Array.examenInfoIndiceActual; i++) {
                                if (!examenInfoArray[i].isEmpty()) {
                                    todosExamenes.append(examenInfoArray[i]).append("\n");
                                    todosExamenes.append(examenPreguntasArray[i]).append("\n");
                                    todosExamenes.append(examenReactivosArray[i]).append("\n");
                                    todosExamenes.append(examenRespuestasArray[i]).append("\n");
                                    todosExamenes.append("---\n"); // Separador entre exámenes
                                }
                            }

                            // Enviar al servidor
                            try (
                                    Socket socket = new Socket("localhost", 5000);
                                    PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
                                    BufferedReader entrada = new BufferedReader(
                                            new InputStreamReader(socket.getInputStream()))) {
                                salida.println("GUARDAR_EXAMENES");
                                salida.println(nombreArchivo); // Nombre del archivo a crear
                                salida.println(todosExamenes.toString()); // Todos los exámenes
                                salida.println(""); 

                                String respuesta;
                                while ((respuesta = entrada.readLine()) != null) {
                                    System.out.println(respuesta);
                                    if (respuesta.contains("Examenes guardados"))
                                        break;
                                }
                            } catch (Exception e) {
                                System.out.println("Error al conectar con el servidor: " + e.getMessage());
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

                        if (opcionVerExamenes == 3) {
                            opcionInicio = 3;
                            opcionDocente = 2;
                            opcionVerExamenes = 0;
                            break;
                        }
                        if (opcionVerExamenes == 4) {
                            opcionInicio = 0;
                            opcionDocente = 0;
                            opcionVerExamenes = 0;
                            break;
                        }
                    }
                }
            }
        } //
    }
}
