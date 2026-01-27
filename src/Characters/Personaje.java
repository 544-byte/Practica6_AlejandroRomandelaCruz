package Characters;


import GameMap.*;
import Misc.Miscellaneous;

import java.util.Random;
import java.util.Scanner;

/**
 * Clase abstracta que representa un personaje del juego.<br>
 * Un personaje puede ser controlado por el jugador o por la IA y dispone de
 * atributos básicos como vida, ataque, defensa, resistencia, velocidad y nivel.
 * Además, gestiona turnos, alianzas, combate, trampas y acciones especiales.<br>
 * Esta clase es abstracta porque define un comportamiento base común,
 * pero deja ciertas acciones (como la acción especial) para que sean
 * implementadas por subclases concretas.
 */
public abstract class Personaje {
    // region Atributos
    /**
     * Lista estática que almacena todos los personajes creados en la partida.
     */
    private static Personaje[] personajes = new Personaje[0];
    /**
     * Array de aliados del personaje.
     */
    Personaje[] aliados;
    /**
     * Número de aliados actuales.
     */
    int nAliados = 0;
    /**
     * Nombre del personaje
     */
    private String nombre;
    /**
     * Nivel del personaje
     */
    private int nivel;
    /**
     * Atributos base de los personajes (vida, ataque, velocidad, armadura y resistencia)
     */
    private double pv, atq, vel, arm, res;
    /**
     * Indica si el personaje es controlado por el jugador
     */
    private boolean esJugador;
    /**
     * Control interno de turnos extra
     */
    private int turno;
    /**
     * Indica si el personaje se está defendiendo
     */
    private boolean defiende;
    /**
     * Identificador de alianza
     */
    private int alianza = -1;
    // endregion

    //region Constructores

    /**
     * Constructor por defecto.
     * Inicializa un personaje con valores básicos.
     */
    public Personaje() {
        nombre = "";
        pv = 100;
        atq = 10;
        arm = 10;
        res = 10;
        vel = 10;
        nivel = 0;
        esJugador = false;
        turno = 0;
        añadirPersonaje(this);
    }

    /**
     * Constructor con nombre.
     *
     * @param nombre Nombre del personaje
     */
    public Personaje(String nombre) {
        setNombre(nombre);
        setNivel(1);
        pv = 100;
        atq = 10;
        arm = 10;
        res = 10;
        vel = 10;
        esJugador = false;
        turno = 0;
        añadirPersonaje(this);
    }

    /**
     * Constructor con nombre y nivel.
     *
     * @param nombre Nombre del personaje
     * @param nivel  Nivel inicial
     */
    public Personaje(String nombre, int nivel) {
        setNombre(nombre);
        setNivel(nivel);
        pv = 100;
        atq = 10;
        arm = 10;
        res = 10;
        vel = 10;
        esJugador = false;
        turno = 0;
        for (int i = 0; i < this.nivel; i++) {
            subirNivel();
        }
        añadirPersonaje(this);
    }

    /**
     * Constructor con nombre, nivel y alianza.
     *
     * @param nombre  Nombre del personaje
     * @param nivel   Nivel inicial
     * @param alianza Alianza a la que pertenece
     */
    public Personaje(String nombre, int nivel, int alianza) {
        setNombre(nombre);
        setNivel(nivel);
        esJugador = false;
        turno = 0;
        for (int i = 1; i < nivel; i++) {
            subirNivel();
        }
        setAlianza(alianza);
        añadirPersonaje(this);
    }

    /**
     * Constructor completo con atributos personalizados.
     */
    public Personaje(String nombre, int nivel, double pv, double atq, double arm, double res, double vel) {
        setNombre(nombre);
        setNivel(nivel);
        setPv(pv);
        setAtq(atq);
        setArm(arm);
        setRes(res);
        setVel(vel);
        esJugador = false;
        turno = 0;
        añadirPersonaje(this);
    }

    /**
     * Constructor completo con alianza.
     */
    public Personaje(String nombre, int nivel, double pv, double atq, double arm, double res, double vel, int alianza) {
        setNombre(nombre);
        setNivel(nivel);
        setPv(pv);
        setAtq(atq);
        setArm(arm);
        setRes(res);
        setVel(vel);
        esJugador = false;
        turno = 0;
        setAlianza(alianza);
        añadirPersonaje(this);
    }

