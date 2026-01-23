package Characters;

import Misc.Miscellaneous;

import java.util.Random;
import java.util.Scanner;

public class Paladin extends Creyente {
    // region Constructores
    public Paladin() {
        super();
    }

    public Paladin(String nombre, int nivel, double pv, double atq, double arm, double res, double vel) {
        super(nombre, nivel, pv, atq, arm, res, vel);
    }

    public Paladin(String nombre, int nivel, double pv, double atq, double arm, double res, double vel, double fe) {
        super(nombre, nivel, pv, atq, arm, res, vel, fe);
    }

    public Paladin(String nombre, int nivel, double pv, double atq, double arm, double res, double vel, int alianza, double fe) {
        super(nombre, nivel, pv, atq, arm, res, vel, alianza, fe);
    }

    // endregion

    public void subirNivel() {
        Random r = new Random();
        if (r.nextInt(100) < 50) setPv(getPv() * 1.05);
        if (r.nextInt(100) < 60) setAtq(getAtq() + 1);
        if (r.nextInt(100) < 70) setArm(getArm() + 2);
        if (r.nextInt(100) < 15) setRes(getRes() + 1);
        if (r.nextInt(100) < 15) setVel(getVel() + 1);
        if (r.nextInt(100) < 30) setFe(getFe() + 1);
        setNivel(getNivel() + 1);
    }

    public void plegaria(Personaje enemigo) {
        Scanner scan = new Scanner(System.in);
        System.out.println(Miscellaneous.opcionesJugador("" + "Escoge una plegaria:\n" + "1- Imbuir arma\n" + "2- Baluarte de fe\n" + "3- Fogonazo sagrado\n" + "4- Cambio de opinión\n"));
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


    // region Setters & Getters

    // endregion

    // region Overrides
    public Paladin clone() {
        return new Paladin(getNombre(), getNivel(), getPv(), getAtq(), getArm(), getRes(), getVel());
    }

    public boolean equals(Paladin l) {
        return getNombre().equals(l.getNombre()) && getPv() == l.getPv() && getAtq() == l.getAtq() && getArm() == l.getArm() && getVel() == l.getVel() && getRes() == l.getRes() && getNivel() == l.getNivel();
    }

    public String toString() {
        return ("Clase: Paladin\n" + super.toString());
    }
    // endregion
}
