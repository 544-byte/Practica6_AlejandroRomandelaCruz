package Characters;

import java.util.Random;

/**
 * Clase que representa a un Monstruo, subclase de {@link Personaje}.<br>
 * Los monstruos tienen una raza específica que influye en el crecimiento
 * de sus atributos al subir de nivel. Pueden pertenecer a diferentes alianzas
 * y se diferencian por su raza: Bestia, No-Muerto o Gigante.
 */
public class Monstruo extends Personaje {
    private int raza;

    // region Constructores
    /**
     * Constructor por defecto del Monstruo.
     * Inicializa un Monstruo con valores base heredados de {@link Personaje}.
     */
    public Monstruo() {
        super();
    }

    /**
     * Constructor completo del Monstruo sin alianza.
     *
     * @param nombre Nombre del Monstruo
     * @param nivel Nivel inicial
     * @param pv Puntos de vida
     * @param atq Ataque base
     * @param arm Armadura
     * @param res Resistencia mágica
     * @param vel Velocidad
     * @param raza Raza del Monstruo (1: Bestia, 2: No-Muerto, 3: Gigante)
     */
    public Monstruo(String nombre, int nivel, double pv, double atq, double arm, double res, double vel, int raza) {
        super(nombre, nivel, pv, atq, arm, res, vel);
    }

    /**
     * Constructor completo del Monstruo con alianza.
     *
     * @param nombre Nombre del Monstruo
     * @param nivel Nivel inicial
     * @param pv Puntos de vida
     * @param atq Ataque base
     * @param arm Armadura
     * @param res Resistencia mágica
     * @param vel Velocidad
     * @param alianza Identificador de la alianza
     * @param raza Raza del Monstruo (1: Bestia, 2: No-Muerto, 3: Gigante)
     */
    public Monstruo(String nombre, int nivel, double pv, double atq, double arm, double res, double vel, int alianza, int raza) {
        super(nombre, nivel, pv, atq, arm, res, vel, alianza);
    }
    // endregion

    /**
     * Incrementa el nivel del Monstruo aumentando aleatoriamente sus atributos
     * según su raza. Cada raza tiene probabilidades y mejoras específicas.
     */
    public void subirNivel() {
        Random r = new Random();
        switch (getRaza()) {
            case 1 -> {
                if (r.nextInt(100) < 50) setPv(getPv() + 1);
                if (r.nextInt(100) < 80) setAtq(getAtq() + 2);
                if (r.nextInt(100) < 15) setArm(getArm() + 1);
                if (r.nextInt(100) < 15) setRes(getRes() + 1);
                if (r.nextInt(100) < 80) setVel(getVel() + 2);
            }
            case 2 -> {
                if (r.nextInt(100) < 30) setPv(getPv() + 1);
                if (r.nextInt(100) < 50) setAtq(getAtq() + 1);
                if (r.nextInt(100) < 30) setArm(getArm() + 1);
                if (r.nextInt(100) < 70) setRes(getRes() + 4);
                if (r.nextInt(100) < 5) setVel(getVel() + 1);
            }
            case 3 -> {
                setPv(getPv() + 1);
                if (r.nextInt(100) < 50) setPv(getPv() + (r.nextInt(1, 3)));
                if (r.nextInt(100) < 50) setAtq(getAtq() + 1);
                if (r.nextInt(100) < 50) setArm(getArm() + 1);
                if (r.nextInt(100) < 10) setRes(getRes() + 1);
                if (r.nextInt(100) < 10) setVel(getVel() + 1);
            }
        }
        setNivel(getNivel() + 1);
    }

    /**
     * metodo de accion especial, actualmente sin uso.
     * @param enemigo Enemigo objetivo
     */
    @Override
    public void accionEspecial(Personaje enemigo) {

    }

    // region Setters & Getters
    /**
     * Devuelve el nombre de la raza del Monstruo.
     *
     * @return Nombre de la raza ("Bestia", "No-Muerto" o "Gigante")
     */
    public String getRazaName() {
        switch (raza) {
            case 1 -> {
                return "Bestia";
            }
            case 2 -> {
                return "No-Muerto";
            }
            case 3 -> {
                return "Gigante";
            }
        }
        return "";
    }

    /**
     * Obtiene el identificador numérico de la raza del Monstruo.
     *
     * @return Entero de la raza (1, 2 o 3)
     */
    public int getRaza() {
        return this.raza;
    }

    /**
     * Establece la raza del Monstruo.
     *
     * @param raza Entero que representa la raza (1: Bestia, 2: No-Muerto, 3: Gigante)
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
     * Crea y devuelve una copia del Monstruo.
     *
     * @return Nuevo objeto Monstruo con los mismos atributos
     */
    public Monstruo clone() {
        return new Monstruo(getNombre(), getNivel(), getPv(), getAtq(), getArm(), getRes(), getVel(), getRaza());
    }

    /**
     * Compara este Monstruo con otro para determinar si son equivalentes
     * en todos sus atributos principales.
     *
     * @param l Monstruo a comparar
     * @return true si todos los atributos coinciden, false en caso contrario
     */
    public boolean equals(Monstruo l) {
        return getNombre().equals(l.getNombre()) && getPv() == l.getPv() && getAtq() == l.getAtq() && getArm() == l.getArm() && getVel() == l.getVel() && getRes() == l.getRes() && getNivel() == l.getNivel();
    }

    /**
     * Devuelve una representación en cadena de texto del Monstruo,
     * incluyendo su clase, raza y atributos heredados de {@link Personaje}.
     *
     * @return Cadena de texto con la información del Monstruo
     */
    public String toString() {
        return ("Clase: Monstruo\n" + "Raza: " + getRazaName() + "\n" + super.toString());
    }
    // endregion
}
