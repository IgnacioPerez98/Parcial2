/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author nicolas
 */
public class TGrafoNoDirigidoTest {

    TGrafoNoDirigido GrafoSimplificado;
    TGrafoNoDirigido GrafoCiudades;
    TGrafoNoDirigido GrafoSaltos;

    public TGrafoNoDirigidoTest() {
    }

    /**
     * MVD SANTOS
     *
     * BSAS
     *
     * PA
     *
     */
    private TGrafoNoDirigido CrearGrafoCiudades() {
        LinkedList<TVertice> verts = new LinkedList<>();
        LinkedList<TArista> arists = new LinkedList<>();
        TVertice v1 = new TVertice("MVD");
        TVertice v2 = new TVertice("PA");
        TVertice v3 = new TVertice("BSAS");
        TVertice v4 = new TVertice("SANTOS");
        verts.add(v1);
        verts.add(v2);
        verts.add(v3);
        verts.add(v4);

        TArista a1 = new TArista("MVD", "BSAS", 0);
        TArista a2 = new TArista("BSAS", "SANTOS", 0);
        TArista a3 = new TArista("MVD", "PA", 0);
        TArista a4 = new TArista("PA", "BSAS", 0);
        TArista a5 = new TArista("PA", "SANTOS", 0);
        TArista a6 = new TArista("MVD", "SANTOS", 0);

        arists.add(a1);
        arists.add(a2);
        arists.add(a3);
        arists.add(a4);
        arists.add(a5);
        arists.add(a6);

        return new TGrafoNoDirigido(verts, arists);
    }

    private TGrafoNoDirigido CrearGrafo() {
        Collection<TVertice> vertices = new LinkedList<>();
        TVertice vert1 = new TVertice("1");
        TVertice vert2 = new TVertice("2");
        TVertice vert3 = new TVertice("3");
        TVertice vert4 = new TVertice("4");
        TVertice vert5 = new TVertice("5");
        TVertice vert6 = new TVertice("6");

        vertices.add(vert1);
        vertices.add(vert2);
        vertices.add(vert3);
        vertices.add(vert4);
        vertices.add(vert5);
        vertices.add(vert6);

        //Costo total 85.
        TAristas lasAristas = new TAristas();
        TArista laAristas1 = new TArista("1", "2", 5);
        TArista laAristas2 = new TArista("1", "3", 7);
        TArista laAristas3 = new TArista("1", "4", 3);
        TArista laAristas4 = new TArista("1", "5", 9);
        TArista laAristas5 = new TArista("1", "6", 4);
        TArista laAristas6 = new TArista("2", "3", 3);
        TArista laAristas7 = new TArista("2", "4", 5);
        TArista laAristas8 = new TArista("2", "5", 7);
        TArista laAristas9 = new TArista("2", "6", 8);
        TArista laAristas10 = new TArista("3", "4", 4);
        TArista laAristas11 = new TArista("3", "5", 5);
        TArista laAristas12 = new TArista("3", "6", 7);
        TArista laAristas13 = new TArista("4", "5", 9);
        TArista laAristas14 = new TArista("4", "6", 3);
        TArista laAristas15 = new TArista("5", "6", 6);
        lasAristas.add(laAristas1);
        lasAristas.add(laAristas2);
        lasAristas.add(laAristas3);
        lasAristas.add(laAristas4);
        lasAristas.add(laAristas5);
        lasAristas.add(laAristas6);
        lasAristas.add(laAristas7);
        lasAristas.add(laAristas8);
        lasAristas.add(laAristas9);
        lasAristas.add(laAristas10);
        lasAristas.add(laAristas11);
        lasAristas.add(laAristas12);
        lasAristas.add(laAristas13);
        lasAristas.add(laAristas14);
        lasAristas.add(laAristas15);
        TGrafoNoDirigido myTgrafito = new TGrafoNoDirigido(vertices, lasAristas);
        return myTgrafito;
    }