    /**
     * Constructor copia.
     *
     * @param p Personaje a copiar
     */
    public Personaje(Personaje p) {
        setNombre(p.getNombre());
        setPv(p.getPv());
        setAtq(p.getAtq());
        setArm(p.getArm());
        setNivel(p.getNivel());
        setRes(p.getRes());
        setVel(p.getVel());
        esJugador = false;
        turno = 0;
        añadirPersonaje(this);
    }
    //endregion

    /**
     * Sube el nivel del personaje aumentando aleatoriamente sus atributos.
     */
    public void subirNivel() {
        Random r = new Random();
        if (r.nextInt(100) < 50) setPv(getPv() + 1);
        if (r.nextInt(100) < 50) setAtq(getAtq() + 1);
        if (r.nextInt(100) < 50) setArm(getArm() + 1);
        if (r.nextInt(100) < 50) setRes(getRes() + 1);
        if (r.nextInt(100) < 50) setVel(getVel() + 1);
        setNivel(getNivel() + 1);
    }

    /**
     * Ejecuta el turno del personaje contra un enemigo.
     *
     * @param enemigo Personaje enemigo
     */
    public void realizarTurno(Personaje enemigo) {
        if (!this.getEsJugador()) {
            this.ataca(getAtq(), enemigo, false);
            System.out.println(new String("\n").repeat(1));
        } else {
            System.out.println("¡" + this.getNombre() + " es tu turno!");
            realizarTurnoJugador(enemigo);
            System.out.println(new String("\n").repeat(1));
        }
        if (this.getVel() > enemigo.getVel() * 2 && enemigo.getPv() != 0 && !this.getEsJugador()) {
            turno++;
            if (turno < 2) {
                realizarTurno(enemigo);
            } else {
                turno = 0;
            }
        }
    }

    /**
     * Gestiona el turno del jugador mostrando opciones por consola.
     *
     * @param enemigo Enemigo al que se enfrenta
     */
    public void realizarTurnoJugador(Personaje enemigo) {
        int opt = 0;
        Scanner scan = new Scanner(System.in);
        System.out.println("Elige una de las siguientes opciones:\n" + "\t· 1- Atacar\n" + "\t· 2- " + getAccionEspecial() + "\n" + "\t· 3- Defender\n" + "\t· 4- Pasar turno\n" + "\t· 5- Observar");
        do {
            System.out.print("Opción: ");
            opt = scan.nextInt();
            if (opt < 1 | opt > 5) {
                System.err.println("Opción incorrecta, ingresela de nuevo.");
            }
        } while (opt < 1 | opt > 5);

        switch (opt) {
            case 1 -> {
                ataca(getAtq(), enemigo, false);
            }
            case 2 -> {
                this.accionEspecial(enemigo);
            }
            case 3 -> {
                this.setDefiende(true);
                this.setArm(this.getArm() * 1.2);
                this.setRes(this.getRes() * 1.2);
                System.out.println("Te defiendes durante el próximo turno");
            }
            case 4 -> {
                System.out.println(this.getNombre() + " ha decidido no hacer absolutamente nada, let him cook.");
            }
            case 5 -> {
                System.out.println("\t· 1- Observar enemigo\n" + "\t· 2- Observarte\n" + "\t· 3- Ver aliados");
                System.out.print("Opción: ");
                opt = scan.nextInt();
                switch (opt) {
                    case 1 -> {
                        System.out.println(Miscellaneous.estadisticasJugador(enemigo.toString()));
                        realizarTurnoJugador(enemigo);
                    }
                    case 2 -> {
                        System.out.println(Miscellaneous.estadisticasJugador(this.toString()));
                        realizarTurnoJugador(enemigo);
                    }
                    case 3 -> {
                        updateAliados();
                        for (int i = 0; i < nAliados; i++) {
                            if (aliados[i] != null) {
                                System.out.println(Miscellaneous.estadisticasJugador(aliados[i].toString()));
                            }
                        }
                        realizarTurnoJugador(enemigo);
                    }
                }
            }
        }
    }

