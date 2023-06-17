
import java.util.Collection;
import java.util.LinkedList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author nacho
 */
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
