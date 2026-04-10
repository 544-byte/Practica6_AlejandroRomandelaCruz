package Characters;

import Gear.Arma;
import Gear.Armadura;
import Gear.Artefacto;
import Misc.Misc;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

/**
 * Clase que representa a un Guerrero, subclase de {@link Personaje}.<br>
 * El Guerrero puede entrar en estado de Furia, lo que modifica temporalmente
 * sus estadísticas de ataque y armadura. Su acción especial consiste en activar
 * o desactivar la Furia durante el combate.
 */
public class Guerrero extends Personaje {
    private boolean furia;
    private Arma arma2 = null;
    // region Constructores
    /**
     * Constructor por defecto del Guerrero.
     * Inicializa un Guerrero con atributos base y sin Furia.
     */
    public Guerrero() {
        super();
        setFuria(false);
    }

    /**
     * Constructor completo del Guerrero.
     * @param raza Raza del Guerrero
     * @param nombre Nombre del Guerrero
     * @param nivel Nivel inicial
     * @param pv Puntos de vida
     * @param atq Ataque base
     * @param arm Armadura
     * @param res Resistencia mágica
     * @param vel Velocidad
     * @param furia Estado inicial de Furia
     * @param alianza Identificador de la alianza
     * @param esJugador Identifica si el personaje es Jugador
     */
    public Guerrero(int raza, String nombre, int nivel, double pv, double atq, double arm, double res, double vel, boolean furia, int alianza, boolean esJugador) {
        super(raza, nombre, nivel, pv, atq, arm, res, vel, alianza, esJugador);
        setFuria(furia);
    }

    /**
     * Constructor por CSV del Guerrero
     * @param csv El array del csv del personaje a crear.
     */
    public Guerrero(String[] csv){
        this(
                Integer.parseInt(csv[1]),
                csv[2],
                Integer.parseInt(csv[3]),
                Double.parseDouble(csv[4]),
                Double.parseDouble(csv[5]),
                Double.parseDouble(csv[6]),
                Double.parseDouble(csv[7]),
                Double.parseDouble(csv[8]),
                Boolean.parseBoolean(csv[9]),
                Integer.parseInt(csv[10]),
                Boolean.parseBoolean(csv[11])
        );
        if (!this.esClase(csv[0])) {
            Misc.alert("El csv proporcionado no es de un guerrero, corresponde a un " + csv[0]);
        }
    }

    // endregion

    /**
     * Incrementa el nivel del Guerrero aumentando aleatoriamente sus atributos
     * según probabilidades específicas y el estado de Furia.
     */
    public void subirNivel() {
        setNivel(getNivel() + 1);
        Misc.happen(this.getNombre()+" Ha subido de nivel,");
        aumentarAtributo("pv",75);
        if (getFuria()) {
            setAtq(getAtq() / 2);
            setArm(getArm() * 2);
            aumentarAtributo("atq",80,2);
            aumentarAtributo("arm",75);
            setAtq(getAtq() * 2);
            setArm(getArm() / 2);
        } else {
            aumentarAtributo("atq",80,2);
            aumentarAtributo("arm",75);
        }
        aumentarAtributo("res",20);
        aumentarAtributo("vel",50);
    } //souted

    /**
     * Cambia el estado de Furia del Guerrero.
     * Si estaba activo, lo desactiva; si estaba inactivo, lo activa.
     */
    public void modificarFuria() {
        if (furia) {
            Misc.happen(this.getNombre() + " desactiva su furia");
            setFuria(false);
        }
        else {
            Misc.happen(this.getNombre() + " activa su furia");
            setFuria(true);
        }
    }   //souted

    /**
     * Ejecuta la acción especial del Guerrero, que consiste en activar
     * o desactivar su Furia durante el combate.
     *
     * @param enemigo Personaje objetivo (puede afectar la interacción del jugador)
     */
    public void accionEspecial(Personaje enemigo) {
        Scanner scan = new Scanner(System.in);
        if (getFuria()) {
            System.out.println("Desactivar Furia: \n" + "Desactivar Furia te calma y vuelve tus estadísitcas a su estado anterior \n" + "Actualmente: Ataque: " + getAtq() + "\tArmadura: " + getArm() + "\n" + "Despues: Ataque: " + getAtq() / 2 + "\tArmadura: " + getArm() * 2 + "\n" + "¿Estás seguro de tu acción? s/n");
        } else {
            System.out.println("Furia: \n" + "Furia aumenta tu poder de ataque el doble a cambio de reducir a la mitad tu armadura \n" + "Actualmente: Ataque: " + getAtq() + "\tArmadura: " + getArm() + "\n" + "Despues: Ataque: " + getAtq() * 2 + "\tArmadura: " + getArm() / 2 + "\n" + "¿Estás seguro de tu acción? s/n");
        }

        char opt = scan.nextLine().toLowerCase().charAt(0);
        switch (opt) {
            case 's' -> {
                modificarFuria();
            }
            case 'n' -> {
                this.realizarTurnoJugador(enemigo);
            }
        }

    }       //souted

