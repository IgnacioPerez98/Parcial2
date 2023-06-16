
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

    @Before
    public void setUp() {
        //Grafo = crearGrafo();//Cambiar los path de los archivos en la funcion
        GrafoSimplif = crearGrafoSimplificado();
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
        TArista arista = new TArista("2","1",100);
        GrafoSimplif.insertarArista(arista);
        boolean actual = GrafoSimplif.existeArista("2", "1");
        assertTrue(actual);
        
    }
    @Test
    public void testExisteAristaFalse() {
        TArista arista = new TArista("2","1",100);
        boolean actual = GrafoSimplif.existeArista("2000", "1");
        assertFalse(actual);
    }

    @Test
    public void testExisteVertice() {
    }

    @Test
    public void testBuscarVertice() {
    }

    @Test
    public void testInsertarArista() {
    }

    @Test
    public void testInsertarVertice_Comparable() {
    }

    @Test
    public void testInsertarVertice_TVertice() {
    }

    @Test
    public void testGetEtiquetasOrdenado() {
    }

    @Test
    public void testGetVertices() {
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
    }

    @Test
    public void testBea_0args() {
    }

    @Test
    public void testBpf_0args() {
    }

    @Test
    public void testBpf_Comparable() {
    }

    @Test
    public void testBpf_TVertice() {
    }

    @Test
    public void testDesvisitarVertices() {
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
