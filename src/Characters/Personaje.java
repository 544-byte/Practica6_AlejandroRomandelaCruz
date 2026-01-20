package Characters;


import GameMap.*;
import Misc.Miscellaneous;

import java.util.Random;
import java.util.Scanner;

public class Personaje {
    private String nombre;
    private int nivel;
    private double pv, atq, vel, arm, res;
    private boolean esJugador;
    private int turno;
    private boolean defiende;

    //region Constructores
    public Personaje() {
        nombre = "";
        pv = 100;
        atq = 10;
        arm = 10;
        res = 10;
        vel = 10;
        nivel = 0;
        esJugador = false;
        turno = 0;
    }

    public Personaje(String nombre) {
        setNombre(nombre);
        setNivel(1);
        pv = 100;
        atq = 10;
        arm = 10;
        res = 10;
        vel = 10;
        esJugador = false;
        turno = 0;
    }

    public Personaje(String nombre, int nivel) {
        setNombre(nombre);
        setNivel(nivel);
        pv = 100;
        atq = 10;
        arm = 10;
        res = 10;
        vel = 10;
        esJugador = false;
        turno = 0;
        for (int i = 0; i < this.nivel; i++) {
            subirNivel();
        }
    }

    public Personaje(String nombre, int nivel, double pv, double atq, double arm, double res, double vel) {
        setNombre(nombre);
        setNivel(nivel);
        setPv(pv);
        setAtq(atq);
        setArm(arm);
        setRes(res);
        setVel(vel);
        esJugador = false;
        turno = 0;
    }

    public Personaje(Personaje p) {
        setNombre(p.getNombre());
        setPv(p.getPv());
        setAtq(p.getAtq());
        setArm(p.getArm());
        setNivel(p.getNivel());
        setRes(p.getRes());
        setVel(p.getVel());
        esJugador = false;
        turno = 0;
    }
    //endregion
    public Personaje inicializa(String n, int lvl, double pv, double atq, double arm, double res, double vel) {
        return new Personaje(n, lvl, pv, atq, arm, res, vel);
    }

    public void realizarTurno(Personaje enemigo){
        if (!this.getEsJugador()){
            this.ataca(enemigo);
        } else {
            System.out.println("¡Es tu turno!");
            realizarTurnoJugador(enemigo);
        }
        if (this.getVel() > enemigo.getVel()*2 && enemigo.getPv() != 0 && !this.getEsJugador()){
            turno ++;
            if (turno < 2) {
                realizarTurno(enemigo);
            } else {
                turno = 0;
            }
        }
    }

    public void realizarTurnoJugador(Personaje enemigo){
        int opt = 0;
        Scanner scan = new Scanner(System.in);
        System.out.println(
                "Elige una de las siguientes opciones:\n" +
                        "\t· 1- Atacar\n" +
                        "\t· 2- " + getAccionEspecial() + "\n" +
                        "\t· 3- Defender\n" +
                        "\t· 4- Pasar turno\n" +
                        "\t· 5- Observar"
        );
        do {
            System.out.print("Opción: ");
            opt = scan.nextInt();
            if (opt < 1 | opt > 5){
                System.err.println("Opción incorrecta, ingresela de nuevo.");
            }
        } while (opt < 1 | opt > 5);

        switch (opt){
            case 1 -> {
                this.ataca(enemigo);
            }
            case 2 -> {
                this.accionEspecial(enemigo);
            }
            case 3 -> {
                this.setDefiende(true);
                this.setArm(this.getArm() * 1.2);
                this.setArm(this.getRes() *1.2);
            }
            case 4 -> {
                System.out.println(this.getNombre() + " ha decidido no hacer absolutamente nada, let him cook.");
            }
            case 5 ->{
                System.out.println(
                        "\t · 1- Observar enemigo \n" +
                        "\t · 2- Observarte"
                );
                System.out.print("Opción: ");
                opt = scan.nextInt();
                switch (opt){
                    case 1 -> {
                        System.out.println(Miscellaneous.opcionesJugador(enemigo.toString()));
                        realizarTurnoJugador(enemigo);
                    }
                    case 2 -> {
                        System.out.println(Miscellaneous.opcionesJugador(this.toString()));
                        realizarTurnoJugador(enemigo);
                    }
                }
            }
        }
    }