    /**
     * Realiza un ataque contra un enemigo.
     *
     * @param atq        Cantidad de ataque
     * @param enemigo    Objetivo del ataque
     * @param dañoMagico Indica si el daño es mágico
     */
    public void ataca(double atq, Personaje enemigo, boolean dañoMagico) {
        if (enemigo.defender(atq, dañoMagico) <= 0) {
            System.out.println(enemigo.getNombre() + " es tan vigoroso que " + nombre + " ha sido incapaz de penetrar sus defensas.");
            atacar(enemigo, dañoMagico);
        } else {
            if (enemigo.getPv() - (atq - enemigo.getArm()) <= 0) {
                atacar(enemigo, dañoMagico);
                System.out.println(nombre + " ha inflingido " + (atq - enemigo.getArm()) + " puntos de daño a " + enemigo.getNombre() + " dejándolo con " + enemigo.getPv() + " puntos de vida.\n¡" + enemigo.getNombre() + " ha muerto!");
            } else {
                atacar(enemigo, dañoMagico);
                System.out.println(nombre + " ha inflingido " + (atq - enemigo.getArm()) + " puntos de daño a " + enemigo.getNombre() + " dejándolo con " + enemigo.getPv() + " puntos de vida.");
            }
        }
        if (enemigo.getDefiende()) {
            enemigo.setDefiende(false);
            enemigo.setArm(enemigo.getArm() / 1.2);
            enemigo.setRes(enemigo.getRes() / 1.2);
        }
    }

    /**
     * Aplica el daño efectivo al enemigo.
     */
    public void atacar(Personaje enemigo, boolean dañoMagico) {
        enemigo.setPv(enemigo.getPv() - enemigo.defender(this.getAtq(), dañoMagico));
    }

    /**
     * Calcula el daño recibido tras aplicar defensas.
     *
     * @param atq        Ataque recibido
     * @param dañoMagico Indica si el daño es mágico
     * @return Daño final
     */
    public double defender(double atq, boolean dañoMagico) {
        if (dañoMagico) {
            if ((atq - getRes()) < 0) return 0;
            else return (atq - getRes());
        } else {
            if ((atq - getArm()) < 0) return 0;
            else return (atq - getArm());
        }
    }

    /**
     * Permite que nuestra vida aumente según la poción siempre y cuando nuestra vida esté por debajo de 30
     *
     * @param pocion los puntos de vida que se van a sumar
     */
    public void beberPocion(int pocion) {
        if (pv <= 30) {
            pv += pocion;
        }
    }

    /**
     * Cuando un personaje cae en una trampa y sale ileso se inspira con la cantidad corerspondiente
     *
     * @param cantidad Los puntos de inspiración
     * @param tipo     Indica que tipo de inspiración es
     */
    public void inspirar(int cantidad, String tipo) {
        if (tipo.equals("ataque")) {
            atq += cantidad;
        } else if (tipo.equals("armadura")) {
            arm += cantidad;
        } else if (tipo.equals("vida")) {
            pv += cantidad;
        } else {
            System.err.println("Introduce un tipo válido");
        }
    }

    /**
     * Indica si el personaje está muerto.
     *
     * @return true si los puntos de vida son 0 o menores
     */
    public boolean estaMuerto() {
        return getPv() <= 0;
    }

    /**
     * Hace a personaje caer en una trampa, puede salir ileso o no...
     *
     * @param t La trampa en la que cae el personaje
     */
    public void caerTrampa(Trampa t) {
        String trampaCategoria = t.getCategoria();
        if (t.activaTrampa() != 0) {
            switch (trampaCategoria) {
                case "Pinchos":
                    System.out.println("Estacas afiladas salen de las superficies cercanas y atraviesan a " + nombre + " por " + t.getPerjuicio() + " puntos de daño.");
                    pv -= t.getPerjuicio();
                    break;
                case "Brea":
                    System.out.println("Aceite viscoso cae de pronto sobre " + nombre + " impidiéndole moverse con libertad.");
                    arm -= t.getPerjuicio();
                    break;
                case "Serpientes":
                    System.out.println("Un nido de víboras y culebras aparece frente a " + nombre + ". La visión es tan terrorífica que pierde las ganas de continuar.");
                    atq -= t.getPerjuicio();
                    break;
            }
        } else {
            switch (trampaCategoria) {
                case "Pinchos":
                    System.out.println("Estacas afiladas salen de las superficies cercanas, pero " + nombre + " es muy avispado y las sortea, inspirandose con " + t.getPerjuicio() + " puntos de vida.");
                    pv += t.getPerjuicio();
                    break;
                case "Brea":
                    System.out.println("Aceite viscoso cae de pronto justo delante de " + nombre + " esta suerte le da tanto alivio que aumenta su armadura en " + t.getPerjuicio() + " puntos.");
                    arm += t.getPerjuicio();
                    break;
                case "Serpientes":
                    System.out.println("Un nido de víboras y culebras aparece frente a " + nombre + ". Por suerte quieren ayudarle y recorren sus brazos para hacer " + t.getPerjuicio() + " puntos de daño extra.");
                    atq += t.getPerjuicio();
                    break;
            }
        }
    }

