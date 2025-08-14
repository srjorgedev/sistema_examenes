// Importamos librerías para entrada/salida y manejo de sockets
import java.io.*;
import java.net.*;

public class Servidor {
    // Contador para saber cuántos clientes están conectados en este momento
    private static int clientesConectados = 0;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(5000)) { 
            // Creamos un ServerSocket escuchando en el puerto 5000
            System.out.println("Servidor iniciado...");

            // Bucle infinito para aceptar conexiones
            while (true) {
                // Espera a que un cliente se conecte
                Socket socket = serverSocket.accept();

                // Incrementa el contador y muestra cuántos clientes hay
                clientesConectados++;
                System.out.println("Nuevo cliente conectado. Clientes activos: " + clientesConectados);

                // Creamos un hilo para manejar al cliente de forma independiente
                new Thread(() -> {
                    try (
                        // Canal de entrada para leer datos del cliente
                        BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        // Canal de salida para enviar datos al cliente
                        PrintWriter salida = new PrintWriter(socket.getOutputStream(), true)
                    ) {
                        // Leemos el primer comando que manda el cliente
                        String comando = entrada.readLine();

                        // --- Registro de Usuarios ---
                        if ("REGISTRO_USUARIO".equalsIgnoreCase(comando)) {
                            // Abrimos el archivo para agregar info de usuario
                            PrintWriter archivo = new PrintWriter(new FileWriter("Usuarios.txt", true));
                            archivo.println("===== Nuevo Registro =====");

                            // Leemos los datos enviados por el cliente hasta que mande una línea vacía
                            String linea;
                            while ((linea = entrada.readLine()) != null && !linea.isEmpty()) {
                                archivo.println(linea);
                            }
                            archivo.println("---");
                            archivo.close();

                            // Confirmación al cliente
                            salida.println("Usuario guardado correctamente");

                            // Le mandamos todo el archivo de usuarios
                            BufferedReader lector = new BufferedReader(new FileReader("Usuarios.txt"));
                            String registro;
                            while ((registro = lector.readLine()) != null) {
                                salida.println(registro);
                            }
                            lector.close();

                            salida.println("=== Fin de consulta ===");
                        }

                        // --- Registro de Exámenes ---
                        if ("REGISTRO_EXAMENES".equalsIgnoreCase(comando)) {
                            PrintWriter archivoE = new PrintWriter(new FileWriter("Examenes.txt", true));
                            archivoE.println("===== Nuevo Examen =====");

                            String lineaE;
                            while ((lineaE = entrada.readLine()) != null && !lineaE.isEmpty()) {
                                archivoE.println(lineaE);
                            }
                            archivoE.println("---");
                            archivoE.close();

                            salida.println("Examen guardado correctamente");

                            BufferedReader lector = new BufferedReader(new FileReader("Examenes.txt"));
                            String registro;
                            while ((registro = lector.readLine()) != null) {
                                salida.println(registro);
                            }
                            lector.close();

                            salida.println("=== Fin de consulta ===");
                        }

                        // --- Registro de Calificaciones ---
                        if ("REGISTRO_CALIFICACIONES".equalsIgnoreCase(comando)) {
                            PrintWriter archivoE = new PrintWriter(new FileWriter("Calificaciones.txt", true));
                            archivoE.println("===== Calificaciones =====");

                            String linea;
                            while ((linea = entrada.readLine()) != null && !linea.isEmpty()) {
                                archivoE.println(linea);
                            }
                            archivoE.println("---");
                            archivoE.close();

                            salida.println("Calificaiones agregadas correctamente");

                            BufferedReader lector = new BufferedReader(new FileReader("Calificaciones.txt"));
                            String registro;
                            while ((registro = lector.readLine()) != null) {
                                salida.println(registro);
                            }
                            lector.close();

                            salida.println("=== Fin de consulta ===");
                        }

                    } catch (IOException e) {
                        // Por si hay algún error en la conexión con el cliente
                        System.out.println("Error con cliente: " + e.getMessage());
                    } finally {
                        try {
                            // Cerramos el socket del cliente
                            socket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        // Disminuimos el contador de clientes
                        clientesConectados--;
                        System.out.println("Cliente desconectado. Clientes activos: " + clientesConectados);
                    }
                }).start(); // Arrancamos el hilo
            }

        } catch (IOException e) {
            // Si hay un error al iniciar el servidor
            System.out.println("Error en el servidor: " + e.getMessage());
        }
    }
}
