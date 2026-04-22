package Gear;


import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

// Solo puede tener estadísticas Fu,Ve,Ma,Fe
public class Arma extends Equipamiento{
    private static HashSet<String> armasUnaMano = new HashSet<>(Arrays.asList("Espada","Maza","Hacha","Cetro","Daga","Mangual"));
    private static HashSet<String> armasDosManos = new HashSet<>(Arrays.asList("Espadon","Martillo","Arco","Baston"));
    private int empuñadura;

    //region Constructores
    public Arma(){
        super();
        empuñadura = -1;
    }

    // Nombre,Rareza,Tipo,Estadisticas(Fu-Ve-Ma-Fe),Valor
    public Arma(String nombre, String rareza, String tipo, HashMap<String, Integer> estadisticas, int valor){
        super(nombre,rareza,tipo,estadisticas,valor);
    }

    public Arma(Arma a){
        super(a);
    }
    //endregion

    //region Setters&Getters
    public int getEmpuñadura() {
        return empuñadura;
    }

    public void setEmpuñadura(int empuñadura) {
        if (empuñadura >=1 && empuñadura <= 2) {
            this.empuñadura = empuñadura;
        } else {
            System.err.println("EMPUÑADURA NO VÁLIDA: " + empuñadura);
        }
    }

    public void setTipo(String tipo) {
        if (armasUnaMano.contains(tipo)){
            setEmpuñadura(1);
            super.setTipo(tipo);
        } else if (armasDosManos.contains(tipo)){
            setEmpuñadura(2);
            super.setTipo(tipo);
        } else {
            System.err.println("TIPO DE ARMA NO VALIDO: " + tipo);
        }
    }

    public static HashSet<String> getTipos(){
        HashSet<String> tipos = new HashSet<>(armasUnaMano);
        tipos.addAll(armasDosManos);
        return tipos;
    }

    //endregion

    //region Overrides
    public String toStringEx() {
        return "\nEmpuñadura: " + getEmpuñadura() + " mano" + (getEmpuñadura() == 2 ? "s" : "");
    }

    public boolean equals(Arma a) {
        return super.equals(a);
    }
    //endregion

}
