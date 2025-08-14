import java.util.ArrayList;

public class Datos {
    public static void crearUsuarios(String[] usuarios, int[] indices) {
        Array.agregarUsuario(usuarios, "A.-.0000.-.admin.-.clave", indices);
        Array.agregarUsuario(usuarios, "E.-.0001.-.estudiante1.-.clave", indices);
        Array.agregarUsuario(usuarios, "D.-.0002.-.profesor1.-.clave", indices);
        Array.agregarUsuario(usuarios, "D.-.0003.-.Jorge Sandoval.-.jorge12345", indices);
        Array.agregarUsuario(usuarios, "D.-.0004.-.Docente chido.-.claveacceso", indices);
    }

    public static void crearExamenes(String[][] examen, int[] indices) {
        Array.agregarExamen(examen, new String[] {
                "EX00.-.Diagnostico 1.-.29/07/27.-.O.-.Matematicas.-.admin.-.0000",
                "EX00.-.o - 1 + 1.-.s - Numeros menores a 10",
                "EX00.-.a)2 b)3 c)4 d)5.-.a)1 b)2 c)4 d)14 e)10.-.",
                "EX00.-.a.-.abc.-."
        }, indices);

        Array.agregarExamen(examen, new String[] {
                "EX01.-.Prueba 2.-.29/07/27.-.O.-.Materia.-.admin.-.0000",
                "EX01.-.o - Pregunta 1.-.s - Pregunta 2",
                "EX01.-.a)A b)B c)C d)D.-.a)A b)V c)B d)C e)D.-.",
                "EX01.-.a.-.abc.-."
        }, indices);

        Array.agregarExamen(examen, new String[] {
                "EX02.-.Prueba 3.-.29/07/27.-.O.-.Materia.-.admin.-.0001",
                "EX02.-.o - Pregunta 1.-.s - Pregunta 2",
                "EX02.-.a)A b)B c)C d)D.-.a)A b)V c)B d)C e)D.-.",
                "EX02.-.a.-.abc.-."
        }, indices);
    }

    // EX00.-.Prueba.-.29/07/27.-.O.-.Materia.-.admin.-.0000
    //
    // EX00.-.a)A b)B c)C d)D.-.a)A b)V c)B d)C e)D.-.
    // EX00.-.a.-.abc.-.
}