    // region Setters & Getters
    /**
     * Obtiene si el Guerrero se encuentra en estado de Furia.
     *
     * @return true si está furioso, false en caso contrario
     */
    public boolean getFuria() {
        return furia;
    }

    /**
     * Establece el estado de Furia del Guerrero.
     *
     * @param furia true para activar la Furia, false para desactivarla
     */
    public void setFuria(boolean furia) {
        if (this.furia == false && furia == true) {
            setAtq(getAtq() * 2);
            setArm(getArm() / 2);
        } else if (this.furia == true && furia == false) {
            setAtq(getAtq() / 2);
            setArm(getArm() * 2);
        }
        this.furia = furia;

    }

    /**
     * Devuelve el nombre de la acción especial dependiendo del estado de Furia.
     *
     * @return "Furia" o "Desactivar Furia"
     */
    public String getAccionEspecial() {
        if (getFuria()) {
            return "Desactivar Furia";
        } else {
            return "Furia";
        }
    }

    /**
     * Metodo que devuelve un string con los valores separados por ":" para usarlo luego y guardarlo en un .csv tanto para importar como para exportar.
     * @return String con los atributos comúnes entre todos los personajes separados por ":"
     */
    public String getCSV() {
        return  super.getCSV() + ":" + this.getFuria() + ":" + getAlianza() + ":" + getEsJugador();
    }

    /**
     * Devuelve el nombre del Atributo especial del Guerreo
     * @return "Furia"
     */
    public String getAtributoEspecial(){
        return "Furia";
    }

    /**
     * Metodo que controla el equipamiento de armas de el guerrero con sus respectivas restricciones
     * @param arma el arma a equipar.
     */
    public void setArma(Arma arma) {
        ArrayList<String> blacklist = new ArrayList<>(Set.of("Cetro","Arco","Baston"));
        if (!blacklist.contains(arma.getTipo())) {
            if (getArma().getEmpuñadura() == 1 && arma.getEmpuñadura() == 1 && this.arma2 == null) {
                this.arma2 = new Arma(arma);
                Misc.happen(getNombre() + " se ha equipado " + arma.getNombre());
            } else {
                super.setArma(arma);
            }
        } else {
            Misc.alert(getNombre() + " es un guerrero, por lo que no se puede equipar un " + arma.getTipo());
        }

    }

    /**
     * Metodo que controla el equipamiento de armas de el guerrero con sus respectivas restricciones
     * @param armadura la armadura a equipar.
     */
    public void addArmadura(Armadura armadura) {
        if (armadura.getMaterial().equals("Metal")){
            super.addArmadura(armadura);
        } else {
            Misc.alert( getNombre() + " es un guerrero, por lo que no se puede equipar una pieza de armadura que no sea de Metal.");
        }
    }

    // endregion

    // region Overrides
    /**
     * Crea y devuelve una copia del Guerrero.
     *
     * @return Nuevo objeto Guerrero con los mismos atributos
     */
    public Guerrero clone() {
        return new Guerrero(getRaza(),getNombre(), getNivel(), getPv(), getAtq(), getArm(), getRes(), getVel(), getFuria(),getAlianza(),getEsJugador());
    }

    /**
     * Compara este Guerrero con otro para determinar si son equivalentes
     * en todos sus atributos principales.
     *
     * @param g Guerrero a comparar
     * @return true si todos los atributos coinciden, false en caso contrario
     */
    public boolean equals(Guerrero g) {
        return getNombre().equals(g.getNombre()) && getPv() == g.getPv() && getAtq() == g.getAtq() && getArm() == g.getArm() && getVel() == g.getVel() && getRes() == g.getRes() && getNivel() == g.getNivel() && getFuria() == g.getFuria();
    }

    /**
     * Devuelve una representación en cadena de texto del Guerrero,
     * incluyendo su clase, atributos heredados y estado de Furia.
     *
     * @return Cadena de texto con la información del Guerrero
     */
    public String toString() {
        return (super.toString());
    }
    // endregion
}
