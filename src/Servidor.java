// Servidor.java
import java.io.*;
import java.net.*;

public class Servidor {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            System.out.println("Servidor iniciado en puerto 5000...");

            while (true) {
                try (Socket socket = serverSocket.accept();
                     BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                     PrintWriter salida = new PrintWriter(socket.getOutputStream(), true)) {

                    String comando = entrada.readLine();

                    if ("REGISTRO_USUARIO".equalsIgnoreCase(comando)) {
                        // Guardar usuario en archivo
                        PrintWriter archivo = new PrintWriter(new FileWriter("Usuarios.txt", true));
                        archivo.println("===== Nuevo Registro =====");

                        String linea;
                        while ((linea = entrada.readLine()) != null && !linea.isEmpty()) {
                            archivo.println(linea);
                        }
                        archivo.println("---");
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
                }
            }
        } catch (IOException e) {
            System.out.println("Error en el servidor: " + e.getMessage());
        
            }

            
    
}
}