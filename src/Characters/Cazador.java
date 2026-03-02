package Characters;

import Misc.Misc;

/**
 * Clase que representa a un Cazador, subclase de {@link Personaje}.
 * El Cazador posee un {@link CompañeroAnimal} que actúa como aliado en combate.
 */
public class Cazador extends Personaje {
    private CompañeroAnimal compañeroAnimal;

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
        public CompañeroAnimal()  {
            super();
        }

        /**
         * Constructor completo del compañero animal.
         * La mayoría de parametros los coge del padre y los setea modificados.
         *
         * @param raza Identificador de raza (1-Cánido, 2-Felino, 3-Rapaz)
         * @param nombre Nombre del animal
         */
        public CompañeroAnimal(int raza, String nombre) {
            setRaza(raza);
            setNombre(nombre);
            setAlianza(Cazador.this.getAlianza());
            updateAtributos();
        }
        // endregion

        /**
         * Sube de nivel al compañero animal ajustando sus atributos según su raza.
         */
        public void subirNivel() {
            updateAtributos();
            setNivel(Cazador.this.getNivel());
        }

        public void updateAtributos(){
            switch (getRaza()) {
                case 1 -> {
                    setAtributos(Cazador.this.getPv() * 0.2, Cazador.this.getAtq() * 0.2, Cazador.this.getArm() * 0.2, Cazador.this.getRes() * 0.2, Cazador.this.getVel() * 0.2);
                }
                case 2 -> {
                    setAtributos(Cazador.this.getPv() * 0.15, Cazador.this.getAtq() * 0.3, Cazador.this.getArm() * 0.15, Cazador.this.getRes() * 0.15, Cazador.this.getVel() * 0.3);
                }
                case 3 -> {
                    setAtributos(Cazador.this.getPv() * 0.05, Cazador.this.getAtq() * 0.15, Cazador.this.getArm() * 0.05, Cazador.this.getRes() * 0.25, Cazador.this.getVel() * 0.35);
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
            return new CompañeroAnimal(getRaza(),getNombre());
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
            return ("Clase: Compañero Animal\n" + "Raza: " + getRazaName() + "\n" + "Dueño: " + Cazador.this.getNombre());
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
     * Constructor completo del Cazador.
     * @param raza Raza del Cazador
     * @param nombre Nombre del Cazador
     * @param nivel Nivel inicial
     * @param pv Puntos de vida
     * @param atq Ataque base
     * @param arm Armadura
     * @param res Resistencia mágica
     * @param vel Velocidad
     * @param alianza Identificador de la alianza
     * @param esJugador Identifica si el personaje es Jugador
     */
    public Cazador(int raza, String nombre, int nivel, double pv, double atq, double arm, double res, double vel, int alianza, boolean esJugador, String nombreCompañero, int razaCompañero) {
        super(raza, nombre, nivel, pv, atq, arm, res, vel, alianza, esJugador);
        setCompañeroAnimal(nombreCompañero,razaCompañero);
    }

    /**
     * Constructor por CSV del Cazador
     * @param csv El array del csv del personaje a crear.
     */
    public Cazador(String[] csv){
        this(
                Integer.parseInt(csv[1]),
                csv[2],
                Integer.parseInt(csv[3]),
                Double.parseDouble(csv[4]),
                Double.parseDouble(csv[5]),
                Double.parseDouble(csv[6]),
                Double.parseDouble(csv[7]),
                Double.parseDouble(csv[8]),
                Integer.parseInt(csv[10]),
                Boolean.parseBoolean(csv[11]),
                csv[12],
                Integer.parseInt(csv[13])
        );
        if (!this.esClase(csv[0])) {
            Misc.alert("El csv proporcionado no es de un cazador, corresponde a un " + csv[0]);
        }
    }

    // endregion


    /**
     * Incrementa el nivel del Cazador y de su compañero animal.
     */
    public void subirNivel() {
        setNivel(getNivel() + 1);
        aumentarAtributo("pv",50);
        aumentarAtributo("atq",50);
        aumentarAtributo("arm",50);
        aumentarAtributo("res",50);
        aumentarAtributo("vel",70,2);
        compañeroAnimal.updateAtributos();
    }

    /**
     * Ataque del Cazador sobre un enemigo, incluyendo ataque del compañero animal.
     *
     * @param enemigo Personaje objetivo
     * @param dañoMagico true si es daño mágico, false si es físico
     */
    public void atacar(Personaje enemigo, boolean dañoMagico) {
        enemigo.setPv(enemigo.getPv() - enemigo.defender(this.getAtq()+compañeroAnimal.getAtq(), dañoMagico));
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
    private void setCompañeroAnimal(String nombre, int raza){
        compañeroAnimal = new CompañeroAnimal(raza,nombre);
    }

    private CompañeroAnimal getCompañeroAnimal(){
        return compañeroAnimal;
    }

    /**
     * Devuelve la descripción de la acción especial del Cazador.
     *
     * @return Cadena indicando que el compañero animal atacará
     */
    public String getAccionEspecial() {
        return "Enviar a " + compañeroAnimal.getNombre() + " a atacar";
    }

    /**
     * Metodo que devuelve un string con los valores separados por ":" para usarlo luego y guardarlo en un .csv tanto para importar como para exportar.
     * @return String con los atributos comúnes entre todos los personajes separados por ":"
     */
    public String getCSV() {
        return  super.getCSV() + ":"  + -1 + ":" + getAlianza() + ":" + getEsJugador() + ":" + getCompañeroAnimal().getNombre() + ":" + getCompañeroAnimal().getRaza();
    }

    // endregion

    // region Overrides
    /**
     * Crea un clon del Cazador.
     *
     * @return Nuevo objeto Cazador idéntico
     */
    public Cazador clone() {
        return new Cazador(getRaza(),getNombre(), getNivel(), getPv(), getAtq(), getArm(), getRes(), getVel(), getAlianza(),getEsJugador(),getCompañeroAnimal().getNombre(),getCompañeroAnimal().getRaza());
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
        return (super.toString() + "\n" + "─────────── Compañero Animal ────────────\nNombre:" + getCompañeroAnimal().getNombre() + "\nRaza: " + getCompañeroAnimal().getRazaName());
    }
    // endregion
}