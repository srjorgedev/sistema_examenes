import java.util.ArrayList;

public class Pregunta {
    String enunciado;
    ArrayList<String> opciones;
    String respuestaCorrecta;

    public Pregunta(String enunciado, ArrayList<String> opciones, String respuestaCorrecta) {
        this.enunciado = enunciado;
        this.opciones = opciones;
        this.respuestaCorrecta = respuestaCorrecta;
    }

    public void mostrarOpciones() {
        char letra = 'a';
        for (String opcion : opciones) {
            System.out.println("     " + letra + ") " + opcion);
            letra++;
        }
        System.out.println("     Respuesta correcta: " + respuestaCorrecta);
    }
}
