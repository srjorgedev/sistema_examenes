public class Array {
    /*
     * Por temas de consumo de recursos
     * ArrayusuariosIndice y similares admiten hasta 99 elementos
     */
    static int usuariosIndiceActual = 0;
    static int historialUsuariosIndiceActual = 0;
    static int examenInfoIndiceActual = 0;
    static int examenPreguntasIndiceActual = 0;
    static int examenReactivosIndiceActual = 0;
    static int examenRespuestasIndiceActual = 0;
    
    public static void agregarUsuario(String[] array, String datos) {
        array[usuariosIndiceActual] = datos;
        usuariosIndiceActual++;
    }

    public static void agregar(String[] array,int indice, String datos) {
        array[indice] = datos;
    }

    public static void modificar(String[] array, int indice, String nuevo) {
        array[indice] = nuevo;
    }

    public static void borrar(String[] array, int indice) {
        array[indice] = "";
    }
}
