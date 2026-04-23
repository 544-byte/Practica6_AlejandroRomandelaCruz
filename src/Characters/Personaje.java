package Characters;


import GameMap.*;
import Gear.Arma;
import Gear.Armadura;
import Gear.Artefacto;
import Gear.Equipamiento;
import Misc.Misc;

import java.io.File;
import java.util.*;

/**
 * Clase abstracta que representa un personaje del juego.<br>
 * Un personaje puede ser controlado por el jugador o por la IA y dispone de
 * atributos básicos como vida, ataque, defensa, resistencia, velocidad y nivel.
 * Además, gestiona turnos, alianzas, combate, trampas y acciones especiales.<br>
 * Esta clase es abstracta porque define un comportamiento base común,
 * pero deja ciertas acciones (como la acción especial) para que sean
 * implementadas por subclases concretas.
 */
public abstract class Personaje implements Comparable<Personaje> {
    // region Atributos
    private static Personaje[] personajes = new Personaje[0];
    Personaje[] aliados;
    int nAliados = 0;

    private int raza;
    private String nombre;
    private int nivel;
    private double pv, atq, vel, arm, res;
    private int alianza = -1;
    private boolean esJugador;

    private int turno;
    private boolean defiende;

    // arma
    private Arma arma = null;
    //armadura
    private ArrayList<Armadura> armadura = new ArrayList<>();
    //Artefactos
    private ArrayList<Artefacto> artefactos = new ArrayList<>();

    // endregion

    //region Constructores

    /**
     * Constructor por defecto.
     * Inicializa un personaje con valores básicos.
     */
    public Personaje() {
        raza = -1;
        nombre = "";
        pv = -1;
        atq = -1;
        arm = -1;
        res = -1;
        vel = -1;
        nivel = 0;
        esJugador = false;
        turno = 0;
        alianza = -1;
        añadirPersonaje(this);
    }

    /**
     * Constructor completo.
     */
    public Personaje(int raza, String nombre, int nivel, double pv, double atq, double arm, double res, double vel, int alianza, boolean esJugador) {
        setRaza(raza);
        setNombre(nombre);
        setNivel(nivel);
        setAtributos(pv,atq,arm,res,vel);
        turno = 0;
        setAlianza(alianza);
        this.esJugador = esJugador;
        añadirPersonaje(this);
    }

    /**
     * Constructor copia.
     *
     * @param p Personaje a copiar
     */
    public Personaje(Personaje p){
        setRaza(p.getRaza());
        setNombre(p.getNombre());
        setNivel(p.getNivel());
        setAtributos(p.getPv(),p.getAtq(),p.getArm(),p.getRes(),p.getVel());
        turno = 0;
        esJugador = getEsJugador();
        añadirPersonaje(this);
    }

    //endregion

