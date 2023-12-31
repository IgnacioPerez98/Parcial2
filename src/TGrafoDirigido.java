
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;

public class TGrafoDirigido implements IGrafoDirigido {

    public void imprimirGrafo(boolean incluirCostos) {

        if (incluirCostos) {
            for (TVertice vertice : vertices.values()) {
                System.out.print("Vertice " + vertice.getEtiqueta() + ":");
                for (Object adyacencia : vertice.getAdyacentes()) {
                    System.out.print(" [" + ((TAdyacencia) adyacencia).getDestino().getEtiqueta() + ", " + ((TAdyacencia) adyacencia).getCosto() + "]");
                }
                System.out.println();
            }
        } else {
            for (TVertice vertice : vertices.values()) {
                System.out.print("Vertice " + vertice.getEtiqueta() + ":");
                for (Object adyacencia : vertice.getAdyacentes()) {
                    System.out.print(" [" + ((TAdyacencia) adyacencia).getDestino().getEtiqueta() + "]");
                }
                System.out.println();
            }

        }
    }
    protected Map<Comparable, TVertice> vertices; // vertices del grafo.-

    public TGrafoDirigido(Collection<TVertice> vertices, Collection<TArista> aristas) {
        this.vertices = new HashMap<>();
        for (TVertice vertice : vertices) {
            insertarVertice(vertice.getEtiqueta());
        }
        for (TArista arista : aristas) {
            insertarArista(arista);
        }
    }

    //<editor-fold desc="Operacion de Aristas/Vertice">
    /**
     * Metodo encargado de eliminar una arista dada por un origen y destino. En
     * caso de no existir la adyacencia, retorna falso. En caso de que las
     * etiquetas sean invalidas, retorna falso.
     *
     */
    public boolean eliminarArista(Comparable nomVerticeOrigen, Comparable nomVerticeDestino) {
        if ((nomVerticeOrigen != null) && (nomVerticeDestino != null)) {
            TVertice vertOrigen = buscarVertice(nomVerticeOrigen);
            if (vertOrigen != null) {
                return vertOrigen.eliminarAdyacencia(nomVerticeDestino);
            }
        }
        return false;
    }

    /**
     * Metodo encargado de verificar la existencia de una arista. Las etiquetas
     * pasadas por par�metro deben ser v�lidas.
     *
     * @return True si existe la adyacencia, false en caso contrario
     */
    public boolean existeArista(Comparable etiquetaOrigen, Comparable etiquetaDestino) {
        TVertice vertOrigen = buscarVertice(etiquetaOrigen);
        TVertice vertDestino = buscarVertice(etiquetaDestino);
        if ((vertOrigen != null) && (vertDestino != null)) {
            return vertOrigen.buscarAdyacencia(vertDestino) != null;
        }
        return false;
    }

    /**
     * Metodo encargado de verificar la existencia de un vertice dentro del
     * grafo.-
     *
     * La etiqueta especificada como par�metro debe ser v�lida.
     *
     * @param unaEtiqueta Etiqueta del vertice a buscar.-
     * @return True si existe el vertice con la etiqueta indicada, false en caso
     * contrario
     */
    public boolean existeVertice(Comparable unaEtiqueta) {
        return getVertices().get(unaEtiqueta) != null;
    }

    /**
     * Metodo encargado de verificar buscar un vertice dentro del grafo.-
     *
     * La etiqueta especificada como parametro debe ser valida.
     *
     * @param unaEtiqueta Etiqueta del vertice a buscar.-
     * @return El vertice encontrado. En caso de no existir, retorna nulo.
     */
    protected TVertice buscarVertice(Comparable unaEtiqueta) {
        return getVertices().get(unaEtiqueta);
    }