    public void ataca(Personaje enemigo) {
        if (atq - enemigo.getArm() <= 0) {
            System.out.println(enemigo.getNombre() + " es tan vigoroso que " + nombre + " ha sido incapaz de penetrar su armadura.");
        } else {
            if (enemigo.getPv() - (atq - enemigo.getArm()) <= 0) {
                enemigo.setPv(0);
                System.out.println(nombre + " ha inflingido " + (atq - enemigo.getArm()) + " puntos de daño a " + enemigo.getNombre() + " dejándolo con " + enemigo.getPv() + " puntos de vida.\n¡" + enemigo.getNombre() + " ha muerto!");
            } else {
                enemigo.setPv(enemigo.getPv() - (atq - enemigo.getArm()));
                System.out.println(nombre + " ha inflingido " + (atq - enemigo.getArm()) + " puntos de daño a " + enemigo.getNombre() + " dejándolo con " + enemigo.getPv() + " puntos de vida.");
            }
        }
        if (enemigo.getDefiende()){
            enemigo.setDefiende(false);
            enemigo.setArm(enemigo.getArm() / 1.2);
            enemigo.setRes(enemigo.getRes() / 1.2);
        }
    }

    public double defender(double atq, boolean dañoMagico) {
        if ((atq - arm) < 0)
            return 0;
        else
            return (atq - arm);
    }

    public void randomStats() {
        int stats[] = new int[3];
        do {
            Random r = new Random();
            stats[0] = r.nextInt(0, 100 + 1);
            stats[1] = r.nextInt(0, 100 - stats[0] + 1);
            stats[2] = r.nextInt(0, 100 - stats[0] - stats[1] + 1);
        } while ((stats[0] + stats[1] + stats[2]) > 100);
        setPv(stats[0]);
        setAtq(stats[1]);
        setArm(stats[2]);
    }

    public void beberPocion(int pocion) {
        if (pv <= 30) {
            pv += pocion;
        }
    }

    public void inspirar(int cantidad, String tipo) {
        if (tipo.equals("ataque")) {
            atq += cantidad;
        } else if (tipo.equals("armadura")) {
            arm += cantidad;
        } else if (tipo.equals("vida")) {
            System.err.println("Para eso usa la poción papanatas.");
        } else {
            System.err.println("Introduce un tipo válido");
        }
    }

    public void subirNivel() {
        Random r = new Random();
        if (r.nextInt(2) == 0) setPv(getPv()+1);
        if (r.nextInt(2) == 0) setAtq(getAtq()+1);
        if (r.nextInt(2) == 0) setArm(getArm()+1);
        if (r.nextInt(2) == 0) setRes(getRes()+1);
        if (r.nextInt(2) == 0)setVel(getVel()+1);
        setNivel(getNivel()+1);
    }

    public boolean estaMuerto() {
        return pv <= 0;
    }

    public void caerTrampa(Trampa t) {
        String trampaCategoria = t.getCategoria();
        if (t.activaTrampa() != 0) {
            switch (trampaCategoria) {
                case "Pinchos":
                    System.out.println("Estacas afiladas salen de las superficies cercanas y atraviesan a " + nombre + " por " + t.getPerjuicio() + " puntos de daño.");
                    pv -= t.getPerjuicio();
                    break;
                case "Brea":
                    System.out.println("Aceite viscoso cae de pronto sobre " + nombre + " impidiéndole moverse con libertad.");
                    arm -= t.getPerjuicio();
                    break;
                case "Serpientes":
                    System.out.println("Un nido de víboras y culebras aparece frente a " + nombre + ". La visión es tan terrorífica que pierde las ganas de continuar.");
                    atq -= t.getPerjuicio();
                    break;
            }
        } else {
            switch (trampaCategoria) {
                case "Pinchos":
                    System.out.println("Estacas afiladas salen de las superficies cercanas, pero " + nombre + " es muy avispado y las sortea, inspirandose con " + t.getPerjuicio() + " puntos de vida.");
                    pv += t.getPerjuicio();
                    break;
                case "Brea":
                    System.out.println("Aceite viscoso cae de pronto justo delante de " + nombre + " esta suerte le da tanto alivio que aumenta su armadura en " + t.getPerjuicio() + " puntos.");
                    arm += t.getPerjuicio();
                    break;
                case "Serpientes":
                    System.out.println("Un nido de víboras y culebras aparece frente a " + nombre + ". Por suerte quieren ayudarle y recorren sus brazos para hacer " + t.getPerjuicio() + " puntos de daño extra.");
                    atq += t.getPerjuicio();
                    break;
                        }
        }
    }

