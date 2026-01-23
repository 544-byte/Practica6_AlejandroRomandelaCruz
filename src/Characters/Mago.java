package Characters;

import Misc.Miscellaneous;

import java.util.Random;
import java.util.Scanner;

/**
 * Clase que representa a un Mago, subclase de {@link Personaje}.<br>
 * El Mago utiliza magia para atacar y protegerse a sí mismo o a sus aliados.
 * Posee puntos de magia que determinan la efectividad de sus conjuros y hechizos.
 */
public class Mago extends Personaje {
    private double magia;

    // region Constructores
    /**
     * Constructor por defecto del Mago.
     * Inicializa un Mago con valores base y magia inicial de 10.
     */
    public Mago() {
        super();
        setMagia(10);
    }

    /**
     * Constructor completo del Mago con magia especificada.
     *
     * @param nombre Nombre del Mago
     * @param nivel Nivel inicial
     * @param pv Puntos de vida
     * @param atq Ataque base
     * @param arm Armadura
     * @param res Resistencia mágica
     * @param vel Velocidad
     * @param magia Puntos de magia del Mago
     */
    public Mago(String nombre, int nivel, double pv, double atq, double arm, double res, double vel, double magia) {
        super(nombre, nivel, pv, atq, arm, res, vel);
        setMagia(magia);
    }

    /**
     * Constructor completo sin especificar magia (por defecto 10).
     *
     * @param nombre Nombre del Mago
     * @param nivel Nivel inicial
     * @param pv Puntos de vida
     * @param atq Ataque base
     * @param arm Armadura
     * @param res Resistencia mágica
     * @param vel Velocidad
     */
    public Mago(String nombre, int nivel, double pv, double atq, double arm, double res, double vel) {
        super(nombre, nivel, pv, atq, arm, res, vel);
        setMagia(10);
    }

    /**
     * Constructor completo del Mago con magia y alianza especificadas.
     *
     * @param nombre Nombre del Mago
     * @param nivel Nivel inicial
     * @param pv Puntos de vida
     * @param atq Ataque base
     * @param arm Armadura
     * @param res Resistencia mágica
     * @param vel Velocidad
     * @param magia Puntos de magia del Mago
     * @param alianza Identificador de la alianza
     */
    public Mago(String nombre, int nivel, double pv, double atq, double arm, double res, double vel, double magia, int alianza) {
        super(nombre, nivel, pv, atq, arm, res, vel, alianza);
        setMagia(magia);
    }

    // endregion

    /**
     * Incrementa el nivel del Mago aumentando aleatoriamente sus atributos
     * y puntos de magia según probabilidades específicas.
     */
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

    /**
     * Ejecuta la acción especial del Mago, que consiste en lanzar un conjuro
     * sobre un enemigo o sobre sí mismo/aliados.
     *
     * @param enemigo Personaje objetivo del conjuro
     */
    public void accionEspecial(Personaje enemigo) {
        lanzarConjuro(enemigo);
    }

    /**
     * Permite al Mago seleccionar y lanzar un conjuro de varias opciones.
     *
     * @param enemigo Personaje objetivo de los conjuros ofensivos
     */
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

    /**
     * Aplica un escudo arcano a un personaje, aumentando armadura y resistencia.
     *
     * @param afectado Personaje que recibe el escudo
     */
    public void escudoArcano(Personaje afectado) {
        afectado.setArm(getArm() + this.getMagia() * 0.5);
        afectado.setRes(getRes() + this.getMagia() * 0.5);
    }

    // region Setters & Getters

    /**
     * Obtiene los puntos de magia del Mago.
     *
     * @return Puntos de magia
     */
    public double getMagia() {
        return magia;
    }

    /**
     * Establece los puntos de magia del Mago.
     *
     * @param magia Puntos de magia a asignar
     */
    public void setMagia(double magia) {
        this.magia = magia;
    }

    /**
     * Devuelve el nombre de la acción especial del Mago.
     *
     * @return "Magia"
     */
    public String getAccionEspecial() {
        return "Magia";
    }

    // endregion

    // region Overrides
    /**
     * Crea y devuelve una copia del Mago.
     *
     * @return Nuevo objeto Mago con los mismos atributos
     */
    public Mago clone() {
        return new Mago(getNombre(), getNivel(), getPv(), getAtq(), getArm(), getRes(), getVel(), this.getMagia());
    }

    /**
     * Compara este Mago con otro para determinar si son equivalentes
     * en todos sus atributos principales incluyendo magia.
     *
     * @param magia Mago a comparar
     * @return true si todos los atributos coinciden, false en caso contrario
     */
    public boolean equals(Mago magia) {
        return getNombre().equals(magia.getNombre()) && getPv() == magia.getPv() && getAtq() == magia.getAtq() && getArm() == magia.getArm() && getVel() == magia.getVel() && getRes() == magia.getRes() && getNivel() == magia.getNivel() && getMagia() == magia.getMagia();
    }

    /**
     * Devuelve una representación en cadena de texto del Mago,
     * incluyendo su clase, atributos heredados de {@link Personaje} y puntos de magia.
     *
     * @return Cadena de texto con la información del Mago
     */
    public String toString() {
        return ("Clase: Mago\n" + super.toString() + "\n" + "Magia: " + getMagia());
    }
    // endregion
}
