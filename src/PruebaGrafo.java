
import java.util.LinkedList;



public class PruebaGrafo {

    public static void main(String[] args) {
        String path = System.getProperty("user.dir");
        TGrafoDirigido gd = (TGrafoDirigido) UtilGrafos.cargarGrafo(path+"\\src\\files\\aeropuertos.txt",path+"\\src\\files\\vuelos.txt",
               false, TGrafoDirigido.class);

        
        Object[] etiquetasarray = gd.getEtiquetasOrdenado();

        Double[][] matriz = UtilGrafos.obtenerMatrizCostos(gd.getVertices());
        UtilGrafos.imprimirMatrizMejorado(matriz, gd.getVertices(), "Matriz");
        Double[][] mfloyd = gd.floyd();
        UtilGrafos.imprimirMatrizMejorado(mfloyd, gd.getVertices(), "Matriz luego de FLOYD");
        for (int i = 0; i < etiquetasarray.length; i++) {
            System.out.println("excentricidad de " + etiquetasarray[i] + " : " + gd.obtenerExcentricidad((Comparable) etiquetasarray[i]));
        }
        System.out.println("Centro del grafo: " + gd.centroDelGrafo());
       /**/
        /*
        TGrafoNoDirigido gndnc = (TGrafoNoDirigido)UtilGrafos.cargarGrafo(path+"\\src\\files\\aeropuertos.txt",path+"\\src\\files\\vuelos.txt",
                false, TGrafoNoDirigido.class);
        
        LinkedList<TArista> noRep = UtilParcial.AristasNOrepetidas(path+"\\src\\files\\vuelos.txt", false);
        TGrafoNoDirigido gnd = (TGrafoNoDirigido)UtilGrafos.cargarGrafo(path+"\\src\\files\\aeropuertos.txt",noRep,
                false, TGrafoNoDirigido.class);        
        
        LinkedList<TVertice> lista = gnd.rutaMenosSaltos("BUF", "CLE");
        for(TVertice t:lista){
            System.out.println(t.getEtiqueta());
        }*/
    }
}
