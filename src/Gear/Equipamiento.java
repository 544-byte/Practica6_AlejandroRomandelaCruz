package Gear;

import java.util.HashMap;

public abstract class Equipamiento {
    private String nombre;
    //Estadisticas: Fu-Ve-Ma-Fe-Ar-RM-V
    // Fuerza, Velocidad, Magia, Fe, Armadura, Resistencia Mágica, Vida
    private HashMap<String, Double> estadisticas;
    private int rareza, valor;

    //region Constructores
    public Equipamiento(){
        nombre = "";
        estadisticas = new HashMap<>();
        rareza = valor = -1;
    }

    public Equipamiento(String nombre, HashMap<String, Double> estadisticas, int rareza, int valor){
        setNombre(nombre);
        setEstadisticas(estadisticas);
        setRareza(rareza);
        setValor(valor);
    }

    public Equipamiento(Equipamiento e){
        setNombre(e.getNombre());
        setEstadisticas(e.getEstadisticas());
        setRareza(e.getRareza());
        setValor(e.getValor());
    }
    //endregion

    //Estadisticas: Fu-Ve-Ma-Fe-Ar-RM-V
    // Fuerza, Velocidad, Magia, Fe, Armadura, Resistencia Mágica, Vida
    public double recuperaEstadistica(String estadistica){
        return estadisticas.get(estadistica);
    }

    //region Setter&Getters

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre.length() <= 20) {
            this.nombre = nombre;
        } else {
            System.err.println("ERROR EN EL SETTER, EL NOMBRE " + nombre + " ES DEMASIADO LARGO");
        }
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

    public String getRarezaName() {
        switch (getRareza()){
            case 1 -> {return "común";}
            case 2 -> {return "raro";}
            case 3 -> {return "épico";}
            case 4 -> {return "legendario";}
            default -> {return "";}
        }
    }

    public void setRareza(int rareza) {
        if (rareza >= 1 && rareza <= 4) {
            this.rareza = rareza;
        } else {
            System.err.println("ERROR, LA RAREZA " + rareza + " NO ES VALIDA");
        }
    }

    public void setRareza(String rareza) {
        switch (rareza){
            case "común" -> setRareza(1);
            case "raro" -> setRareza(2);
            case "épico" -> setRareza(3);
            case "legendario" -> setRareza(4);
            default -> {}
        }
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        if (valor >= 1) {
            this.valor = valor;
        } else {
            System.err.println("ERROR, EL VALOR " + valor + " NO ES VALIDO");
        }
    }

    //endregion

    public String toString() {
        return super.toString();
    }

    public boolean equals(Equipamiento e) {
        if (getNombre().equals(e.getNombre()) && getRareza() == e.getRareza() && getValor() == e.getValor() && getEstadisticas() == e.getEstadisticas()){
            return true;
        } else {
            return false;
        }
    }
}
