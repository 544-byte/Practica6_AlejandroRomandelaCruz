package Characters;

import Gear.Arma;
import Gear.Armadura;
import Misc.Misc;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

/**
 * Clase que representa a un Ladrón, subclase de {@link Personaje}.<br>
 * El Ladrón destaca por su velocidad y habilidad para robar durante los combates.
 * Su acción especial depende de la velocidad del personaje.
 */
public class Ladron extends Personaje {

    // region Constructores
    /**
     * Constructor por defecto del Ladrón.
     * Inicializa un Ladrón con atributos base.
     */
    public Ladron() {
        super();
    }

    /**
     * Constructor completo del Ladrón con alianza especificada.
     *
     * @param nombre Nombre del Ladrón
     * @param nivel Nivel inicial
     * @param pv Puntos de vida
     * @param atq Ataque base
     * @param arm Armadura
     * @param res Resistencia mágica
     * @param vel Velocidad
     * @param alianza Identificador de la alianza
     */
    public Ladron(int raza, String nombre, int nivel, double pv, double atq, double arm, double res, double vel, int alianza, boolean esJugador) {
        super(raza, nombre, nivel, pv, atq, arm, res, vel,alianza, esJugador);
    }

    /**
     * Constructor por CSV del Ladron
     * @param csv El array del csv del personaje a crear.
     */
    public Ladron(String[] csv) {
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
        if (!this.esClase(csv[0])){
            Misc.alert("El csv proporcionado no es de un Ladron, corresponde a un " + csv[0]);
        }
    }

    // endregion

    /**
     * Incrementa el nivel del Ladrón aumentando aleatoriamente sus atributos
     * según probabilidades específicas.
     */
    public void subirNivel() {
        setNivel(getNivel() + 1);
        Misc.happen(this.getNombre()+" Ha subido de nivel,");
        aumentarAtributo("pv",40);
        aumentarAtributo("atq",60);
        aumentarAtributo("arm",40);
        aumentarAtributo("res",40);
        aumentarAtributo("vel",80,2);
    }

    /**
     * Ejecuta la acción especial del Ladrón, que consiste en un ataque
     * basado en su velocidad sobre un enemigo.
     *
     * @param enemigo Personaje objetivo del ataque
     */
    public void accionEspecial(Personaje enemigo) {
        ataca(getVel(), enemigo, false);
    }

    // region Setters & Getters

    /**
     * Devuelve el nombre de la acción especial del Ladrón.
     *
     * @return "Robar"
     */
    public String getAccionEspecial() {
        return "Robar";
    }

    /**
     * Metodo que devuelve un string con los valores separados por ":" para usarlo luego y guardarlo en un .csv tanto para importar como para exportar.
     * @return String con los atributos comúnes entre todos los personajes separados por ":"
     */
    public String getCSV() {
        return   super.getCSV() + ":" + -1 + ":" + getAlianza() + ":" + getEsJugador();
    }

    /**
     * Metodo que controla el equipamiento de armas de el ladron con sus respectivas restricciones
     * @param arma el arma a equipar.
     */
    public void setArma(Arma arma) {
        ArrayList<String> whitelist = new ArrayList<>(Set.of("Espada","Daga"));
        if (whitelist.contains(arma.getTipo())) {
            super.setArma(arma);
        } else {
            Misc.alert(getNombre() + " es un Ladron, por lo que no se puede equipar un " + arma.getTipo());
        }
    }

    /**
     * Metodo que controla el equipamiento de armas de el ladron con sus respectivas restricciones
     * @param armadura la armadura a equipar.
     */
    public void addArmadura(Armadura armadura) {
        ArrayList<String> whitelist = new ArrayList<>(Set.of("Tela","Cuero"));
        if (whitelist.contains(armadura.getMaterial())){
            super.addArmadura(armadura);
        } else {
            Misc.alert( getNombre() + " es un Ladron, por lo que no se puede equipar una pieza de armadura que no sea de Tela o Cuero.");
        }
    }

    // endregion

    // region Overrides
    /**
     * Crea y devuelve una copia del Ladrón.
     *
     * @return Nuevo objeto Ladron con los mismos atributos
     */
    public Ladron clone() {
        return new Ladron(getRaza(),getNombre(), getNivel(), getPv(), getAtq(), getArm(), getRes(), getVel(),getAlianza(),getEsJugador());
    }

    /**
     * Compara este Ladrón con otro para determinar si son equivalentes
     * en todos sus atributos principales.
     *
     * @param l Ladrón a comparar
     * @return true si todos los atributos coinciden, false en caso contrario
     */
    public boolean equals(Ladron l) {
        return getNombre().equals(l.getNombre()) && getPv() == l.getPv() && getAtq() == l.getAtq() && getArm() == l.getArm() && getVel() == l.getVel() && getRes() == l.getRes() && getNivel() == l.getNivel();
    }

    /**
     * Devuelve una representación en cadena de texto del Ladrón,
     * incluyendo su clase y los atributos heredados de {@link Personaje}.
     *
     * @return Cadena de texto con la información del Ladrón
     */
    public String toString() {
        return (super.toString());
    }
    // endregion
}
