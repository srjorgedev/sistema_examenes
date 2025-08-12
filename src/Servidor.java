
// Servidor.java
import java.io.*;
import java.net.*;

public class Servidor {
    private static int clientesConectados = 0;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            System.out.println("Servidor iniciado...");

            while (true) {

                Socket socket = serverSocket.accept();

                // Incrementar y mostrar cuántos clientes hay
                clientesConectados++;
                System.out.println("Nuevo cliente conectado. Clientes activos: " + clientesConectados);

                // Manejar cliente en un hilo separado
                new Thread(() -> {
                    try (BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                            PrintWriter salida = new PrintWriter(socket.getOutputStream(), true)) {

                        String comando = entrada.readLine();

                        if ("REGISTRO_USUARIO".equalsIgnoreCase(comando)) {
                            // Guardar usuario en archivo
                            PrintWriter archivo = new PrintWriter(new FileWriter("Usuarios.txt", true));

                            String linea;
                            while ((linea = entrada.readLine()) != null && !linea.isEmpty()) {
                                archivo.println(linea);
                            }

                            archivo.close();

                            // Confirmar
                            salida.println("Usuario guardado correctamente");

                            // Mandar automáticamente el contenido del archivo
                            BufferedReader lector = new BufferedReader(new FileReader("Usuarios.txt"));
                            String registro;
                            while ((registro = lector.readLine()) != null) {
                                salida.println(registro);
                            }
                            lector.close();

                            salida.println("=== Fin de consulta ===");
                        }
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
                        System.out.println("Error con cliente: " + e.getMessage());
                    } finally {
                        try {
                            socket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        // Decrementar y mostrar cuántos clientes hay
                        clientesConectados--;
                        System.out.println("Cliente desconectado. Clientes activos: " + clientesConectados);
                    }
                }).start();
            }

        } catch (IOException e) {
            System.out.println("Error en el servidor: " + e.getMessage());
        }
    }
}
