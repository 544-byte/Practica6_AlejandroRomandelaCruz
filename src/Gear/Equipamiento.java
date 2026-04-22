package Gear;

import java.util.*;

public abstract class Equipamiento {
    private static HashSet<String> rarezas = new HashSet<>(Arrays.asList("Comun","Raro","Epico","Legendario"));
    private String nombre, rareza, tipo;
    //Estadisticas: Fu-Ve-Ma-Fe-Ar-RM-V
    private HashMap<String, Integer> estadisticas;
    private int valor;

    //region Constructores
    public Equipamiento(){
        nombre = rareza = tipo = "";
        estadisticas = new HashMap<>();
        valor = -1;
    }

    public Equipamiento(String nombre, String rareza, String tipo, HashMap<String, Integer> estadisticas, int valor){
        setNombre(nombre);
        setRareza(rareza);
        setTipo(tipo);
        setEstadisticas(estadisticas);
        setValor(valor);
    }

    public Equipamiento(Equipamiento e){
        setNombre(e.getNombre());
        setRareza(e.getRareza());
        setTipo(e.getTipo());
        setEstadisticas(e.getEstadisticas());
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
        ArrayList<String> blacklist = new ArrayList<>(Arrays.asList("del","el","la","de"));
        if (nombre.length() <= 20) {
            this.nombre = nombre;
        } else {
            String nn = nombre.substring(0, 20).trim();
            if (nombre.charAt(20) == ' '){
            } else {
                ArrayList<String> aux = new ArrayList<>(Arrays.asList(nn.toString().split(" ")));
                aux.removeLast();
                nn = "";
                for (String s : aux){
                    nn += s;
                    nn += " ";
                }
                nn = nn.trim();
                if (nn.contains(",")){
                    nn = nn.split(",")[0];
                }
            }
            ArrayList<String> aux = new ArrayList<>(Arrays.asList(nn.split(" ")));
            if (!Collections.disjoint(aux,blacklist)) {
                while (!Collections.disjoint(new ArrayList<>(Arrays.asList(aux.getLast())),blacklist)) {
                    aux.removeLast();
                }
                nn = "";
                for (String s : aux){
                    nn += s;
                    nn += " ";
                }
                nn = nn.trim();
            }
            setNombre(nn);
            System.err.println("ERROR EN EL SETTER, EL NOMBRE " + nombre + " ES DEMASIADO LARGO, SE VA A SUSTITUIR POR " + nn );
        }
    }

    public String getRareza() {
        return rareza;
    }

    public void setRareza(String rareza) {
        if (rarezas.contains(rareza)) {
            this.rareza = rareza;
        } else {
            System.err.println("ERROR, LA RAREZA " + rareza + " NO ES VALIDA");
        }
    }

    public void setTipo(String tipo){
        this.tipo = tipo;
    }

    public String getTipo(){
        return tipo;
    }

    public HashMap<String, Integer> getEstadisticas() {
        return estadisticas;
    }

    public void setEstadisticas(HashMap<String, Integer> estadisticas) {
        this.estadisticas = estadisticas;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        if (valor >= 1) {
            this.valor = valor;
        } else {
            System.err.println("ERROR, EL VALOR " + valor + " NO ES VALIDO.");
        }
    }

    //endregion

    //region Overrides
    public abstract String toStringEx();
    public String toString() {
        return getNombre() + ":" +
                "\nRareza: " + getRareza() +
                "\nTipo: " + getTipo() +
                toStringEx() +
                "\nEstadisticas: " + getEstadisticas() +
                "\nValor: " + getValor();
    }

    public boolean equals(Equipamiento e) {
        return getNombre().equals(e.getNombre()) &&
                getRareza().equals(e.getRareza()) &&
                getTipo().equals(e.getTipo()) &&
                getValor() == e.getValor() &&
                getEstadisticas().equals(e.getEstadisticas());
    }
    //endregion
}
