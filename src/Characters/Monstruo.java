package Characters;

import java.util.Random;

public class Monstruo extends Personaje {
    private int raza;

    // region Constructores
    public Monstruo() {
        super();
    }

    public Monstruo(String nombre, int nivel, double pv, double atq, double arm, double res, double vel, int raza) {
        super(nombre, nivel, pv, atq, arm, res, vel);
    }

    public Monstruo(String nombre, int nivel, double pv, double atq, double arm, double res, double vel, int alianza, int raza) {
        super(nombre, nivel, pv, atq, arm, res, vel, alianza);
    }
    // endregion

    public void subirNivel() {
        Random r = new Random();
        switch (getRaza()) {
            case 1 -> {
                if (r.nextInt(100) < 50) setPv(getPv() + 1);
                if (r.nextInt(100) < 80) setAtq(getAtq() + 2);
                if (r.nextInt(100) < 15) setArm(getArm() + 1);
                if (r.nextInt(100) < 15) setRes(getRes() + 1);
                if (r.nextInt(100) < 80) setVel(getVel() + 2);
            }
            case 2 -> {
                if (r.nextInt(100) < 30) setPv(getPv() + 1);
                if (r.nextInt(100) < 50) setAtq(getAtq() + 1);
                if (r.nextInt(100) < 30) setArm(getArm() + 1);
                if (r.nextInt(100) < 70) setRes(getRes() + 4);
                if (r.nextInt(100) < 5) setVel(getVel() + 1);
            }
            case 3 -> {
                setPv(getPv() + 1);
                if (r.nextInt(100) < 50) setPv(getPv() + (r.nextInt(1, 3)));
                if (r.nextInt(100) < 50) setAtq(getAtq() + 1);
                if (r.nextInt(100) < 50) setArm(getArm() + 1);
                if (r.nextInt(100) < 10) setRes(getRes() + 1);
                if (r.nextInt(100) < 10) setVel(getVel() + 1);
            }
        }
        setNivel(getNivel() + 1);
    }

    // region Setters & Getters
    public String getRazaName() {
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

    public int getRaza() {
        return this.raza;
    }

    public void setRaza(int raza) {
        if (raza >= 1 && raza <= 3) {
            this.raza = raza;
        } else {
            System.err.println("Error en setRaza: Introduce una raza válida\nInput: " + raza);
        }
    }
    // endregion

    // region Overrides
    public Monstruo clone() {
        return new Monstruo(getNombre(), getNivel(), getPv(), getAtq(), getArm(), getRes(), getVel(), getRaza());
    }

    public boolean equals(Monstruo l) {
        return getNombre().equals(l.getNombre()) && getPv() == l.getPv() && getAtq() == l.getAtq() && getArm() == l.getArm() && getVel() == l.getVel() && getRes() == l.getRes() && getNivel() == l.getNivel();
    }

    public String toString() {
        return ("Clase: Monstruo\n" + "Raza: " + getRazaName() + "\n" + super.toString());
    }
    // endregion
}
