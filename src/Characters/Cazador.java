package Characters;

import java.util.Random;

/**
 * Clase que representa a un Cazador, subclase de {@link Personaje}.
 * El Cazador posee un {@link CompañeroAnimal} que actúa como aliado en combate.
 */
public class Cazador extends Personaje {
    CompañeroAnimal compañeroAnimal;

    /**
     * Clase interna que representa al compañero animal del Cazador.
     * Cada compañero tiene una raza y atributos que dependen de la raza del animal.
     */
    private class CompañeroAnimal extends Personaje {
        // 1- Cánido , 2- Felino , 3- Rapaz
        private int raza;

        // region Constructores
        /**
         * Constructor por defecto del compañero animal.
         * Inicializa un animal con raza no definida.
         */
        public CompañeroAnimal() {
            super();
            raza = -1;
        }

        /**
         * Constructor completo del compañero animal.
         *
         * @param nombre Nombre del animal
         * @param nivel Nivel inicial del animal
         * @param pv Puntos de vida del animal
         * @param atq Ataque del animal
         * @param arm Armadura del animal
         * @param res Resistencia del animal
         * @param vel Velocidad del animal
         * @param alianza Alianza a la que pertenece
         * @param raza Identificador de raza (1-Cánido, 2-Felino, 3-Rapaz)
         */
        public CompañeroAnimal(String nombre, int nivel, double pv, double atq, double arm, double res, double vel, int alianza, int raza) {
            super(nombre, nivel, alianza);
            setRaza(raza);
            switch (getRaza()) {
                case 1 -> {
                    setAtributos(pv * 0.2, atq * 0.2, arm * 0.2, res * 0.2, vel * 0.2);
                }
                case 2 -> {
                    setAtributos(pv * 0.15, atq * 0.3, arm * 0.15, res * 0.15, vel * 0.3);
                }
                case 3 -> {
                    setAtributos(pv * 0.05, atq * 0.15, arm * 0.05, res * 0.25, vel * 0.35);
                }
            }
        }
        // endregion

        /**
         * Sube de nivel al compañero animal ajustando sus atributos según su raza.
         *
         * @param pv Puntos de vida del dueño
         * @param atq Ataque del dueño
         * @param arm Armadura del dueño
         * @param res Resistencia del dueño
         * @param vel Velocidad del dueño
         */
        public void subirNivel(double pv, double atq, double arm, double res, double vel) {
            switch (getRaza()) {
                case 1 -> {
                    setAtributos(pv * 0.2, atq * 0.2, arm * 0.2, res * 0.2, vel * 0.2);
                    setNivel(Cazador.this.getNivel());
                }
                case 2 -> {
                    setAtributos(pv * 0.15, atq * 0.3, arm * 0.15, res * 0.15, vel * 0.3);
                    setNivel(Cazador.this.getNivel());
                }
                case 3 -> {
                    setAtributos(pv * 0.05, atq * 0.15, arm * 0.05, res * 0.25, vel * 0.35);
                    setNivel(Cazador.this.getNivel());
                }
            }
        }

        /**
         * Acción especial del compañero animal.
         *
         * @param enemigo Personaje objetivo de la acción
         */
        public void accionEspecial(Personaje enemigo) {
        }

        // region Setters & Getters
        /**
         * Devuelve el nombre de la raza del compañero animal.
         *
         * @return Nombre de la raza (Cánido, Felino, Rapaz)
         */
        public String getRazaName() {
            switch (raza) {
                case 1 -> {
                    return "Cánido";
                }
                case 2 -> {
                    return "Felino";
                }
                case 3 -> {
                    return "Rapaz";
                }
            }
            return "";
        }

        /**
         * Devuelve el identificador de la raza del compañero animal.
         *
         * @return Identificador de la raza
         */
        public int getRaza() {
            return this.raza;
        }

        /**
         * Establece la raza del compañero animal.
         *
         * @param raza Identificador de la raza (1-3)
         */
        public void setRaza(int raza) {
            if (raza >= 1 && raza <= 3) {
                this.raza = raza;
            } else {
                System.err.println("Error en setRaza: Introduce una raza válida\nInput: " + raza);
            }
        }

        // endregion

        // region Overrides
        /**
         * Crea un clon del compañero animal.
         *
         * @return Nuevo objeto CompañeroAnimal idéntico
         */
        public CompañeroAnimal clone() {
            return new CompañeroAnimal(getNombre(), getNivel(), getPv(), getAtq(), getArm(), getRes(), getVel(), getAlianza(), getRaza());
        }

        /**
         * Compara este compañero animal con otro.
         *
         * @param c CompañeroAnimal a comparar
         * @return true si todos los atributos coinciden
         */
        public boolean equals(CompañeroAnimal c) {
            return getNombre().equals(c.getNombre()) && getPv() == c.getPv() && getAtq() == c.getAtq() && getArm() == c.getArm() && getVel() == c.getVel() && getRes() == c.getRes() && getNivel() == c.getNivel();
        }

