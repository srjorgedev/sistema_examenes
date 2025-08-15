
// Servidor.java
import java.io.*;
import java.net.*;

public class Servidor {

    public static void main(String[] args) {
        // Inicializar arrays
        String[] usuariosArray = new String[99];
        String[] historialUsuariosArray = new String[99];

        String[] cursosArray = new String[100];
        String[] temasArray = new String[100];

        String regex = "\\.-\\."; // para separar un texto por cada .-.

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

        Datos.crearUsuarios(usuariosArray, indice);
        Datos.crearExamenes(examenMatriz, indice);

        try (ServerSocket serverSocket = new ServerSocket(5000)) {

            System.out.println("Servidor iniciado...");

            while (true) {

                try (Socket socket = serverSocket.accept();
                        BufferedReader entrada = new BufferedReader(
                                new InputStreamReader(socket.getInputStream()));
                        PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);) {
                    // Incrementar y mostrar cuántos clientes hay

                    System.out.println("Nuevo cliente conectado. Cliente: " + socket.getInetAddress());

                    String comando;

                    while ((comando = entrada.readLine()) != null) {
                        System.out.println(
                                "Nuevo comando recibido desde " + socket.getInetAddress() + ", comando: " + comando);

                        switch (comando) {
                            case "REGISTRO_USUARIO":
                                Array.agregarUsuario(usuariosArray, entrada.readLine(), indice);

                                salida.println("=== Fin de consulta ===");
                                break;

                            case "REGISTRO_HISTORIAL":
                                Array.agregarHistorial(historialUsuariosArray, entrada.readLine(), indice);

                                salida.println("=== Fin de consulta ===");
                                break;

                            case "MODIFICAR_USUARIO":
                                String nuevosDatos = entrada.readLine();
                                int indiceModificar = Integer.parseInt(entrada.readLine());

                                Array.modificar(usuariosArray, indiceModificar, nuevosDatos);
                                salida.println("=== Fin de consulta ===");

                                break;

                            case "ELIMINAR_USUARIO":
                                String usuarioBorrar = entrada.readLine();

                                for (int i = 0; i < usuariosArray.length; i++) {
                                    if (usuariosArray[i].equals(usuarioBorrar)) {
                                        Array.borrar(usuariosArray, i);
                                        break;
                                    }
                                }

                                salida.println("=== Fin de consulta ===");

                                break;

                            case "CREAR_EXAMEN":
                                String examenInfo = entrada.readLine();
                                String examenPreguntas = entrada.readLine();
                                String examenReactivos = entrada.readLine();
                                String examenRespuestas = entrada.readLine();

                                System.out.println(examenInfo);
                                System.out.println(examenPreguntas);
                                System.out.println(examenReactivos);
                                System.out.println(examenRespuestas);

                                Array.agregarExamen(examenMatriz, new String[] {
                                        examenInfo,
                                        examenPreguntas,
                                        examenReactivos,
                                        examenRespuestas
                                }, indice);

                                salida.println("=== Fin de consulta ===");

                                break;

                            case "INICIAR_SESION":
                                String usuarioSesionID = entrada.readLine();
                                String usuarioSesionClave = entrada.readLine();
                                boolean encontrado = false;

                                for (int i = 0; i < indice[0]; i++) {
                                    if (usuariosArray[i] == null || usuariosArray[i].isEmpty())
                                        continue;

                                    String[] datos = usuariosArray[i].split(regex);
                                    if (datos[1].equals(usuarioSesionID) && datos[3].equals(usuarioSesionClave)) {
                                        salida.println(usuariosArray[i]);
                                        encontrado = true;
                                        break;
                                    }
                                }

                                if (!encontrado) {
                                    salida.println("USUARIO_NO_ENCONTRADO");
                                }
                                salida.println("=== Fin de consulta ===");
                                break;

                            case "OBTENER_USUARIO_INDICE_ACTUAL":
                                salida.println(indice[0]);
                                salida.println("=== Fin de consulta ===");
                                break;

                            case "OBTENER_EXAMEN_INDICE_ACTUAL":
                                salida.println(indice[2]);
                                salida.println("=== Fin de consulta ===");
                                break;

                            case "OBTENER_CURSO_INDICE_ACTUAL":
                                salida.println(indice[3]);
                                salida.println("=== Fin de consulta ===");
                                break;

                            case "OBTENER_TEMA_INDICE_ACTUAL":
                                salida.println(indice[4]);
                                salida.println("=== Fin de consulta ===");
                                break;

                            case "OBTENER_HISTORIAL":
                                String usuarioID = entrada.readLine();

                                for (int i = 0; i < indice[0]; i++) {
                                    if (!historialUsuariosArray[i].contains(usuarioID))
                                        continue;

                                    salida.println(historialUsuariosArray[i]);
                                }

                                salida.println("=== Fin de consulta ===");
                                break;

                            case "OBTENER_USUARIOS":
                                for (int i = 0; i < indice[0]; i++) {
                                    if (usuariosArray[i].isEmpty())
                                        continue;

                                    salida.println(usuariosArray[i]);
                                }

                                salida.println("=== Fin de consulta ===");
                                break;

                            case "OBTENER_CURSOS":
                                for (int i = 0; i < indice[3]; i++) {
                                    if (cursosArray[i].isEmpty())
                                        continue;

                                    salida.println(cursosArray[i]);
                                }

                                salida.println("=== Fin de consulta ===");
                                break;

                            case "OBTENER_TEMAS":
                                for (int i = 0; i < indice[4]; i++) {
                                    if (temasArray[i].isEmpty())
                                        continue;

                                    salida.println(temasArray[i]);
                                }

                                salida.println("=== Fin de consulta ===");
                                break;

                            case "OBTENER_EXAMENES":
                                for (int i = 0; i < indice[2]; i++) {
                                    String fila = String.join(";", examenMatriz[i]);
                                    salida.println(fila);
                                }

                                salida.println("=== Fin de consulta ===");
                                break;

                            case "CREAR_CURSO":
                                Array.agregarCurso(cursosArray, entrada.readLine(), indice);
                                salida.println("=== Fin de consulta ===");

                                break;

                            case "CREAR_TEMA":
                                Array.agregarTema(temasArray, entrada.readLine(), indice);
                                salida.println("=== Fin de consulta ===");

                                break;

                            default:
                                break;
                        }
                    }

                } catch (Exception e) {
                    // TODO: handle exception
                }

            }

        } catch (IOException e) {
            System.out.println("Error en el servidor: " + e.getMessage());
        }
    }

}
