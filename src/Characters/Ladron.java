package Characters;

import java.util.Random;
import java.util.Scanner;

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
    // endregion

    /**
     * Incrementa el nivel del Ladrón aumentando aleatoriamente sus atributos
     * según probabilidades específicas.
     */
    public void subirNivel() {
        setPv(getPv() + aumentarAtributo(40));
        setAtq(getAtq() + aumentarAtributo(60));
        setArm(getArm() + aumentarAtributo(40));
        setRes(getRes() + aumentarAtributo(40));
        setVel(getVel() + aumentarAtributo(85,2));
        setNivel(getNivel() + 1);
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
        return ("Clase: Ladron\n" + super.toString());
    }
    // endregion
}
