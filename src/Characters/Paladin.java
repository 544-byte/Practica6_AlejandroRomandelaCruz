package Characters;

import Misc.Misc;

import java.util.Random;
import java.util.Scanner;

/**
 * Clase que representa a un Paladín, una subclase de {@link Creyente}.<br>
 * El Paladín combina ataque físico y fe para realizar acciones especiales
 * llamadas plegarias, que pueden afectar tanto a enemigos como a sí mismo.
 */
public class Paladin extends Creyente {
    // region Constructores
    /**
     * Constructor por defecto del Paladín.
     * Inicializa un Paladín con valores base heredados de {@link Creyente}.
     */
    public Paladin() {
        super();
    }

    /**
     * Constructor completo del Paladin.
     * @param raza Raza del Paladin
     * @param nombre Nombre del Paladin
     * @param nivel Nivel inicial
     * @param pv Puntos de vida
     * @param atq Ataque base
     * @param arm Armadura
     * @param res Resistencia mágica
     * @param vel Velocidad
     * @param fe especial Atributo especial
     * @param alianza Identificador de la alianza
     * @param esJugador Identifica si el personaje es Jugador
     */
    public Paladin(int raza, String nombre, int nivel, double pv, double atq, double arm, double res, double vel, double fe, int alianza, boolean esJugador) {
        super(raza, nombre, nivel, pv, atq, arm, res, vel, fe, alianza, esJugador);
    }

    /**
     * Constructor por CSV del Paladin
     * @param csv El array del csv del personaje a crear.
     */
    public Paladin(String[] csv){
        if (!this.esClase(csv[0]))
            Misc.alert("El csv proporcionado no es de un Paladin, corresponde a un " + csv[0]);
        return;
        new Paladin(
                Integer.parseInt(csv[1]),
                csv[2],
                Integer.parseInt(csv[3]),
                Double.parseDouble(csv[4]),
                Double.parseDouble(csv[5]),
                Double.parseDouble(csv[6]),
                Double.parseDouble(csv[7]),
                Double.parseDouble(csv[8]),
                Double.parseDouble(csv[9]),
                Integer.parseInt(csv[10]),
                Boolean.parseBoolean(csv[11])
        );
    }

    // endregion

    /**
     * Incrementa el nivel del Paladín aumentando aleatoriamente sus atributos
     * y su fe, siguiendo probabilidades específicas para cada atributo.
     */
    public void subirNivel() {
        setPv(getPv() + aumentarAtributo(50,getPv()*0.05));
        setAtq(getAtq() + aumentarAtributo(60));
        setArm(getArm() + aumentarAtributo(70,2));
        setRes(getRes() + aumentarAtributo(15));
        setVel(getVel() + aumentarAtributo(15));
        setFe(getFe() + aumentarAtributo(30));
        setNivel(getNivel() + 1);
    }

    /**
     * Permite al Paladín realizar una plegaria, eligiendo entre varias opciones
     * que afectan al enemigo o a sí mismo.
     *
     * @param enemigo Personaje enemigo que puede verse afectado por la plegaria
     */
    public void plegaria(Personaje enemigo) {
        Scanner scan = new Scanner(System.in);
        System.out.println(Misc.opcionesJugador("" + "Escoge una plegaria:\n" + "1- Imbuir arma\n" + "2- Baluarte de fe\n" + "3- Fogonazo sagrado\n" + "4- Cambio de opinión\n"));
        System.out.print("Opción: ");
        int opt = scan.nextInt();
        switch (opt) {
            case 1 -> {
                System.out.println("Atacas con un arma llena de fé");
                setAtq(getAtq() + getFe() * 0.8);
                atacar(enemigo, false);
                setAtq(getAtq() - getFe() * 0.8);
            }
            case 2 -> {
                System.out.println("Bendices tu armadura aumentandola en " + getFe() * 0.3 + " puntos");
                setArm(getArm() + getFe() * 0.3);
            }
            case 3 -> {
                System.out.println("Un rayo del mismísimo Dios cae sobre tu enemigo causando una gran cegera");
                enemigo.setVel(enemigo.getVel() - getFe() * 0.4);
                enemigo.setRes(enemigo.getRes() - getFe() * 0.4);
            }
            case 4 -> {
                System.out.println("Cambias de opinión y decides hacer otra cosa...");
                realizarTurnoJugador(enemigo);
            }
        }
    }

    /**
     * Metodo que devuelve un string con los valores separados por ":" para usarlo luego y guardarlo en un .csv tanto para importar como para exportar.
     * @return String con los atributos comúnes entre todos los personajes separados por ":"
     */
    public String getCSV() {
        return  "Paladin:" + super.getCSV();
    }

    // region Setters & Getters

    // endregion

    // region Overrides
    /**
     * Crea y devuelve una copia del Paladín.
     *
     * @return Nuevo objeto Paladín con los mismos atributos
     */
    public Paladin clone() {
        return new Paladin(getRaza(), getNombre(), getNivel(), getPv(), getAtq(), getArm(), getRes(), getVel(), getFe(), getAlianza(), getEsJugador());
    }

    /**
     * Compara este Paladín con otro para determinar si son equivalentes
     * en todos sus atributos principales.
     *
     * @param l Paladín a comparar
     * @return true si todos los atributos coinciden, false en caso contrario
     */
    public boolean equals(Paladin l) {
        return getNombre().equals(l.getNombre()) && getPv() == l.getPv() && getAtq() == l.getAtq() && getArm() == l.getArm() && getVel() == l.getVel() && getRes() == l.getRes() && getNivel() == l.getNivel();
    }

    /**
     * Devuelve una representación en cadena de texto del Paladín,
     * incluyendo su clase y atributos heredados de {@link Creyente}.
     *
     * @return Cadena de texto con la información del Paladín
     */
    public String toString() {
        return ("Clase: Paladin\n" + super.toString());
    }
    // endregion
}
