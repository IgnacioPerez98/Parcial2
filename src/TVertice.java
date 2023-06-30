
import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

public class TVertice<T> implements IVertice {

    private final Comparable etiqueta;
    private LinkedList<TAdyacencia> adyacentes;
    private boolean visitado;
    private T datos;
    private TVertice<T> padre;

    // <editor-fold defaultstate="collapsed" desc="Getters y Setters">
    /**
     *
     * @return
     */
    public TVertice<T> getFather() {
        return this.padre;
    }

    public void setFather(TVertice<T> valor) {
        this.padre = valor;
    }

    public T getDatos() {
        return datos;
    }

    public void setVisitado(boolean valor) {
        this.visitado = valor;
    }

    public boolean getVisitado() {
        return this.visitado;
    }

    @Override
    public LinkedList<TAdyacencia> getAdyacentes() {
        return adyacentes;
    }

    public Comparable getEtiqueta() {
        return etiqueta;
    }

    // </editor-fold>
    public TVertice(Comparable unaEtiqueta) {
        this.etiqueta = unaEtiqueta;
        adyacentes = new LinkedList();
        visitado = false;
    }

    @Override
    public String toString() {
        return "(" + etiqueta + ", " + visitado + ")";
    }

    // <editor-fold defaultstate="collapsed" desc="Operaciones de Adyacencia">  
    @Override
    public TAdyacencia buscarAdyacencia(TVertice verticeDestino) {
        if (verticeDestino != null) {
            return buscarAdyacencia(verticeDestino.getEtiqueta());
        }
        return null;
    }

    @Override
    public Double obtenerCostoAdyacencia(TVertice verticeDestino) {
        TAdyacencia ady = buscarAdyacencia(verticeDestino);
        if (ady != null) {
            return ady.getCosto();
        }
        return Double.MAX_VALUE;
    }

    @Override
    public boolean insertarAdyacencia(Double costo, TVertice verticeDestino) {
        if (buscarAdyacencia(verticeDestino) == null) {
            TAdyacencia ady = new TAdyacencia(costo, verticeDestino);
            return adyacentes.add(ady);
        }
        return false;
    }

    @Override
    public boolean eliminarAdyacencia(Comparable nomVerticeDestino) {
        TAdyacencia ady = buscarAdyacencia(nomVerticeDestino);
        if (ady != null) {
            adyacentes.remove(ady);
            return true;
        }
        return false;
    }

    @Override
    public TVertice primerAdyacente() {
        if (this.adyacentes.getFirst() != null) {
            return this.adyacentes.getFirst().getDestino();
        }
        return null;
    }

    @Override
    public TVertice siguienteAdyacente(TVertice w) {
        TAdyacencia adyacente = buscarAdyacencia(w.getEtiqueta());
        int index = adyacentes.indexOf(adyacente);
        if (index + 1 < adyacentes.size()) {
            return adyacentes.get(index + 1).getDestino();
        }
        return null;
    }

    @Override
    public TAdyacencia buscarAdyacencia(Comparable etiquetaDestino) {
        for (TAdyacencia adyacencia : adyacentes) {
            if (adyacencia.getDestino().getEtiqueta().compareTo(etiquetaDestino) == 0) {
                return adyacencia;
            }
        }
        return null;
    }

    public void printAdyacencia() {
        for (TAdyacencia ady : adyacentes) {
            System.out.println("(" + ady.getDestino().getEtiqueta() + "," + ady.getCosto() + ")");
        }
    }

    // </editor-fold>
    public void bpf(Collection<Comparable> visitados) {
        setVisitado(true);
        visitados.add(this.getEtiqueta());
        for (TAdyacencia adyacente : adyacentes) {
            TVertice vertAdy = adyacente.getDestino();
            if (!vertAdy.getVisitado()) {
                vertAdy.bpf(visitados);
            }
        }
    }

    public boolean tieneCiclo(TCamino camino) {
        setVisitado(true);
        boolean ciclo = false;
        for (TAdyacencia adyacencia : this.getAdyacentes()) {
            if (ciclo) {
                break;
            }
            TVertice w = adyacencia.getDestino();
            if (!w.getVisitado()) {
                camino.agregarAdyacencia(adyacencia);
                ciclo = w.tieneCiclo(camino);
            } else {
                if (camino.buscarVertice(w.getEtiqueta())) {
                    ciclo = true;
                    System.out.println("hay ciclo : " + camino.imprimirDesdeClave(w.etiqueta));
                }
            }
        }
        camino.getOtrosVertices().remove(this.getEtiqueta());
        return ciclo;

    }

