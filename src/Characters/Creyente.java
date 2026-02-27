package Characters;

import Misc.Misc;
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
        fe = -1;
    }

    /**
     * Constructor completo del Creyente.
     * @param raza Raza del Creyente
     * @param nombre Nombre del Creyente
     * @param nivel Nivel inicial
     * @param pv Puntos de vida
     * @param atq Ataque base
     * @param arm Armadura
     * @param res Resistencia mágica
     * @param vel Velocidad
     * @param fe especial Atributo especial
     * @param alianza Identificador de la alianza
     * @param esJugador Identifica si el personaje es Jugador
     */
    public Creyente(int raza, String nombre, int nivel, double pv, double atq, double arm, double res, double vel, double fe, int alianza, boolean esJugador) {
        super(raza, nombre, nivel, pv, atq, arm, res, vel, alianza, esJugador);
        setFe(fe);
    }
    // endregion

    /**
     * Incrementa el nivel del Creyente aumentando aleatoriamente sus atributos
     * según probabilidades definidas.
     */
    public void subirNivel() {
        setNivel(getNivel() + 1);
        Misc.happen(this.getNombre()+" Ha subido de nivel,");
        aumentarAtributo("pv",40);
        aumentarAtributo("atq",60);
        aumentarAtributo("arm",40);
        aumentarAtributo("res",40);
        aumentarAtributo("vel",85,2);
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

    public void aumentarAtributo(String atr,int prcnt,double cantidad){
        super.aumentarAtributo(atr,prcnt,cantidad);
        if (atr.equals("fe")){
            setFe(getFe() + cantidad);
            Misc.happen(this.getNombre() + " ha aumentado sus puntos de fe " + cantidad + " puntos, dejandolo con " + getFe() + " puntos de fe");
        }
    }

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

    /**
     * Metodo que devuelve un string con los valores separados por ":" para usarlo luego y guardarlo en un .csv tanto para importar como para exportar.
     * @return String con los atributos comúnes entre todos los personajes separados por ":"
     */
    public String getCSV() {
        return  getRaza() + ":" + getNombre() + ":" + getNivel() + ":" + getPv() + ":" + getAtq() + ":" + getArm() + ":" + getRes() + ":" + getVel() + ":" + getFe() + ":" + getAlianza() + ":" + getEsJugador();
    }

    /**
     * Devuelve el nombre del Atributo especial del Creyente
     * @return "Fe"
     */
    public static String getAtributoEspecial(){
        return "Fe";
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
