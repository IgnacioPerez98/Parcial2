
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

/**
 * Cuando se crea la clase, se instancia con el tipo y devuelve las colecciones
 * ya casteadas
 */
public class UtilParcial {

    public static LinkedList<String> StringArrayToLinkedList(String[] array) {
        LinkedList<String> lista = new LinkedList<>();
        if (array == null) {
            return null;
        }
        for (String f : array) {
            lista.add(f);
        }
        return lista;
    }

    public static LinkedList<String> StringToLinkedList(String array, String Separator) {
        LinkedList<String> lista = new LinkedList<>();
        if (array == null) {
            return null;
        }
        String[] baseArray = array.split(Separator);
        for (String f : baseArray) {
            lista.add(f);
        }
        return lista;
    }

    public static boolean ControlarAristasNoRepetidasNoDirigido(String path, boolean ignorarPrimerLinea) {
        String[] file = ManejadorArchivosGenerico.leerArchivo(path, ignorarPrimerLinea);
        HashSet<String> keys = new HashSet<>();
        for (String s : file) {
            String[] primer2 = s.split(",");
            boolean arista = keys.add(primer2[0] + "," + primer2[1]);
            boolean aristaInversa = keys.add(primer2[1] + "," + primer2[0]);
            if (arista == false || aristaInversa == false) {
                return false;
            }

        }
        return true;
    }

    public static LinkedList<TArista> AristasNOrepetidas(String path, boolean ignorarPrimerLinea) {
        String[] file = ManejadorArchivosGenerico.leerArchivo(path, ignorarPrimerLinea);
        HashSet<String> keys = new HashSet<>();
        LinkedList<TArista> teControle = new LinkedList<>();
        for (String s : file) {
            String[] primer2 = s.split(",");
            String ida = primer2[0] + "," + primer2[1];
            String vuelta = primer2[1] + "," + primer2[0];
            if (!keys.contains(ida) && !keys.contains(vuelta)) {
                keys.add(ida);
                double costo = Double.parseDouble(primer2[2]);
                TArista comun = new TArista(primer2[0], primer2[1], costo);
                teControle.addFirst(comun);
            }

        }
        return teControle;
    }

}
