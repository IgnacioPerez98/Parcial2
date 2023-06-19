
import java.util.Collection;
import java.util.LinkedList;

public class UtilGenerico<T> {
    public LinkedList<T> FromCollection(Collection<T> collection){
        LinkedList<T> lista = new LinkedList<>();
        Object[] vector = collection.toArray();
        for(var e : vector){
            lista.add((T)e);
        }
        return lista;
        
    }
}
