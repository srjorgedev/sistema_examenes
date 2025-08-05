import java.util.ArrayList;

public class Datos {
    public static void crearUsuarios(ArrayList<UsuariosDatos> usuarios) {
        usuarios.add(new UsuariosDatos("00000000", "A", "Administrador", "clave"));
        usuarios.add(new UsuariosDatos("00000001", "E", "Jorge Sandoval", "clave"));
        usuarios.add(new UsuariosDatos("00000002", "E", "Sara Daniela", "clave"));
        usuarios.add(new UsuariosDatos("00000003", "E", "Julian Adolfo", "clave"));
    }

    public static void crearUsuarios(String[] usuarios) {
        Array.agregarUsuario(usuarios, "E.-.00000000.-.admin.-.clave");
        Array.agregarUsuario(usuarios, "E.-.00000001.-.estudiante1.-.clave");
        Array.agregarUsuario(usuarios, "D.-.00000002.-.profesor1.-.clave");
        Array.agregarUsuario(usuarios, "D.-.00000003.-.Jorge Sandoval.-.jorge12345");
        Array.agregarUsuario(usuarios, "E.-.00000004.-.Docente chido.-.claveacceso");
    }
}
