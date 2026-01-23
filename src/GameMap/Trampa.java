package GameMap;

import java.util.Random;

import Characters.*;
import Combat.*;
import GameMap.*;
import Misc.*;

/**
 * Representa una trampa dentro del mapa del juego.<br>
 * Una trampa posee una categoría, un valor de perjuicio y una probabilidad
 * de fracaso que determina si se activa o no al ser pisada por un personaje.
 */
public class Trampa {
    /**
     * Categoría de la trampa (Pinchos, Brea o Serpientes).
     */
    String categoria;
    /**
     * Valor del perjuicio que causa la trampa al activarse.
     */
    int perjuicio;
    /**
     * Probabilidad de fracaso de la trampa (0–75).
     */
    double fracaso;

    /**
     * Constructor por defecto.
     * Inicializa la trampa con valores neutros.
     */
    public Trampa() {
        categoria = "";
        perjuicio = 0;
        fracaso = 0;
    }

    /**
     * Constructor que inicializa la trampa con una categoría
     * y valores aleatorios de perjuicio y fracaso.
     *
     * @param categoria Categoría de la trampa
     */
    public Trampa(String categoria) {
        Random r = new Random();
        setCategoria(categoria);
        setPerjuicio(r.nextInt(5, 20 + 1));
        setFracaso(r.nextInt(0, 75 + 1));
    }

    /**
     * Constructor que inicializa la trampa con una categoría y perjuicio fijo.
     * El valor de fracaso se genera aleatoriamente.
     *
     * @param categoria Categoría de la trampa
     * @param perjuicio Perjuicio que causará la trampa
     */
    public Trampa(String categoria, int perjuicio) {
        Random r = new Random();
        setCategoria(categoria);
        setPerjuicio(perjuicio);
        setFracaso(r.nextInt(0, 75 + 1));
    }

    /**
     * Constructor copia.
     *
     * @param trampa Trampa a copiar
     */
    public Trampa(Trampa trampa) {
        this.setCategoria(trampa.getCategoria());
        this.setPerjuicio(trampa.getPerjuicio());
        this.setFracaso(trampa.getFracaso());
    }

    /**
     * Compara dos trampas por sus atributos.
     *
     * @param t Trampa a comparar
     * @return true si ambas trampas son equivalentes
     */
    public boolean equals(Trampa t) {
        return (t.getCategoria().equals(getCategoria()) && t.getPerjuicio() == getPerjuicio() && t.getFracaso() == getFracaso());
    }

    /**
     * Representación textual de la trampa.
     *
     * @return Información de la trampa
     */
    public String toString() {
        return ("Categoría: " + getCategoria() + "\nPerjuicio: " + getPerjuicio() + "\nFracaso " + getFracaso());
    }

    /**
     * Obtiene la categoría de la trampa.
     *
     * @return Categoría de la trampa
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * Establece la categoría de la trampa.<br>
     * Las categorías válidas son: Pinchos, Brea y Serpientes.
     *
     * @param categoriaSetter Categoría de la trampa
     */
    public void setCategoria(String categoriaSetter) {
        if (categoriaSetter.equals("Pinchos") || categoriaSetter.equals("Brea") || categoriaSetter.equals("Serpientes"))
            categoria = categoriaSetter;
        else System.err.println("La categoría es incorrecta.");
    }

    /**
     * Obtiene el valor del perjuicio de la trampa.
     *
     * @return Perjuicio
     */
    public int getPerjuicio() {
        return perjuicio;
    }

    /**
     * Establece el valor del perjuicio de la trampa.<br>
     * El valor se limita automáticamente al rango 5–20.
     *
     * @param perjuicioSetter Valor del perjuicio
     */
    public void setPerjuicio(int perjuicioSetter) {
        if (perjuicioSetter >= 5 && perjuicioSetter <= 20) perjuicio = perjuicioSetter;
        else if (perjuicioSetter < 5) perjuicio = 5;
        else perjuicio = 20;
    }

    /**
     * Obtiene la probabilidad de fracaso de la trampa.
     *
     * @return Probabilidad de fracaso
     */
    public double getFracaso() {
        return fracaso;
    }

    /**
     * Establece la probabilidad de fracaso de la trampa.
     * <p>
     * El valor se limita automáticamente al rango 0–75.
     * </p>
     *
     * @param fracasoSetter Probabilidad de fracaso
     */
    public void setFracaso(double fracasoSetter) {
        if (fracasoSetter >= 0 && fracasoSetter <= 75) fracaso = fracasoSetter;
        else if (fracasoSetter < 0) fracaso = 0;
        else if (fracasoSetter > 75) fracaso = 75;
        else System.err.println("El valor del fracaso es incorrecto.");
    }

    /**
     * Intenta activar la trampa.
     * <p>
     * Si el valor aleatorio es menor que la probabilidad de fracaso,
     * la trampa se activa y devuelve el perjuicio; en caso contrario,
     * no se activa.
     * </p>
     *
     * @return Perjuicio causado por la trampa o 0 si falla
     */
    public int activaTrampa() {
        Random r = new Random();
        if (r.nextDouble(0, 100 + 1) < fracaso) return perjuicio;
        else return 0;
    }
}
