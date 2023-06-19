
import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

public class TVertice<T> implements IVertice, IVerticeKevinBacon {

    private Comparable etiqueta;
    private LinkedList<TAdyacencia> adyacentes;
    private boolean visitado;
    private T datos;

    public TVertice(Comparable unaEtiqueta) {
        this.etiqueta = unaEtiqueta;
        adyacentes = new LinkedList();
        visitado = false;
        bacon = 0;
    }

    // <editor-fold defaultstate="collapsed" desc="Get & Set">
    public Comparable getEtiqueta() {
        return etiqueta;
    }

    public LinkedList<TAdyacencia> getAdyacentes() {
        return adyacentes;
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
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Adyacencias">
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
    public TAdyacencia buscarAdyacencia(Comparable etiquetaDestino) {
        for (TAdyacencia adyacencia : adyacentes) {
            if (adyacencia.getDestino().getEtiqueta().compareTo(etiquetaDestino) == 0) {
                return adyacencia;
            }
        }
        return null;
    }

    @Override
    public TVertice siguienteAdyacente(TVertice vertice) {
        TAdyacencia adyacente = buscarAdyacencia(vertice.getEtiqueta());
        int index = adyacentes.indexOf(adyacente);
        if (index + 1 < adyacentes.size()) {
            return adyacentes.get(index + 1).getDestino();
        }
        return null;
    }

    public void printAdyacencia() {
        for (TAdyacencia ady : adyacentes) {
            System.out.println("(" + ady.getDestino().getEtiqueta() + "," + ady.getCosto() + ")");
        }
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Busqueda en Profundidad (BPF)"> 
    @Override
    public void bpf(Collection<TVertice> visitados) {
        setVisitado(true);
        visitados.add(this);
        for (TAdyacencia adyacente : adyacentes) {
            TVertice vertAdy = adyacente.getDestino();
            if (!vertAdy.getVisitado()) {
                vertAdy.bpf(visitados);
            }
        }
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Busqueda en Amplitud (BEA)">
    @Override
    public void bea(Collection<TVertice> visitados) {
        LinkedList<TVertice> cola = new LinkedList<>();
        LinkedList<TAdyacencia> ad;
        this.visitado = true;
        cola.add(this);
        TVertice x;
        while (!cola.isEmpty()) {
            x = cola.remove();
            ad = x.getAdyacentes();
            for (TAdyacencia ady : ad) {
                if (!ady.getDestino().visitado) {
                    ady.getDestino().visitado = true;
                    cola.addLast(ady.getDestino());
                    visitados.add(ady.getDestino());
                }
            }
        }
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Numero Bacon">
    public int beaBacon(Comparable actor) {
        LinkedList<TVertice> cola = new LinkedList<>();
        LinkedList<TAdyacencia> ad;
        this.visitado = true;
        cola.add(this);
        TVertice x;
        while (!cola.isEmpty()) {
            x = cola.removeFirst();
            ad = x.getAdyacentes();

            for (TAdyacencia ady : ad) {
                if (!ady.getDestino().visitado) {
                    ady.getDestino().visitado = true;
                    ady.getDestino().setBacon(x.bacon + 1);
                    cola.addLast(ady.getDestino());
                    if (ady.getDestino().etiqueta.compareTo(actor) == 0) {
                        return x.bacon + 1;
                    }
                }
            }
        }
        return Integer.MAX_VALUE; //pa tener algo
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Numero Bacon"> 
    private int bacon;

    @Override
    public int getBacon() {
        return this.bacon;
    }

    @Override
    public void setBacon(int newBacon) {
        this.bacon = newBacon;
    }

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Punto Articulacion">
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
    // </editor-fold>

    @Override
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

    @Override
    public boolean tieneCiclo(TCamino camino) {
        setVisitado(true);
        boolean ciclo = false;
        for (TAdyacencia adyacencia : this.getAdyacentes()) {
            if (ciclo) {
                break;
            }
            TVertice vertice = adyacencia.getDestino();
            if (!vertice.getVisitado()) {
                camino.agregarAdyacencia(adyacencia);
                ciclo = vertice.tieneCiclo(camino);
            } else {
                if (camino.buscarVertice(vertice.getEtiqueta())) {
                    ciclo = true;
                    System.out.println("hay ciclo : " + camino.imprimirDesdeClave(vertice.etiqueta));
                }
            }
        }
        camino.getOtrosVertices().remove(this.getEtiqueta());
        return ciclo;
    }

    @Override
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
            }
        }
        return encontrado;
    }

}
