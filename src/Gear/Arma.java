package Gear;

public class Arma extends Equipamiento{
    private int empuñadura;
    private String tipo;

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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        switch (getEmpuñadura()){
            case 1 -> {
                switch (tipo){
                    case "espada" -> this.tipo = tipo;
                    case "maza" -> this.tipo = tipo;
                    case "hacha" -> this.tipo = tipo;
                    case "cetro" -> this.tipo = tipo;
                    case "daga" -> this.tipo = tipo;
                    default -> System.err.println("Error en el tipo de arma: " + tipo);
                }
            }
            case 2 -> {
                switch (tipo){
                    case "espadón" -> this.tipo = tipo;
                    case "martillo" -> this.tipo = tipo;
                    case "arco" -> this.tipo = tipo;
                    case "bastón" -> this.tipo = tipo;
                    default -> System.err.println("Error en el tipo de arma: " + tipo);
                }
            }
            default -> {
                System.err.println("Error en el tipo de empuñadura: " + getEmpuñadura());
            }
        }

    }
    //endregion
}