    /**
     * Devuelve el nombre de la acción especial.
     *
     * @return Nombre de la acción especial
     */

    public String getAccionEspecial() {
        return "Acción especial";
    }

    /**
     * Hace que el personaje se convierta en un personaje jugable.
     */
    public void setEsJugador() {
        esJugador = true;
        setAlianza(0);
    }

    /**
     * Ejecuta la acción especial del personaje.<br>
     * Debe ser sobrescrita por las subclases.
     *
     * @param enemigo Enemigo objetivo
     */
    public abstract void accionEspecial(Personaje enemigo);

    /**
     * Comprueba si otro personaje es aliado.
     *
     * @param personaje Personaje a comprobar
     * @return true si pertenecen a la misma alianza
     */
    public boolean esAliado(Personaje personaje) {
        if (this.getAlianza() == personaje.getAlianza()) return true;
        else return false;

    }

    /**
     * Añade un personaje a la lista global.
     *
     * @param personaje Personaje a añadir
     */
    public static void añadirPersonaje(Personaje personaje) {
        Personaje[] supportArray = personajes;
        personajes = new Personaje[supportArray.length + 1];
        for (int i = 0; i < supportArray.length; i++) {
            personajes[i] = supportArray[i];
        }
        personajes[personajes.length - 1] = personaje;
    }

    /**
     * Actualiza el array de aliados.
     */
    public void updateAliados() {
        int aliadosAñadidos = 0;
        String aliadosString = "";
        for (int i = 0; i < personajes.length; i++) {
            if (this.esAliado(personajes[i]) && personajes[i] != this) {
                nAliados++;
            }
        }
        aliados = new Personaje[nAliados];
        for (int i = 0; i < personajes.length; i++) {
            if (this.esAliado(personajes[i]) && !personajes[i].equals(this)) {
                aliados[aliadosAñadidos] = personajes[i];
                aliadosAñadidos++;
                aliadosString += "" + "\t · " + aliadosAñadidos + "- " + aliados[aliadosAñadidos - 1].getNombre() + "\n";
            }
        }

    }

    //region Setters

    /**
     * Establece el nombre del personaje.<br>
     * El nombre debe tener al menos 2 caracteres, no estar vacío
     * y no contener la cadena reservada "GM".
     *
     * @param nombre Nombre del personaje
     */
    public void setNombre(String nombre) {
        if (nombre.isBlank() || nombre.contains("GM") || nombre.length() < 2) {
            System.err.println("Introduce un nombre válido");
        } else {
            this.nombre = nombre;
        }
    }

    /**
     * Establece todos los atributos principales del personaje.
     *
     * @param pv  Puntos de vida
     * @param atq Ataque
     * @param arm Armadura
     * @param res Resistencia mágica
     * @param vel Velocidad
     */
    public void setAtributos(double pv, double atq, double arm, double res, double vel) {
        setPv(pv);
        setAtq(atq);
        setArm(arm);
        setRes(res);
        setVel(vel);
    }

    /**
     * Establece los puntos de vida del personaje.<br>
     * Si el valor es negativo, se ajusta automáticamente a 0.
     *
     * @param pv Puntos de vida
     */
    public void setPv(double pv) {
        if (pv < 0) {
            this.pv = 0;
        } else {
            this.pv = pv;
        }
    }

    /**
     * Establece el valor de ataque del personaje.<br>
     * Si el valor es negativo, se ajusta automáticamente a 0.
     *
     * @param atq Valor de ataque
     */
    public void setAtq(double atq) {
        if (atq < 0) {
            this.atq = 0;
        } else {
            this.atq = atq;
        }
    }

    /**
     * Establece el valor de armadura del personaje.<br>
     * Si el valor es negativo, se ajusta automáticamente a 0.
     *
     * @param arm Valor de armadura
     */
    public void setArm(double arm) {
        if (arm < 0) {
            this.arm = 0;
        } else {
            this.arm = arm;
        }
    }

    /**
     * Establece el nivel del personaje.<br>
     * El nivel debe estar entre 1 y 100.
     *
     * @param lvl Nivel del personaje
     */
    public void setNivel(int lvl) {
        if (lvl < 1 || lvl > 100) {
            System.err.println("Introduce un nivel válido para " + nombre + "\tInput: " + lvl);
        } else {
            nivel = lvl;
        }
    }

