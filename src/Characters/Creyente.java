package Characters;

import java.util.Random;

/**
 * Clase abstracta que representa a un Creyente, subclase de {@link Personaje}.<br>
 * Los Creyentes poseen un atributo adicional de fe, que afecta sus habilidades
 * especiales llamadas plegarias.
 */
public abstract class Creyente extends Personaje {
    double fe;

    // region Constructores
    /**
     * Constructor por defecto del Creyente.
     * Inicializa un Creyente con atributos base y sin fe específica.
     */
    public Creyente() {
        super();
    }

    /**
     * Constructor completo del Creyente sin especificar fe.
     *
     * @param nombre Nombre del Creyente
     * @param nivel Nivel inicial
     * @param pv Puntos de vida
     * @param atq Ataque base
     * @param arm Armadura
     * @param res Resistencia mágica
     * @param vel Velocidad
     */
    public Creyente(String nombre, int nivel, double pv, double atq, double arm, double res, double vel) {
        super(nombre, nivel, pv, atq, arm, res, vel);
    }

    /**
     * Constructor completo del Creyente incluyendo fe.
     *
     * @param nombre Nombre del Creyente
     * @param nivel Nivel inicial
     * @param pv Puntos de vida
     * @param atq Ataque base
     * @param arm Armadura
     * @param res Resistencia mágica
     * @param vel Velocidad
     * @param fe Valor inicial de fe
     */
    public Creyente(String nombre, int nivel, double pv, double atq, double arm, double res, double vel, double fe) {
        super(nombre, nivel, pv, atq, arm, res, vel);
        setFe(fe);
    }

    /**
     * Constructor completo del Creyente con fe y alianza.
     *
     * @param nombre Nombre del Creyente
     * @param nivel Nivel inicial
     * @param pv Puntos de vida
     * @param atq Ataque base
     * @param arm Armadura
     * @param res Resistencia mágica
     * @param vel Velocidad
     * @param alianza Identificador de la alianza
     * @param fe Valor inicial de fe
     */
    public Creyente(String nombre, int nivel, double pv, double atq, double arm, double res, double vel, int alianza, double fe) {
        super(nombre, nivel, pv, atq, arm, res, vel, alianza);
        setFe(fe);
    }
    // endregion

    /**
     * Incrementa el nivel del Creyente aumentando aleatoriamente sus atributos
     * según probabilidades definidas.
     */
    public void subirNivel() {
        Random r = new Random();
        if (r.nextInt(100) < 40) setPv(getPv() + 1);
        if (r.nextInt(100) < 60) setAtq(getAtq() + 1);
        if (r.nextInt(100) < 40) setArm(getArm() + 1);
        if (r.nextInt(100) < 40) setRes(getRes() + 1);
        if (r.nextInt(100) < 85) setVel(getVel() + 2);
        setNivel(getNivel() + 1);
    }

    /**
     * Ejecuta la acción especial del Creyente, que consiste en realizar
     * una plegaria sobre un enemigo.
     *
     * @param enemigo Personaje objetivo de la plegaria
     */
    public void accionEspecial(Personaje enemigo) {
        plegaria(enemigo);
    }

    /**
     * Metodo abstracto que define la plegaria del Creyente.
     * Debe ser implementado por las subclases.
     *
     * @param enemigo Personaje objetivo de la plegaria
     */
    public abstract void plegaria(Personaje enemigo);

    // region Setters & Getters

    /**
     * Devuelve el nombre de la acción especial del Creyente.
     *
     * @return Siempre retorna "Plegaria"
     */
    public String getAccionEspecial() {
        return "Plegaria";
    }

    /**
     * Obtiene el valor de fe del Creyente.
     *
     * @return Valor de fe
     */
    public double getFe() {
        return fe;
    }

    /**
     * Establece el valor de fe del Creyente.
     *
     * @param fe Nuevo valor de fe
     */
    public void setFe(double fe) {
        this.fe = fe;
    }

    // endregion

    // region Overrides
    /**
     * Compara este Creyente con otro para determinar si son equivalentes
     * en todos sus atributos principales, incluyendo la fe.
     *
     * @param c Creyente a comparar
     * @return true si todos los atributos coinciden, false en caso contrario
     */
    public boolean equals(Creyente c) {
        return getNombre().equals(c.getNombre()) && getPv() == c.getPv() && getAtq() == c.getAtq() && getArm() == c.getArm() && getVel() == c.getVel() && getRes() == c.getRes() && getNivel() == c.getNivel() && getFe() == c.getFe();
    }

    /**
     * Devuelve una representación en cadena de texto del Creyente,
     * incluyendo los atributos heredados y la fe.
     *
     * @return Cadena de texto con la información del Creyente
     */
    public String toString() {
        return (super.toString() + "\n" + "Fe: " + getFe());
    }
    // endregion
}
