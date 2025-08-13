import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        String regex = "\\.-\\.";

        // Inicializar arrays
        String[] usuariosArray = new String[99];
        String[] historialUsuariosArray = new String[99];

        String[] cursosArray = new String[100];
        String[] temasArray = new String[100];

        String[] sesion = {
                "", // Usuario - ID
                "", // Usuario - Nombre
                "", // Usuario - Tipo
        };

        // Inicializar matriz
        // Explicacion matriz
        // [][0] Examen - Informacion
        // [][1] Examen - Preguntas
        // [][2] Examen - Reactivos
        // [][3] Examen - Respuestas

        String[][] examenMatriz = new String[99][4];

        int[] indice = {
                0, // Usuarios
                0, // Historial usuarios
                0, // Examen
                0, // Cursos
                0, // Temas
        };

        // Array de uso para el apartado de Docente

        // Leer los archivos guardados
        // String[] usuariosArchivo = FuncionesServidor.leerArchivo("Usuarios");

        Datos.crearUsuarios(usuariosArray, indice);

        // Crear datos de ejemplo
        Datos.crearExamenes(examenMatriz, indice);

        // Instancia del Scanner
        Scanner scan = new Scanner(System.in);

        // Variables para gestionar el flujo del sistema
        int opcionInicio = 0, opcionPanel = 0, opcionPanelCrear = 0, opcionAdmin = 0,
                opcionProfesor = 0, opcionAdministrar = 0, opcionIniciarSesion = 0, opcionBorrar = 0,
                opcionCrearExamen = 0;

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

            while (sesion[0].isEmpty()) {

                opcionIniciarSesion = Administrador.iniciarSesion(scan, sesion, usuariosArray, indice);

                if (opcionIniciarSesion == 2) {
                    opcionInicio = 0;
                    opcionIniciarSesion = 0;
                    break;
                }
            }

            switch (opcionInicio) {
                case 1: {
                    opcionPanel = Administrador.menuAdmin(scan);

                    if (opcionPanel == 3)
                        opcionInicio = 0;

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
                }

                case 2: {
                    int opcionEstudiante, opcionAdministrarPerfil;

                    opcionEstudiante = Estudiante.mostrarMenu(scan);

                    switch (opcionEstudiante) {
                        case 1:
                            opcionAdministrarPerfil = Estudiante.administrarPerfil(scan);
                            break;

                        default:
                            break;
                    }

                }

                case 3: {
                    int opcionDocente = 0;

                    opcionDocente = Docente.mostrarPanel(scan);

                    if (opcionDocente == 1) {
                        opcionCrearExamen = Docente.crearExamen(scan, examenMatriz, sesion, indice);

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
                    }

                    if (opcionDocente == 2) {
                        int opcionVerExamenes;

                        Interfaz.imprimirTitulo("Examenes creados");
                        Interfaz.imprimirBordeIzqDer();

                        for (int i = 0; i < indice[2]; i++) {
                            String[] examenInfo = examenMatriz[i][0].split(regex);

                            if (!(examenInfo[examenInfo.length - 1].equals(sesion[0])) && !sesion[0].equals("0000"))
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

                            if (indice[3] < cursosArray.length) {
                                cursosArray[indice[3]] = nuevoCurso;
                                indice[3]++;
                                System.out.println("Curso agregado correctamente.");
                            } else {
                                System.out.println("No hay espacio para más cursos.");
                            }
                        }

                        if (opcionDocente == 4) {
                            System.out.print("Nombre del tema: ");
                            String nuevoTema = scan.nextLine();

                            if (indice[4] < temasArray.length) {
                                temasArray[indice[4]] = nuevoTema;
                                indice[4]++;
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
    }
} //
