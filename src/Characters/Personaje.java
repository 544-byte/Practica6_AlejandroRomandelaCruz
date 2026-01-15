package Characters;


import Combat.*;
import GameMap.*;
import Misc.*;

import java.util.Random;

public class Personaje {
    private String nombre;
    private int nivel, pv, atq, arm, res, vel;

    //region Constructores
    public Personaje() {
        nombre = "";
        pv = 100;
        atq = 10;
        arm = 10;
        res = 10;
        vel = 10;
        nivel = 0;
    }

    public Personaje(String nombre) {
        setNombre(nombre);
        setNivel(1);
        pv = 100;
        atq = 10;
        arm = 10;
        res = 10;
        vel = 10;
    }

    public Personaje(String nombre, int nivel) {
        setNombre(nombre);
        setNivel(nivel);
        pv = 100;
        atq = 10;
        arm = 10;
        res = 10;
        vel = 10;
        for (int i = 0; i < this.nivel; i++) {
            subirNivel();
        }
    }

    public Personaje(String nombre, int nivel, int pv, int atq, int arm, int res, int vel) {
        setNombre(nombre);
        setNivel(nivel);
        setPv(pv);
        setAtq(atq);
        setArm(arm);
        setRes(res);
        setVel(vel);
    }

    public Personaje(Personaje p) {
        setNombre(p.getNombre());
        setPv(p.getPv());
        setAtq(p.getAtq());
        setArm(p.getArm());
        setNivel(p.getNivel());
        setRes(p.getRes());
        setVel(p.getVel());
    }

    //endregion
    public Personaje inicializa(String n, int lvl, int pv, int atq, int arm, int res, int vel) {
        return new Personaje(n, lvl, pv, atq, arm, res, vel);
    }

    public int atacar() {
        return getAtq();
    }

    public int defender(int atq) {
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

    public void ataca(Personaje p) {
        if (atq - p.getArm() <= 0) {
            System.out.println(p.getNombre() + " es tan vigoroso que " + nombre + " ha sido incapaz de penetrar su armensa.");
        } else {
            if (p.getPv() - (atq - p.getArm()) <= 0) {
                p.setPv(0);
                System.out.println("¡" + p.getNombre() + " ha muerto!");
            } else {
                p.setPv(p.getPv() - p.getArm());
                System.out.println(nombre + " ha inflingido " + (atq - p.getArm()) + " puntos de daño a " + p.getNombre() + " dejándolo con " + p.getPv() + " puntos de vida.");
            }
        }

    }

    public void beberPocion(int pocion) {
        if (pv <= 30) {
            pv += pocion;
        }
    }

    public void inspirar(int cantidad, String tipo) {
        if (tipo.equals("ataque")) {
            atq += cantidad;
        } else if (tipo.equals("armensa")) {
            arm += cantidad;
        } else if (tipo.equals("vida")) {
            System.err.println("Para eso usa la poción papanatas.");
        } else {
            System.err.println("Introduce un tipo válido");
        }
    }

    public void subirNivel() {
        Random r = new Random();
        if (r.nextInt(2) == 0) setPv(pv++);
        if (r.nextInt(2) == 0) setPv(atq++);
        if (r.nextInt(2) == 0) setPv(arm++);
        if (r.nextInt(2) == 0) setPv(res++);
        if (r.nextInt(2) == 0)setPv(vel++);
        nivel++;
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
                    System.out.println("Aceite viscoso cae de pronto justo delante de " + nombre + " esta suerte le da tanto alivio que aumenta su armensa en " + t.getPerjuicio() + " puntos.");
                    arm += t.getPerjuicio();
                    break;
                case "Serpientes":
                    System.out.println("Un nido de víboras y culebras aparece frente a " + nombre + ". Por suerte quieren ayudarle y recorren sus brazos para hacer " + t.getPerjuicio() + " puntos de daño extra.");
                    atq += t.getPerjuicio();
                    break;
            }
        }
    }

    //region Setters
    public void setNombre(String nombre) {
        if (nombre.isBlank() || nombre.contains("GM") || nombre.length() < 2) {
            System.err.println("Introduce un nombre válido");
        } else {
            this.nombre = nombre;
        }
    }

    public void setPv(int pv) {
        if (pv < 0) {
            System.err.println("Los puntos de vida de " + nombre + " no pueden ser menor de 0");
        } else {
            this.pv = pv;
        }
    }

    public void setAtq(int atq) {
        if (atq < 0) {
            System.err.println("Los puntos de ataque de " + nombre + " no pueden ser menor de 0");
        } else {
            this.atq = atq;
        }
    }

    public void setArm(int arm) {
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

    public void setRes(int res) {
        if (res < 0) {
            System.err.println("Los puntos de resistencia mágica de " + nombre + " no pueden ser menor de 0");
        } else {
            this.res = res;
        }
    }

    public void setVel(int vel) {
        if (vel < 0) {
            System.err.println("Los puntos de velocidad de " + nombre + " no pueden ser menor de 0");
        } else {
            this.vel = vel;
        }
    }
    //endregion

    //region Getters
    public String getNombre() {
        return nombre;
    }

    public int getNivel() {
        return nivel;
    }

    public int getPv() {
        return pv;
    }

    public int getAtq() {
        return atq;
    }

    public int getArm() {
        return arm;
    }

    public int getRes() {
        return res;
    }

    public int getVel() {
        return vel;
    }
    //endregion

    public Personaje clone() {
        Personaje clon = new Personaje();
        clon.setNombre(getNombre());
        clon.setPv(getPv());
        clon.setAtq(getAtq());
        clon.setArm(getArm());
        clon.setNivel(getNivel());
        return clon;
    }

    public boolean equals(Personaje p) {
        return getNombre().equals(p.getNombre()) &&
                getPv() == p.getPv() &&
                getAtq() == p.getAtq() &&
                getArm() == p.getArm() &&
                getNivel() == p.getNivel();
    }

    public String toString() {
        return ("Nombre: " + getNombre() + "\n" +
                "Pv: " + getPv() + "\n" +
                "Atq: " + getAtq() + "\n" +
                "arm: " + getArm() + "\n" +
                "Nivel: " + getNivel()
        );
    }
}
