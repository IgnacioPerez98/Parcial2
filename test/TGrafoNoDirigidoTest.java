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
    public TGrafoNoDirigidoTest() {
    }

    private TGrafoNoDirigido CrearGrafo(){
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
    @Before
    public  void SetUp(){
        GrafoSimplificado = CrearGrafo();
    }
    @Test
    public void testPrim() {
        
        var res = GrafoSimplificado.esConexo();
        if(res){
            TGrafoNoDirigido myTgrafito2 = GrafoSimplificado.Prim();
            myTgrafito2.imprimirGrafo(false);
            System.out.println(myTgrafito2.getCostoTotal());
            assertEquals(18.0, myTgrafito2.getCostoTotal(),0);
            return;
        }
        assertFalse(res);   
    }

    @Test
    public void testKruskal() {
        var res = GrafoSimplificado.esConexo();
        if(res){
            TGrafoNoDirigido myTgrafito2 = GrafoSimplificado.Kruskal();
            myTgrafito2.imprimirGrafo(false);
            System.out.println(myTgrafito2.getCostoTotal());
            assertEquals(18.0, myTgrafito2.getCostoTotal(),0);
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
        for(int i = 0; i < colection.length; i++){
            //System.out.println(colection[i]+"");
            expected += ((TVertice)colection[i]).getEtiqueta()+"";
        }
        assertEquals("Test de Busqueda en Amplitud", expected, actual);
    }
    
    @Test
    public void testBpf(){
        Collection<TVertice> col = GrafoSimplificado.bpf();
        
        String actual = "123456";
        String expected = "";
        var colection = col.toArray();
        for(int i = 0; i < colection.length; i++){
            System.out.println(colection[i]+"");
            expected += colection[i]+"";
        }
        assertEquals("Test de Busqueda en Profundidad", expected, actual);
        
        
        
    }

    @org.junit.Test
    public void testPuntosArticulacion() {
        
    }

    @org.junit.Test
    public void testEsConexo() {
        var esConexo =GrafoSimplificado.esConexo();
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
        var res =  GrafoSimplificado.conectados(u, v);
        assertTrue(res);
    }

    @org.junit.Test
    public void testGetCostoTotal() {
        var costo = GrafoSimplificado.getCostoTotal();
        System.out.println(costo+"");
        //Delta es la diferencia maxima tolerable
        assertEquals(costo, 85.0, 0);
        
    }

    @org.junit.Test
    public void testNumBacon() {//Agregar maximo de saltos
        
    }
    
}
