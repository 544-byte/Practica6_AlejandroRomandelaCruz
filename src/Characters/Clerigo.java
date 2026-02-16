package Characters;

import Misc.Misc;

import java.util.Random;
import java.util.Scanner;

/**
 * Clase que representa a un Clérigo, subclase de {@link Creyente}.
 * Los clérigos utilizan plegarias para curar aliados o dañar enemigos.
 */
public class Clerigo extends Creyente {
    // region Constructores
    /**
     * Constructor por defecto del Clérigo.
     */
    public Clerigo() {
        super();
    }

    /**
     * Constructor completo del Clérigo sin fe ni alianza.
     *
     * @param nombre Nombre del Clérigo
     * @param nivel Nivel inicial
     * @param pv Puntos de vida
     * @param atq Ataque
     * @param arm Armadura
     * @param res Resistencia
     * @param vel Velocidad
     */
    public Clerigo(String nombre, int nivel, double pv, double atq, double arm, double res, double vel) {
        super(nombre, nivel, pv, atq, arm, res, vel);
    }

    /**
     * Constructor completo del Clérigo con fe inicial.
     *
     * @param nombre Nombre del Clérigo
     * @param nivel Nivel inicial
     * @param pv Puntos de vida
     * @param atq Ataque
     * @param arm Armadura
     * @param res Resistencia
     * @param vel Velocidad
     * @param fe Valor inicial de fe
     */
    public Clerigo(String nombre, int nivel, double pv, double atq, double arm, double res, double vel, double fe) {
        super(nombre, nivel, pv, atq, arm, res, vel, fe);
    }

    /**
     * Constructor completo del Clérigo con fe inicial y alianza.
     *
     * @param nombre Nombre del Clérigo
     * @param nivel Nivel inicial
     * @param pv Puntos de vida
     * @param atq Ataque
     * @param arm Armadura
     * @param res Resistencia
     * @param vel Velocidad
     * @param alianza Alianza del Clérigo
     * @param fe Valor inicial de fe
     */
    public Clerigo(String nombre, int nivel, double pv, double atq, double arm, double res, double vel, int alianza, double fe) {
        super(nombre, nivel, pv, atq, arm, res, vel, alianza, fe);
    }

    // endregion

    /**
     * Incrementa el nivel del Clérigo aplicando mejoras aleatorias a sus atributos y a su fe.
     */
    public void subirNivel() {
        Random r = new Random();
        if (r.nextInt(100) < 20) setPv(getPv() + 1);
        if (r.nextInt(100) < 10) setAtq(getAtq() + 1);
        if (r.nextInt(100) < 20) setArm(getArm() + 2);
        if (r.nextInt(100) < 80) setRes(getRes() + 2);
        if (r.nextInt(100) < 50) setVel(getVel() + 1);
        if (r.nextInt(100) < 80) setFe(getFe() + 2);
        setNivel(getNivel() + 1);
    }

    /**
     * Permite al Clérigo realizar una plegaria, que puede curar aliados, afectar a todos los aliados,
     * infligir daño mágico a un enemigo o cambiar de opinión.
     *
     * @param enemigo Personaje enemigo objetivo de la plegaria
     */
    public void plegaria(Personaje enemigo) {
        Scanner scan = new Scanner(System.in);
        System.out.println(Misc.opcionesJugador("" + "Escoge una plegaria:\n" + "1- Sanación\n" + "2- Rezo sagrado\n" + "3- Cólera Divina\n" + "4- Cambio de opinión\n"));
        System.out.print("Opción: ");
        int opt = scan.nextInt();
        switch (opt) {
            case 1 -> {
                System.out.println("Selecciona un aliado:");
                System.out.println(this.getAliados());
                System.out.print("Opción: ");
                opt = scan.nextInt();
                System.out.println("Sanas a " + aliados[opt - 1].getNombre() + " con " + getFe() * 0.7 + " puntos de vida");
                aliados[opt - 1].setPv(getPv() + getFe() * 0.7);
            }
            case 2 -> {
                System.out.println("Rezas una fuerte oración que cura a todos tus aliados " + getFe() * 0.3 + " puntos de vida");
                for (int i = 0; i < aliados.length; i++) {
                    aliados[i].setPv(getPv() + getFe() * 0.3);
                }
            }
            case 3 -> {
                System.out.println("La cólera de los de arriba cae brutalmente sobre " + enemigo.getNombre() + " causandole " + enemigo.defender(getAtq() + getFe() * 0.55, true) + " puntos de daño");
                setAtq(getAtq() + getFe() * 0.55);
                this.atacar(enemigo, true);
                setAtq(getAtq() - getFe() * 0.55);
            }
            case 4 -> {
                System.out.println("Cambias de opinión y decides hacer otra cosa...");
                realizarTurnoJugador(enemigo);
            }
        }
    }


    // region Setters & Getters

    // endregion

    // region Overrides
    /**
     * Crea un clon del Clérigo.
     *
     * @return Nuevo objeto Clerigo idéntico
     */
    public Clerigo clone() {
        return new Clerigo(getNombre(), getNivel(), getPv(), getAtq(), getArm(), getRes(), getVel());
    }

    /**
     * Compara este Clérigo con otro.
     *
     * @param l Clérigo a comparar
     * @return true si todos los atributos coinciden
     */
    public boolean equals(Clerigo l) {
        return getNombre().equals(l.getNombre()) && getPv() == l.getPv() && getAtq() == l.getAtq() && getArm() == l.getArm() && getVel() == l.getVel() && getRes() == l.getRes() && getNivel() == l.getNivel();
    }

    /**
     * Representación en cadena de texto del Clérigo.
     *
     * @return Cadena con información del Clérigo
     */
    public String toString() {
        return ("Clase: Clerigo\n" + super.toString());
    }
    // endregion
}
