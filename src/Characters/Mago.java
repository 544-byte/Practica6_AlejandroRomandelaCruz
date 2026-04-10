package Characters;

import Gear.Arma;
import Gear.Armadura;
import Misc.Misc;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

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
     * Constructor completo del Mago con magia y alianza especificadas.
     *
     * @param raza Raza del Mago
     * @param nombre Nombre del Mago
     * @param nivel Nivel inicial
     * @param pv Puntos de vida
     * @param atq Ataque base
     * @param arm Armadura
     * @param res Resistencia mágica
     * @param vel Velocidad
     * @param magia Puntos de magia del Mago
     * @param alianza Identificador de la alianza
     * @param esJugador Identifica si es o no jugador
     */
    public Mago(int raza, String nombre, int nivel, double pv, double atq, double arm, double res, double vel, double magia,int alianza, boolean esJugador) {
        super(raza, nombre, nivel, pv, atq, arm, res, vel, alianza, esJugador);
        setMagia(magia);
    }

    /**
     * Constructor por CSV del Mago
     * @param csv El array del csv del personaje a crear.
     */
    public Mago(String[] csv){
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
            Misc.alert("El csv proporcionado no es de un mago, corresponde a un " + csv[0]);
        }
    }

    // endregion

    /**
     * Incrementa el nivel del Mago aumentando aleatoriamente sus atributos
     * y puntos de magia según probabilidades específicas.
     */
    public void subirNivel() {
        setNivel(getNivel() + 1);
        Misc.happen(this.getNombre()+" Ha subido de nivel,");
        aumentarAtributo("pv",35);
        aumentarAtributo("atq",15);
        aumentarAtributo("arm",35);
        aumentarAtributo("res",80);
        aumentarAtributo("vel",65);
        aumentarAtributo("magia",85);
    }   //souted

    /**
     * Ejecuta la acción especial del Mago, que consiste en lanzar un conjuro
     * sobre un enemigo o sobre sí mismo/aliados.
     *
     * @param enemigo Personaje objetivo del conjuro
     */
    public void accionEspecial(Personaje enemigo) {
        lanzarConjuro(enemigo);
    }   //souted

    /**
     * Permite al Mago seleccionar y lanzar un conjuro de varias opciones.
     *
     * @param enemigo Personaje objetivo de los conjuros ofensivos
     */
    public void lanzarConjuro(Personaje enemigo) {
        Scanner scan = new Scanner(System.in);
        System.out.println(Misc.opcionesJugador("" + "Escoge un conjuro:\n" + "1- Bola de fuego\n" + "2- Escudo arcano\n" + "3- Céfiro\n" + "4- Presteza mental\n" + "5- Info\n" + "6- Cambio de opinión\n"));
        System.out.print("Opción: ");
        int opt = scan.nextInt();
        switch (opt) {
            case 1 -> {
                Misc.happen(this.getNombre() + " lanza una bola de fuego sobre " + enemigo.getNombre());
                ataca(getMagia() * 0.70, enemigo, true);
            }
            case 2 -> {
                System.out.println("Invocas un hechizo arcano, ¿pero a quien proteges?\n" + "\t· 1-Protegerte\n" + "\t· 2-Proteger aliado");
                System.out.print("Proteges a: ");
                opt = scan.nextInt();
                switch (opt) {
                    case 1 -> {
                        Misc.happen(this.getNombre() +" aplica un escudo arcano sobre si mismo.");
                        escudoArcano(this);
                    }
                    case 2 -> {
                        System.out.println("Selecciona un aliado:");
                        System.out.println(this.getAliados());
                        System.out.print("Opción: ");
                        opt = scan.nextInt();
                        Misc.happen( this.getNombre()+ " aplica un escudo arcano a su aliado " + aliados[opt - 1].getNombre());
                        escudoArcano(aliados[opt - 1]);
                    }
                }

            }
            case 3 -> {
                Misc.happen(this.getNombre() + " lanza un céfiro contra " + enemigo.getNombre());
                ataca(getMagia() * 0.3, enemigo, true);
            }
            case 4 -> {
                System.out.println("¿A quién quieres aplicar con Presteza mental? \n" + "\t· 1-Aplicarte Presteza mental\n" + "\t· 2-Aplicar Presteza mental a un aliado");
                System.out.print("Aplicar a: ");
                opt = scan.nextInt();
                switch (opt) {
                    case 1 -> {
                        Misc.happen( this.getNombre()+ " se aplica presteza mental aumentando su agilidad en " + this.getMagia() + " puntos." );
                        this.setVel(this.getVel() + this.getMagia());
                    }
                    case 2 -> {
                        System.out.println("Selecciona un aliado:");
                        System.out.println(this.getAliados());
                        System.out.print("Opción: ");
                        opt = scan.nextInt();
                        Misc.happen( this.getNombre()+ " aplica presteza mental a su aliado " + aliados[opt - 1].getNombre() + " aumentando su agilidad en " + this.getMagia() + " puntos.");
                        aliados[opt - 1].setVel(aliados[opt - 1].getVel() + this.getMagia());
                    }
                }
            }
            case 5 -> {
                System.out.println("Información de los conjuros:\n" +
                        "1- Bola de fuego: Lanza un ataque potente que inflige el 70% de tus puntos de magia.\n" +
                        "2- Escudo arcano: Protege a un aliado o a ti mismo, reduciendo el daño recibido.\n" +
                        "3- Céfiro: Una ventisca que golpea al enemigo con un daño equivalente al 30% de tu ataque.\n" +
                        "4- Presteza mental: Aumenta la velocidad del objetivo (tú o un aliado) sumando tu estadística de Magia.\n");
                lanzarConjuro(enemigo);
            }
            case 6 -> {
                System.out.println("Cambias de opinión y decides hacer otra cosa...");
                realizarTurnoJugador(enemigo);
            }
            default -> {
                System.out.println("Introduce una opción correcta.");
                lanzarConjuro(enemigo);
            }
        }

    }       //souted

    /**
     * Aplica un escudo arcano a un personaje, aumentando armadura y resistencia.
     *
     * @param afectado Personaje que recibe el escudo
     */
    public void escudoArcano(Personaje afectado) {
        afectado.setArm(getArm() + this.getMagia() * 0.5);
        afectado.setRes(getRes() + this.getMagia() * 0.5);
    }

    public void aumentarAtributo(String atr,int prcnt,double cantidad){
        super.aumentarAtributo(atr,prcnt,cantidad);
        if (atr.equals("magia")){
            setMagia(getMagia() + cantidad);
            Misc.happen(this.getNombre() + " ha aumentado sus puntos de magia " + cantidad + " puntos, dejandolo con " + getMagia() + " puntos de magia");
        }
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
     * @return "Hechizo"
     */
    public String getAccionEspecial() {
        return "Hechizo";
    }

    /**
     * Devuelve el nombre del Atributo especial del mago
     * @return "Magia"
     */
    public String getAtributoEspecial(){
        return "Magia";
    }

    /**
     * Metodo que devuelve un string con los valores separados por ":" para usarlo luego y guardarlo en un .csv tanto para importar como para exportar.
     * @return String con los atributos comúnes entre todos los personajes separados por ":"
     */
    public String getCSV() {
        return  super.getCSV() + ":" + getMagia() + ":" + getAlianza() + ":" + getEsJugador();
    }

    public void setAtributoEspecial(double atributo){
        setMagia(atributo);
    }

    public double getAtributo(){
        return getMagia();
    }

    /**
     * Metodo que controla el equipamiento de armas de el mago con sus respectivas restricciones
     * @param arma el arma a equipar.
     */
    public void setArma(Arma arma) {
        ArrayList<String> whitelist = new ArrayList<>(Set.of("Cetro","Baston"));
        if (whitelist.contains(arma.getTipo())) {
            super.setArma(arma);
        } else {
            Misc.alert(getNombre() + " es un Mago, por lo que no se puede equipar un " + arma.getTipo());
        }
    }

    /**
     * Metodo que controla el equipamiento de armas de el mago con sus respectivas restricciones
     * @param armadura la armadura a equipar.
     */
    public void addArmadura(Armadura armadura) {
        if (armadura.getMaterial().equals("Tela")){
            super.addArmadura(armadura);
        } else {
            Misc.alert( getNombre() + " es un Mago, por lo que no se puede equipar una pieza de armadura que no sea de Tela.");
        }
    }

    // endregion

    // region Overrides
    /**
     * Crea y devuelve una copia del Mago.
     *
     * @return Nuevo objeto Mago con los mismos atributos
     */
    public Mago clone() {
        return new Mago(getRaza(),getNombre(), getNivel(), getPv(), getAtq(), getArm(), getRes(), getVel(), getMagia(),getAlianza(),getEsJugador());
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
        return (super.toString());
    }
    // endregion
}
