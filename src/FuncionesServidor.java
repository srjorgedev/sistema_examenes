import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class FuncionesServidor {
    public static void guardarUsuario(String usuario) {
        try (Socket socket = new Socket("localhost", 5000);
                PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            // Mandar comando al servidor
            salida.println("REGISTRO_USUARIO");
            salida.println(usuario); // Datos del usuario
            salida.println("");

            StringBuilder respuestaServidor = new StringBuilder();
            String respuesta;
            while ((respuesta = entrada.readLine()) != null) {
                respuestaServidor.append(respuesta).append("\n");
                if (respuesta.contains("=== Fin de consulta ===")) {
                    break;
                }
            }
            String resultadoFinal = respuestaServidor.toString();

        } catch (IOException e) {
            System.out.println("Error al conectar con el servidor: " + e.getMessage());
        }
    }

    public static String[] leerArchivo(String archivo) {
        String rutaArchivo = "./" + archivo + ".txt";

        int numeroLineas = 0;
        String[] usuarios;

        // Contamos el número de líneas
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            while (br.readLine() != null) {
                numeroLineas++;
            }
        } catch (IOException e) {
            System.err.println("Error al contar líneas: " + e.getMessage());
            return usuarios = new String[0];
        }

        // Creamos el array con el tamaño exacto
        usuarios = new String[numeroLineas];

        // Luego, leemos el archivo de nuevo y guardamos cada línea en el array
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            for (int i = 0; i < numeroLineas; i++) {
                usuarios[i] = br.readLine();
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
            return usuarios = new String[0];
        }

        // Imprimimos el array para verificar
        for (String linea : usuarios) {
            System.out.println(linea);
        }

        return usuarios;
    }
}
