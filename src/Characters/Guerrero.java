package Characters;

import java.util.Random;
import java.util.Scanner;

public class Guerrero extends Personaje{
    private boolean furia;

    // region Constructores
    public Guerrero() {
        super();
        setFuria(false);
    }

    public Guerrero(String nombre, int nivel, double pv, double atq, double arm, double res, double vel, boolean furia) {
        super(nombre, nivel, pv, atq, arm, res, vel);
        setFuria(furia);
    }

    public Guerrero(String nombre, int nivel, double pv, double atq, double arm, double res, double vel) {
        super(nombre, nivel, pv, atq, arm, res, vel);
        setFuria(false);
    }

    public Guerrero(String nombre, int nivel, double pv, double atq, double arm, double res, double vel, boolean furia, int alianza) {
        super(nombre, nivel, pv, atq, arm, res, vel,alianza);
        setFuria(furia);
    }

    public Guerrero(String nombre, int nivel, double pv, double atq, double arm, double res, double vel, int alianza) {
        super(nombre, nivel, pv, atq, arm, res, vel,alianza);
        setFuria(false);
    }

    public Guerrero(String nombre, boolean furia) {
        super(nombre);
        setFuria(furia);
    }

    public Guerrero(String nombre, int nivel, boolean furia) {
        super(nombre, nivel);
        setFuria(furia);
    }
    // endregion

    public void subirNivel() {
        Random r = new Random();
        if (r.nextInt(100) < 75) setPv(getPv() +1);
        if (getFuria()) {
            setAtq(getAtq()/2);
            if (r.nextInt(100) < 80) setAtq(getAtq() +2);
            setAtq(getAtq()*2);
        } else if (r.nextInt(100) < 80) setAtq(getAtq() +2);
        if (getFuria()) {
            setAtq(getAtq()*2);
            if (r.nextInt(100) < 75) setArm(getArm() +1);
            setAtq(getAtq()/2);
        } else if (r.nextInt(100) < 75) setArm(getArm() +1);
        if (r.nextInt(100) < 20) setRes(getRes() +1);
        if (r.nextInt(100) < 50) setVel(getVel()+1);
        this.setNivel(getNivel() +1);
    }

    public void modificarFuria(){
        if (furia) setFuria(false);
        else setFuria(true);
    }

    public void accionEspecial(Personaje enemigo){
        Scanner scan = new Scanner(System.in);
        if (getFuria()){
            System.out.println("Desactivar Furia: \n" +
                    "Desactivar Furia te calma y vuelve tus estadísitcas a su estado anterior \n" +
                    "Actualmente: Ataque: " + getAtq() + "\tArmadura: " + getArm() + "\n" +
                    "Despues: Ataque: " + getAtq()/2 + "\tArmadura: " + getArm()*2 + "\n" +
                    "¿Estás seguro de tu acción? s/n");
        } else {
            System.out.println("Furia: \n" +
                    "Furia aumenta tu poder de ataque el doble a cambio de reducir a la mitad tu armadura \n" +
                    "Actualmente: Ataque: " + getAtq() + "\tArmadura: " + getArm() + "\n" +
                    "Despues: Ataque: " + getAtq()*2 + "\tArmadura: " + getArm()/2 + "\n" +
                    "¿Estás seguro de tu acción? s/n");
        }

        char opt = scan.nextLine().toLowerCase().charAt(0);
        switch (opt){
            case 's' -> {
                if (getFuria()){
                    System.out.println("Te calmas un poco, menos mal...");
                } else {
                    System.out.println("¡Furia activada!");
                }
                modificarFuria();
            }
            case 'n' -> {
                this.realizarTurnoJugador(enemigo);
            }
        }

    }

    // region Setters & Getters
    public boolean getFuria() {
        return furia;
    }

    public void setFuria(boolean furia) {
        if (this.furia == false && furia == true){
            setAtq(getAtq() * 2);
            setArm(getArm() / 2);
        } else if (this.furia == true && furia == false){
            setAtq(getAtq() / 2);
            setArm(getArm() * 2);
        }
        this.furia = furia;

    }

    public String getAccionEspecial(){
        if (getFuria()){
            return "Desactivar Furia";
        } else {
            return "Furia";
        }
    }


    // endregion

    // region Overrides
    public Guerrero clone() {
        return new Guerrero(getNombre(),getNivel(),getPv(),getAtq(),getArm(),getRes(),getVel(),this.getFuria());
    }

    public boolean equals(Guerrero g) {
        return getNombre().equals(g.getNombre()) &&
                getPv() == g.getPv() &&
                getAtq() == g.getAtq() &&
                getArm() == g.getArm() &&
                getVel() == g.getVel() &&
                getRes() == g.getRes() &&
                getNivel() == g.getNivel() &&
                getFuria() == g.getFuria();
    }

    public String toString() {
        return ("Clase: Guerrero\n" +
                super.toString() + "\n" +
                "¿Furioso? " + getFuria()
        );
    }
    // endregion
}
