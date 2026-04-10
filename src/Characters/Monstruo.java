package Characters;

import Gear.Arma;
import Gear.Armadura;
import Gear.Artefacto;
import Misc.Misc;

import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

/**
 * Clase que representa a un Monstruo, subclase de {@link Personaje}.<br>
 * Los monstruos tienen una raza específica que influye en el crecimiento
 * de sus atributos al subir de nivel. Pueden pertenecer a diferentes alianzas
 * y se diferencian por su raza: Bestia, No-Muerto o Gigante.
 */
public class Monstruo extends Personaje {
    private int raza;

    // region Constructores
    /**
     * Constructor por defecto del Monstruo.
     * Inicializa un Monstruo con valores base heredados de {@link Personaje}.
     */
    public Monstruo() {
        super();
    }

    /**
     * Constructor completo del Monstruo.
     * @param raza Raza del Monstruo
     * @param nombre Nombre del Monstruo
     * @param nivel Nivel inicial
     * @param pv Puntos de vida
     * @param atq Ataque base
     * @param arm Armadura
     * @param res Resistencia mágica
     * @param vel Velocidad
     * @param alianza Identificador de la alianza
     * @param esJugador Identifica si el personaje es Jugador
     */
    public Monstruo(int raza, String nombre, int nivel, double pv, double atq, double arm, double res, double vel, int alianza, boolean esJugador) {
        super(raza, nombre, nivel, pv, atq, arm, res, vel, alianza, esJugador);
    }

    /**
     * Constructor por CSV del Monstruo
     * @param csv El array del csv del personaje a crear.
     */
    public Monstruo(String[] csv){
        this(
                Integer.parseInt(csv[1]),
                csv[2],
                Integer.parseInt(csv[3]),
                Double.parseDouble(csv[4]),
                Double.parseDouble(csv[5]),
                Double.parseDouble(csv[6]),
                Double.parseDouble(csv[7]),
                Double.parseDouble(csv[8]),
                Integer.parseInt(csv[10]),
                Boolean.parseBoolean(csv[11])
        );
        if (!this.esClase(csv[0])) {
            Misc.alert("El csv proporcionado no es de un monstruo, corresponde a un " + csv[0]);
        }
    }


    // endregion

    /**
     * Incrementa el nivel del Monstruo aumentando aleatoriamente sus atributos
     * según su raza. Cada raza tiene probabilidades y mejoras específicas.
     */
    public void subirNivel() {
        setNivel(getNivel() + 1);
        Misc.happen(this.getNombre()+" Ha subido de nivel,");
        switch (getRaza()) {
            case 1 -> {
                aumentarAtributo("pv",50);
                aumentarAtributo("atq",80,2);
                aumentarAtributo("arm",15);
                aumentarAtributo("res",15);
                aumentarAtributo("vel",80,2);
            }
            case 2 -> {
                aumentarAtributo("pv",30);
                aumentarAtributo("atq",50);
                aumentarAtributo("arm",30);
                aumentarAtributo("res",70,4);
                aumentarAtributo("vel",5);
            }
            case 3 -> {
                aumentarAtributo("pv",100);
                aumentarAtributo("pv",50,1,3);
                aumentarAtributo("atq",50);
                aumentarAtributo("arm",50);
                aumentarAtributo("res",10);
                aumentarAtributo("vel",10);
            }
        }
    }

    /**
     * metodo de accion especial, actualmente sin uso.
     * @param enemigo Enemigo objetivo
     */
    @Override
    public void accionEspecial(Personaje enemigo) {

    }

    // region Setters & Getters
    /**
     * Devuelve el nombre de la raza del Monstruo.
     *
     * @return Nombre de la raza ("Bestia", "No-Muerto" o "Gigante")
     */
    public String getRazaName() {
        switch (getRaza()) {
            case 1 -> {
                return "Bestia";
            }
            case 2 -> {
                return "No-Muerto";
            }
            case 3 -> {
                return "Gigante";
            }
        }
        return "";
    }

    public String getRazaName(int raza) {
        switch (raza) {
            case 1 -> {
                return "Bestia";
            }
            case 2 -> {
                return "No-Muerto";
            }
            case 3 -> {
                return "Gigante";
            }
        }
        return "";
    }