        /**
         * Representación en cadena de texto del compañero animal.
         *
         * @return Cadena con información del compañero animal
         */
        public String toString() {
            return ("Clase: Compañero Animal\n" + "Raza: " + getRazaName() + "\n" + "Dueño: " + Cazador.this.getNombre() + "\n" + super.toString());
        }
        // endregion
    }

    // region Constructores
    /**
     * Constructor por defecto del Cazador.
     */
    public Cazador() {
        super();
    }

    /**
     * Constructor completo del Cazador sin compañero animal.
     *
     * @param nombre Nombre del Cazador
     * @param nivel Nivel inicial
     * @param pv Puntos de vida
     * @param atq Ataque
     * @param arm Armadura
     * @param res Resistencia
     * @param vel Velocidad
     */
    public Cazador(String nombre, int nivel, double pv, double atq, double arm, double res, double vel) {
        super(nombre, nivel, pv, atq, arm, res, vel);
    }


    /**
     * Constructor completo del Cazador con compañero animal.
     *
     * @param nombre Nombre del Cazador
     * @param nivel Nivel inicial
     * @param pv Puntos de vida
     * @param atq Ataque
     * @param arm Armadura
     * @param res Resistencia
     * @param vel Velocidad
     * @param alianza Alianza del Cazador
     * @param nombreAnimal Nombre del compañero animal
     * @param raza Raza del compañero animal (1-Cánido, 2-Felino, 3-Rapaz)
     */
    public Cazador(String nombre, int nivel, double pv, double atq, double arm, double res, double vel, int alianza, String nombreAnimal, int raza) {
        super(nombre, nivel, pv, atq, arm, res, vel, alianza);
        compañeroAnimal = new CompañeroAnimal(nombreAnimal, nivel, pv, atq, arm, res, vel, alianza, raza);
    }
    // endregion


    /**
     * Incrementa el nivel del Cazador y de su compañero animal.
     */
    public void subirNivel() {
        Random r = new Random();
        if (r.nextInt(100) < 50) setPv(getPv() + 1);
        if (r.nextInt(100) < 50) setAtq(getAtq() + 1);
        if (r.nextInt(100) < 50) setArm(getArm() + 1);
        if (r.nextInt(100) < 50) setRes(getRes() + 1);
        if (r.nextInt(100) < 70) setVel(getVel() + 1);
        setNivel(getNivel() + 1);
        compañeroAnimal.subirNivel(getPv(), getAtq(), getArm(), getRes(), getVel());
    }

    /**
     * Ataque del Cazador sobre un enemigo, incluyendo ataque del compañero animal.
     *
     * @param enemigo Personaje objetivo
     * @param dañoMagico true si es daño mágico, false si es físico
     */
    public void atacar(Personaje enemigo, boolean dañoMagico) {
        enemigo.setPv(enemigo.getPv() - enemigo.defender(this.getAtq(), dañoMagico));
        compañeroAnimal.ataca(compañeroAnimal.getAtq(), enemigo, false);
    }

    /**
     * Acción especial del Cazador: el compañero animal ataca al enemigo.
     *
     * @param enemigo Personaje objetivo
     */
    public void accionEspecial(Personaje enemigo) {
        compañeroAnimal.ataca(compañeroAnimal.getAtq(), enemigo, false);
    }

    // region Setters & Getters
    /**
     * Devuelve la descripción de la acción especial del Cazador.
     *
     * @return Cadena indicando que el compañero animal atacará
     */
    public String getAccionEspecial() {
        return "Enviar a " + compañeroAnimal.getNombre() + " a atacar";
    }

    // endregion

    // region Overrides
    /**
     * Crea un clon del Cazador.
     *
     * @return Nuevo objeto Cazador idéntico
     */
    public Cazador clone() {
        return new Cazador(getNombre(), getNivel(), getPv(), getAtq(), getArm(), getRes(), getVel());
    }

    /**
     * Compara este Cazador con otro.
     *
     * @param c Cazador a comparar
     * @return true si todos los atributos coinciden
     */
    public boolean equals(Cazador c) {
        return getNombre().equals(c.getNombre()) && getPv() == c.getPv() && getAtq() == c.getAtq() && getArm() == c.getArm() && getVel() == c.getVel() && getRes() == c.getRes() && getNivel() == c.getNivel();
    }

    /**
     * Representación en cadena de texto del Cazador y su compañero animal.
     *
     * @return Cadena con información del Cazador y su compañero
     */
    public String toString() {
        return ("Clase: Cazador\n" + super.toString() + "\n" + "─────────── Compañero Animal ────────────\n" + this.compañeroAnimal.toString());
    }
    // endregion
}