    /**
     * Metodo encargado de insertar una arista en el grafo (con un cierto
     * costo), dado su vertice origen y destino.- Para que la arista sea valida,
     * se deben cumplir los siguientes casos: 1) Las etiquetas pasadas por
     * parametros son v�lidas.- 2) Los vertices (origen y destino) existen
     * dentro del grafo.- 3) No es posible ingresar una arista ya existente
     * (miso origen y mismo destino, aunque el costo sea diferente).- 4) El
     * costo debe ser mayor que 0.
     *
     * @return True si se pudo insertar la adyacencia, false en caso contrario
     */
    public boolean insertarArista(TArista arista) {
        if ((arista.getEtiquetaOrigen() != null) && (arista.getEtiquetaDestino() != null)) {
            TVertice vertOrigen = buscarVertice(arista.getEtiquetaOrigen());
            TVertice vertDestino = buscarVertice(arista.getEtiquetaDestino());
            if ((vertOrigen != null) && (vertDestino != null)) {
                return vertOrigen.insertarAdyacencia(arista.getCosto(), vertDestino);
            }
        }
        return false;
    }

    /**
     * Metodo encargado de insertar un vertice en el grafo.
     *
     * No pueden ingresarse vertices con la misma etiqueta. La etiqueta
     * especificada como par�metro debe ser v�lida.
     *
     * @param unaEtiqueta Etiqueta del vertice a ingresar.
     * @return True si se pudo insertar el vertice, false en caso contrario
     */
    public boolean insertarVertice(Comparable unaEtiqueta) {
        if ((unaEtiqueta != null) && (!existeVertice(unaEtiqueta))) {
            TVertice vert = new TVertice(unaEtiqueta);
            getVertices().put(unaEtiqueta, vert);
            return getVertices().containsKey(unaEtiqueta);
        }
        return false;
    }

    @Override

    public boolean insertarVertice(TVertice vertice) {
        Comparable unaEtiqueta = vertice.getEtiqueta();
        if ((unaEtiqueta != null) && (!existeVertice(unaEtiqueta))) {
            getVertices().put(unaEtiqueta, vertice);
            return getVertices().containsKey(unaEtiqueta);
        }
        return false;
    }

    public Object[] getEtiquetasOrdenado() {
        TreeMap<Comparable, TVertice> mapOrdenado = new TreeMap<>(this.getVertices());
        return mapOrdenado.keySet().toArray();
    }

    //</editor-fold>
    /**
     * @return the vertices
     */
    public Map<Comparable, TVertice> getVertices() {
        return vertices;
    }

    @Override
    public Comparable centroDelGrafo() {
        Object[] etiquetasarray = this.getEtiquetasOrdenado();
        Comparable[] excentricidad = new Comparable[]{Double.MAX_VALUE, null};
        for (int i = 0; i < etiquetasarray.length; i++) {
            Comparable excentricidadVertice = this.obtenerExcentricidad((Comparable) etiquetasarray[i]);
            if ((excentricidadVertice.compareTo(-1d) > 0) && excentricidadVertice.compareTo(excentricidad[0]) < 0) {
                excentricidad[0] = excentricidadVertice;
                excentricidad[1] = i;
            }
        }
        return (Comparable) etiquetasarray[(int) excentricidad[1]];
    }

    /*@Override
    public Comparable centroDelGrafo() {
        Map<Comparable, Double> excentricidades = obtenerExcentricidades();
        double minimo = Double.MAX_VALUE;
        Comparable etiquetaMinimo = " ";

        for (Map.Entry<Comparable, Double> entry : excentricidades.entrySet()) {
            if (entry.getValue() > -1 && entry.getValue() < minimo) {
                minimo = entry.getValue();
                etiquetaMinimo = entry.getKey();
            }
        }
        return etiquetaMinimo;
    }*/
    @Override
    public Double[][] floyd() {
        Double[][] matrizCostos = UtilGrafos.obtenerMatrizCostos(getVertices());
        for (int k = 0; k < matrizCostos.length; k++) {
            for (int i = 0; i < matrizCostos.length; i++) {
                for (int j = 0; j < matrizCostos.length; j++) {
                    if ((i != k) && (k != j) && (i != j)) {
                        double costoIK = matrizCostos[i][k];
                        double costoKJ = matrizCostos[k][j];
                        double costoIJ = matrizCostos[i][j];
                        if (!((costoIK == Double.MAX_VALUE) || (costoKJ == Double.MAX_VALUE))) {
                            matrizCostos[i][j] = Math.min(costoIJ, (costoIK + costoKJ));
                        }
                    }
                }
            }
        }
        return matrizCostos;
    }

