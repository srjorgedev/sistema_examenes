import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class FuncionesServidor {
    public static String subirAlServidor(PrintWriter out, BufferedReader in, String comando, String datos)
            throws IOException {
        out.println(comando.toUpperCase());
        out.println(datos);

        StringBuilder respuestaServidor = new StringBuilder();
        String linea;
        while ((linea = in.readLine()) != null) {
            if (linea.equals("=== Fin de consulta ==="))
                break;
            respuestaServidor.append(linea).append("\n");
        }

        return respuestaServidor.toString().trim();
    }

    public static String subirAlServidor(PrintWriter out, BufferedReader in, String comando, String datos[])
            throws IOException {
        out.println(comando.toUpperCase());

        for (String dato : datos) {
            out.println(dato);
        }

        StringBuilder respuestaServidor = new StringBuilder();
        String linea;
        while ((linea = in.readLine()) != null) {
            if (linea.equals("=== Fin de consulta ==="))
                break;
            respuestaServidor.append(linea).append("\n");
        }

        return respuestaServidor.toString().trim();
    }

    public static String obtenerDelServidor(PrintWriter out, BufferedReader in, String comando)
            throws IOException {
        out.println(comando.toUpperCase());

        StringBuilder respuestaServidor = new StringBuilder();
        String linea;
        while ((linea = in.readLine()) != null) {
            if (linea.equals("=== Fin de consulta ==="))
                break;
            respuestaServidor.append(linea).append("\n");
        }

        return respuestaServidor.toString().trim();
    }

    public static String obtenerDelServidor(PrintWriter out, BufferedReader in, String comando, String datos)
            throws IOException {
        out.println(comando.toUpperCase());
        out.println(datos);

        StringBuilder respuestaServidor = new StringBuilder();
        String linea;
        while ((linea = in.readLine()) != null) {
            if (linea.equals("=== Fin de consulta ==="))
                break;
            respuestaServidor.append(linea).append("\n");
        }

        return respuestaServidor.toString().trim();
    }

}
