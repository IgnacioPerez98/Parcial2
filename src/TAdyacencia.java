
public class TAdyacencia implements IAdyacencia {

    private Comparable etiqueta;
    private double costo;
    private TVertice destino;

    public TAdyacencia(double costo, TVertice destino) {
        this.etiqueta = destino.getEtiqueta();
        this.costo = costo;
        this.destino = destino;
    }

    // <editor-fold defaultstate="collapsed" desc="Get & Set">
    @Override
    public Comparable getEtiqueta() {
        return etiqueta;
    }

    @Override
    public double getCosto() {
        return costo;
    }

    @Override
    public TVertice getDestino() {
        return destino;
    }
    // </editor-fold>
}
