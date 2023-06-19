
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

public class TCamino {

    private final TVertice origen;
    private final Collection<Comparable> otrosVertices;
    private Double costoTotal;

    public TCamino(TVertice v) {
        this.costoTotal = 0.0d;
        this.origen = v;
        this.otrosVertices = new LinkedList();
    }

    // <editor-fold defaultstate="collapsed" desc="Get & Set">    
    public TVertice getOrigen() {
        return origen;
    }

    public Collection<Comparable> getOtrosVertices() {
        return otrosVertices;
    }

    public Double getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(Double costoTotal) {
        this.costoTotal = costoTotal;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Adyacencias">
    public boolean agregarAdyacencia(TAdyacencia adyacenciaActual) {
        if (adyacenciaActual.getDestino() != null) {
            costoTotal += adyacenciaActual.getCosto();
            return getOtrosVertices().add(adyacenciaActual.getDestino().getEtiqueta());
        }
        return false;
    }

    public boolean eliminarAdyacencia(TAdyacencia adyacenciaActual) {
        if (getOtrosVertices().contains(adyacenciaActual.getDestino().getEtiqueta())) {
            costoTotal -= adyacenciaActual.getCosto();
            return getOtrosVertices().remove(adyacenciaActual.getDestino().getEtiqueta());
        }
        return false;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Impresiones">
    public String imprimirDesdeClave(Comparable clave) {
        StringBuilder sb = new StringBuilder();
        boolean start = false;
        Collection<Comparable> listaDefinitiva = new LinkedList<Comparable>();
        listaDefinitiva.add(this.getOrigen().getEtiqueta());
        listaDefinitiva.addAll(this.getOtrosVertices());

        for (Iterator<Comparable> iterator = listaDefinitiva.iterator(); iterator.hasNext();) {
            Comparable next = iterator.next();
            if (next.compareTo(clave) == 0) {
                start = true;
            }
            if (start) {
                sb.append(" " + next + " ");
            }
        }
        return sb.toString();
    }

    public void imprimirEtiquetasConsola() {
        System.out.println(imprimirEtiquetas());
    }

    public String imprimirEtiquetas() {
        StringBuilder sb = new StringBuilder();
        sb.append("Origen: " + getOrigen().getEtiqueta());
        for (Comparable adyacente : getOtrosVertices()) {
            sb.append(" -> " + adyacente);
        }
        return sb.toString();
    }

    // </editor-fold>
    
    public TCamino copiar() {
        TVertice origenCopia = new TVertice(this.getOrigen().getEtiqueta());
        TCamino copia = new TCamino(origenCopia);
        copia.setCostoTotal(this.getCostoTotal());
        copia.getOtrosVertices().addAll(this.getOtrosVertices());

        return copia;
    }

    public boolean buscarVertice(Comparable clave) {
        if (this.origen.getEtiqueta().equals(clave)) {
            return true;
        }
        for (Comparable vert : this.otrosVertices) {
            if (clave.equals(vert)) {
                return true;
            }

        }
        return false;
    }

}