    private TGrafoNoDirigido CrearGrafoParaBeacon(){
        Collection<TVertice> vertices = new LinkedList<>();
        TVertice vert1 = new TVertice("1");
        TVertice vert2 = new TVertice("2");
        TVertice vert3 = new TVertice("3");
        TVertice vert4 = new TVertice("4");
        TVertice vert5 = new TVertice("5");
        TVertice vert6 = new TVertice("6");
        TVertice vert7 = new TVertice("7");
        TVertice vert8 = new TVertice("8");

        vertices.add(vert1);
        vertices.add(vert2);
        vertices.add(vert3);
        vertices.add(vert4);
        vertices.add(vert5);
        vertices.add(vert6);
        vertices.add(vert7);
        vertices.add(vert8);

        //Costo total 85.
        TAristas lasAristas = new TAristas();
        TArista laAristas1 = new TArista("3", "7", 5);
        TArista laAristas2 = new TArista("3", "4", 7);
        TArista laAristas3 = new TArista("3", "5", 3);
        TArista laAristas4 = new TArista("3", "6", 9);
        TArista laAristas5 = new TArista("6", "1", 4);
        TArista laAristas6 = new TArista("1", "8", 3);
        TArista laAristas7 = new TArista("5", "2", 5);
        lasAristas.add(laAristas1);
        lasAristas.add(laAristas2);
        lasAristas.add(laAristas3);
        lasAristas.add(laAristas4);
        lasAristas.add(laAristas5);
        lasAristas.add(laAristas6);
        lasAristas.add(laAristas7);
        TGrafoNoDirigido myTgrafito = new TGrafoNoDirigido(vertices, lasAristas);
        return myTgrafito;
    }
    
    @Before
    public void SetUp() {
        GrafoSimplificado = CrearGrafo();
        GrafoCiudades = CrearGrafoCiudades();
        GrafoSaltos = CrearGrafoParaBeacon();
    }

    @Test 
    public void TestBeacon(){
        var res = GrafoSaltos.numBacon("3", "8");
        assertEquals(res, 3);
    }
    
    
    
    @Test
    public void testPrim() {

        var res = GrafoSimplificado.esConexo();
        if (res) {
            TGrafoNoDirigido myTgrafito2 = GrafoSimplificado.Prim();
            myTgrafito2.imprimirGrafo(false);
            System.out.println(myTgrafito2.getCostoTotal());
            assertEquals(18.0, myTgrafito2.getCostoTotal(), 0);
            return;
        }
        assertFalse(res);
    }

    @Test
    public void testKruskal() {
        var res = GrafoSimplificado.esConexo();
        if (res) {
            TGrafoNoDirigido myTgrafito2 = GrafoSimplificado.Kruskal();
            myTgrafito2.imprimirGrafo(false);
            System.out.println(myTgrafito2.getCostoTotal());
            assertEquals(18.0, myTgrafito2.getCostoTotal(), 0);
            return;
        }
        assertFalse(res);
    }

    @org.junit.Test
    public void testBea() {
        Collection<TVertice> grafo = GrafoSimplificado.bea();
        String actual = "123456";
        String expected = "";
        var colection = grafo.toArray();
        for (int i = 0; i < colection.length; i++) {
            System.out.println(colection[i] + "");
            expected += ((TVertice) colection[i]).getEtiqueta() + "";
        }
        assertEquals("Test de Busqueda en Amplitud", expected, actual);
    }
    
    @Test
    public void TOLEARN(){
        Collection<TVertice> vertices = new LinkedList<>();
        TVertice vert1 = new TVertice("A");
        TVertice vert2 = new TVertice("B");
        TVertice vert3 = new TVertice("C");
        TVertice vert4 = new TVertice("D");
        TVertice vert5 = new TVertice("E");
        vertices.add(vert1);
        vertices.add(vert2);
        vertices.add(vert3);
        vertices.add(vert4);
        vertices.add(vert5);

        TAristas lasAristas = new TAristas();
        TArista laAristas1 = new TArista("A", "B", 0);
        TArista laAristas2 = new TArista("A", "D", 0);
        TArista laAristas3 = new TArista("B", "C", 0);
        TArista laAristas4 = new TArista("C", "D", 0);
        TArista laAristas5 = new TArista("D", "E", 0);
        lasAristas.add(laAristas1);
        lasAristas.add(laAristas2);
        lasAristas.add(laAristas3);
        lasAristas.add(laAristas4);
        lasAristas.add(laAristas5);
        TGrafoNoDirigido myTgrafito = new TGrafoNoDirigido(vertices, lasAristas);
        var res =  myTgrafito.listarContactos("A", 1);
        var vect = res.toArray();
        for (int i = 0; i < vect.length; i++) {
            System.out.println(vect[i]+"");
        }
    }

