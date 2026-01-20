package Characters;

import Misc.Alianza;
import Misc.Miscellaneous;

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

    public Mago(String nombre, int nivel, double pv, double atq, double arm, double res, double vel) {
        super(nombre, nivel, pv, atq, arm, res, vel);
        setMagia(10);
    }

    public Mago(String nombre, int nivel, double pv, double atq, double arm, double res, double vel, double magia, Alianza alianza) {
        super(nombre, nivel, pv, atq, arm, res, vel,alianza);
        setMagia(magia);
    }

    public Mago(String nombre, int nivel, double pv, double atq, double arm, double res, double vel, Alianza alianza) {
        super(nombre, nivel, pv, atq, arm, res, vel,alianza);
        setMagia(10);
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
        System.out.println(Miscellaneous.opcionesJugador(""
                + "Escoge un conjuro:\n"
                + "1- Bola de fuego\n"
                + "2- Escudo arcano\n"
                + "3- Céfiro\n"
                + "4- Presteza mental\n"
                + "5- Cambio de opinión\n"
        ));
        System.out.print("Opción: ");
        int opt = scan.nextInt();
        switch (opt){
            case 1 -> {
                ataca(getAtq()*0.7,enemigo,true);
            }
            case 2 -> {
                System.out.println("Invocas un hechizo arcano, ¿pero a quien protejes? \n"
                        +"\t· 1-Protegerte"
                        +"\t· 2-Proteger aliado");
                System.out.print("Proteges a: ");
                opt = scan.nextInt();
                switch (opt){
                    case 1 -> {
                        escudoArcano(this);
                    }
                    /*case 2 -> {
                        escudoArcano();
                    }*/
                }

            }
            case 3 -> {

            }
            case 4 -> {

            }
            case 5 -> {
                System.out.println("Cambias de opinión y decides hacer otra cosa...");
                realizarTurnoJugador(enemigo);
            }
        }
    }

    public void escudoArcano(Personaje afectado){
        afectado.setArm(this.getMagia()*0.5);
        afectado.setRes(this.getMagia()*0.5);
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