    /*
    public Double[][] floyd() {
        Double[][] matrizFloyd = UtilGrafos.obtenerMatrizCostos(vertices);
        Double[][] matrizPredecesores = new Double[matrizFloyd.length][matrizFloyd.length];
        int tamanio = matrizFloyd.length;

        for (int i = 1; i < tamanio; i++) {//Setea la diagonal a 0 
            matrizFloyd[i][i] = 0d;
        }
        for (int k = 1; k < tamanio; k++) {
            for (int i = 1; i < tamanio; i++) {
                for (int j = 1; j < tamanio; j++) {
                    if (i != k && i != j && k != j) {
                        if (matrizFloyd[i][k] + matrizFloyd[k][j] < matrizFloyd[i][j]) {
                            matrizFloyd[i][j] = matrizFloyd[i][k] + matrizFloyd[k][j];
                            matrizPredecesores[i][j] = (double) k;
                        }
                    }
                }
            }
        }
        return matrizFloyd;
    }
     */
    private Map<Comparable, Double> obtenerExcentricidades() {
        Double[][] matrizFloyd = floyd();
        int tamanio = vertices.size();
        Comparable[] etiquetas = new Comparable[tamanio];

        int n = 0;
        for (Comparable etiqueta : vertices.keySet()) {
            etiquetas[n++] = etiqueta;
        }

        Map<Comparable, Double> resultado = new HashMap<>();

        for (int i = 0; i < tamanio; i++) {
            double maximo = 0; //cada vez que entra en la columna es 0 porque se va a fijar el menor de cada columna 
            for (int j = 0; j < tamanio; j++) {
                if (maximo < matrizFloyd[j][i]) {
                    maximo = matrizFloyd[j][i];
                }
            }
            resultado.put(etiquetas[i], maximo);
        }
        return resultado;
    }

    /*
    Dado G=(V,A), la excentricidad de un nodo v se define como la máxima de todas las longitudes mínimas de los caminos entre cada
    uno de los otros nodos y el nodo v. 
    • El centro de G es un vértice de mínima excentricidad.
    • Para obtener el centro de un grafo hacer:
        – aplicar Floyd para obtener el largo de los caminos,
        – encontrar el máximo valor en cada columna i, y con ello se obtiene la excentricidad de i,
        – encontrar el vértice con excentricidad mínima: el centro de G.*/
    @Override
    public Comparable obtenerExcentricidad(Comparable etiquetaVertice) {
        Double[][] floyd = this.floyd();
        Map<Comparable, TVertice> vertices = this.getVertices();
        Object[] etiquetas = vertices.keySet().toArray();
        Integer indiceEtiqueta = null;
        for (int i = 0; i < etiquetas.length; i++) {
            if (etiquetas[i].equals(etiquetaVertice)) {
                indiceEtiqueta = i;
                break;
            }
        }
        Double excentricidad = -1d;
        for (int i = 0; i < floyd.length; i++) {
            if ((floyd[i][indiceEtiqueta] != Double.MAX_VALUE) && i != indiceEtiqueta && floyd[i][indiceEtiqueta] > excentricidad) {
                excentricidad = floyd[i][indiceEtiqueta];
            }
        }
        return excentricidad;
    }

    @Override
    public boolean[][] warshall() {
        Double[][] matrizADevolver = UtilGrafos.obtenerMatrizCostos(this.vertices);
        boolean[][] matrizADevolverWarshall = new boolean[matrizADevolver.length][matrizADevolver.length];

        for (int k = 0; k < matrizADevolverWarshall.length; k++) {
            for (int i = 0; i < matrizADevolverWarshall.length; i++) {
                for (int j = 0; j < matrizADevolverWarshall.length; j++) {
                    //Inicializo las conexiones directas con true (deberia funcar igual que hacerlo antes arriba)
                    if ((i != j) && (matrizADevolver[i][j] != Double.MAX_VALUE)) {
                        matrizADevolverWarshall[i][j] = true;
                    }

                    if ((i != j) && (matrizADevolverWarshall[i][j] == false)) {
                        matrizADevolverWarshall[i][j] = matrizADevolverWarshall[i][k] && matrizADevolverWarshall[k][j];
                    }
                }
            }
        }
        return matrizADevolverWarshall;
    }

    @Override
    public boolean eliminarVertice(Comparable nombreVertice) {
        if (nombreVertice != null) {
            getVertices().remove(nombreVertice);
            return getVertices().containsKey(nombreVertice);
        }
        return false;
    }

