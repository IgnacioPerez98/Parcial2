

public class PruebaGrafo {

    public static void main(String[] args) {
        String path = System.getProperty("user.dir");
        TGrafoDirigido gd = (TGrafoDirigido) UtilGrafos.cargarGrafo(path+"\\src\\aeropuertos_1.txt",path+"\\src\\conexiones_1.txt",
                false, TGrafoDirigido.class);


        Object[] etiquetasarray = gd.getEtiquetasOrdenado();

        Double[][] matriz = UtilGrafos.obtenerMatrizCostos(gd.getVertices());
        UtilGrafos.imprimirMatrizMejorado(matriz, gd.getVertices(), "Matriz");
        Double[][] mfloyd = gd.floyd();
        UtilGrafos.imprimirMatrizMejorado(mfloyd, gd.getVertices(), "Matriz luego de FLOYD");
        for (int i = 0; i < etiquetasarray.length; i++) {
            System.out.println("excentricidad de " + etiquetasarray[i] + " : " + gd.obtenerExcentricidad((Comparable) etiquetasarray[i]));
        }
        System.out.println();
        System.out.println("Centro del grafo: " + gd.centroDelGrafo());
       
       
    }
}
