package Characters;

import java.util.Random;
import java.util.Scanner;

public class Mago extends Personaje{
    private double magia;

    // region Constructores
    public Mago() {
        super();
        setMagia(10);
    }

    public Mago(String nombre, int nivel, double pv, double atq, double arm, double res, double vel, double magia) {
        super(nombre, nivel, pv, atq, arm, res, vel);
        setMagia(magia);
    }

    public Mago(String nombre, double magia) {
        super(nombre);
        setMagia(magia);
    }

    public Mago(String nombre, int nivel, double magia) {
        super(nombre, nivel);
        setMagia(magia);
    }

    public Mago(Personaje p, double magia) {
        super(p);
        setMagia(magia);
    }
    // endregion

    public void subirNivel() {
        Random r = new Random();
        if (r.nextInt(100) < 35) setPv(getPv()+1);
        if (r.nextInt(100) < 15) setAtq(getAtq()+1);
        if (r.nextInt(100) < 35) setArm(getArm()+1);
        if (r.nextInt(100) < 80) setRes(getRes()+1);
        if (r.nextInt(100) < 65) setVel(getVel()+1);
        if (r.nextInt(100) < 85) setMagia(getMagia()+1);
        setNivel(getNivel()+1);
    }

    public void accionEspecial(Personaje enemigo){
        lanzarConjuro(enemigo);
    }

    public void lanzarConjuro(Personaje enemigo){
        Scanner scan = new Scanner(System.in);
        System.out.println("───═≡✦✧ ▾ ✧✦≡═───\n");
        int opt = scan.nextInt();
    }

    // region Setters & Getters
    
    public double getMagia() {
        return magia;
    }

    public void setMagia(double magia) {
        this.magia = magia;
    }

    public String getAccionEspecial(){
        return "Magia";
    }

    // endregion

    // region Overrides
    public Mago clone() {
        return new Mago(super.clone(), this.getMagia());
    }

    public boolean equals(Mago magia) {
        return getNombre().equals(magia.getNombre()) &&
                getPv() == magia.getPv() &&
                getAtq() == magia.getAtq() &&
                getArm() == magia.getArm() &&
                getVel() == magia.getVel() &&
                getRes() == magia.getRes() &&
                getNivel() == magia.getNivel() &&
                getMagia() == magia.getMagia();
    }

    public String toString() {
        return ("Clase: Mago\n" +
                super.toString() + "\n" +
                "Magia: " + getMagia()
        );
    }
    // endregion
}
