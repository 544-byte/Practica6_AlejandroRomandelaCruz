package Characters;

import Misc.Miscellaneous;

import java.util.Random;
import java.util.Scanner;

public class Clerigo extends Creyente {
    // region Constructores
    public Clerigo() {
        super();
    }

    public Clerigo(String nombre, int nivel, double pv, double atq, double arm, double res, double vel) {
        super(nombre, nivel, pv, atq, arm, res, vel);
    }

    public Clerigo(String nombre, int nivel, double pv, double atq, double arm, double res, double vel, double fe) {
        super(nombre, nivel, pv, atq, arm, res, vel, fe);
    }

    public Clerigo(String nombre, int nivel, double pv, double atq, double arm, double res, double vel, int alianza, double fe) {
        super(nombre, nivel, pv, atq, arm, res, vel, alianza, fe);
    }

    // endregion

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

    public void plegaria(Personaje enemigo) {
        Scanner scan = new Scanner(System.in);
        System.out.println(Miscellaneous.opcionesJugador("" + "Escoge una plegaria:\n" + "1- Sanación\n" + "2- Rezo sagrado\n" + "3- Cólera Divina\n" + "4- Cambio de opinión\n"));
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
    public Clerigo clone() {
        return new Clerigo(getNombre(), getNivel(), getPv(), getAtq(), getArm(), getRes(), getVel());
    }

    public boolean equals(Clerigo l) {
        return getNombre().equals(l.getNombre()) && getPv() == l.getPv() && getAtq() == l.getAtq() && getArm() == l.getArm() && getVel() == l.getVel() && getRes() == l.getRes() && getNivel() == l.getNivel();
    }

    public String toString() {
        return ("Clase: Clerigo\n" + super.toString());
    }
    // endregion
}
