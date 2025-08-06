import java.util.ArrayList;

public class Examen {
    String id;
    String nombre;
    String fecha;
    String tipo;
    String materia;
    String docente;
    ArrayList<Pregunta> preguntas;

    public Examen(String id, String nombre, String fecha, String tipo, String materia, String docente) {
        this.id = id;
        this.nombre = nombre;
        this.fecha = fecha;
        this.tipo = tipo;
        this.materia = materia;
        this.docente = docente;
        this.preguntas = new ArrayList<>();
    }

    public void mostrarExamen() {
        System.out.println("ID: " + id);
        System.out.println("Nombre: " + nombre);
        System.out.println("Fecha: " + fecha);
        System.out.println("Tipo: " + tipo);
        System.out.println("Materia: " + materia);
        System.out.println("Docente: " + docente);
        System.out.println("Preguntas:");
        for (int i = 0; i < preguntas.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + preguntas.get(i).enunciado);
            preguntas.get(i).mostrarOpciones();
        }
    }
}