    public Collection<TVertice> bea(Comparable etiquetaOrigen) {
        desvisitarVertices();
        LinkedList<TVertice> col = new LinkedList<>();
        getVertices().get(etiquetaOrigen).bea(col);
        return col;
    }

    public Collection<TVertice> bea() {
        LinkedList<TVertice> resultado = new LinkedList<>();
        this.desvisitarVertices();
        this.getVertices().forEach((key, value) -> {
            if (!value.getVisitado()) {
                value.bea(resultado);
            }
        });
        return resultado;
    }

//    public Collection<TVertice> bea() {
//        desvisitarVertices();
//        LinkedList<TVertice> col = new LinkedList<>();
//        LinkedList<TVertice> vertices = (LinkedList<TVertice>) this.vertices.values();
//        getVertices().get(vertices.getFirst()).bea(col);
//        return col;
//    }
    public Collection<TVertice> bpf() {
        desvisitarVertices(); //agregué este bpf porque no usamos el bpf con vertice al final
        LinkedList<TVertice> visitados = new LinkedList<>();
        Collection<TVertice> vertices = this.vertices.values();
        for (TVertice v : vertices) {
            if (!v.getVisitado()) {
                v.bpf(visitados);
            }
        }
        return visitados;
    }

    public Collection<TVertice> bpf(Comparable etiquetaOrigen) {
        TVertice aux = vertices.get(etiquetaOrigen);
        return this.bpf(aux);
    }

    public Collection<TVertice> bpf(TVertice vertice) {
        desvisitarVertices();
        Collection<TVertice> visitados = new LinkedList<>();
        if (vertice != null) {
            vertice.bpf(visitados);
        }
        return visitados;
    }

    public void desvisitarVertices() {
        for (TVertice vertice : this.vertices.values()) {
            vertice.setVisitado(false);
        }
    }

    public boolean tieneCiclo(Comparable etiquetaOrigen) {
        TVertice vertV = vertices.get(etiquetaOrigen);
        if (vertV != null) {
            desvisitarVertices();
            TCamino camino = new TCamino(vertV);
            return vertV.tieneCiclo(camino);
        }
        return false;
    }

    public boolean tieneCiclo(TCamino camino) {
        desvisitarVertices();
        if (camino == null) {
            return false;
        }
        return camino.getOrigen().tieneCiclo(camino);
    }

    public boolean tieneCiclo() {
        boolean result = false;
        if (vertices.isEmpty()) {
            System.out.println("El grafo está vacio");
            return result;
        }
        desvisitarVertices();

        for (TVertice vertV : this.vertices.values()) {
            if (result) {
                break;
            }
            if (!vertV.getVisitado()) {
                TCamino camino = new TCamino(vertV);
                result = vertV.tieneCiclo(camino);
                if (result) {
                    camino.imprimirEtiquetasConsola();
                }
            }
        }
        if (!result) {
            System.out.println("no hay ciclos");
        }
        return result;
    }

    public TCaminos todosLosCaminos(Comparable etiquetaOrigen, Comparable etiquetaDestino) {
        desvisitarVertices();
        TCaminos todosLosCaminos = new TCaminos();
        TVertice v = buscarVertice(etiquetaOrigen);
        if (v != null) {
            TCamino caminoPrevio = new TCamino(v);
            v.todosLosCaminos(etiquetaDestino, caminoPrevio, todosLosCaminos);
            return todosLosCaminos;
        }
        return null;
    }

    public boolean alcanceTotal(Comparable nombreAeropuerto) {
        desvisitarVertices();
        TVertice vertice = buscarVertice(nombreAeropuerto);
        if (vertice == null) {
            return false; // El aeropuerto no existe en el grafo
        }
        Collection<Comparable> visitados = new LinkedList<>();
        vertice.bpf(visitados); // Realizar una búsqueda en profundidad desde el aeropuerto dado

        // Verificar si todos los aeropuertos han sido visitados
        Collection<TVertice> vertices = this.vertices.values();
        for (TVertice v : vertices) {
            if (!visitados.contains(v.getEtiqueta())) {
                return false; // No se pudo alcanzar a todos los aeropuertos
            }
        }
        return true; // Se pudo alcanzar a todos los aeropuertos
    }