    @Test
    public void testBpf() {
        Collection<TVertice> col = GrafoSimplificado.bpf();

        String actual = "123456";
        String expected = "";
        var colection = col.toArray();
        for (int i = 0; i < colection.length; i++) {
            System.out.println(colection[i] + "");
            expected += colection[i] + "";
        }
        assertEquals("Test de Busqueda en Profundidad", expected, actual);

    }

   

    @org.junit.Test
    public void testEsConexo() {
        var esConexo = GrafoSimplificado.esConexo();
        System.out.println(esConexo);
        assertTrue(esConexo);
    }

    @org.junit.Test
    public void testConectados() {
        //Esta mal el metodo conectados 
        var u = GrafoSimplificado.buscarVertice("1");
        var v = GrafoSimplificado.buscarVertice("4");
        //Precondicion
        GrafoSimplificado.desvisitarVertices();
        var res = GrafoSimplificado.conectados(u, v);
        assertTrue(res);
    }

    @org.junit.Test
    public void testGetCostoTotal() {
        var costo = GrafoSimplificado.getCostoTotal();
        System.out.println(costo + "");
        assertEquals(costo, 85.0, 0);

    }

    @org.junit.Test
    public void recuperarSaltos() {//Agregar maximo de saltos
        var res = GrafoSaltos.SaltosDesdeVertice("3", 2 );
        for(Object t: res){
            System.out.println(t+"");
        }
        
    }
    
    //<editor-fold  desc="TESTS CAMINO MAS CORTO EXHAUSTIVO" >
    @Test
    /**
     Se asegura que para el grafo de las ciudades, el menor camino sea el que es directo 
     */
    public void testCaminomasCorto() {
        var res = GrafoCiudades.rutaMenosSaltos("MVD", "SANTOS");
        String expected = "MVDSANTOS";
        String actual = "";
        for (Iterator<TVertice> iterator = res.iterator(); iterator.hasNext();) {
            TVertice next = iterator.next();
            System.out.println(next.getEtiqueta() + "");
            actual += next.getEtiqueta() + "";
        }
        assertEquals(expected, actual);
    }
    
    /**
     Se asegura que para el grafo de las ciudades, el menor camino sea el que NO ES DIRECTO.
     * Prueba que el metodo no devuelve una arista, sino que hace la coneccion de una ruta.
     */
    @Test
    public void testCaminomasCortoNoDirecto() {
        GrafoCiudades.eliminarArista("MVD", "SANTOS");
        var res = GrafoCiudades.rutaMenosSaltos("MVD", "SANTOS");
        String expected = "MVDBSASSANTOS";
        String actual = "";
        for (Iterator<TVertice> iterator = res.iterator(); iterator.hasNext();) {
            TVertice next = iterator.next();
            System.out.println(next.getEtiqueta() + "");
            actual += next.getEtiqueta() + "";
        }
        assertEquals(expected, actual);
    }

    @Test
    /**
     Se espera que devuelva una lista vacia si nesta vacio
     */
    public void testCaminomasCortoVacio() {
        TGrafoNoDirigido graph = new TGrafoNoDirigido(new LinkedList<TVertice>(), new LinkedList<>());
        var res = graph.rutaMenosSaltos("algo", "algo2");
        int tamanioActual = res.size();        
        assertEquals(tamanioActual, 0);
    }
    
    
    @Test
    /**
     Se espera que devuelva una lista vacia si el parametro es nulo
     */
    public void testCaminomasCortoFaltaPrimerParametro() {
        var res = GrafoCiudades.rutaMenosSaltos(null, "SANTOS");
        int tamanioActual = res.size();        
        assertEquals(tamanioActual, 0);
    }
    @Test
    /**
     Se espera que devuelva una lista vacia si el parametro es Cadena vacia y no lo encuentra
     */
    public void testCaminomasCortoFaltaSegundoParametro() {
        var res = GrafoCiudades.rutaMenosSaltos("MVD", "");
        int tamanioActual = res.size();        
        assertEquals(tamanioActual, 0);
    }
    @Test
    /**
     Se espera que devuelva una lista vacia si el parametro no lo encuentra
     */
    public void testCaminomasCortoParametroInexistente() {
        var res = GrafoCiudades.rutaMenosSaltos("MVD", "NACHOLANDIALGORITMOS");
        int tamanioActual = res.size();        
        assertEquals(tamanioActual, 0);
    }   
    //</editor-fold>
    
}
