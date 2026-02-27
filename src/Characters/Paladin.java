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
        this(
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
        if (!this.esClase(csv[0])) {
            Misc.alert("El csv proporcionado no es de un Paladin, corresponde a un " + csv[0]);
        }
    }

    // endregion

    /**
     * Incrementa el nivel del Paladín aumentando aleatoriamente sus atributos
     * y su fe, siguiendo probabilidades específicas para cada atributo.
     */
    public void subirNivel() {
        setNivel(getNivel() + 1);
        Misc.happen(this.getNombre()+" Ha subido de nivel,");
        aumentarAtributo("pv",50,getPv()*0.05);
        aumentarAtributo("atq",60);
        aumentarAtributo("arm",70,2);
        aumentarAtributo("res",15);
        aumentarAtributo("vel",15);
        aumentarAtributo("fe",30);
    }

    /**
     * Permite al Paladín realizar una plegaria, eligiendo entre varias opciones
     * que afectan al enemigo o a sí mismo.
     *
     * @param enemigo Personaje enemigo que puede verse afectado por la plegaria
     */
    public void plegaria(Personaje enemigo) {
        Scanner scan = new Scanner(System.in);
        System.out.println(Misc.opcionesJugador("" + "Escoge una plegaria:\n" + "1- Imbuir arma\n" + "2- Baluarte de fe\n" + "3- Fogonazo sagrado\n" + "4- Info\n" + "5- Cambio de opinión\n"));
        System.out.print("Opción: ");
        int opt = scan.nextInt();
        switch (opt) {
            case 1 -> {
                Misc.happen(this.getNombre() + " imbuye su arma aumentando su ataque " + getFe()*0.8 + " puntos.");
                setAtq(getAtq() + getFe() * 0.8);
            }
            case 2 -> {
                Misc.happen(this.getNombre() + " bendice su armadura aumentandola en " + getFe() * 0.3 + " puntos");
                setArm(getArm() + getFe() * 0.3);
            }
            case 3 -> {
                Misc.happen(this.getNombre() + " hace caer sobre " + enemigo.getNombre() + " causandole una gran cegera disminuyendo su velocidad y resistencia " + getFe()*0.4 + " puntos.");
                enemigo.setVel(enemigo.getVel() - getFe() * 0.4);
                enemigo.setRes(enemigo.getRes() - getFe() * 0.4);
            }
            case 4 -> {
                System.out.println("Información de las plegarias:\n" +
                        "1- Imbuir arma: Bendice tu arma aumentando tu estadística de Ataque en un 80% de tu Fe.\n" +
                        "2- Baluarte de fe: Refuerza tu defensa aumentando tu Armadura en un 30% de tu estadística de Fe.\n" +
                        "3- Fogonazo sagrado: Deslumbra al enemigo reduciendo su Velocidad y Resistencia en un 40% de tu Fe.\n");
                plegaria(enemigo);
            }
            case 5 -> {
                System.out.println("Cambias de opinión y decides hacer otra cosa...");
                realizarTurnoJugador(enemigo);
            }
            default -> {
                System.out.println("Introduce una opción correcta.");
                plegaria(enemigo);
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
