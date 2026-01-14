package GameMap;

import java.util.Random;

import Characters.*;
import Combat.*;
import GameMap.*;
import Misc.*;

public class Trampa {
    String categoria;
    int perjuicio;
    double fracaso;

    public Trampa() {
        categoria = "";
        perjuicio = 0;
        fracaso = 0;
    }

    public Trampa(String categoria) {
        Random r = new Random();
        setCategoria(categoria);
        setPerjuicio(r.nextInt(5, 20 + 1));
        setFracaso(r.nextInt(0, 75 + 1));
    }

    public Trampa(String categoria, int perjuicio) {
        Random r = new Random();
        setCategoria(categoria);
        setPerjuicio(perjuicio);
        setFracaso(r.nextInt(0, 75 + 1));
    }

    public Trampa(Trampa trampa){
        this.setCategoria(trampa.getCategoria());
        this.setPerjuicio(trampa.getPerjuicio());
        this.setFracaso(trampa.getFracaso());
    }

    public boolean equals(Trampa t) {
        return (t.getCategoria().equals(getCategoria()) && t.getPerjuicio() == getPerjuicio() && t.getFracaso() == getFracaso());
    }

    public String toString() {
        return (
                "Categoría: " + getCategoria() +
                        "\nPerjuicio: " + getPerjuicio() +
                        "\nFracaso " + getFracaso()
        );
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoriaSetter) {
        if (categoriaSetter.equals("Pinchos") || categoriaSetter.equals("Brea") || categoriaSetter.equals("Serpientes"))
            categoria = categoriaSetter;
        else System.err.println("La categoría es incorrecta.");
    }

    public int getPerjuicio() {
        return perjuicio;
    }

    public void setPerjuicio(int perjuicioSetter) {
        if (perjuicioSetter >= 5 && perjuicioSetter <= 20)
            perjuicio = perjuicioSetter;
        else if (perjuicioSetter < 5) perjuicio = 5;
        else perjuicio = 20;
    }

    public double getFracaso() {
        return fracaso;
    }

    public void setFracaso(double fracasoSetter) {
        if (fracasoSetter >= 0 && fracasoSetter <= 75)
            fracaso = fracasoSetter;
        else if (fracasoSetter < 0) fracaso = 0;
        else if (fracasoSetter > 75) fracaso = 75;
        else System.err.println("El valor del fracaso es incorrecto.");
    }

    public int activaTrampa() {
        Random r = new Random();
        if (r.nextDouble(0, 100 + 1) < fracaso)
            return perjuicio;
        else return 0;
    }
}
