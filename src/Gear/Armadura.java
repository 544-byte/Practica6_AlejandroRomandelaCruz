package Gear;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

// Solo puede tener estadísticas Ar,RM,V
public class Armadura extends Equipamiento{
    private static HashSet<String> tipos = new HashSet<>(Set.of("Yelmo","Pechera","Hombreras","Guanteletes","Grebas","Botas"));
    private static HashSet<String> materiales = new HashSet<>(Set.of("Tela","Cuero","Metal"));
    private String material;

    //region Constructores
    public Armadura(){
        super();
        material = "";
    }

    // Nombre,Rareza,Pieza,Tipo,Estadistica(Ar-RM-V),Valor
    public Armadura(String nombre, String rareza,String tipo, String material , HashMap<String, Integer> estadisticas, int valor){
        super(nombre,rareza,tipo,estadisticas,valor);
        setMaterial(material);
    }

    public Armadura(Armadura a){
        super(a);
        setMaterial(a.getMaterial());
    }
    //endregion

    //region Setters&Getters
    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        if (materiales.contains(material)){
            this.material = material;
        } else {
            System.err.println("MATERIAL NO VÁLIDO: " + material);
        }
    }

    public void setTipo(String tipo) {
        if (tipos.contains(tipo)){
            super.setTipo(tipo);
        } else {
            System.err.println("TIPO DE ARMADURA NO VALIDO: " + tipo);
        }
    }

    public static HashSet<String> getTipos(){
        return tipos;
    }
    //endregion

    //region Overrides
    public String toStringEx() {
        return "\nMaterial: " + getMaterial();
    }

    public boolean equals(Armadura a) {
        return super.equals(a) && getMaterial().equals(a.getMaterial());
    }
    //endregion

}