    public LinkedList<TVertice> rutaMenosSaltos(Comparable servidor1, Comparable servidor2) {
        TVertice verticeInicio = buscarVertice(servidor1);
        TVertice verticeDestino = buscarVertice(servidor2);

        if (verticeInicio == null || verticeDestino == null) {
            System.out.println("No existe");
            return new LinkedList<>(); //Uno de los servidores no existe
        }

        Queue<TVertice> cola = new LinkedList<>();
        Set<Comparable> visitados = new HashSet<>();

        cola.add(verticeInicio);
        visitados.add(servidor1);
        verticeInicio.setFather(null);

        while (!cola.isEmpty()) {
            TVertice actual = cola.poll();

            if (actual.getEtiqueta().equals(servidor2)) {
                // Found the route, construct the list of vertices in order
                LinkedList<TVertice> ruta = new LinkedList<>();
                ruta.addFirst(actual);

                TVertice padre = actual.getFather();
                while (padre != null) {
                    ruta.addFirst(padre);
                    padre = padre.getFather();
                }

                return ruta;
            }

            LinkedList<TAdyacencia> laLista = actual.getAdyacentes();
            for (TAdyacencia adyacencia : laLista) {
                TVertice adyacente = adyacencia.getDestino();
                if (!visitados.contains(adyacente.getEtiqueta())) {
                    cola.add(adyacente);
                    visitados.add(adyacente.getEtiqueta());
                    adyacente.setFather(actual);
                }
            }
        }

        return null; // No route found between the servers
    }

    public LinkedList<Comparable> aeropuertosNoAccesiblesDesdeTodos() {
        Collection<TVertice> verticesAProcesar = this.getVertices().values();
        Map<Comparable, TVertice> verticesNoAccesiblesDesdeUno = new HashMap<>();

        for (TVertice vertice : verticesAProcesar) {
            this.desvisitarVertices();
            Map<Comparable, TVertice> verticesVisitadosHash = new HashMap<>();
            Collection<Comparable> verticesVisitados = new LinkedList<>();
            vertice.bpf(verticesVisitados);
            if (verticesVisitados.size() < verticesAProcesar.size()) {
                for (Comparable verticeVisitado : verticesVisitados) {
                    verticesVisitadosHash.put(verticeVisitado, this.getVertices().get(verticeVisitado));
                }
                for (TVertice verticeAComparar : verticesAProcesar) {
                    if (verticesVisitadosHash.get(verticeAComparar.getEtiqueta()) == null) {
                        verticesNoAccesiblesDesdeUno.put(verticeAComparar.getEtiqueta(), verticeAComparar);
                    }
                }
            }
        }
        this.desvisitarVertices();
        LinkedList<Comparable> verticesResultantes = new LinkedList<Comparable>();
        for (Comparable vertice : verticesNoAccesiblesDesdeUno.keySet()) {
            verticesResultantes.add(vertice);
        }
        if (verticesResultantes.isEmpty()) {
            return null;
        } else {
            return verticesResultantes;
        }
    }
    
    
    public Collection<TVertice> listarContactos(String nombreActor, int maxSaltos) {
        this.desvisitarVertices();
        Collection<TVertice> resultado = new LinkedList<>();
        TVertice vertActor = this.buscarVertice(nombreActor);
        if (vertActor != null) {
            vertActor.listarContactos(resultado, maxSaltos);
        }
        return resultado;
    }
    
    
    public void clasificarArcos(TAristas Arbol, TAristas Retroceso, TAristas Avance, TAristas Cruzados) {
        this.desvisitarVertices();
        for (TVertice vertV : this.vertices.values()) { //Para cada entrada de "vertices"...
            if (!vertV.getVisitado()) { //Si el vertice no está visitado
                vertV.clasificarArcos(Arbol, Retroceso, Avance, Cruzados); //Ejecuto el metodo bpf() del vertice.
            }
        }
    }
    
    public Collection<Comparable> ClasificacionTopologica(Comparable destino) {
        TVertice vert = buscarVertice(destino);
        if(vert == null){
            return null;
        }
        Collection<Comparable> res= new LinkedList<>();
        vert.ClasificacionTopologica(res);
        return res;
    }
}