    public void bea(Collection<TVertice> visitados) {
        //Marco como Visitado
        this.setVisitado(true);
        //Añado el vertice a la lista 
        visitados.add(this);
        TVertice x = null;
        //StringBuilder sb = new StringBuilder();
        //Creo una cola de vertices
        Queue<TVertice> C = new LinkedList<>();
        C.add(this);
        //sb.append(this.etiqueta + " ");
        while (!C.isEmpty()) {
            //Obtengo y remuevo el primero
            x = C.remove();
            //Para cada adyacente hacer
            for (TAdyacencia y : (LinkedList<TAdyacencia>) x.getAdyacentes()) {
                //Obtener destino
                TVertice actual = y.getDestino();
                //Si el vertice no se visito 
                if (!actual.getVisitado()) {
                    //Añado el vertice a la lista
                    visitados.add(actual);
                    //Marco el vertice como visitado
                    actual.setVisitado(true);
                    //Añado el vertice a la cola
                    C.add(actual);
                    //sb.append(actual.getEtiqueta() + " ");
                }
            }
        }
//        System.out.println(sb.toString());
    }

    public TCaminos todosLosCaminos(Comparable etVertDest, TCamino caminoPrevio, TCaminos todosLosCaminos) {
        this.setVisitado(true);
        for (TAdyacencia adyacencia : this.getAdyacentes()) {
            TVertice destino = adyacencia.getDestino();
            if (!destino.getVisitado()) {
                if (destino.getEtiqueta().compareTo(etVertDest) == 0) {
                    TCamino copia = caminoPrevio.copiar();
                    copia.agregarAdyacencia(adyacencia);
                    todosLosCaminos.getCaminos().add(copia);
                } else {
                    caminoPrevio.agregarAdyacencia(adyacencia);
                    destino.todosLosCaminos(etVertDest, caminoPrevio, todosLosCaminos);
                    caminoPrevio.eliminarAdyacencia(adyacencia);
                }
            }
        }
        this.setVisitado(false);
        return todosLosCaminos;
    }

    // Este metodo tiene como precondicion que todos los vertices del grafo se encuentren con getVisitado en false.
    public boolean conectadoCon(TVertice destino) {
        setVisitado(true);
        boolean encontrado = false;
        if (this.getEtiqueta().equals(destino.getEtiqueta())) {
            return true;
        }
        for (TAdyacencia adyacente : adyacentes) {
            TVertice vertAdy = adyacente.getDestino();
            if (!vertAdy.getVisitado()) {
                encontrado = vertAdy.conectadoCon(destino);
                if (encontrado) {
                    return true;
                }
            }
        }
        return encontrado;
    }

    private int numBp = -1;
    private int numBajo = -1;

