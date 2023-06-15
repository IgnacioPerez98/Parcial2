/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

/**
 *
 * @author Administrator
 */
import java.util.Collection;
import java.util.Map;

public interface IGrafoNoDirigido {

    public Collection<TVertice> bea();

    public Collection<TVertice> bea(Comparable etiquetaOrigen);

    public TGrafoNoDirigido Prim();

    public TGrafoNoDirigido Kruskal();
}
