
import java.util.LinkedList;


/**
 * Cuando se crea la clase, se instancia con el tipo y devuelve las colecciones ya casteadas
 */
public class UtilParcial {
    public static LinkedList<String> StringArrayToLinkedList(String[] array) {
        LinkedList<String> lista = new LinkedList<>();
        if(array== null){
            return null;
        }
        for(String f : array){
            lista.add(f);
        }        
        return lista;
    }
    
    public static LinkedList<String> StringToLinkedList(String array, String Separator) {
        LinkedList<String> lista = new LinkedList<>();
        if(array== null){
            return null;
        }
        String[] baseArray = array.split(Separator);
        for(String f : baseArray){
            lista.add(f);
        }        
        return lista;
    }
    
}

