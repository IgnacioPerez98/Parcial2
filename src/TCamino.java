/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author Administrator
 */
public class TCamino {

    private final TVertice origen;
    private final Collection<Comparable> otrosVertices;
    // es una lista de etiquetas de los vertices
    // ATENCIÓN: PONER LA CLASE CONCRETA QUE
    // SEA MÁS APROPIADA
    private Double costoTotal;

    
    public TCamino(TVertice v) {
        this.costoTotal = 0.0d;
        this.origen = v;
        this.otrosVertices = new LinkedList();

    }
    // <editor-fold defaultstate="collapsed" desc="Getters y Setters">
    
       public TVertice getOrigen() {
        return origen;
    }

    public Collection<Comparable> getOtrosVertices() {
        return otrosVertices;
    }

    /*public void setOtrosVertices(Collection<Comparable> otrosVertices) {
        this.otrosVertices = otrosVertices;
      }*/
    public Double getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(Double costoTotal) {
        this.costoTotal = costoTotal;
    }
    // </editor-fold>
    
        public boolean agregarAdyacencia(TAdyacencia adyacenciaActual) {
        if (adyacenciaActual.getDestino() != null) {
            costoTotal += adyacenciaActual.getCosto();
            return getOtrosVertices().add(adyacenciaActual.getDestino().getEtiqueta());
        }
        return false;
    }

    /* public boolean agregarAdyacencia(TAdyacencia adyacenciaActual) {
        if (adyacenciaActual.getDestino() != null) {
            setCostoTotal((Double) getCostoTotal() + ((Number) adyacenciaActual.getCosto()).doubleValue());
            return getOtrosVertices().add(adyacenciaActual.getDestino().getEtiqueta());
        }
        return false;
    }*/
    public boolean eliminarAdyacencia(TAdyacencia adyacenciaActual) {
        if (getOtrosVertices().contains(adyacenciaActual.getDestino().getEtiqueta())) {
            costoTotal -= adyacenciaActual.getCosto();
            return getOtrosVertices().remove(adyacenciaActual.getDestino().getEtiqueta());
        }
        return false;
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




 

    public TCamino copiar() {
        TVertice origenCopia = new TVertice(this.getOrigen().getEtiqueta());
        TCamino copia = new TCamino(origenCopia);
        copia.setCostoTotal(this.getCostoTotal());
        // origenCopia.getAdyacentes().addAll(this.getOrigen().getAdyacentes());
        copia.getOtrosVertices().addAll(this.getOtrosVertices());

        return copia;
    }

}

