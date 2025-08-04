import java.util.Scanner;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) {

        // Inicializar arrays con ArrayList
        ArrayList<String> usuarios = new ArrayList<>();

        // Crear datos de ejemplo
        Datos.crearUsuarios(usuarios);

        // Inicializar arrays
        String[] usuariosArray = new String[99];
        String[] historialUsuariosArray = new String[99];
        String[] examenInfoArray = new String[99];
        String[] examenPreguntasArray = new String[99];
        String[] examenReactivosArray = new String[99];
        String[] examenRespuestasArray = new String[99];

        // Crear datos de ejemplo
        Datos.crearUsuarios(usuariosArray);

        // Variables de captura de datos del usuario
        String usuarioNombre, usuarioPass, usuarioId, usuarioTipo;

        // Instancia del Scanner
        Scanner scan = new Scanner(System.in);

        // Variables para gestionar el flujo del sistema
        int opcionInicio, opcionAdmin, opcionEstudiante, opcionProfesor;

        // Formato de incersion de datos a los arrays
        // String txt =
        // "HOLA.-.Me.-.LLAMO.-.JAVIER.-.Y.-.ESTOY.-.EN.-.JAVA.-.CON.-.UN.-.PROYECTO.-.DE.-.EXAMENES";
        // String[] palabras = txt.split("\\.-\\.");
        // System.out.println(palabras[2]);

        /*
         * Examen Info
         * ID Nombre Fecha Tipo Materia Docente
         * 00000001.-.Quimica 2.-.29/Julio/25.-.Ordinario.-.Quimica.-.Javier Perez
         * 
         * Examen Preguntas
         * ID Preguntas
         * 00000001.-.¿Quien fue Cristobal Colon?.-.¿Cuando se descubrio america?
         * 
         * Examen Reactivos
         * 00000001.-.a) Mi padre b) Un puñetas c) Dios.-.a) Ayer b) Hoy c) Mañana
         * 
         * Examen Respuestas
         * 00000001.-.a).-.b)c)
         */

        do {
            System.out.println();
            Interfaz.imprimirTitulo("Bienvenido al sistema de examenes");
            Interfaz.imprimirTextoLineaSalto("Seleccione su usuario:");
            Interfaz.imprimirBordeIzqDer();
            Interfaz.imprimirTextoLineaSalto("1. Administrador");
            Interfaz.imprimirTextoLineaSalto("2. Estudiante");
            Interfaz.imprimirTextoLineaSalto("3. Profesor");
            Interfaz.imprimirBordeIzqDer();
            Interfaz.imprimirLineaInfIzqDer();

            System.out.print("  Ingrese su opcion: ");
            opcionInicio = scan.nextInt();
        } while (!(opcionInicio == 1 || opcionInicio == 2 || opcionInicio == 3));

        if (opcionInicio == 1) {
            do {
                System.out.println();
                Interfaz.imprimirLineaSupIzqDer();
                Interfaz.imprimirTextoLineaSalto("Panel de Administrador");
                Interfaz.imprimirLineaConexion();
                Interfaz.imprimirTextoLineaSalto("Acciones disponibles:");
                Interfaz.imprimirBordeIzqDer();
                Interfaz.imprimirTextoLineaSalto("1. Crear usuarios");
                Interfaz.imprimirTextoLineaSalto("2. Administrar usuarios");

                Interfaz.imprimirBordeIzqDer();
                Interfaz.imprimirLineaInfIzqDer();

                System.out.print("  Ingrese su opcion: ");
                opcionAdmin = scan.nextInt();
            } while (!(opcionAdmin == 1 || opcionAdmin == 2));

            if (opcionAdmin == 1) {
                do {
                    usuarioId = "0".repeat(8 - String.valueOf(usuarios.size()).length()) + usuarios.size();

                    System.out.println();
                    Interfaz.imprimirLineaSupIzqDer();
                    Interfaz.imprimirTextoLineaSalto("Crear Usuario");
                    Interfaz.imprimirLineaConexion();

                    Interfaz.imprimirTextoLineaSalto("ID: " + usuarioId);
                    do {
                        Interfaz.imprimirTextoLineaSalto("Tipo -> (E)studiante     (D)ocente");
                        System.out.print("    Tipo: ");
                        scan.nextLine();
                        usuarioTipo = scan.nextLine();
                    } while (!(usuarioTipo.equals("D") || usuarioTipo.equals("E")
                            || (usuarioTipo.equals("d") || usuarioTipo.equals("e"))));

                    System.out.print("    Nombre: ");
                    usuarioNombre = scan.nextLine();

                    System.out.print("    Contraseña: ");
                    usuarioPass = scan.nextLine();

                    Interfaz.imprimirLineaConexion();
                    Interfaz.imprimirTextoLineaSalto("Usuario creado.");
                    Interfaz.imprimirLineaConexion();
                    Interfaz.imprimirTextoLineaSalto("1. Crear otro usuario");
                    Interfaz.imprimirTextoLineaSalto("2. Volver al panel de administrador");
                    Interfaz.imprimirTextoLineaSalto("3. Volver al inicio");

                    Interfaz.imprimirLineaInfIzqDer();

                    System.out.print("  Ingrese su opcion: ");
                    opcionEstudiante = scan.nextInt();

                    String nuevoUsuario = usuarioTipo.toUpperCase() + ".-." + usuarioId + ".-." + usuarioNombre + ".-."
                            + usuarioPass;
                    usuarios.add(nuevoUsuario);
                } while (opcionEstudiante > 0 && opcionEstudiante < 2);
            }

            if (opcionAdmin == 2) {
                do {
                    System.out.println();
                    Interfaz.imprimirLineaSupIzqDer();
                    Interfaz.imprimirTextoLineaSalto("Administrar Usuarios");
                    Interfaz.imprimirLineaConexion();

                    Interfaz.imprimirTextoLineaSalto("Tipo  ID           Nombre");

                    for (String usuarioString : usuarios) {
                        String[] usuarioDatos = usuarioString.split("\\.-\\.");

                        for (int i = 0; i < usuarioDatos.length; i++) {
                            if (i == 0)
                                System.out.print("    ");

                            System.out.print(usuarioDatos[i] + "     ");

                            if (i == usuarioDatos.length - 2)
                                System.out.print("\t");
                            if (i == usuarioDatos.length - 1)
                                System.out.println();
                        }
                    }

                    Interfaz.imprimirTextoLineaSalto("1. Crear otro usuario");
                    Interfaz.imprimirTextoLineaSalto("2. Volver al panel de administrador");
                    Interfaz.imprimirTextoLineaSalto("3. Volver al inicio");

                    Interfaz.imprimirLineaInfIzqDer();

                    System.out.print("  Ingrese su opcion: ");
                    opcionEstudiante = scan.nextInt();

                } while (opcionEstudiante > 0 && opcionEstudiante < 2);
            }
        }

        scan.close();
    }
}