    public String getAccionEspecial(){
        return "Acción especial";
    }

    /**
     * Debería de incluir esto como setter o no pq realmente no es un setter
     * ya que no recibe nada por parametro pero setea que es el jugador asiq...
     */
    public void setEsJugador(){
        esJugador = true;
    }

    public void accionEspecial(Personaje enemigo) {
    }

    public double atacar() {
        return getAtq();
    }

    //region Setters
    public void setNombre(String nombre) {
        if (nombre.isBlank() || nombre.contains("GM") || nombre.length() < 2) {
            System.err.println("Introduce un nombre válido");
        } else {
            this.nombre = nombre;
        }
    }

    public void setPv(double pv) {
        if (pv < 0) {
            System.err.println("Los puntos de vida de " + nombre + " no pueden ser menor de 0");
        } else {
            this.pv = pv;
        }
    }

    public void setAtq(double atq) {
        if (atq < 0) {
            System.err.println("Los puntos de ataque de " + nombre + " no pueden ser menor de 0");
        } else {
            this.atq = atq;
        }
    }

    public void setArm(double arm) {
        if (arm < 0) {
            System.err.println("Los puntos de armadura de " + nombre + " no pueden ser menor de 0");
        } else {
            this.arm = arm;
        }
    }

    public void setNivel(int lvl) {
        if (lvl < 1 || lvl > 100) {
            System.err.println("Introduce un nivel válido para" + nombre);
        } else {
            nivel = lvl;
        }
    }

    public void setRes(double res) {
        if (res < 0) {
            System.err.println("Los puntos de resistencia mágica de " + nombre + " no pueden ser menor de 0");
        } else {
            this.res = res;
        }
    }

    public void setVel(double vel) {
        if (vel < 0) {
            System.err.println("Los puntos de velocidad de " + nombre + " no pueden ser menor de 0");
        } else {
            this.vel = vel;
        }
    }

    public void setDefiende(boolean defiende) {
        this.defiende = defiende;
    }
    //endregion
    //region Getters
    public String getNombre() {
        return nombre;
    }

    public int getNivel() {
        return nivel;
    }

    public double getPv() {
        return pv;
    }

    public double getAtq() {
        return atq;
    }

    public double getArm() {
        return arm;
    }

    public double getRes() {
        return res;
    }

    public double getVel() {
        return vel;
    }

    public boolean getEsJugador(){
        return esJugador;
    }
    public boolean getDefiende() {
        return defiende;
    }
    //endregion
    // region Overrides
    public Personaje clone() {
        return new Personaje(getNombre(),getNivel(),getPv(),getAtq(),getArm(),getRes(),getVel());
    }

    public boolean equals(Personaje p) {
        return getNombre().equals(p.getNombre()) &&
                getPv() == p.getPv() &&
                getAtq() == p.getAtq() &&
                getArm() == p.getArm() &&
                getVel() == p.getVel() &&
                getRes() == p.getRes() &&
                getNivel() == p.getNivel();
    }

    public String toString() {
        return ("Nombre: " + getNombre() + "\n" +
                "Puntos de vida: " + getPv()  + "\n" +
                "Ataque: " + getAtq() + "\n" +
                "Armadura: " + getArm() + "\n" +
                "Resistencia: " + getRes() + "\n" +
                "Velocidad: " + getVel() + "\n" +
                "Nivel: " + getNivel() + "\n" +
                "¿Es jugador?" + getEsJugador()
        );
    }
    // endregion
}
