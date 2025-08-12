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

            if (opcionAdministrar == 3 || opcionAdministrar == 4)
                break;
        }

        return opcionAdministrar;
    }

    public static int modificarUsuario(Scanner scan, String[] usuariosArray, int[] indices) {
        String regex = "\\.-\\."; // Expresion regular para separar los string que estan unidos por .-. en un
                                  // array

        int opcionAdministrar = 0;
        String idModificar;

        Interfaz.imprimirTitulo("Modificar Usuario");
        Interfaz.imprimirBordeIzqDer();

        do {
            System.out.print("    ID: ");
            idModificar = scan.nextLine();
        } while (!idModificar.matches("^\\d{4}$"));

        boolean usuarioEncontrado = false;

        for (int i = 0; i < indices[0]; i++) {
            String[] datos = usuariosArray[i].split(regex);

            if (datos[1].equals(idModificar)) {
                usuarioEncontrado = true;
                Interfaz.imprimirLineaConexion();

                Interfaz.imprimirTextoLineaSalto("Usuario encontrado, datos: ");
                Interfaz.imprimirTextoLineaSalto("Nombre: " + datos[2]);
                Interfaz.imprimirTextoLineaSalto("Clave: " + datos[3]);
                Interfaz.imprimirTextoLineaSalto("Tipo: " + datos[0].toUpperCase());
                Interfaz.imprimirLineaConexion();

                Interfaz.imprimirTextoLineaSalto("Nota: Si un campo no sera modificado, dejar en blanco.");
                Interfaz.imprimirLineaConexion();

                System.out.print("    Nuevo nombre: ");
                String nuevoNombre = scan.nextLine();

                System.out.print("    Nueva clave: ");
                String nuevaClave = scan.nextLine();

                if (!nuevoNombre.isEmpty())
                    datos[2] = nuevoNombre;
                if (!nuevaClave.isEmpty())
                    datos[3] = nuevaClave;

                String nuevoUsuario = datos[0] + ".-." + datos[1] + ".-." + datos[2] + ".-." + datos[3];
                Array.modificar(usuariosArray, i, nuevoUsuario);

                Interfaz.imprimirLineaConexion();
                Interfaz.imprimirTextoLineaSalto("Usuario modificado correctamente.");
                break;
            }

        }

        Interfaz.imprimirLineaConexion();
        if (!usuarioEncontrado) {
            Interfaz.imprimirTextoLineaSalto("Usuario no encontrado.");
            Interfaz.imprimirLineaConexion();
            Interfaz.imprimirTextoLineaSalto("1. Intentar otra vez");
            Interfaz.imprimirTextoLineaSalto("2. Salir");
            Interfaz.imprimirLineaInfIzqDer();

            System.out.print("  Ingrese su opcion: ");
            opcionAdministrar = scan.nextInt();
            scan.nextLine();

            System.out.println();

            if (opcionAdministrar == 2)
                return opcionAdministrar;
        }

        if (usuarioEncontrado) {
            Interfaz.imprimirTextoLineaSalto("1. Modificar otro usuario");
            Interfaz.imprimirTextoLineaSalto("2. Volver al panel de administrador");
            Interfaz.imprimirTextoLineaSalto("3. Volver al inicio");

            Interfaz.imprimirLineaInfIzqDer();

            System.out.print("  Ingrese su opcion: ");
            opcionAdministrar = scan.nextInt();
            scan.nextLine();

            System.out.println();

        }

        return opcionAdministrar;
    }

    public static int borrarUsuario(Scanner scan, String[] usuariosArray, int[] indices) {
        int indiceBorrar = 0, borrarOpcion = 0, opcionBorrar = 0;
        String regex = "\\.-\\.";
        String idBorrar;

        Interfaz.imprimirTitulo("Borrar Usuario");

        do {
            System.out.print("    ID: ");
            idBorrar = scan.nextLine();
        } while (!idBorrar.matches("^\\d{4}$"));

        boolean usuarioEncontrado = false;
        String[] datos = null;

        for (int i = 0; i < indices[0]; i++) {
            if (usuariosArray[i].isEmpty())
                continue;

            datos = usuariosArray[i].split(regex);
            indiceBorrar = i;

            if (datos[1].equals(idBorrar)) {
                usuarioEncontrado = true;
                break;
            }
        }

        Interfaz.imprimirLineaConexion();

        if (usuarioEncontrado) {
            Interfaz.imprimirTextoLineaSalto("Usuario encontrado, datos: ");
            Interfaz.imprimirTextoLineaSalto("Nombre: " + datos[2]);
            Interfaz.imprimirTextoLineaSalto("Clave: " + datos[3]);
            Interfaz.imprimirTextoLineaSalto("Tipo: " + datos[0].toUpperCase());
            Interfaz.imprimirLineaConexion();

            Interfaz.imprimirTextoLineaSalto("Esta seguro de borrar el usuario?");
            Interfaz.imprimirTextoLineaSalto("1. Si");
            Interfaz.imprimirTextoLineaSalto("2. No");
            Interfaz.imprimirLineaInfIzqDer();

            System.out.print("  Ingrese su opcion: ");
            borrarOpcion = scan.nextInt();
            scan.nextLine();

            System.out.println();

            if (borrarOpcion == 1)
                Array.borrar(usuariosArray, indiceBorrar);

            Interfaz.imprimirLineaConexion();
            Interfaz.imprimirTextoLineaSalto("Usuario borrado correctamente.");

            Interfaz.imprimirLineaConexion();
            Interfaz.imprimirTextoLineaSalto("Acciones disponibles:");
            Interfaz.imprimirBordeIzqDer();

            Interfaz.imprimirTextoLineaSalto("1. Borrar a otro usuario");
            Interfaz.imprimirTextoLineaSalto("2. Volver al panel de administrador");
            Interfaz.imprimirTextoLineaSalto("3. Volver al inicio");

            Interfaz.imprimirLineaInfIzqDer();

            System.out.print("  Ingrese su opcion: ");
            opcionBorrar = scan.nextInt();
            scan.nextLine();

            System.out.println();
        }

        if (!usuarioEncontrado) {
            Interfaz.imprimirTextoLineaSalto("Usuario no encontrado.");
            Interfaz.imprimirLineaConexion();
            Interfaz.imprimirTextoLineaSalto("1. Intentar otra vez");
            Interfaz.imprimirTextoLineaSalto("2. Salir");
            Interfaz.imprimirLineaInfIzqDer();

            System.out.print("  Ingrese su opcion: ");
            opcionBorrar = scan.nextInt();
            scan.nextLine();

            System.out.println();
        }

        return opcionBorrar;
    }
}
