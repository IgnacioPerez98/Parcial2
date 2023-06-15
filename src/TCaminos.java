/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.util.Collection;
import java.util.LinkedList;

/**
 *
 * @author Administrator
 */
public class TCaminos {

    private Collection<TCamino> caminos;

    public TCaminos() {
        this.caminos = new LinkedList<>();
    }

    public String imprimirCaminos() {
        StringBuilder sb = new StringBuilder();
        for (TCamino camino : caminos) {
            sb.append(camino.imprimirEtiquetas() + "\n");
        }
        return sb.toString();
    }

    public void imprimirCaminosConsola() {
        System.out.println(imprimirCaminos());
    }

    public Collection<TCamino> getCaminos() {
        return caminos;
    }
}
