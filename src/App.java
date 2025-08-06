import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        // Inicializar arrays con ArrayList
        ArrayList<UsuariosDatos> usuarios = new ArrayList<>();
        ArrayList<Examen> examenes = new ArrayList<>();

        // Crear datos de ejemplo
        Datos.crearUsuarios(usuarios);

        System.out.println((UsuariosDatos) usuarios.get(1));

        // Inicializar arrays
        String[] usuariosArray = new String[99];
        String[] historialUsuariosArray = new String[99];
        String[] examenInfoArray = new String[99];
        String[] examenPreguntasArray = new String[99];
        String[] examenReactivosArray = new String[99];
        String[] examenRespuestasArray = new String[99];

        // Crear datos de ejemplo
        Datos.crearUsuarios(usuariosArray);

        // Variables de captura de datos del usuario
        String usuarioNombre, usuarioPass, usuarioId, usuarioTipo;

        // Variables de gestion del usuario actual
        String usuarioActual;

        // Instancia del Scanner
        Scanner scan = new Scanner(System.in);

        // Variables para gestionar el flujo del sistema
        int opcionInicio = 0, opcionPanel = 0, opcionPanelCrear = 0, opcionAdmin = 0, opcionEstudiante = 0,
                opcionProfesor = 0, opcionAdministrar = 0;

        /*
         * Examen Info
         * ID Nombre Fecha Tipo Materia Docente
         * 00000001.-.Quimica 2.-.29/Julio/25.-.Ordinario.-.Quimica.-.Javier Perez
         * 
         * Examen Preguntas
         * ID Preguntas
         * 00000001.-.¿Quien fue Cristobal Colon?.-.¿Cuando se descubrio america?
         * 
         * Examen Reactivos
         * 00000001.-.a) Mi padre b) Un puñetas c) Dios.-.a) Ayer b) Hoy c) Mañana
         * 
         * Examen Respuestas
         * 00000001.-.a).-.b)c)
         */

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
            }
            ;

            if (opcionInicio == 4)
                break;

            while (opcionPanel == 1) {
                System.out.println();
                Interfaz.imprimirTitulo("Iniciar sesion");

            }

            if (opcionInicio == 1) {
                while (opcionPanel == 0 || !(opcionPanel == 1 || opcionPanel == 2 || opcionPanel == 3)) {
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

                    System.out.print("  Ingrese su opcion: ");
                    opcionPanel = scan.nextInt();

                    if (opcionPanel == 3) {
                        opcionInicio = 0;
                        opcionPanel = 0;
                        break;
                    }
                }
                ;

                if (opcionPanel == 1) {
                    while (opcionPanelCrear == 0
                            || !(opcionPanelCrear == 1 || opcionPanelCrear == 2 || opcionPanelCrear == 3)) {
                        usuarioId = "0".repeat(8 - String.valueOf(usuarios.size()).length()) + usuarios.size();

                        System.out.println();
                        Interfaz.imprimirLineaSupIzqDer();
                        Interfaz.imprimirTextoLineaSalto("Crear Usuario");
                        Interfaz.imprimirLineaConexion();

                        Interfaz.imprimirTextoLineaSalto("ID: " + usuarioId);
                        do {
                            Interfaz.imprimirTextoLineaSalto("Tipo -> (E)studiante     (D)ocente");
                            System.out.print("    Tipo: ");
                            scan.nextLine();
                            usuarioTipo = scan.nextLine();
                        } while (!(usuarioTipo.equals("D") || usuarioTipo.equals("E")
                                || (usuarioTipo.equals("d") || usuarioTipo.equals("e"))));

                        do {
                            System.out.print("    Nombre: ");
                            usuarioNombre = scan.nextLine();
                            if (usuarioNombre.trim().isEmpty()) {
                                System.out.println("    El nombre no puede estar vacío. Intente de nuevo.");
                            }
                        } while (usuarioNombre.trim().isEmpty());

                        System.out.print("    Clave: ");
                        usuarioPass = scan.nextLine();

                        Interfaz.imprimirLineaConexion();
                        Interfaz.imprimirTextoLineaSalto("Usuario creado.");
                        Interfaz.imprimirLineaConexion();
                        Interfaz.imprimirTextoLineaSalto("1. Crear otro usuario");
                        Interfaz.imprimirTextoLineaSalto("2. Volver al panel de administrador");
                        Interfaz.imprimirTextoLineaSalto("3. Volver al inicio");

                        Interfaz.imprimirLineaInfIzqDer();

                        System.out.print("  Ingrese su opcion: ");
                        opcionPanelCrear = scan.nextInt();

                        String nuevoUsuario = usuarioTipo.toUpperCase() + ".-." + usuarioId + ".-." + usuarioNombre
                                + ".-."
                                + usuarioPass;
                        usuarios.add(nuevoUsuario);

                        if (opcionPanelCrear == 2) {
                            opcionPanel = 0;
                            opcionPanelCrear = 0;
                            break;
                        }
                        if (opcionPanelCrear == 3) {
                            opcionInicio = 0;
                            opcionPanel = 0;
                            opcionPanelCrear = 0;
                            break;
                        }
                    }
                }

                if (opcionPanel == 2) {
                    while (opcionAdministrar == 0
                            || !(opcionAdministrar == 1 || opcionAdministrar == 2 || opcionAdministrar == 3)) {
                        System.out.println();
                        Interfaz.imprimirLineaSupIzqDer();
                        Interfaz.imprimirTextoLineaSalto("Administrar Usuarios");
                        Interfaz.imprimirLineaConexion();

                        Interfaz.imprimirTextoLineaSalto("Tipo  ID           Nombre     \tClave");

                        // for (String usuarioString : usuarios) {
                        // String[] usuarioDatos = usuarioString.split("\\.-\\.");

                        // for (int i = 0; i < usuarioDatos.length; i++) {
                        // if (i == 0)
                        // System.out.print(" ");

                        // System.out.print(usuarioDatos[i] + " ");

                        // if (i == usuarioDatos.length - 2)
                        // System.out.print("\t");
                        // if (i == usuarioDatos.length - 1)
                        // System.out.println();
                        // }
                        // }

                        Interfaz.imprimirBordeIzqDer();
                        Interfaz.imprimirTextoLineaSalto("Acciones disponibles:");
                        Interfaz.imprimirTextoLineaSalto("1. Modificar usuario");
                        Interfaz.imprimirTextoLineaSalto("2. Eliminar usuario");

                        Interfaz.imprimirBordeIzqDer();
                        Interfaz.imprimirTextoLineaSalto("3. Volver al panel de administrador");
                        Interfaz.imprimirTextoLineaSalto("4. Volver al inicio");
                        Interfaz.imprimirBordeIzqDer();

                        Interfaz.imprimirLineaInfIzqDer();

                        System.out.print("  Ingrese su opcion: ");
                        opcionAdministrar = scan.nextInt();

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
                    ;
                }
            }

            if (opcionInicio == 2) {
                int opcionDocente = 0;
                while (opcionDocente != 3) {
                    System.out.println("Panel Docente");
                    System.out.println("1. Crear examen");
                    System.out.println("2. Ver exámenes");
                    System.out.println("3. Volver");
                    System.out.print("Opción: ");
                    opcionDocente = scan.nextInt();
                    scan.nextLine(); // limpiar buffer

                    if (opcionDocente == 1) {
                        System.out.println("Crear Examen");
                        String id = "EX" + (examenes.size() + 1);
                        System.out.print("Nombre del examen: ");
                        String nombre = scan.nextLine();
                        System.out.print("Fecha (ej: 29/Julio/25): ");
                        String fecha = scan.nextLine();
                        System.out.print("Tipo (ej: Ordinario): ");
                        String tipo = scan.nextLine();
                        System.out.print("Materia: ");
                        String materia = scan.nextLine();
                        System.out.print("Nombre del docente: ");
                        String docente = scan.nextLine();

                        Examen examen = new Examen(id, nombre, fecha, tipo, materia, docente);

                        // Agregar preguntas
                        while (true) {
                            System.out.print("¿Agregar una pregunta? (s/n): ");
                            String resp = scan.nextLine();
                            if (!resp.equalsIgnoreCase("s"))
                                break;

                            System.out.print("Pregunta: ");
                            String pregunta = scan.nextLine();

                            ArrayList<String> opciones = new ArrayList<>();
                            char letra = 'a';
                            boolean agregarOtraOpcion = true;
                            while (agregarOtraOpcion) {
                                System.out.print("Opción " + letra + ": ");
                                opciones.add(scan.nextLine());
                                if (letra >= 'c') {
                                    System.out.print("¿Desea agregar otra opción? (s/n): ");
                                    String masOpc = scan.nextLine();
                                    if (!masOpc.equalsIgnoreCase("s")) {
                                        agregarOtraOpcion = false;
                                    } else {
                                        letra++;
                                    }
                                } else {
                                    letra++;
                                }
                            }

                            // Mostrar letras válidas para la respuesta correcta
                            StringBuilder letrasValidas = new StringBuilder();
                            for (char l = 'a'; l < 'a' + opciones.size(); l++) {
                                letrasValidas.append(l);
                                if (l < 'a' + opciones.size() - 1)
                                    letrasValidas.append("/");
                            }
                            System.out.print("Respuesta correcta (" + letrasValidas + "): ");
                            String correcta = scan.nextLine();

                            Pregunta nuevaPregunta = new Pregunta(pregunta, opciones, correcta);
                            examen.preguntas.add(nuevaPregunta);
                        }

                        examenes.add(examen);
                        System.out.println("Examen creado correctamente.");
                    }

                    if (opcionDocente == 2) {
                        System.out.println("\n--- Exámenes Creados ---");
                        if (examenes.isEmpty()) {
                            System.out.println("No hay exámenes registrados.");
                        } else {
                            for (Examen ex : examenes) {
                                System.out.println();
                                ex.mostrarExamen();
                            }
                        }
                    }
                }
            }
        }

        scan.close();
    }
}