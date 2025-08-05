import java.util.ArrayList;

public class Datos {
    public static void crearUsuarios(ArrayList<String> usuarios) {
        usuarios.add("E.-.00000000.-.admin.-.clave");
        usuarios.add("E.-.00000001.-.estudiante1.-.clave");
        usuarios.add("D.-.00000002.-.profesor1.-.clave");
        usuarios.add("D.-.00000003.-.Jorge Sandoval.-.jorge12345");
        usuarios.add("E.-.00000004.-.Docente chido.-.claveacceso");
    }

    public static void crearUsuarios(String[] usuarios) {
        Array.agregarUsuario(usuarios, "E.-.00000000.-.admin.-.clave");
        Array.agregarUsuario(usuarios, "E.-.00000001.-.estudiante1.-.clave");
        Array.agregarUsuario(usuarios, "D.-.00000002.-.profesor1.-.clave");
        Array.agregarUsuario(usuarios, "D.-.00000003.-.Jorge Sandoval.-.jorge12345");
        Array.agregarUsuario(usuarios, "E.-.00000004.-.Docente chido.-.claveacceso");
    }
}