    /**
     * Obtiene el identificador numérico de la raza del Monstruo.
     *
     * @return Entero de la raza (1, 2 o 3)
     */
    public int getRaza() {
        return this.raza;
    }

    /**
     * Establece la raza del Monstruo.
     *
     * @param raza Entero que representa la raza (1: Bestia, 2: No-Muerto, 3: Gigante)
     */
    public void setRaza(int raza) {
        if (raza >= 1 && raza <= 3) {
            this.raza = raza;
        } else {
            System.err.println("Error en setRaza: Introduce una raza válida\nInput: " + raza);
        }
    }

    /**
     * Metodo que devuelve un string con los valores separados por ":" para usarlo luego y guardarlo en un .csv tanto para importar como para exportar.
     * @return String con los atributos comúnes entre todos los personajes separados por ":"
     */
    public String getCSV() {
        return  super.getCSV() + ":" + -1 + ":" + getAlianza() + ":" + getEsJugador();
    }

    /**
     * Metodo que controla el equipamiento de armas de el Monstruo con sus respectivas restricciones
     * @param arma el arma a equipar.
     */
    public void setArma(Arma arma) {
        switch (getRaza()) {
            case 1 -> { //Bestia
                Misc.alert(getNombre() + " es un Bestia, por lo que no se puede equipar un " + arma.getTipo());
            }
            case 2 -> { //No-Muerto
                super.setArma(arma);
            }
            case 3 -> { //Gigante
                Misc.alert(getNombre() + " es tan grande que no existen armas de su tamaño ");
            }
        }
    }

    /**
     * Metodo que controla el equipamiento de armas de el monstruo con sus respectivas restricciones
     * @param armadura la armadura a equipar.
     */
    public void addArmadura(Armadura armadura) {
        switch (getRaza()) {
            case 1 -> { //Bestia
                Misc.alert( getNombre() + " es una Bestia, por lo que no se puede equipar una pieza de armadura");
            }
            case 2 -> { //No-Muerto
                Misc.alert(getNombre() + " es un No-Muerto por lo que no puede equiparse armadura");
            }
            case 3 -> { //Gigante
                if (armadura.getMaterial().equals("Cuero")){
                    super.addArmadura(armadura);
                } else {
                    Misc.alert( getNombre() + " es un Monstruo, por lo que no se puede equipar una pieza de armadura que no sea de Cuero.");
                }
            }
        }
    }

    public void addArtefacto(Artefacto artefacto) {
        switch (getRaza()) {
            case 1 -> { //Bestia
                if (getArtefactos().size() >= 1) {
                    Misc.alert( getNombre() + " es una Bestia, por lo que no se puede equipar un artefacto");
                } else {
                    super.addArtefacto(artefacto);
                }
            }
            case 2 -> { //No-Muerto
                super.addArtefacto(artefacto);
            }
            case 3 -> { //Gigante
                Misc.alert(getNombre() + " es tan grande que no existen artefactos de su tamaño");
            }
        }
        if (getArtefactos().size() >= 1) {

        } else {
            super.addArtefacto(artefacto);
        }
    }

    // endregion

    // region Overrides
    /**
     * Crea y devuelve una copia del Monstruo.
     *
     * @return Nuevo objeto Monstruo con los mismos atributos
     */
    public Monstruo clone() {
        return new Monstruo(getRaza(),getNombre(), getNivel(), getPv(), getAtq(), getArm(), getRes(), getVel(),getAlianza(),getEsJugador());
    }

    /**
     * Compara este Monstruo con otro para determinar si son equivalentes
     * en todos sus atributos principales.
     *
     * @param l Monstruo a comparar
     * @return true si todos los atributos coinciden, false en caso contrario
     */
    public boolean equals(Monstruo l) {
        return getNombre().equals(l.getNombre()) && getPv() == l.getPv() && getAtq() == l.getAtq() && getArm() == l.getArm() && getVel() == l.getVel() && getRes() == l.getRes() && getNivel() == l.getNivel();
    }

    /**
     * Devuelve una representación en cadena de texto del Monstruo,
     * incluyendo su clase, raza y atributos heredados de {@link Personaje}.
     *
     * @return Cadena de texto con la información del Monstruo
     */
    public String toString() {
        return (super.toString());
    }
    // endregion
}
