package Characters;

import java.util.Random;

public class Cazador extends Personaje {
    CompañeroAnimal compañeroAnimal;
    private class CompañeroAnimal extends Personaje{
        // 1- Cánido , 2- Felino , 3- Rapaz
        private int raza;

        // region Constructores
        public CompañeroAnimal() {
            super();
            raza = -1;
        }

        public CompañeroAnimal(String nombre, int nivel, double pv, double atq, double arm, double res, double vel, int alianza, int raza) {
            super(nombre, nivel, alianza);
            setRaza(raza);
            switch (getRaza()){
                case 1 -> {
                    setAtributos(pv*0.2,atq*0.2,arm*0.2,res*0.2,vel*0.2);
                }
                case 2 -> {
                    setAtributos(pv*0.15,atq*0.3,arm*0.15,res*0.15,vel*0.3);
                }
                case 3 -> {
                    setAtributos(pv*0.05,atq*0.15,arm*0.05,res*0.25,vel*0.35);
                }
            }
        }
        // endregion

        public void subirNivel(double pv,double atq,double arm,double res,double vel) {
            switch (getRaza()){
                case 1 -> {
                    setAtributos(pv*0.2,atq*0.2,arm*0.2,res*0.2,vel*0.2);
                    setNivel(Cazador.this.getNivel());
                }
                case 2 -> {
                    setAtributos(pv*0.15,atq*0.3,arm*0.15,res*0.15,vel*0.3);
                    setNivel(Cazador.this.getNivel());
                }
                case 3 -> {
                    setAtributos(pv*0.05,atq*0.15,arm*0.05,res*0.25,vel*0.35);
                    setNivel(Cazador.this.getNivel());
                }
            }
            setNivel(getNivel()+1);
        }

        public void accionEspecial(Personaje enemigo){
        }

        // region Setters & Getters
        public int getRaza() {
            return raza;
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
        public CompañeroAnimal clone() {
            return new CompañeroAnimal(getNombre(),getNivel(),getPv(),getAtq(),getArm(),getRes(),getVel(),getAlianza(),getRaza());
        }

        public boolean equals(CompañeroAnimal c) {
            return getNombre().equals(c.getNombre()) &&
                    getPv() == c.getPv() &&
                    getAtq() == c.getAtq() &&
                    getArm() == c.getArm() &&
                    getVel() == c.getVel() &&
                    getRes() == c.getRes() &&
                    getNivel() == c.getNivel();
        }

        public String toString() {
            return ("Clase: Compañero Animal\n" +
                    "Dueño: " + Cazador.this.getNombre() + "\n" +
                    super.toString()
            );
        }
        // endregion
    }

    // region Constructores
    public Cazador() {
        super();
    }

    public Cazador(String nombre, int nivel, double pv, double atq, double arm, double res, double vel) {
        super(nombre, nivel, pv, atq, arm, res, vel);
    }

    public Cazador(String nombre, int nivel, double pv, double atq, double arm, double res, double vel, int alianza) {
        super(nombre, nivel, pv, atq, arm, res, vel,alianza);
    }

    public Cazador(String nombre, int nivel, double pv, double atq, double arm, double res, double vel, int alianza, String nombreAnimal, int raza) {
        super(nombre, nivel, pv, atq, arm, res, vel,alianza);
        compañeroAnimal = new CompañeroAnimal(nombreAnimal,nivel,pv,atq,arm,res,vel,alianza,raza);
    }

    public Cazador(String nombre) {
        super(nombre);
    }

    public Cazador(String nombre, int nivel) {
        super(nombre, nivel);
    }
    // endregion


    public void subirNivel() {
        Random r = new Random();
        if (r.nextInt(100) < 50) setPv(getPv()+1);
        if (r.nextInt(100) < 50) setAtq(getAtq()+1);
        if (r.nextInt(100) < 50) setArm(getArm()+1);
        if (r.nextInt(100) < 50) setRes(getRes()+1);
        if (r.nextInt(100) < 70) setVel(getVel()+1);
        setNivel(getNivel()+1);
        compañeroAnimal.subirNivel(getPv(),getAtq(),getArm(),getRes(),getVel());
    }

    public void atacar(Personaje enemigo,boolean dañoMagico) {
        enemigo.setPv(getPv()-enemigo.defender(this.getAtq(),dañoMagico));
        compañeroAnimal.ataca(compañeroAnimal.getAtq(), enemigo,false);
    }

    public void accionEspecial(Personaje enemigo){
        compañeroAnimal.ataca(compañeroAnimal.getAtq(), enemigo,false);
    }

    // region Setters & Getters
    public String getAccionEspecial(){
        return "Enviar a " + compañeroAnimal.getNombre() + " a atacar";
    }

    // endregion

    // region Overrides
    public Cazador clone() {
        return new Cazador(getNombre(),getNivel(),getPv(),getAtq(),getArm(),getRes(),getVel());
    }

    public boolean equals(Cazador c) {
        return getNombre().equals(c.getNombre()) &&
                getPv() == c.getPv() &&
                getAtq() == c.getAtq() &&
                getArm() == c.getArm() &&
                getVel() == c.getVel() &&
                getRes() == c.getRes() &&
                getNivel() == c.getNivel();
    }

    public String toString() {
        return ("Clase: Cazador\n" +
                super.toString()
        );
    }
    // endregion
}