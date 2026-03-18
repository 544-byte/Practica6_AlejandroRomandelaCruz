package Gear;

import java.util.HashMap;

public abstract class Equipamiento {
    private String nombre;
    private HashMap<String, Double> estadisticas;
    private int rareza, valor;

    // region Setter&Getters

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public HashMap<String, Double> getEstadisticas() {
        return estadisticas;
    }

    public void setEstadisticas(HashMap<String, Double> estadisticas) {
        this.estadisticas = estadisticas;
    }

    public int getRareza() {
        return rareza;
    }

    public void setRareza(int rareza) {
        this.rareza = rareza;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    // end region
}