    /**
     * Sube el nivel del personaje aumentando aleatoriamente sus atributos.
     */
    public void subirNivel() {
        setNivel(getNivel() + 1);
        Misc.happen(this.getNombre()+" Ha subido de nivel,");
        aumentarAtributo("pv",50);
        aumentarAtributo("atq",50);
        aumentarAtributo("arm",50);
        aumentarAtributo("res",50);
        aumentarAtributo("vel",50);
    } // souteeed

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
    } // souteeeed

    /**
     * Gestiona el turno del jugador mostrando opciones por consola.
     *
     * @param enemigo Enemigo al que se enfrenta
     */
    public void realizarTurnoJugador(Personaje enemigo) {
        int opt = 0;
        Scanner scan = new Scanner(System.in);
        System.out.println(Misc.opcionesJugador("Elige una opción:\n" + "1- Atacar\n" + "2- " + getAccionEspecial() + "\n" + "3- Defender\n" + "4- Pasar turno\n" + "5- Observar"));
        do {
            System.out.print("Opción: ");
            opt = Misc.leerOpt();
            if (opt < 1 | opt > 5) {
                System.err.println("Opción incorrecta, ingresela de nuevo.");
            }
        } while (opt < 1 | opt > 5);
        realizarTurnoJugador(enemigo,opt);
    } // souteeeed

    public void realizarTurnoJugador(Personaje enemigo, int opt) {
        Scanner scan = new Scanner(System.in);
        switch (opt) {
            case 1 -> {
                Misc.info(this.getNombre() + " ha elegido atacar,");
                ataca(getAtq(), enemigo, false);
            }
            case 2 -> {
                Misc.info(this.getNombre() + " ha elegido " + this.getAccionEspecial());
                this.accionEspecial(enemigo);
            }
            case 3 -> {
                this.setDefiende(true);
                this.setArm(this.getArm() * 1.2);
                this.setRes(this.getRes() * 1.2);
                Misc.happen(this.getNombre() + " ha subido sus defensas.");
                Misc.info(this.getNombre() + " se defiende durante el próximo turno.");
            }
            case 4 -> {
                Misc.happen(this.getNombre() + " mira muy desafiante a su enemigo, no se que habrá intentado hacer...");
                Misc.info(this.getNombre() + " ha decidido no hacer absolutamente nada, let him cook.");
            }
            case 5 -> {
                System.out.println("\t· 1- Observar enemigo\n" + "\t· 2- Observarte\n" + "\t· 3- Ver aliados");
                System.out.print("Opción: ");
                opt = Misc.leerOpt();
                switch (opt) {
                    case 1 -> {
                        Misc.happen(this.getNombre() + " observa a " + enemigo.getNombre());
                        System.out.println(Misc.estadisticasJugador(enemigo.toString()));
                        realizarTurnoJugador(enemigo);
                    }
                    case 2 -> {
                        Misc.happen(this.getNombre() + " se observa, parece que está disociando un poco");
                        System.out.println(Misc.estadisticasJugador(this.toString()));
                        realizarTurnoJugador(enemigo);
                    }
                    case 3 -> {
                        updateAliados();
                        for (Personaje aliado : aliados ) {
                            if (aliado != null) {
                                Misc.happen(this.getNombre() + " observa a su aliado " + aliado.getNombre());
                                System.out.println(Misc.estadisticasJugador(aliado.toString()));
                            }
                        }
                        realizarTurnoJugador(enemigo);
                    }
                    default -> {
                        System.out.println("Opción no contemplada, elige una opción correcta.");
                        realizarTurnoJugador(enemigo,5);
                    }
                }
            }
        }
    } //souteeed

    /**
     * Realiza un ataque contra un enemigo.
     *
     * @param atq        Cantidad de ataque
     * @param enemigo    Objetivo del ataque
     * @param dañoMagico Indica si el daño es mágico
     */
    public void ataca(double atq, Personaje enemigo, boolean dañoMagico)  {
        if (enemigo.defender(atq, dañoMagico) <= 0) {
            Misc.happen(enemigo.getNombre() + " es tan vigoroso que " + nombre + " ha sido incapaz de penetrar sus defensas.");
            atacar(enemigo, dañoMagico);
        } else {
            if (enemigo.getPv() - (atq - enemigo.getArm()) <= 0) {
                atacar(enemigo, dañoMagico);
                Misc.happen(nombre + " ha inflingido " + enemigo.defender(this.getAtq(), dañoMagico) + " puntos de daño a " + enemigo.getNombre() + " dejándolo con " + enemigo.getPv() + " puntos de vida acabando por completo con su existencia.");

            } else {
                atacar(enemigo, dañoMagico);
                Misc.happen(nombre + " ha inflingido " + enemigo.defender(this.getAtq(), dañoMagico) + " puntos de daño a " + enemigo.getNombre() + " dejándolo con " + enemigo.getPv() + " puntos de vida.");

            }
        }
        if (enemigo.getDefiende()) {
            enemigo.setDefiende(false);
            enemigo.setArm(enemigo.getArm() / 1.2);
            enemigo.setRes(enemigo.getRes() / 1.2);
            Misc.happen("Se bajan las defensas de " + enemigo.getNombre());
        }
    } //SOUTEADOOOOOOOO

    /**
     * Aplica el daño efectivo al enemigo.
     */
    public void atacar(Personaje enemigo, boolean dañoMagico) {
        enemigo.setPv(enemigo.getPv() - enemigo.defender(this.getAtq(), dañoMagico));
    }           //souted

    /**
     * Calcula el daño recibido tras aplicar defensas.
     *
     * @param atq        Ataque recibido
     * @param dañoMagico Indica si el daño es mágico
     * @return Daño final
     */
    public double defender(double atq, boolean dañoMagico) {
        if (dañoMagico) {

            if ((atq - getRes()) < 0) {
                return 0;
            }
            else {
                return (atq - getRes());
            }
        } else {
            if ((atq - getArm()) < 0) {
                return 0;
            }
            else {
                return (atq - getArm());
            }
        }
    }       //souted

    /**
     * Permite que nuestra vida aumente según la poción siempre y cuando nuestra vida esté por debajo de 30
     *
     * @param pocion los puntos de vida que se van a sumar
     */
    public void beberPocion(int pocion) {
        if (getPv() <= 30) {
            setPv(getPv() + pocion);
            Misc.happen(this.getNombre() + " ha bebido una poción aumentando su vida en " + pocion + " puntos, ahora tiene " + this.getPv() + " puntos de vida.");
        } else {
            Misc.happen(getNombre() + " ha querido beber una poción pero se ha arrepentido a mitad de camino porque tiene muuuuucha vida... (" + this.getPv() + ")");
        }
    }   //souted

    /**
     * Cuando un personaje cae en una trampa y sale ileso se inspira con la cantidad corerspondiente
     *
     * @param cantidad Los puntos de inspiración
     * @param tipo     Indica que tipo de inspiración es
     */
    public void inspirar(int cantidad, String tipo) {
        switch (tipo.toLowerCase()){
            case "ataque" -> setAtq(getAtq() + cantidad);
            case "armadura" -> setArm(getArm() + cantidad);
            case "vida" -> setPv(getPv() + cantidad);
            default -> System.err.println("Introduce un tipo válido");
        }
    }   //souted

    /**
     * Indica si el personaje está muerto.
     *
     * @return true si los puntos de vida son 0 o menores
     */
    public boolean estaMuerto() {
        return getPv() <= 0;
    }           //souted

    /**
     * Hace a personaje caer en una trampa, puede salir ileso o no...
     *
     * @param t La trampa en la que cae el personaje
     */
    public void caerTrampa(Trampa t) {
        String trampaCategoria = t.getCategoria();
        switch (trampaCategoria) {
            case "Pinchos" -> {
                if (t.activaTrampa() != 0){
                    Misc.happen("Estacas afiladas salen de las superficies cercanas y atraviesan a " + nombre + " por " + t.getPerjuicio() + " puntos de daño.");
                    setPv(getPv() - t.getPerjuicio());
                } else {
                    Misc.happen("Estacas afiladas salen de las superficies cercanas, pero " + nombre + " es muy avispado y las sortea, inspirandose con " + t.getPerjuicio() + " puntos de vida.");
                    setPv(getPv() + t.getPerjuicio());
                }

            }
            case "Brea" -> {
                if (t.activaTrampa() != 0){
                    Misc.happen("Aceite viscoso cae de pronto sobre " + nombre + " impidiéndole moverse con libertad.");
                    setArm(getArm() - t.getPerjuicio());
                } else {
                    Misc.happen("Aceite viscoso cae de pronto justo delante de " + nombre + " esta suerte le da tanto alivio que aumenta su armadura en " + t.getPerjuicio() + " puntos.");
                    setArm(getArm() + t.getPerjuicio());
                }
            }
            case "Serpientes" -> {
                if (t.activaTrampa() != 0){
                    Misc.happen("Un nido de víboras y culebras aparece frente a " + nombre + ". La visión es tan terrorífica que pierde las ganas de continuar.");
                    setAtq(getAtq() - t.getPerjuicio());
                } else {
                    Misc.happen("Un nido de víboras y culebras aparece frente a " + nombre + ". Por suerte quieren ayudarle y recorren sus brazos para hacer " + t.getPerjuicio() + " puntos de daño extra.");
                    setAtq(getAtq() + t.getPerjuicio());
                }
            }
        }
    }   //souted

    /**
     * Ejecuta la acción especial del personaje.<br>
     * Debe ser sobrescrita por las subclases.
     *
     * @param enemigo Enemigo objetivo
     */
    public abstract void accionEspecial(Personaje enemigo);

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
                aliadosString += "\t · " + aliadosAñadidos + "- " + aliados[aliadosAñadidos - 1].getNombre() + "\n";
            }
        }

    }

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
     * Un metodo que compara la clase recibida por parámetros
     * (está pensado para pasar el array[0] del csv de un personaje)
     * y devolver un true en caso correcto
     * (Pensado para usarse en el constructor por csv del personaje)
     * @param clase Recibe por parámetro el nombre de la clase a checkear.
     * @return true si es la misma clase false en caso contrario
     */
    public boolean esClase(String clase){
        return clase.toLowerCase().equals(this.getClass().getSimpleName().toLowerCase());
    }

    /**
     * metodo del ejercicio 4
     * Comprueba si un personaje tiene el mismo nobmre que el de la ficha y si eso es correcto, vuelca todos los atributos de la ficha en el personaje.
     * @param ficha la ficha que se va a usar para actualizar el personaje
     * @throws Exception Excepción de importarPersonaje
     */
    public void updateCharacter(File ficha) throws Exception {
        if (!Misc.importarPersonaje(ficha).getCSV().split(":")[2].equals(this.getNombre())) return;
        Personaje p = Misc.importarPersonaje(ficha);
        if (this.equals(Misc.importarPersonaje(ficha))){
            this.setRaza(p.getRaza());
            this.setNivel(p.getNivel());
            this.setAtributos(p.getPv(),p.getAtq(),p.getArm(),p.getRes(),p.getVel());
            if (!getAtributoEspecial().equals("")){
                this.setAtributoEspecial(this.getAtributo());
            }
            this.setAlianza(p.getAlianza());
            if (p.getEsJugador()){
                this.setEsJugador();
            }
        }
    }

    //region Setters

    /**
     * Calcula el porcentaje y si sale true devuelve 1 (que se suma con el getAtributo correspondiente dentro del setAtributo correspondiente)
     * Se usa con el metodo de subir nivel
     * @param prcnt El porcentaje de probabilidad de subir
     */
    public void aumentarAtributo(String atr,int prcnt) {
        aumentarAtributo(atr,prcnt,1);
        // Para los atributos extra llamar super + personalizado
    }

    /**
     * Sobrecarga de aumentarAtributo(prcnt) que añade un int cantidad para elegir la cantidad que se suma.
     * @param cantidad La subida del atributo que se va a hacer en caso positivo
     * @param prcnt El porcentaje de probabilidad de subir
     * @return cantidad si el cálculo del porcentaje es positivo y 0 en su defecto
     */
    public void aumentarAtributo(String atr,int prcnt, double cantidad){
        Random r = new Random();
        if (r.nextInt(100) < prcnt)
            switch (atr) {
                case "pv" -> {
                    setPv(getPv() + cantidad);
                    Misc.happen(this.getNombre() + " ha aumentado sus puntos de vida " + cantidad + " puntos, dejandolo con " + getPv() + " puntos de vida");
                }
                case "atq" -> {
                    setAtq(getAtq() + cantidad);
                    Misc.happen(this.getNombre() + " ha aumentado sus puntos de ataque " + cantidad + " puntos, dejandolo con " + getAtq() + " puntos de ataque");
                }
                case "arm" -> {
                    setArm(getArm() + cantidad);
                    Misc.happen(this.getNombre() + " ha aumentado sus puntos de armadura " + cantidad + " puntos, dejandolo con " + getArm() + " puntos de armadura");
                }
                case "res" -> {
                    setRes(getRes() + cantidad);
                    Misc.happen(this.getNombre() + " ha aumentado sus puntos de resistencia " + cantidad + " puntos, dejandolo con " + getRes() + " puntos de resistencia");
                }
                case "vel" -> {
                    setVel(getVel() + cantidad);
                    Misc.happen(this.getNombre() + " ha aumentado sus puntos de velocidad " + cantidad + " puntos, dejandolo con " + getVel() + " puntos de velocidad");
                }
                // Para los atributos extra llamar super + personalizado
                /*
                if (atr.equals("ATRIBUTO")){
                    setATRIBUTO(getATRIBUTO() + cantidad);
                    Misc.happen(this.getNombre() + " ha aumentado sus puntos de ATRIBUTO " + cantidad + " puntos, dejandolo con " + getATRIBUTO() + " puntos de ATRIBUTO");
                }
                 */
            }
    }

    /**
     * Sobrecarga de aumentarAtributo que en vez de subir una cierta cantidad calcula sobre un mínimo y un máximo
     * @param prcnt Calcula un random para saber si sube o no de nivel usando el prcnt como límite
     * @param min La subida mínima posible
     * @param max La subida máxima posible
     * @return
     */
    public void aumentarAtributo(String atr,int prcnt, int min, int max){
        Random r = new Random();
        double cantidad = r.nextInt(min,max);
        aumentarAtributo(atr,prcnt,cantidad);
    }



    public void setRaza(int raza) {
        if (raza >=1 && raza <=5){
            this.raza = raza;
        } else {
            Misc.alert(Misc.formato(Misc.RojoB,"Indica una Raza válida"));
        }
    }

    /**
     * Establece el nombre del personaje.<br>
     * El nombre debe tener al menos 2 caracteres, no estar vacío
     * y no contener la cadena reservada "GM".
     *
     * @param nombre Nombre del personaje
     */
    public void setNombre(String nombre) {
        if (nombre.isBlank() || nombre.contains("GM") || nombre.length() < 2 && nombre.contains(":")) {
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

    /**
     * Hace que el personaje se convierta en un personaje jugable.
     */
    public void setEsJugador() {
        esJugador = true;
        setAlianza(0);
    }

    public void setEsJugador(boolean b) {
        esJugador = b;
        setAlianza(0);
    }

    public void setAtributoEspecial(double atributo){

    }

    public void setArmadura(ArrayList<Armadura> armadura) {
        this.armadura = new ArrayList<>(armadura);
    }

    public void setArtefactos(ArrayList<Artefacto> artefactos) {
        this.artefactos = new ArrayList<>(artefactos);
    }

    public boolean setArma(Arma arma) {
        if (this.arma == null) {
            this.arma = arma;
            Misc.happen(getNombre() + " se ha equipado " + arma.getNombre());
            return true;
        } else {
            Misc.alert(getNombre() + " ha intentado equiparse " + arma.getNombre() + " pero no ha podido porque ya tiene equipado " + this.arma.getNombre());
            return false;
        }
    }

    public boolean addArmadura(Armadura armadura) {
        ArrayList<String> piezasArmadura = new ArrayList<>();
        for (Armadura a : this.armadura){
            piezasArmadura.add(a.getTipo());
        }
        if (piezasArmadura.contains(armadura.getTipo())) {
            Misc.alert(getNombre() + " ha intentado equiparse " + armadura.getNombre() + " pero no ha podido ya que tiene " + armadura.getTipo() + " equipado.");
            return false;
        } else {
            this.armadura.add(armadura);
            Misc.happen(getNombre() + " se ha equipado " + armadura.getNombre());
            return true;
        }
    }

    public boolean addArtefacto(Artefacto artefacto) {
        if (artefactos.size() <= 1) {
            artefactos.add(artefacto);
            Misc.happen(getNombre() + " se ha equipado " + artefacto.getNombre());
            return true;
        } else if (artefactos.size() >= 3){
            Misc.alert(getNombre() + " ha intentado equiparse " + artefacto.getNombre() + " pero no ha podido ya que no tenía espacios libres.");
            return false;
        } else {
            for (Artefacto a : artefactos) {
                if (a.getTipo().equals("Amuleto")) {
                    if (artefacto.getTipo().equals("Amuleto")) {
                        Misc.alert(getNombre() + " no se puede equipar más de un amuleto.");
                        return false;
                    } else {
                        artefactos.add(artefacto);
                        Misc.happen(getNombre() + " se ha equipado " + artefacto.getNombre());
                        return true;
                    }
                } else {
                    artefactos.add(artefacto);
                    Misc.happen(getNombre() + " se ha equipado " + artefacto.getNombre());
                    return true;
                }
            }
            return false;
        }
    }

    public Equipamiento añadirEquipamiento(Equipamiento e) {
        if (Arma.getTipos().contains(e.getTipo())){
            if (setArma((Arma) e)) return null;
            else return e;
        } else if (Armadura.getTipos().contains(e.getTipo())){
            if (addArmadura((Armadura)e)) return null;
            else return e;
        } else if (Artefacto.getTipos().contains(e)){
            if (addArtefacto((Artefacto)e)) return null;
            else return e;
        }
        return e;
    }

    public Arma removeArma(Arma arma){
        if (this.arma.equals(arma)){
            Arma armaRemovida = new Arma(this.arma);
            this.arma = null;
            return armaRemovida;
        } else {
            return null;
        }
    }

    public Armadura removeArmadura(Armadura armadura){
        if (this.armadura.contains(armadura)){
            this.armadura.remove(armadura);
            return armadura;
        } else {
            return null;
        }
    }

    public Artefacto removeArtefacto(Artefacto artefacto){
        if (this.artefactos.contains(artefacto)){
            this.artefactos.remove(artefacto);
            return artefacto;
        } else {
            return null;
        }
    }



    //endregion

    //region Getters

    public int getRaza() {
        return raza;
    }

    public String getRazaName() {
        return getRazaName(getRaza());
    }

    public String getRazaName(int raza) {
        switch (raza){
            case -1 -> {
                return "Raza no inicializada";
            }
            case 1 -> {
                return "Humano";
            }
            case 2 -> {
                return "Enano";
            }
            case 3 -> {
                return "Elfo";
            }
            case 4 -> {
                return "Pandaren";
            }
            case 5 -> {
                return "Kenku";
            }
            default -> {
                Misc.alert(Misc.formato(Misc.RojoB,"La raza asignada es errónea."));
                return "Aquí algo no cuadra...";
            }
        }
    }

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
        double vida = pv;
        for (Equipamiento e : armadura){
            vida += e.recuperaEstadistica("V");
        }
        for (Equipamiento e : artefactos){
            vida += e.recuperaEstadistica("V");
        }
        return vida;
    }

    /**
     * Obtiene el ataque del personaje
     *
     * @return Ataque del personaje
     */
    public double getAtq() {
        double ataque = atq;
        if (arma != null) {
            ataque += arma.recuperaEstadistica("Fu");
        }
        for (Equipamiento e : artefactos){
            ataque += e.recuperaEstadistica("Fu");
        }
        return ataque;
    }

    /**
     * Obtiene la armadura del personaje
     *
     * @return Armadura del personaje
     */
    public double getArm() {
        double armadur = arm;
        for (Equipamiento e : armadura){
            armadur += e.recuperaEstadistica("Ar");
        }
        for (Equipamiento e : artefactos){
            armadur += e.recuperaEstadistica("Ar");
        }
        return armadur;
    }

    /**
     * Obtiene la resistencia del personaje
     *
     * @return Resistencia del personaje
     */
    public double getRes() {
        double resmag = res;
        for (Equipamiento e : armadura){
            resmag += e.recuperaEstadistica("RM");
        }
        for (Equipamiento e : artefactos){
            resmag += e.recuperaEstadistica("RM");
        }
        return resmag;
    }

    /**
     * Obtiene la velocidad del personaje
     *
     * @return Velocidad del personaje
     */
    public double getVel() {
        double velocidad = vel;
        if (arma != null) {
            velocidad += arma.recuperaEstadistica("Ve");
            for (Equipamiento e : artefactos) {
                velocidad += e.recuperaEstadistica("Ve");
            }
        }
        return velocidad;
    }

    /**
     * Devuelve el nombre de la acción especial.
     *
     * @return Nombre de la acción especial
     */
    public String getAccionEspecial() {
        return "Acción especial";
    }

    public double getAtributo(){
        return -1;
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
     * Obtiene si el personaje es jugable o no.
     *
     * @return true si el personaje es jugador.
     */
    public boolean getEsJugador() {
        return esJugador;
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

    /**
     * Obtiene si el personaje está defendiendo.
     *
     * @return true si el personaje está defendiendo
     */
    public boolean getDefiende() {
        return defiende;
    }

    /**
     * Metodo que devuelve un string con los valores separados por ":" para usarlo luego y guardarlo en un .csv tanto para importar como para exportar.
     * @return String con los atributos comúnes entre todos los personajes separados por ":"
     */
    public String getCSV() {
        // Clase,Raza,Nombre,Nivel,PV,ATQ,ARM,RES,VEL,ESP,ALIANZA,ESJUGADOR
        return this.getClass().getSimpleName() + ":" + getRaza() + ":" + getNombre() + ":" + getNivel() + ":" + getPv() + ":" + getAtq() + ":" + getArm() + ":" + getRes() + ":" + getVel();
    }

    public String getAtributoEspecial(){
        return "";
    };

    public ArrayList<Artefacto> getArtefactos() {
        return artefactos;
    }

    public ArrayList<Armadura> getArmadura() {
        return armadura;
    }

    public Arma getArma() {
        return arma;
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
        return p.getCSV().equals(this.getCSV());

        //return getNombre().equals(p.getNombre()) && getPv() == p.getPv() && getAtq() == p.getAtq() && getArm() == p.getArm() && getVel() == p.getVel() && getRes() == p.getRes() && getNivel() == p.getNivel();
    }

    /**
     * Representación textual del personaje.
     *
     * @return Información del personaje
     */
    public String toString() {
        String [] stats = this.getCSV().split(":");
        String toString = ("Clase: " + stats[0] +
                "\nRaza: " + getRazaName(Integer.parseInt(stats[1])) +
                "\nNombre: " + stats[2] +
                "\nNivel: " + stats[3] +
                "\nVida: " + stats[4] +
                "\nAtaque: " + stats[5] +
                "\nArmadura: " + stats[6] +
                "\nResistencia: " + stats[7] +
                "\nVelocidad: " + stats[8]);

        if (!getAtributoEspecial().equals("")){
            toString = toString.concat("\n"+getAtributoEspecial() + ": " + stats[9]);
        }

        toString = toString.concat("\nAlianza: " + stats[10] +
                "\n¿Es Jugador? " + stats[11]);
        return toString;
    }

    public int compareTo(Personaje personaje){
        return Double.compare(personaje.getVel(),this.getVel());
    }
    // endregion
}
