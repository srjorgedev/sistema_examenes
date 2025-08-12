import java.io.BufferedReader;
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
}