    public void puntosArt(Collection<TVertice> puntos, int[] cont) {
        setVisitado(true);
        cont[0]++;
        this.numBp = cont[0];
        this.numBajo = cont[0];
        LinkedList<TVertice> hijos = new LinkedList<>();
        for (TAdyacencia ady : this.getAdyacentes()) {
            TVertice adyacente = ady.getDestino();
            if (!adyacente.getVisitado()) {
                adyacente.puntosArt(puntos, cont);
                hijos.add(adyacente);
                this.numBajo = Math.min(this.numBajo, adyacente.numBajo);
            } else {
                this.numBajo = Math.min(this.numBajo, adyacente.numBp);
            }
        }

        if (this.numBp > 1) {
            for (TVertice hijo : hijos) {
                if (hijo.numBajo >= this.numBp) // Si para más de un hijo se cumple esto, se va a agregar más de 1 vez a "puntos"
                {
                    puntos.add(this);           //Esto se podría mejorar con otra Collection, un Set por ej.
                }
            }
        } else {
            if (hijos.size() > 1) {
                puntos.add(this);
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Numero Bacon">  
    private int numBacon = -1;

    public int getBacon() {
        return this.numBacon;
    }

    public void setBacon(int newBacon) {
        this.numBacon = newBacon;
    }

    public int numBacon(Comparable actor) {
        this.setBacon(0);
        this.setVisitado(true);
        TVertice x;
        Queue<TVertice> cola = new LinkedList<TVertice>();
        cola.add(this);
        while (!cola.isEmpty()) {
            x = cola.remove();
            LinkedList<TAdyacencia> adyacencias = x.getAdyacentes();
            for (TAdyacencia tAdyacencia : adyacencias) {
                TVertice y = tAdyacencia.getDestino();
                if (!y.getVisitado()) {
                    y.setBacon(x.getBacon() + 1);
                    y.setVisitado(true);
                    if (y.getEtiqueta().compareTo(actor) == 0) {
                        return y.getBacon();
                    }
                    cola.add(y);

                }
            }
        }
        return Integer.MAX_VALUE;
    }
    
    
    public void ClasificacionTopologica(Collection<Comparable> lista){
        setVisitado(true);
        for(TAdyacencia ady : (LinkedList<TAdyacencia>)this.adyacentes){
            TVertice vert = ady.getDestino();
            if(!vert.getVisitado()){
                vert.ClasificacionTopologica(lista);
            }
        }
        lista.add(etiqueta);
    }

    // </editor-fold>
    public void SaltosDesdeVertice(Collection<TVertice> visitados, int maxSaltos) {
        // Mark the current vertex as visited
        this.setVisitado(true);
        // Add the vertex to the visited collection
        visitados.add(this);

        // Create a queue of vertices
        Queue<TVertice> queue = new LinkedList<>();
        queue.add(this);

        int saltos = 0;

        while (!queue.isEmpty()) {
            int verticesEnNivel = queue.size();

            while (verticesEnNivel > 0) {
                // Remove the first vertex from the queue
                TVertice x = queue.remove();

                // For each adjacent vertex
                for (TAdyacencia adyacencia : (LinkedList<TAdyacencia>)x.getAdyacentes()) {
                    TVertice actual = adyacencia.getDestino();

                    if (!actual.getVisitado() && saltos < maxSaltos) {
                        // Add the vertex to the visited collection
                        visitados.add(actual);
                        // Mark the vertex as visited
                        actual.setVisitado(true);
                        // Add the vertex to the queue
                        queue.add(actual);
                    }
                }

                verticesEnNivel--;
            }

            saltos++;
        }
    }
    
    public void listarContactos(Collection<TVertice> visitados, int maxSaltos) {
        this.setVisitado(true);
        TVertice x = null;
        Queue<TVertice> C = new LinkedList<>();
        C.add(this);
        while (!C.isEmpty()) { //Mientras la cola no esté vacía...
            x = C.remove();     //Toma el siguiente en la cola...
            for (TAdyacencia y : (LinkedList<TAdyacencia>) x.getAdyacentes()) { //Para cada uno de los adyacentes del que agarré...
                TVertice adyacente = y.getDestino(); //Tomo el vertice destino del adyacente...
                if (!adyacente.getVisitado()) { //Si no está visitado...
                    adyacente.setBacon(x.getBacon()+ 1);     //Le sumo uno al num de bacon (Osea los saltos)
                    if (adyacente.getBacon() <= maxSaltos) {  //Si a ese que le acabo de sumar: NO supera los saltos...
                        adyacente.setVisitado(true);
                        C.add(adyacente);   //Lo pongo en la cola para seguir iterando con los ady
                        visitados.add(adyacente);   //Y lo pongo en los visitados para imprimirlo dsps
                    }
                }
            }
        }
    }
    
    int numDescendencia= 0;
    boolean explorado = false;
    public int getNumDescendencia () { return numDescendencia;}
    public void setExplorado(boolean  value){ explorado = value; }
    public boolean getExplorado(){ return explorado;}
    
    
    public void clasificarArcos(TAristas Arbol, TAristas Retroceso, TAristas Avance, TAristas Cruzados) {
        setVisitado(true);
        this.numDescendencia++; //Aumento el número del recorrido
        for (TAdyacencia adyacente : adyacentes) {
            TVertice vertAdy = adyacente.getDestino();
            TArista arista = new TArista(this.etiqueta, vertAdy.getEtiqueta(), obtenerCostoAdyacencia(vertAdy));
            if (!vertAdy.getVisitado()) { //        Si verticeDestino no ha sido visitado
                Arbol.add(arista); //            Agregar el arco a ArcosArbol
                vertAdy.clasificarArcos(Arbol, Retroceso, Avance, Cruzados); //            Llamar a ClasificarArcos(verticeDestino, ArcosArbol, ArcosRetroceso, ArcosAvance, ArcosCruzados)
            }
            if (vertAdy.getVisitado() & !vertAdy.getExplorado()) { //        Si verticeDestino ha sido visitado y no ha terminado su exploración
                Retroceso.add(arista); //            Agregar el arco a ArcosRetroceso
            }
            if ((vertAdy.getVisitado() & vertAdy.getExplorado())) { //        Si verticeDestino ha sido visitado y ha terminado su exploración
                if (vertAdy.getNumDescendencia() > this.numDescendencia) { //            Si el verticeDestino tiene un número de descendientes mayor que verticeOrigen

                    Avance.add(arista);//                Agregar el arco a ArcosAvance
                }
                if (vertAdy.getNumDescendencia() < this.numDescendencia) { //            Si el verticeDestino tiene un número de descendientes menor que verticeOrigen

                    Cruzados.add(arista);//                Agregar el arco a ArcosCruzados
                }
            }
            this.setExplorado(visitado); //    marcar verticeOrigen como explorado
        }
    }
}
