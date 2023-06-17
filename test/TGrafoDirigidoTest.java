
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author nacho
 */
public class TGrafoDirigidoTest {

    TGrafoDirigido Grafo;
    TGrafoDirigido GrafoSimplif;
    TGrafoDirigido GrafoSimplif2;

    public TGrafoDirigidoTest() {
    }

    private TGrafoDirigido crearGrafo() {
        String path = System.getProperty("user.dir") + "\\src\\files\\";
        return UtilGrafos.cargarGrafo(path + "aeropuertos.txt", path + "vuelos.txt", false, TGrafoDirigido.class);
    }

    private TGrafoDirigido crearGrafoSimplificado() {
        //Creo los Vertices
        LinkedList<TVertice> vertices = new LinkedList<>();
        TVertice vert1 = new TVertice("1");
        TVertice vert2 = new TVertice("2");
        TVertice vert3 = new TVertice("3");
        vertices.add(vert1);
        vertices.add(vert2);
        vertices.add(vert3);

        //creo las Aristas
        TAristas aristas = new TAristas();
        TArista arista1 = new TArista("1", "2", 2);
        TArista arista2 = new TArista("1", "3", 1);
        TArista arista3 = new TArista("2", "3", 10);
        aristas.add(arista1);
        aristas.add(arista2);
        aristas.add(arista3);
        TGrafoDirigido grafito = new TGrafoDirigido(vertices, aristas);
        return grafito;
    }
    private TGrafoDirigido crearGrafoSimplificado2() {
        //Creo los Vertices
        LinkedList<TVertice> vertices = new LinkedList<>();
        TVertice vert1 = new TVertice("1");
        TVertice vert2 = new TVertice("2");
        TVertice vert3 = new TVertice("3");
        TVertice vert4 = new TVertice("4");
        TVertice vert5 = new TVertice("5");
        vertices.add(vert1);
        vertices.add(vert2);
        vertices.add(vert3);
        vertices.add(vert4);
        vertices.add(vert5);

        //creo las Aristas
        TAristas aristas = new TAristas();
        TArista arista1 = new TArista("1", "2", 2);
        TArista arista2 = new TArista("1", "3", 1);
        TArista arista3 = new TArista("2", "5", 10);
        TArista arista4 = new TArista("3", "5", 10);
        TArista arista5 = new TArista("2", "4", 10);
        aristas.add(arista1);
        aristas.add(arista2);
        aristas.add(arista3);
        aristas.add(arista4);
        aristas.add(arista5);
        TGrafoDirigido grafito = new TGrafoDirigido(vertices, aristas);
        return grafito;
    }

    @Before
    public void setUp() {
        //Grafo = crearGrafo();//Cambiar los path de los archivos en la funcion
        GrafoSimplif = crearGrafoSimplificado();
        GrafoSimplif2 = crearGrafoSimplificado2();

    }

    @Test
    public void testEliminarArista() {
        boolean expected = GrafoSimplif.existeArista("1", "3");
        GrafoSimplif.eliminarArista("1", "3");
        boolean actual = GrafoSimplif.existeArista("1", "3");
        assertEquals(expected, !actual);
    }

    @Test
    public void testExisteAristaTrue() {
        TArista arista = new TArista("2", "1", 100);
        GrafoSimplif.insertarArista(arista);
        boolean actual = GrafoSimplif.existeArista("2", "1");
        assertTrue(actual);

    }

    @Test
    public void testExisteAristaFalse() {
        TArista arista = new TArista("2", "1", 100);
        boolean actual = GrafoSimplif.existeArista("2000", "1");
        assertFalse(actual);
    }

    @Test
    public void testExisteVertice() {
        boolean expected = GrafoSimplif.existeVertice("3");
        assertTrue(expected);
    }

    @Test
    public void testExisteVerticeCuandoNoExiste() {
        boolean expected = GrafoSimplif.existeVertice("10");
        assertFalse(expected);
    }

    @Test
    public void testBuscarVertice() {
        TVertice expected = GrafoSimplif.buscarVertice("3");
        assertNotNull(expected);
    }

    @Test
    public void testBuscarVerticeInexistente() {
        TVertice expected = GrafoSimplif.buscarVertice("100");
        assertNull(expected);
    }

    @Test
    public void testInsertarArista() {
        boolean actual = GrafoSimplif.existeArista("2","1");
        GrafoSimplif.insertarArista(new TArista("2","1",100));
        boolean expected = GrafoSimplif.existeArista("2","1");
        assertEquals(!actual,expected);
    }

    @Test
    public void testInsertarVertice_Comparable() {
        boolean expected = GrafoSimplif.existeVertice("4");
        GrafoSimplif.insertarVertice("4");
        boolean actual = GrafoSimplif.existeVertice("4");
        assertEquals(actual, !expected);
    }

    @Test
    public void testInsertarVertice_TVertice() {
        boolean expected = GrafoSimplif.existeVertice("4");
        TVertice num4 = new TVertice("4");
        GrafoSimplif.insertarVertice(num4);
        boolean actual = GrafoSimplif.existeVertice("4");
        assertEquals(actual, !expected);
    }

