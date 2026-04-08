package Gear;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Artefacto extends Equipamiento{
    private static HashSet<String> tipos = new HashSet<>(Set.of("Anillo","Amuleto"));

    //region Constructores
    public Artefacto(){
        super();
    }

    // Nombre,Rareza,Pieza,Estadistica(Fu-Ve-Ma-Fe-Ar-RM-V),Valor
    public Artefacto(String nombre, String rareza, String tipo, HashMap<String, Integer> estadisticas, int valor){
        super(nombre,rareza,tipo,estadisticas,valor);
    }

    public Artefacto(Artefacto a){
        super(a);
    }
    //endregion

    //region Setters&Getters
    public void setTipo(String tipo) {
        if (tipos.contains(tipo)){
            super.setTipo(tipo);
        } else {
            System.err.println("TIPO DE AMULETO NO VALIDO: " + tipo);
        }
    }
    //endregion

    //region Overrides
    public String toStringEx(){
        return "";
    }

    public boolean equals(Artefacto a) {
        return super.equals(a);
    }
    //endregion

}
