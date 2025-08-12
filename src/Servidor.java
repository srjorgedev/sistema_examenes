import java.io.*;
import java.net.*;

public class Servidor {
    public static void main(String[] args) throws IOException {
        ServerSocket servidor = new ServerSocket(5000);
        System.out.println("Servidor esperando conexiones...");

        // Crear carpetas si no existen
        new File("usuarios").mkdirs();
        new File("examenes").mkdirs();
        new File("calificaciones").mkdirs();

        while (true) {
            Socket socket = servidor.accept();
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);

            String comando = entrada.readLine(); // Lee la opción enviada por el cliente

            if (comando.equalsIgnoreCase("REGISTRO_USUARIO")) {
                String nombreArchivo = entrada.readLine(); // Nombre del archivo

                StringBuilder datosUsuario = new StringBuilder();
                String linea;  // Datos del usuario
                while ((linea = entrada.readLine()) != null && !linea.isEmpty()) {
                    datosUsuario.append(linea).append("\n");
                }
                File archivo = new File("usuarios/" + nombreArchivo);
                try (PrintWriter pw = new PrintWriter(new FileWriter(archivo, true))) { // true para agregar si ya existe
                    pw.println(datosUsuario);
                    salida.println("Usuario guardado correctamente en " + nombreArchivo+".txt");
                } catch (Exception e) {
                    salida.println("Error al guardar el usuario: " + e.getMessage());
                }
            }

            if (comando.equalsIgnoreCase("GUARDAR_EXAMENES")) {
                String nombreArchivo = entrada.readLine(); // Nombre del archivo

                // Leer todas las líneas de exámenes hasta que no haya más o llegue una línea vacía
                StringBuilder datosExamenes = new StringBuilder();
                String linea;
                while ((linea = entrada.readLine()) != null && !linea.isEmpty()) {
                    datosExamenes.append(linea).append("\n");
                }

                File archivo = new File("examenes/" + nombreArchivo);
                try (PrintWriter pw = new PrintWriter(new FileWriter(archivo, true))) {
                    pw.print(datosExamenes.toString());
                    salida.println("Examenes guardados correctamente en " + nombreArchivo);
                } catch (Exception e) {
                    salida.println("Error al guardar los examenes: " + e.getMessage());
                }
            }

            socket.close();
        }
    }
}