    /**
     * Establece la resistencia mágica del personaje.<br>
     * Si el valor es negativo, se ajusta automáticamente a 0.
     *
     * @param res Resistencia mágica
     */
    public void setRes(double res) {
        if (res < 0) {
            this.res = 0;
        } else {
            this.res = res;
        }
    }

    /**
     * Establece la velocidad del personaje.<br>
     * Si el valor es negativo, se ajusta automáticamente a 0.
     *
     * @param vel Velocidad
     */
    public void setVel(double vel) {
        if (vel < 0) {
            System.err.println("Los puntos de velocidad de " + nombre + " no pueden ser menor de 0");
            this.vel = 0;
        } else {
            this.vel = vel;
        }
    }

    /**
     * Establece si el personaje se encuentra defendiendo.
     *
     * @param defiende true si está defendiendo, false en caso contrario
     */
    public void setDefiende(boolean defiende) {
        this.defiende = defiende;
    }

    /**
     * Establece la alianza a la que pertenece el personaje.
     *
     * @param alianza Identificador de la alianza
     */
    public void setAlianza(int alianza) {
        this.alianza = alianza;
    }

    //endregion

    //region Getters

    /**
     * Obtiene el nombre del personaje
     *
     * @return Nombre del personaje
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene el nivel del personaje
     *
     * @return Nivel del personaje
     */
    public int getNivel() {
        return nivel;
    }

    /**
     * Obtiene la vida del personaje
     *
     * @return Vida del personaje
     */
    public double getPv() {
        return pv;
    }

    /**
     * Obtiene el ataque del personaje
     *
     * @return Ataque del personaje
     */
    public double getAtq() {
        return atq;
    }

    /**
     * Obtiene la armadura del personaje
     *
     * @return Armadura del personaje
     */
    public double getArm() {
        return arm;
    }

    /**
     * Obtiene la resistencia del personaje
     *
     * @return Resistencia del personaje
     */
    public double getRes() {
        return res;
    }

    /**
     * Obtiene la velocidad del personaje
     *
     * @return Velocidad del personaje
     */
    public double getVel() {
        return vel;
    }

    /**
     * Obtiene si el personaje es jugable o no.
     *
     * @return true si el personaje es jugador.
     */
    public boolean getEsJugador() {
        return esJugador;
    }

    /**
     * Obtiene si el personaje está defendiendo.
     *
     * @return true si el personaje está defendiendo
     */
    public boolean getDefiende() {
        return defiende;
    }

    /**
     * Obtiene la alianza del personaje
     *
     * @return Alianza del personaje
     */
    public int getAlianza() {
        return alianza;
    }

    /**
     * Obtiene los aliados del personaje
     *
     * @return String con todos los aliados del personaje
     */
    public String getAliados() {
        int aliadosAñadidos = 0;
        String aliadosString = "";
        for (int i = 0; i < personajes.length; i++) {
            if (this.esAliado(personajes[i]) && personajes[i] != this) {
                nAliados++;
            }
        }
        aliados = new Personaje[nAliados];
        for (int i = 0; i < personajes.length; i++) {
            if (this.esAliado(personajes[i]) && !personajes[i].equals(this)) {
                aliados[aliadosAñadidos] = personajes[i];
                aliadosAñadidos++;
                aliadosString += "" + "\t · " + aliadosAñadidos + "- " + aliados[aliadosAñadidos - 1].getNombre() + "\n";
            }
        }

        return aliadosString;

    }
    //endregion

    // region Overrides

    /**
     * Compara dos personajes por atributos.
     *
     * @param p Personaje a comparar
     * @return true si son equivalentes
     */
    public boolean equals(Personaje p) {
        return getNombre().equals(p.getNombre()) && getPv() == p.getPv() && getAtq() == p.getAtq() && getArm() == p.getArm() && getVel() == p.getVel() && getRes() == p.getRes() && getNivel() == p.getNivel();
    }

    /**
     * Representación textual del personaje.
     *
     * @return Información del personaje
     */
    public String toString() {
        return ("Nombre: " + getNombre() + "\n" + "Puntos de vida: " + getPv() + "\n" + "Ataque: " + getAtq() + "\n" + "Armadura: " + getArm() + "\n" + "Resistencia: " + getRes() + "\n" + "Velocidad: " + getVel() + "\n" + "Nivel: " + getNivel() + "\n" + "¿Es jugador?" + getEsJugador());
    }
    // endregion
}
