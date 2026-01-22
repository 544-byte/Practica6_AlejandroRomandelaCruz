package Characters;

import java.util.Random;
import java.util.Scanner;

public class Ladron extends Personaje {

    // region Constructores
    public Ladron() {
        super();
    }

    public Ladron(String nombre, int nivel, double pv, double atq, double arm, double res, double vel) {
        super(nombre, nivel, pv, atq, arm, res, vel);
    }

    public Ladron(String nombre, int nivel, double pv, double atq, double arm, double res, double vel, int alianza) {
        super(nombre, nivel, pv, atq, arm, res, vel,alianza);
    }

    public Ladron(String nombre) {
        super(nombre);
    }

    public Ladron(String nombre, int nivel) {
        super(nombre, nivel);
    }
    // endregion

    public void subirNivel() {
        Random r = new Random();
        if (r.nextInt(100) < 40) setPv(getPv()+1);
        if (r.nextInt(100) < 60) setAtq(getAtq()+1);
        if (r.nextInt(100) < 40) setArm(getArm()+1);
        if (r.nextInt(100) < 40) setRes(getRes()+1);
        if (r.nextInt(100) < 85)setVel(getVel()+2);
        setNivel(getNivel()+1);
    }

    public void accionEspecial(Personaje enemigo){
        ataca(getVel(),enemigo,false);
    }

    // region Setters & Getters

    public String getAccionEspecial(){
        return "Robar";
    }


    // endregion

    // region Overrides
    public Ladron clone() {
        return new Ladron(getNombre(),getNivel(),getPv(),getAtq(),getArm(),getRes(),getVel());
    }

    public boolean equals(Ladron l) {
        return getNombre().equals(l.getNombre()) &&
                getPv() == l.getPv() &&
                getAtq() == l.getAtq() &&
                getArm() == l.getArm() &&
                getVel() == l.getVel() &&
                getRes() == l.getRes() &&
                getNivel() == l.getNivel();
    }

    public String toString() {
        return ("Clase: Ladron\n" +
                super.toString()
        );
    }
    // endregion
}
