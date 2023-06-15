/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */

import java.util.Collection;
import java.util.LinkedList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author nicolas
 */
public class TGrafoNoDirigidoTest {
        
    public TGrafoNoDirigidoTest() {
    }

    @org.junit.Test
    public void testInsertarArista() {
    }

    @org.junit.Test
    public void testGetLasAristas() {
    }

    @org.junit.Test
    public void testPrim() {
        Collection<TVertice> vertices = new LinkedList<>();
        TVertice miVerticetito1 = new TVertice("Hola");
        TVertice miVerticetito2 = new TVertice("Hola2");
        TVertice miVerticetito3 = new TVertice("Hola3");
        vertices.add(miVerticetito2);
        vertices.add(miVerticetito1);
        vertices.add(miVerticetito3);
        
        TAristas lasAristasDelNico = new TAristas();
        TArista laAristaDelNico1 = new TArista("Hola", "Hola2", 2);
        TArista laAristaDelNico2 = new TArista("Hola", "Hola3", 1);
        TArista laAristaDelNico3 = new TArista("Hola2", "Hola3", 100);
        lasAristasDelNico.add(laAristaDelNico1);
        lasAristasDelNico.add(laAristaDelNico2);
        lasAristasDelNico.add(laAristaDelNico3);
               
        TGrafoNoDirigido myTgrafito = new TGrafoNoDirigido(vertices, lasAristasDelNico);
        
        TGrafoNoDirigido myTgrafito2 = myTgrafito.Prim();
               
        double sum = 0;
        
        for(TArista a: myTgrafito2.getLasAristas()){
            sum = sum + a.costo;
        }
        
        assert(6.0 == sum);
    }

    @org.junit.Test
    public void testKruskal() {
    }

    @org.junit.Test
    public void testBea() {
        Collection<TVertice> vertices = new LinkedList<>();
        TVertice miVerticetito1 = new TVertice("1");
        TVertice miVerticetito2 = new TVertice("2");
        TVertice miVerticetito3 = new TVertice("3");
        vertices.add(miVerticetito2);
        vertices.add(miVerticetito1);
        vertices.add(miVerticetito3);
        
        TAristas lasAristasDelNico = new TAristas();
        TArista laAristaDelNico1 = new TArista("1", "2", 2);
        TArista laAristaDelNico2 = new TArista("1", "3", 1);
        TArista laAristaDelNico3 = new TArista("2", "3", 100);
        lasAristasDelNico.add(laAristaDelNico1);
        lasAristasDelNico.add(laAristaDelNico2);
        lasAristasDelNico.add(laAristaDelNico3);
               
        TGrafoNoDirigido myTgrafito = new TGrafoNoDirigido(vertices, lasAristasDelNico);
        
        Collection<TVertice> myTgrafito2 = myTgrafito.bea();
        String res = "";
        
        for(TVertice v: myTgrafito2){
            res = res + v.getEtiqueta();
        }
        
        assertEquals("123", res);
    }

    @org.junit.Test
    public void testPuntosArticulacion() {
    }

    @org.junit.Test
    public void testEsConexo() {
    }

    @org.junit.Test
    public void testConectados() {
    }

    @org.junit.Test
    public void testGetCostoTotal() {
    }

    @org.junit.Test
    public void testNumBacon() {
    }
    
}
