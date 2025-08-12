import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
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

    public static int crearUsuario(Scanner scan, String[] usuariosArray, int[] indices) {
        int opcionPanelCrear = 0;
        String usuarioId, usuarioTipo, usuarioNombre, usuarioPass;

        while (opcionPanelCrear == 0
                || opcionPanelCrear == 1
                || !(opcionPanelCrear >= 2 && opcionPanelCrear <= 3)) {

            usuarioId = "0".repeat(4 - String.valueOf(indices[0]).length())
                    + indices[0];

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

            Array.agregarUsuario(usuariosArray, nuevoUsuario, indices);

            if (opcionPanelCrear == 2 || opcionPanelCrear == 3) {
                break;
            }
        }

        return opcionPanelCrear;
    }

    // public static void iniciarSesion() {}

    public static int mostrarUsuarios(Scanner scan, String[] usuariosArray, int[] indices) {
        String regex = "\\.-\\.";

        int nombreMasLargo = 0;
        int opcionAdministrar = 0;

        while (opcionAdministrar == 0
                || !(opcionAdministrar == 1 || opcionAdministrar == 2 || opcionAdministrar == 3)) {
            System.out.println();
            Interfaz.imprimirLineaSupIzqDer();
            Interfaz.imprimirTextoLineaSalto("Administrar Usuarios");
            Interfaz.imprimirLineaConexion();

            Interfaz.imprimirTextoLineaSalto("Tipo    ID       Nombre                 Clave");
            Interfaz.imprimirLineaInfIzqDer();

            System.out.println();

            for (int i = 0; i < indices[0]; i++) {
                if (usuariosArray[i].isEmpty())
                    continue; // Si el valor del array esta vacio pasa a la siguiente iteracion, para que no
                              // de error.

                String[] datos = usuariosArray[i].split(regex);
                if (datos[2].length() > nombreMasLargo)
                    nombreMasLargo = datos[2].length() + 2; // Almacena la longitud del nombre mas largo de un
                // usuario
            }

            for (int i = 0; i < indices[0]; i++) {
                if (usuariosArray[i].isEmpty())
                    continue; // Si el valor del array esta vacio pasa a la siguiente iteracion, para que no
                              // de error.

                String[] datos = usuariosArray[i].split(regex);
                String usuarioTexto = "     " + datos[0] + "      " + datos[1] + " "
                        + datos[2] + " ".repeat(nombreMasLargo - datos[2].length()) + "\t" + datos[3];
                System.out.println(usuarioTexto);
            }

            System.out.println();

            Interfaz.imprimirLineaSupIzqDer();
            Interfaz.imprimirTextoLineaSalto("Acciones disponibles:");
            Interfaz.imprimirTextoLineaSalto("1. Modificar usuario");
            Interfaz.imprimirTextoLineaSalto("2. Eliminar usuario");

            Interfaz.imprimirBordeIzqDer();
            Interfaz.imprimirTextoLineaSalto("3. Volver al panel de administrador");
            Interfaz.imprimirTextoLineaSalto("4. Volver al inicio");

            Interfaz.imprimirLineaInfIzqDer();

            System.out.print("  Ingrese su opcion: ");
            opcionAdministrar = scan.nextInt();
            scan.nextLine();

            System.out.println();

            if (opcionAdministrar == 3 || opcionAdministrar == 4) break;
        }

        return opcionAdministrar;
    }
}
