package Characters;

import Misc.Miscellaneous;

import java.util.Random;
import java.util.Scanner;

public class Mago extends Personaje {
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

    public Mago(String nombre, int nivel, double pv, double atq, double arm, double res, double vel, double magia, int alianza) {
        super(nombre, nivel, pv, atq, arm, res, vel, alianza);
        setMagia(magia);
    }

    public Mago(String nombre, int nivel, double pv, double atq, double arm, double res, double vel, int alianza) {
        super(nombre, nivel, pv, atq, arm, res, vel, alianza);
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
    // endregion

    public void subirNivel() {
        Random r = new Random();
        if (r.nextInt(100) < 35) setPv(getPv() + 1);
        if (r.nextInt(100) < 15) setAtq(getAtq() + 1);
        if (r.nextInt(100) < 35) setArm(getArm() + 1);
        if (r.nextInt(100) < 80) setRes(getRes() + 1);
        if (r.nextInt(100) < 65) setVel(getVel() + 1);
        if (r.nextInt(100) < 85) setMagia(getMagia() + 1);
        setNivel(getNivel() + 1);
    }

    public void accionEspecial(Personaje enemigo) {
        lanzarConjuro(enemigo);
    }

    public void lanzarConjuro(Personaje enemigo) {
        Scanner scan = new Scanner(System.in);
        System.out.println(Miscellaneous.opcionesJugador("" + "Escoge un conjuro:\n" + "1- Bola de fuego\n" + "2- Escudo arcano\n" + "3- Céfiro\n" + "4- Presteza mental\n" + "5- Cambio de opinión\n"));
        System.out.print("Opción: ");
        int opt = scan.nextInt();
        switch (opt) {
            case 1 -> {
                System.out.println("¡Bola de fuego va!");
                ataca(getAtq() * 0.7, enemigo, true);
            }
            case 2 -> {
                System.out.println("Invocas un hechizo arcano, ¿pero a quien protejes? \n" + "\t· 1-Protegerte\n" + "\t· 2-Proteger aliado");
                System.out.print("Proteges a: ");
                opt = scan.nextInt();
                switch (opt) {
                    case 1 -> {
                        System.out.println("Aplicas el escudo arcano sobre ti");
                        escudoArcano(this);
                    }
                    case 2 -> {
                        System.out.println("Selecciona un aliado:");
                        System.out.println(this.getAliados());
                        System.out.print("Opción: ");
                        opt = scan.nextInt();
                        System.out.println("Aplicas el escudo arcano a tu aliado " + aliados[opt - 1].getNombre());
                        escudoArcano(aliados[opt - 1]);
                    }
                }

            }
            case 3 -> {
                System.out.println("Céfiro: Una fuerte ventisca afecta a los enemigos");
                ataca(getAtq() * 0.3, enemigo, true);
            }
            case 4 -> {
                System.out.println("¿A quién quieres aplicar con Presteza mental? \n" + "\t· 1-Aplicarte Presteza mental\n" + "\t· 2-Aplicar Presteza mental a un aliado");
                System.out.print("Aplicar a: ");
                opt = scan.nextInt();
                switch (opt) {
                    case 1 -> {
                        System.out.println("Aumenta tu agilidad en " + getMagia() + " puntos");
                        this.setVel(this.getVel() + this.getMagia());
                    }
                    case 2 -> {
                        System.out.println("Selecciona un aliado:");
                        System.out.println(this.getAliados());
                        System.out.print("Opción: ");
                        opt = scan.nextInt();
                        System.out.println("Aplicas el escudo arcano a tu aliado " + aliados[opt - 1].getNombre());
                        aliados[opt - 1].setVel(aliados[opt - 1].getVel() + this.getMagia());
                    }
                }
            }
            case 5 -> {
                System.out.println("Cambias de opinión y decides hacer otra cosa...");
                realizarTurnoJugador(enemigo);
            }
        }

    }

    public void escudoArcano(Personaje afectado) {
        afectado.setArm(getArm() + this.getMagia() * 0.5);
        afectado.setRes(getRes() + this.getMagia() * 0.5);
    }

    // region Setters & Getters

    public double getMagia() {
        return magia;
    }

    public void setMagia(double magia) {
        this.magia = magia;
    }

    public String getAccionEspecial() {
        return "Magia";
    }

    // endregion

    // region Overrides
    public Mago clone() {
        return new Mago(getNombre(), getNivel(), getPv(), getAtq(), getArm(), getRes(), getVel(), this.getMagia());
    }

    public boolean equals(Mago magia) {
        return getNombre().equals(magia.getNombre()) && getPv() == magia.getPv() && getAtq() == magia.getAtq() && getArm() == magia.getArm() && getVel() == magia.getVel() && getRes() == magia.getRes() && getNivel() == magia.getNivel() && getMagia() == magia.getMagia();
    }

    public String toString() {
        return ("Clase: Mago\n" + super.toString() + "\n" + "Magia: " + getMagia());
    }
    // endregion
}
