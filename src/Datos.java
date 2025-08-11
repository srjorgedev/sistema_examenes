import java.util.ArrayList;

public class Datos {
    public static void crearUsuarios(String[] usuarios) {
        Array.agregarUsuario(usuarios, "E.-.0000.-.admin.-.clave");
        Array.agregarUsuario(usuarios, "E.-.0001.-.estudiante1.-.clave");
        Array.agregarUsuario(usuarios, "D.-.0002.-.profesor1.-.clave");
        Array.agregarUsuario(usuarios, "D.-.0003.-.Jorge Sandoval.-.jorge12345");
        Array.agregarUsuario(usuarios, "D.-.0004.-.Docente chido.-.claveacceso");
    }

    public static void crearExamenInformacion(String[] array) {
        Array.agregarExamenInformacion(array, "EX00.-.Prueba.-.29/07/27.-.O.-.Materia.-.admin.-.0000");
    }

    public static void crearExamenPreguntas(String[] array) {
        Array.agregarExamenPreguntas(array, "EX00.-.o-Pregunta 1.-.s-Pregunta 2");
    }

    public static void crearExamenReactivos(String[] array) {
        Array.agregarExamenReactivos(array, "EX00.-.a)A b)B c)C d)D.-.a)A b)V c)B d)C e)D.-.");
    }

    public static void crearExamenRespuestas(String[] array) {
        Array.agregarExamenRespuestas(array, "EX00.-.a.-.abc.-.");
    }

    // EX00.-.Prueba.-.29/07/27.-.O.-.Materia.-.admin.-.0000
    //
    // EX00.-.a)A b)B c)C d)D.-.a)A b)V c)B d)C e)D.-.
    // EX00.-.a.-.abc.-.
}
