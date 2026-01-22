package Characters;

import java.util.Random;

public abstract class Creyente extends Personaje{
    double fe;

    // region Constructores
    public Creyente() {
        super();
    }
    public Creyente(String nombre, int nivel, double pv, double atq, double arm, double res, double vel) {
        super(nombre, nivel, pv, atq, arm, res, vel);
    }

    public Creyente(String nombre, int nivel, double pv, double atq, double arm, double res, double vel,double fe) {
        super(nombre, nivel, pv, atq, arm, res, vel);
        setFe(fe);
    }

    public Creyente(String nombre, int nivel, double pv, double atq, double arm, double res, double vel, int alianza,double fe) {
        super(nombre, nivel, pv, atq, arm, res, vel,alianza);
        setFe(fe);
    }
    // endregion

    public void subirNivel() {
        Random r = new Random();
        if (r.nextInt(100) < 40) setPv(getPv()+1);
        if (r.nextInt(100) < 60) setAtq(getAtq()+1);
        if (r.nextInt(100) < 40) setArm(getArm()+1);
        if (r.nextInt(100) < 40) setRes(getRes()+1);
        if (r.nextInt(100) < 85) setVel(getVel()+2);
        setNivel(getNivel()+1);
    }

    public void accionEspecial(Personaje enemigo){
        plegaria(enemigo);
    }

    public abstract void plegaria(Personaje enemigo);

    // region Setters & Getters

    public String getAccionEspecial(){
        return "Plegaria";
    }

    public double getFe() {
        return fe;
    }

    public void setFe(double fe) {
        this.fe = fe;
    }

    // endregion

    // region Overrides
    public boolean equals(Creyente c) {
        return getNombre().equals(c.getNombre()) &&
                getPv() == c.getPv() &&
                getAtq() == c.getAtq() &&
                getArm() == c.getArm() &&
                getVel() == c.getVel() &&
                getRes() == c.getRes() &&
                getNivel() == c.getNivel() &&
                getFe() == c.getFe();
    }

    public String toString() {
        return (super.toString() + "\n"
                + "Fe: " + getFe()
        );
    }
    // endregion
}
