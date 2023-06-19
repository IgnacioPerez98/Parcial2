
import java.util.Collection;
import java.util.LinkedList;

public class TGrafoNoDirigido extends TGrafoDirigido implements IGrafoNoDirigido, IGrafoKevinBacon {

    protected TAristas lasAristas = new TAristas();

    /**
     *
     * @param vertices
     * @param aristas
     */
    public TGrafoNoDirigido(Collection<TVertice> vertices, Collection<TArista> aristas) {
        super(vertices, aristas);
        lasAristas.insertarAmbosSentidos(aristas);

    }

    public double getCostoTotal() {
        double costo = 0;
        for (TArista tArista : lasAristas) {
            costo += tArista.getCosto();
        }
        return costo / 2;
    }

    @Override
    public boolean esConexo() {
        Collection<TVertice> vertices = this.getVertices().values();
        Collection<TVertice> visitados = this.bpf(vertices.iterator().next());
        return vertices.size() == visitados.size();
    }

    @Override
    public boolean conectados(TVertice origen, TVertice destino) {
        this.desvisitarVertices();
        if (this.existeVertice(origen.getEtiqueta()) && this.existeVertice(destino.getEtiqueta())) {
            return this.buscarVertice(origen.getEtiqueta()).conectadoCon(destino);
        }
        return false;
    }

    // <editor-fold defaultstate="collapsed" desc="Aristas">
    @Override
    public boolean insertarArista(TArista arista) {
        boolean tempbool = false;
        TArista arInv = new TArista(arista.getEtiquetaDestino(), arista.getEtiquetaOrigen(), arista.getCosto());
        tempbool = (super.insertarArista(arista) && super.insertarArista(arInv));
        return tempbool;
    }

    public TAristas getLasAristas() {
        return lasAristas;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Prim"> 
//---------Pseudo--------
//TGrafoNoDirigido.Prim()
//T: coleccion de TAritas vacia.
//V: coleccion de TVertice vacia
//B: clonación de coleccion de TVertice ya existentes
//u,v: de tipo Tvertice
//COM
//	T.vaciar(); //conjunto de aristas AAM (Árbol Abarcador de costo Mínimo)
//	V.agregar(1);
//	B.eliminar(1);
//	mientras V no este vacio, hacer:
//		TArista aux <- TAristas.buscarMinima(V,B);
//		T.agregar(aux)
//		U.agregarElQuNoEsta(aux);
//		B.eliminarElQuEsta(aux);
//	Fin Mientras
//FIN
//---------Pseudo--------
    @Override
    public TGrafoNoDirigido Prim() {

        TAristas T = new TAristas();
        Collection<Comparable> VerticesU = new LinkedList<>();
        LinkedList<Comparable> VerticesV = copiarColeccionVertices();

        TArista aristaMin;

        Comparable u, v;
        u = VerticesV.getFirst();
        VerticesU.add(u);
        VerticesV.remove(u);

        while (!VerticesV.isEmpty()) {
            aristaMin = this.lasAristas.buscarMin(VerticesU, VerticesV);
            T.add(aristaMin);
            v = aristaMin.getEtiquetaDestino();
            VerticesU.add(v);
            VerticesV.remove(v);
        }

        LinkedList<TVertice> vertices = new LinkedList<>();
        for (Comparable etiqueta : VerticesU) {
            vertices.add(new TVertice<>(etiqueta));
        }

        return new TGrafoNoDirigido(vertices, T);

    }

    private LinkedList<Comparable> copiarColeccionVertices() {
        Collection<TVertice> vertices = this.getVertices().values();
        LinkedList<Comparable> copiaVertices = new LinkedList<>();
        for (TVertice copiado : vertices) {
            copiaVertices.add(copiado.getEtiqueta());
        }
        return copiaVertices;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Kruskal">
//---------Pseudo--------
//GrafoNoDirigido.Kruskal()
//pendiente
//FIN
//---------Pseudo--------
    @Override
    public TGrafoNoDirigido Kruskal() {
        TGrafoNoDirigido arbolCostoMinimo = new TGrafoNoDirigido(vertices.values(), new TAristas());
        TAristas aristasOrdenadas = lasAristas.copiarAristasOrdenadas();
        int aristasAgregadas = 0;

        while (aristasAgregadas != getVertices().size() - 1) {
            TArista aristaMin = aristasOrdenadas.removeFirst();
            TVertice verticeOrigen = arbolCostoMinimo.buscarVertice(aristaMin.getEtiquetaOrigen());
            TVertice verticeDestino = arbolCostoMinimo.buscarVertice(aristaMin.getEtiquetaDestino());
            if (!arbolCostoMinimo.conectados(verticeOrigen, verticeDestino)) {
                arbolCostoMinimo.insertarArista(aristaMin);
                arbolCostoMinimo.getLasAristas().add(aristaMin);
                arbolCostoMinimo.getLasAristas().add(aristaMin.aristaInversa());
                aristasAgregadas++;
            }
        }
        return arbolCostoMinimo;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Busqueda en Amplitud (BEA)">
    @Override
    public Collection<TVertice> bea(Comparable etiquetaOrigen) {
        LinkedList<TVertice> visitados = new LinkedList<>();
        this.desvisitarVertices();
        TVertice origen = this.buscarVertice(etiquetaOrigen);
        if (origen != null) {
            origen.bea(visitados);
        }
        return visitados;
    }

    @Override
    public Collection<TVertice> bea() {
        LinkedList<TVertice> visitados = new LinkedList<>();
        for (TVertice vertice : this.getVertices().values()) {
            if (!visitados.contains(vertice)) {
                vertice.bea(visitados);
            }
        }
        return visitados;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Numero Bacon"> 
    @Override
    public int numBacon(Comparable actor) {

        if (!this.existeVertice(actor) && !this.existeVertice("Kevin_Bacon")) {
            return 0;
        }
        this.desvisitarVertices();
        int miNumerito = this.getVertices().get("Kevin_Bacon").beaBacon(actor);
        return miNumerito;

    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Punto Articulacion">
    //Precondicion: El grafo tiene que ser conexo.
    public LinkedList<TVertice> puntosArticulacion(Comparable verticeOrigen) {
        if (esConexo()) {
            desvisitarVertices();
            int[] cont = {0};
            LinkedList<TVertice> puntos = new LinkedList<>();
            TVertice vert = this.buscarVertice(verticeOrigen);
            if (vert != null) {
                vert.puntosArt(puntos, cont);
            }
            return puntos;
        }
        return null;
    }
    // </editor-fold>
}
