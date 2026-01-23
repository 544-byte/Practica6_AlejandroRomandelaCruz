package Characters;

import java.util.Random;
import java.util.Scanner;

/**
 * Clase que representa a un Guerrero, subclase de {@link Personaje}.<br>
 * El Guerrero puede entrar en estado de Furia, lo que modifica temporalmente
 * sus estadísticas de ataque y armadura. Su acción especial consiste en activar
 * o desactivar la Furia durante el combate.
 */
public class Guerrero extends Personaje {
    private boolean furia;

    // region Constructores
    /**
     * Constructor por defecto del Guerrero.
     * Inicializa un Guerrero con atributos base y sin Furia.
     */
    public Guerrero() {
        super();
        setFuria(false);
    }

    /**
     * Constructor completo del Guerrero incluyendo estado de Furia.
     *
     * @param nombre Nombre del Guerrero
     * @param nivel Nivel inicial
     * @param pv Puntos de vida
     * @param atq Ataque base
     * @param arm Armadura
     * @param res Resistencia mágica
     * @param vel Velocidad
     * @param furia Estado inicial de Furia
     */
    public Guerrero(String nombre, int nivel, double pv, double atq, double arm, double res, double vel, boolean furia) {
        super(nombre, nivel, pv, atq, arm, res, vel);
        setFuria(furia);
    }

    /**
     * Constructor completo del Guerrero sin especificar estado de Furia.
     *
     * @param nombre Nombre del Guerrero
     * @param nivel Nivel inicial
     * @param pv Puntos de vida
     * @param atq Ataque base
     * @param arm Armadura
     * @param res Resistencia mágica
     * @param vel Velocidad
     */
    public Guerrero(String nombre, int nivel, double pv, double atq, double arm, double res, double vel) {
        super(nombre, nivel, pv, atq, arm, res, vel);
        setFuria(false);
    }

    /**
     * Constructor completo del Guerrero con estado de Furia y alianza.
     *
     * @param nombre Nombre del Guerrero
     * @param nivel Nivel inicial
     * @param pv Puntos de vida
     * @param atq Ataque base
     * @param arm Armadura
     * @param res Resistencia mágica
     * @param vel Velocidad
     * @param furia Estado inicial de Furia
     * @param alianza Identificador de la alianza
     */
    public Guerrero(String nombre, int nivel, double pv, double atq, double arm, double res, double vel, boolean furia, int alianza) {
        super(nombre, nivel, pv, atq, arm, res, vel, alianza);
        setFuria(furia);
    }

    // endregion

    /**
     * Incrementa el nivel del Guerrero aumentando aleatoriamente sus atributos
     * según probabilidades específicas y el estado de Furia.
     */
    public void subirNivel() {
        Random r = new Random();
        if (r.nextInt(100) < 75) setPv(getPv() + 1);
        if (getFuria()) {
            setAtq(getAtq() / 2);
            if (r.nextInt(100) < 80) setAtq(getAtq() + 2);
            setAtq(getAtq() * 2);
        } else if (r.nextInt(100) < 80) setAtq(getAtq() + 2);
        if (getFuria()) {
            setAtq(getAtq() * 2);
            if (r.nextInt(100) < 75) setArm(getArm() + 1);
            setAtq(getAtq() / 2);
        } else if (r.nextInt(100) < 75) setArm(getArm() + 1);
        if (r.nextInt(100) < 20) setRes(getRes() + 1);
        if (r.nextInt(100) < 50) setVel(getVel() + 1);
        this.setNivel(getNivel() + 1);
    }

    /**
     * Cambia el estado de Furia del Guerrero.
     * Si estaba activo, lo desactiva; si estaba inactivo, lo activa.
     */
    public void modificarFuria() {
        if (furia) setFuria(false);
        else setFuria(true);
    }

    /**
     * Ejecuta la acción especial del Guerrero, que consiste en activar
     * o desactivar su Furia durante el combate.
     *
     * @param enemigo Personaje objetivo (puede afectar la interacción del jugador)
     */
    public void accionEspecial(Personaje enemigo) {
        Scanner scan = new Scanner(System.in);
        if (getFuria()) {
            System.out.println("Desactivar Furia: \n" + "Desactivar Furia te calma y vuelve tus estadísitcas a su estado anterior \n" + "Actualmente: Ataque: " + getAtq() + "\tArmadura: " + getArm() + "\n" + "Despues: Ataque: " + getAtq() / 2 + "\tArmadura: " + getArm() * 2 + "\n" + "¿Estás seguro de tu acción? s/n");
        } else {
            System.out.println("Furia: \n" + "Furia aumenta tu poder de ataque el doble a cambio de reducir a la mitad tu armadura \n" + "Actualmente: Ataque: " + getAtq() + "\tArmadura: " + getArm() + "\n" + "Despues: Ataque: " + getAtq() * 2 + "\tArmadura: " + getArm() / 2 + "\n" + "¿Estás seguro de tu acción? s/n");
        }

        char opt = scan.nextLine().toLowerCase().charAt(0);
        switch (opt) {
            case 's' -> {
                if (getFuria()) {
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
    /**
     * Obtiene si el Guerrero se encuentra en estado de Furia.
     *
     * @return true si está furioso, false en caso contrario
     */
    public boolean getFuria() {
        return furia;
    }

    /**
     * Establece el estado de Furia del Guerrero.
     *
     * @param furia true para activar la Furia, false para desactivarla
     */
    public void setFuria(boolean furia) {
        if (this.furia == false && furia == true) {
            setAtq(getAtq() * 2);
            setArm(getArm() / 2);
        } else if (this.furia == true && furia == false) {
            setAtq(getAtq() / 2);
            setArm(getArm() * 2);
        }
        this.furia = furia;

    }

    /**
     * Devuelve el nombre de la acción especial dependiendo del estado de Furia.
     *
     * @return "Furia" o "Desactivar Furia"
     */
    public String getAccionEspecial() {
        if (getFuria()) {
            return "Desactivar Furia";
        } else {
            return "Furia";
        }
    }


    // endregion

    // region Overrides
    /**
     * Crea y devuelve una copia del Guerrero.
     *
     * @return Nuevo objeto Guerrero con los mismos atributos
     */
    public Guerrero clone() {
        return new Guerrero(getNombre(), getNivel(), getPv(), getAtq(), getArm(), getRes(), getVel(), this.getFuria());
    }

    /**
     * Compara este Guerrero con otro para determinar si son equivalentes
     * en todos sus atributos principales.
     *
     * @param g Guerrero a comparar
     * @return true si todos los atributos coinciden, false en caso contrario
     */
    public boolean equals(Guerrero g) {
        return getNombre().equals(g.getNombre()) && getPv() == g.getPv() && getAtq() == g.getAtq() && getArm() == g.getArm() && getVel() == g.getVel() && getRes() == g.getRes() && getNivel() == g.getNivel() && getFuria() == g.getFuria();
    }

    /**
     * Devuelve una representación en cadena de texto del Guerrero,
     * incluyendo su clase, atributos heredados y estado de Furia.
     *
     * @return Cadena de texto con la información del Guerrero
     */
    public String toString() {
        return ("Clase: Guerrero\n" + super.toString() + "\n" + "¿Furioso? " + getFuria());
    }
    // endregion
}