    @Test
    public void testGetEtiquetasOrdenado() {
        LinkedList<TVertice> vertices = new LinkedList<>();
        TVertice vert1 = new TVertice("3");
        TVertice vert2 = new TVertice("1");
        TVertice vert3 = new TVertice("2");
        vertices.add(vert1);
        vertices.add(vert2);
        vertices.add(vert3);
        //creo las Aristas
        TAristas aristas = new TAristas();
        TArista arista1 = new TArista("3", "2", 2);
        TArista arista2 = new TArista("1", "3", 1);
        TArista arista3 = new TArista("2", "3", 10);
        aristas.add(arista1);
        aristas.add(arista2);
        aristas.add(arista3);
        TGrafoDirigido grafito = new TGrafoDirigido(vertices, aristas);
        Object[] obj = grafito.getEtiquetasOrdenado();

        boolean primero = obj[0]=="1";
        boolean segundo = obj[0]=="2";
        boolean tercero = obj[0]=="3";
        if(primero == true && segundo == true && tercero ==true){
            assertTrue(true);
        }else {
            assertFalse(false);;
        }

    }

    @Test
    public void testGetVertices() {
        var vertices = GrafoSimplif.vertices.values();
        //Creo los Vertices
        LinkedList<TVertice> verticesprueba = new LinkedList<>();
        TVertice vert1 = new TVertice("1");
        TVertice vert2 = new TVertice("2");
        TVertice vert3 = new TVertice("3");
        verticesprueba.add(vert1);
        verticesprueba.add(vert2);
        verticesprueba.add(vert3);
        
        //Precondicion ambas colecciones tienen el mismo numero de elementos para hacer el test
        boolean ctl = true;
        var itExpexcted = verticesprueba.iterator();
        var itGrafo = vertices.iterator();
        while(itExpexcted.hasNext()){
            Comparable etexpected = itExpexcted.next().getEtiqueta();
            Comparable etgrafo = itGrafo.next().getEtiqueta();
            if(etexpected.compareTo(etgrafo)!= 0){
                ctl =false;
                break;
            }
        }
        assertTrue(ctl);
    }

    @Test
    public void testCentroDelGrafo2() {
    }

    @Test
    public void testCentroDelGrafo() {
    }

    @Test
    public void testFloyd2() {
    }

    @Test
    public void testFloyd() {
    }

    @Test
    public void testObtenerExcentricidad() {
    }

    @Test
    public void testWarshall() {
    }

    @Test
    public void testEliminarVertice() {
    }

    @Test
    public void testBea_Comparable() {
        GrafoSimplif2.imprimirGrafo();
        var ordenado = GrafoSimplif2.bea("1");
        String expected = "12354";
        String actual = "";
        UtilGenerico<TVertice> util = new UtilGenerico<>();
        LinkedList<TVertice> listado = util.FromCollection(ordenado);
        for(TVertice v : listado){
            actual += v.getEtiqueta();
        }

        assertEquals(expected,actual);
    }

    @Test
    public void testBea_0args() {
        var ordenado = GrafoSimplif.bea();
        String expected = "123";
        String actual = ""; 
        UtilGenerico<TVertice> util = new UtilGenerico<>();
        LinkedList<TVertice> listado = util.FromCollection(ordenado);
        for(TVertice v : listado){
            actual += (String)v.getEtiqueta();
        }
        assertEquals(expected,actual);
    }

    @Test
    public void testBpf_0args() {
        var ordenado = GrafoSimplif2.bpf();
        String expected = "12543";
        String actual = "";
        var i = ordenado.toArray();
        for (int j = 0; j < i.length; j++) {
            actual += (String)i[j];
            System.out.println(i[j]);
        }
        assertEquals(expected,actual);
    }

    @Test
    public void testBpf_Comparable() {
        var ordenado = GrafoSimplif2.bpf("2");
        String expected = "254";
        String actual = "";
        var i = ordenado.toArray();
        for (int j = 0; j < i.length; j++) {
            actual += (String)i[j];
            System.out.println(i[j]);
        }
        assertEquals(expected,actual);
    }

    @Test
    public void testBpf_TVertice() {
        TVertice v = GrafoSimplif2.buscarVertice("2");
        var ordenado = GrafoSimplif2.bpf(v);
        String expected = "254";
        String actual = "";
        var i = ordenado.toArray();
        for (int j = 0; j < i.length; j++) {
            actual += (String)i[j];
            System.out.println(i[j]);
        }
        assertEquals(expected,actual);
    }

    @Test
    public void testDesvisitarVertices() {
        GrafoSimplif.desvisitarVertices();
        var vertices = GrafoSimplif.getVertices().values();
        boolean ctl = true;
        for(TVertice vert: vertices){
            if(vert.getVisitado()){
                ctl = false;
                break;
            }
        }
        assertTrue(ctl);
    }

    @Test
    public void testTieneCiclo_Comparable() {
    }

    @Test
    public void testTieneCiclo_TCamino() {
    }

    @Test
    public void testTieneCiclo_0args() {
    }

    @Test
    public void testTodosLosCaminos() {
    }

}
