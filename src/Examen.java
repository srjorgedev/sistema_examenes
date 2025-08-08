import java.util.Scanner;

public class Examen {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Datos básicos del examen
        String id, nombre, fecha, tipo, materia, docente;

        // Preguntas y opciones
        int maxPreguntas = 5;
        String[] preguntas = new String[maxPreguntas];
        String[][] opciones = new String[maxPreguntas][4];
        String[] respuestasCorrectas = new String[maxPreguntas];
        int numPreguntas = 0;

        // Captura de datos generales
        System.out.print("ID: ");
        id = sc.nextLine();
        System.out.print("Nombre: ");
        nombre = sc.nextLine();
        System.out.print("Fecha: ");
        fecha = sc.nextLine();
        System.out.print("Tipo: ");
        tipo = sc.nextLine();
        System.out.print("Materia: ");
        materia = sc.nextLine();
        System.out.print("Docente: ");
        docente = sc.nextLine();

        // Captura de preguntas
        for (int i = 0; i < maxPreguntas; i++) {
            System.out.print("Pregunta " + (i + 1) + ": ");
            preguntas[i] = sc.nextLine();

            for (int j = 0; j < 4; j++) {
                System.out.print("Opción " + (j + 1) + ": ");
                opciones[i][j] = sc.nextLine();
            }

            System.out.print("Respuesta correcta (a/b/c/d): ");
            respuestasCorrectas[i] = sc.nextLine();

            numPreguntas++;
            System.out.print("¿Agregar otra pregunta? (s/n): ");
            if (sc.nextLine().equalsIgnoreCase("n")) {
                break;
            }
        }

        sc.close();
        System.out.println("\n--- EXAMEN ---");
        System.out.println("ID: " + id);
        System.out.println("Nombre: " + nombre);
        System.out.println("Fecha: " + fecha);
        System.out.println("Tipo: " + tipo);
        System.out.println("Materia: " + materia);
        System.out.println("Docente: " + docente);

        System.out.println("\nPreguntas:");
        for (int i = 0; i < numPreguntas; i++) {
            System.out.println((i + 1) + ". " + preguntas[i]);
            char letra = 'a';
            for (int j = 0; j < 4; j++) {
                System.out.println("   " + letra + ") " + opciones[i][j]);
                letra++;
            }
            System.out.println("   Respuesta correcta: " + respuestasCorrectas[i]);
        }
    }
}
