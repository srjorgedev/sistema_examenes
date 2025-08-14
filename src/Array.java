public class Array {
    public static void agregarUsuario(String[] array, String datos, int[] indices) {
        array[indices[0]] = datos;
        indices[0]++;
    }

    public static void agregarExamen(String[][] examen, String[] datos, int[] indices) {
        examen[indices[2]][0] = datos[0];
        examen[indices[2]][1] = datos[1];
        examen[indices[2]][2] = datos[2];
        examen[indices[2]][3] = datos[3];

        indices[2]++;
    }

    public static void agregarHistorial(String[] array, String datos, int[] indices) {
        array[indices[1]] = datos;
        indices[1]++;
    }

    public static void agregar(String[] array, int indice, String datos) {
        array[indice] = datos;
    }

    public static void modificar(String[] array, int indice, String nuevo) {
        array[indice] = nuevo;
    }

    public static void borrar(String[] array, int indice) {
        array[indice] = "";
    }
}
