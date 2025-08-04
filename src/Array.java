public class Array {
    /*
     * Por temas de consumo de recursos
     * ArrayusuariosIndice y similares admiten hasta 99 elementos
     */
    static int usuariosIndice = 0;
    
    public static void agregarUsuario(String[] array, String datos) {
        array[usuariosIndice] = datos;
        usuariosIndice++;
    }

    public static void agregar(String[] array,int indice, String datos) {
        array[indice] = datos;
    }

    public static void modificar(String[] array, int indice, String nuevo) {
        array[indice] = nuevo;
    }
}
