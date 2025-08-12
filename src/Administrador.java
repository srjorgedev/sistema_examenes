import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Administrador {
    public static int menuAdmin(Scanner scan) {
        int opcionPanel = 0;

        while (opcionPanel == 0 || !(opcionPanel == 1 || opcionPanel == 2 || opcionPanel == 3)) {
            System.out.println();
            Interfaz.imprimirLineaSupIzqDer();
            Interfaz.imprimirTextoLineaSalto("Panel de Administrador");
            Interfaz.imprimirLineaConexion();
            Interfaz.imprimirTextoLineaSalto("Acciones disponibles:");
            Interfaz.imprimirBordeIzqDer();
            Interfaz.imprimirTextoLineaSalto("1. Crear usuarios");
            Interfaz.imprimirTextoLineaSalto("2. Administrar usuarios");
            Interfaz.imprimirBordeIzqDer();
            Interfaz.imprimirTextoLineaSalto("3. Volver al inicio");

            Interfaz.imprimirBordeIzqDer();
            Interfaz.imprimirLineaInfIzqDer();

            System.out.print("\n  Ingrese su opcion: ");
            opcionPanel = scan.nextInt();
            scan.nextLine();

            System.out.println();

            if (opcionPanel == 3) {
                break;
            }
        }

        return opcionPanel;
    }

    public static int crearUsuario(Scanner scan, String[] usuariosArray) {
        int opcionPanelCrear = 0;
        String usuarioId, usuarioTipo, usuarioNombre, usuarioPass;

        while (opcionPanelCrear == 0
                || opcionPanelCrear == 1
                || !(opcionPanelCrear >= 2 && opcionPanelCrear <= 3)) {

            usuarioId = "0".repeat(4 - String.valueOf(Array.usuariosIndiceActual).length())
                    + Array.usuariosIndiceActual;

            System.out.println();
            Interfaz.imprimirLineaSupIzqDer();
            Interfaz.imprimirTextoLineaSalto("Crear Usuario");
            Interfaz.imprimirLineaConexion();

            Interfaz.imprimirTextoLineaSalto("ID: " + usuarioId);
            Interfaz.imprimirTextoLineaSalto("Tipo -> (E)studiante     (D)ocente");

            do {
                System.out.print("    Tipo: ");
                usuarioTipo = scan.nextLine();
            } while (!(usuarioTipo.toUpperCase().equals("D") || usuarioTipo.toUpperCase().equals("E")));

            do {
                System.out.print("    Nombre: ");
                usuarioNombre = scan.nextLine();
                if (usuarioNombre.trim().isEmpty()) {
                    System.out.println("    El nombre no puede estar vacío. Intente de nuevo.");
                }
            } while (usuarioNombre.trim().isEmpty());

            System.out.print("    Clave: ");
            usuarioPass = scan.nextLine();

            Interfaz.imprimirLineaConexion();
            Interfaz.imprimirTextoLineaSalto("Usuario creado.");
            Interfaz.imprimirLineaConexion();
            Interfaz.imprimirTextoLineaSalto("1. Crear otro usuario");
            Interfaz.imprimirTextoLineaSalto("2. Volver al panel de administrador");
            Interfaz.imprimirTextoLineaSalto("3. Volver al inicio");
            Interfaz.imprimirLineaInfIzqDer();

            System.out.print("  Ingrese su opcion: ");
            opcionPanelCrear = scan.nextInt();
            scan.nextLine();

            System.out.println();

            String nuevoUsuario = usuarioTipo.toUpperCase() + ".-." + usuarioId + ".-."
                    + usuarioNombre + ".-." + usuarioPass;

            try (Socket socket = new Socket("localhost", 5000);
     PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
     BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

    // Mandar comando al servidor
    salida.println("REGISTRO_USUARIO");
    salida.println(nuevoUsuario); // Datos del usuario
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


Array.agregarUsuario(usuariosArray, nuevoUsuario);

            if (opcionPanelCrear == 2 || opcionPanelCrear == 3) {
                break;
            }

        }

        return opcionPanelCrear;
    }

    // public static int mostrarUsuarios() {

    // }
}
