import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        try {
            String regex = "\\.-\\.";

            Socket socket = new Socket("localhost", 5000);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Inicializar arrays
            String[] usuariosArray = new String[99];

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

                    if (!sesion[0].isEmpty()) {
                        Interfaz.imprimirTextoLineaSalto("5. Cerrar sesion");
                    }

                    Interfaz.imprimirLineaInfIzqDer();

                    System.out.print("  Ingrese su opcion: ");
                    opcionInicio = scan.nextInt();
                    scan.nextLine();

                    System.out.println();

                    if (opcionInicio == 5) {
                        sesion[0] = "";
                        sesion[1] = "";
                        sesion[2] = "";
                    }
                }
                ;

                if (opcionInicio == 4)
                    break;

                while (sesion[0].isEmpty()) {
                    opcionIniciarSesion = Administrador.iniciarSesion(scan, sesion, out, in);

                    if (opcionIniciarSesion == 2) {
                        opcionInicio = 0;
                        opcionIniciarSesion = 0;
                        break;
                    }
                }

                switch (opcionInicio) {
                    case 1:
                        if (sesion[2].toUpperCase().equals("A"))
                            opcionPanel = Administrador.menuAdmin(scan);
                        else {
                            Administrador.noAutorizado("Administrador", sesion[2]);
                            opcionPanel = 3;
                        }

                        if (opcionPanel == 3)
                            opcionInicio = 0;

                        if (opcionPanel == 1) {
                            opcionPanelCrear = Administrador.crearUsuario(scan, out, in);

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
                            opcionAdministrar = Administrador.mostrarUsuarios(scan, out, in);

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
                                opcionAdministrar = Administrador.modificarUsuario(scan, out, in);

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

                        break;

                    case 2:
                        int opcionEstudiante, opcionAdministrarPerfil = 0, opcionParticiparExamen = 0;

                        opcionEstudiante = Estudiante.mostrarMenu(scan);

                        switch (opcionEstudiante) {
                            case 1:
                                opcionAdministrarPerfil = Estudiante.administrarPerfil(scan);
                                break;

                            case 2:
                                opcionParticiparExamen = Estudiante.participarExamen(scan, sesion, out, in);

                                if (opcionParticiparExamen == 1) {

                                }
                                break;

                            default:
                                break;
                        }

                        break;

                    case 3:
                        int opcionDocente = 0;

                        opcionDocente = Docente.mostrarPanel(scan);

                        if (opcionDocente == 1) {
                            opcionCrearExamen = Docente.crearExamen(scan, sesion, out, in);

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

                            opcionVerExamenes = Docente.mostrarExamenes(scan, sesion, out, in);

                            if (opcionVerExamenes == 3) {
                                opcionInicio = 3;
                                opcionDocente = 0;
                                opcionVerExamenes = 0;
                            }

                            if (opcionVerExamenes == 3) {
                                opcionInicio = 0;
                                opcionDocente = 0;
                                opcionVerExamenes = 0;
                            }
                        }

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

                        if (opcionDocente == 5) {
                            opcionDocente = 0; // Regresa al inicio
                            break;
                        }
                        break;
                }
            }
        } catch (IOException e) {
            System.err.println("Error de conexión: " + e.getMessage());
        }
    }
} //
