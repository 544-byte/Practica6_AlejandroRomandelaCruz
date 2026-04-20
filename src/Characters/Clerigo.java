package Characters;

import Gear.Arma;
import Gear.Armadura;
import Misc.Misc;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

/**
 * Clase que representa a un Clérigo, subclase de {@link Creyente}.
 * Los clérigos utilizan plegarias para curar aliados o dañar enemigos.
 */
public class Clerigo extends Creyente {
    // region Constructores
    /**
     * Constructor por defecto del Clérigo.
     */
    public Clerigo() {
        super();
    }

    /**
     * Constructor completo del Clerigo.
     * @param raza Raza del Clerigo
     * @param nombre Nombre del Clerigo
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
    public Clerigo(int raza, String nombre, int nivel, double pv, double atq, double arm, double res, double vel, double fe, int alianza, boolean esJugador) {
        super(raza, nombre, nivel, pv, atq, arm, res, vel, fe, alianza, esJugador);
    }

    /**
     * Constructor por CSV del Clerigo
     * @param csv El array del csv del personaje a crear.
     */
    public Clerigo(String[] csv){
        this(
                Integer.parseInt(csv[1]),
                csv[2],
                Integer.parseInt(csv[3]),
                Double.parseDouble(csv[4]),
                Double.parseDouble(csv[5]),
                Double.parseDouble(csv[6]),
                Double.parseDouble(csv[7]),
                Double.parseDouble(csv[8]),
                Double.parseDouble(csv[9]),
                Integer.parseInt(csv[10]),
                Boolean.parseBoolean(csv[11])
        );
        if (!this.esClase(csv[0])) {
            Misc.alert("El csv proporcionado no es de un Clerigo, corresponde a un " + csv[0]);
        }
    }

    // endregion

    /**
     * Incrementa el nivel del Clérigo aplicando mejoras aleatorias a sus atributos y a su fe.
     */
    public void subirNivel() {
        setNivel(getNivel() + 1);
        Misc.happen(this.getNombre()+" Ha subido de nivel,");
        aumentarAtributo("pv",20);
        aumentarAtributo("atq",10);
        aumentarAtributo("arm",20,2);
        aumentarAtributo("res",80,2);
        aumentarAtributo("vel",50);
        aumentarAtributo("fe",80,2);
    }

    /**
     * Permite al Clérigo realizar una plegaria, que puede curar aliados, afectar a todos los aliados,
     * infligir daño mágico a un enemigo o cambiar de opinión.
     *
     * @param enemigo Personaje enemigo objetivo de la plegaria
     */
    public void plegaria(Personaje enemigo) {
        Scanner scan = new Scanner(System.in);
        System.out.println(Misc.opcionesJugador("" + "Escoge una plegaria:\n" + "1- Sanación\n" + "2- Rezo sagrado\n" + "3- Cólera Divina\n" + "4- Info\n" + "5- Cambio de opinión\n"));
        System.out.print("Opción: ");
        int opt = scan.nextInt();
        switch (opt) {
            case 1 -> {
                System.out.println("Selecciona un aliado:");
                System.out.println(this.getAliados());
                System.out.print("Opción: ");
                opt = scan.nextInt();
                Misc.happen(this.getNombre() + " sana a " + aliados[opt - 1].getNombre() + " con " + getFe() * 0.7 + " puntos de vida");
                aliados[opt - 1].setPv(aliados[opt - 1].getPv() + getFe() * 0.7);
            }
            case 2 -> {
                Misc.happen(this.getNombre() + " reza una fuerte oración que cura a todos sus aliados y a si mismo " + getFe() * 0.3 + " puntos de vida");
                this.setPv(getPv() + getFe() * 0.3);
                for (Personaje aliado : aliados) {
                    aliado.setPv(aliado.getPv() + getFe() * 0.3);
                }
            }
            case 3 -> {
                Misc.happen(this.getNombre() + " invoca la cólera del señor haciéndola caer sobre " + enemigo.getNombre() + " causandole " + enemigo.defender(getAtq() + getFe() * 0.55,true)  + " puntos de daño.");
                setAtq(getAtq() + getFe() * 0.55);
                this.atacar(enemigo, true);
                setAtq(getAtq() - getFe() * 0.55);
            }
            case 4 -> {
                System.out.println("Información de las plegarias:\n" +
                        "1- Sanación: Sana a un aliado una cantidad de puntos de vida equivalente al 70% de tu Fe.\n" +
                        "2- Rezo sagrado: Una oración que cura a todos los aliados y a ti mismo el 30% de tu estadística de Fe.\n" +
                        "3- Cólera Divina: Invoca un castigo que inflige daño al enemigo sumando el 55% de tu Fe a tu ataque base.\n");
                plegaria(enemigo);
            }
            case 5 -> {
                System.out.println("Cambias de opinión y decides hacer otra cosa...");
                realizarTurnoJugador(enemigo);
            }
            default -> {
                System.out.println("Introduce una opción correcta.");
                plegaria(enemigo);
            }
        }
    }       //souted

    // region Setters & Getters

    /**
     * Metodo que devuelve un string con los valores separados por ":" para usarlo luego y guardarlo en un .csv tanto para importar como para exportar.
     * @return String con los atributos comúnes entre todos los personajes separados por ":"
     */
    public String getCSV() {
        return  "Clerigo:" + super.getCSV();
    }

    /**
     * Metodo que controla el equipamiento de armas de el Clerigo con sus respectivas restricciones
     * @param arma el arma a equipar.
     */
    public boolean setArma(Arma arma) {
        ArrayList<String> whitelist = new ArrayList<>(Set.of("Baston"));
        if (whitelist.contains(arma.getTipo())) {
            return super.setArma(arma);
        } else {
            Misc.alert(getNombre() + " es un Clerigo, por lo que no se puede equipar un " + arma.getTipo());
            return false;
        }
    }

    /**
     * Metodo que controla el equipamiento de armas de el clerigo con sus respectivas restricciones
     * @param armadura la armadura a equipar.
     */
    public boolean addArmadura(Armadura armadura) {
        if (armadura.getMaterial().equals("Tela")){
            return super.addArmadura(armadura);
        } else {
            Misc.alert( getNombre() + " es un Clerigo, por lo que no se puede equipar una pieza de armadura que no sea de Tela.");
            return false;
        }
    }

    // endregion

    // region Overrides
    /**
     * Crea un clon del Clérigo.
     *
     * @return Nuevo objeto Clerigo idéntico
     */
    public Clerigo clone() {
        return new Clerigo(getRaza(),getNombre(), getNivel(), getPv(), getAtq(), getArm(), getRes(), getVel(), getFe(), getAlianza(), getEsJugador());
    }

    /**
     * Compara este Clérigo con otro.
     *
     * @param l Clérigo a comparar
     * @return true si todos los atributos coinciden
     */
    public boolean equals(Clerigo l) {
        return getNombre().equals(l.getNombre()) && getPv() == l.getPv() && getAtq() == l.getAtq() && getArm() == l.getArm() && getVel() == l.getVel() && getRes() == l.getRes() && getNivel() == l.getNivel();
    }

    /**
     * Representación en cadena de texto del Clérigo.
     *
     * @return Cadena con información del Clérigo
     */
    public String toString() {
        return (super.toString());
    }
    // endregion
}
