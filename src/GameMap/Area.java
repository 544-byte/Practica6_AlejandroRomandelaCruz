package GameMap;

import Characters.*;
import Combat.*;
import GameMap.*;
import Misc.*;

import java.util.Random;

/**
 * Representa un área del mapa del juego.<br>
 * Un área posee un nombre, un bioma y un nivel asociado que determina
 * la dificultad de los encuentros, las recompensas y la peligrosidad
 * de las trampas.
 */
public class Area {
    /**
     * Nivel del área, que determina su dificultad.
     */
    private int nivel;
    /**
     * Nombre del área.
     */
    private String name;
    /**
     * Bioma del área (Pradera, Jungla, Desierto, Montaña o Mazmorra).
     */
    private String bioma;
    /**
     * Trampa asociada al área.
     */
    private Trampa trampa;

    /**
     * Constructor por defecto.
     * Inicializa el área con valores básicos.
     */
    public Area() {
        name = "???";
        bioma = "Pradera";
        nivel = 1;
    }

    /**
     * Constructor que inicializa el área con un nombre y un bioma.
     * El nivel se genera automáticamente según el bioma.
     *
     * @param name  Nombre del área
     * @param bioma Bioma del área
     */
    public Area(String name, String bioma) {
        setName(name);
        setBioma(bioma);
        randomNivel();
    }

    /**
     * Constructor copia.
     *
     * @param area Área a copiar
     */
    public Area(Area area) {
        this.setName(area.getName());
        this.setBioma(area.getBioma());
        this.setNivel(area.getNivel());
    }

    /**
     * Obtiene el nivel del área.
     *
     * @return Nivel del área
     */
    public int getNivel() {
        return nivel;
    }

    /**
     * Obtiene el nombre del área.
     *
     * @return Nombre del área
     */
    public String getName() {
        return name;
    }

    /**
     * Obtiene el bioma del área.
     *
     * @return Bioma del área
     */
    public String getBioma() {
        return bioma;
    }

    /**
     * Genera aleatoriamente el nivel del área en función de su bioma.
     * <p>
     * Cada bioma tiene un rango de niveles específico.
     * </p>
     */
    public void randomNivel() {
        Random r = new Random();
        switch (bioma) {
            case "Pradera" -> nivel = r.nextInt(1, 10 + 1);
            case "Jungla" -> nivel = r.nextInt(11, 30 + 1);
            case "Desierto" -> nivel = r.nextInt(31, 60 + 1);
            case "Montaña" -> nivel = r.nextInt(61, 90 + 1);
            case "Mazmorra" -> nivel = r.nextInt(91, 105 + 1);
            default ->
                    System.err.println("No se ha establecido un nivel aleatorio para el área " + name + " porque tiene un nombre de bioma incorrecto \"" + bioma + "\"");

        }
    }

    /**
     * Establece el nivel del área respetando los límites del bioma.
     *
     * @param nivel Nivel del área
     */
    public void setNivel(int nivel) {
        if (!(nivel < 1 || nivel > 105)) {
            switch (bioma) {
                case "Pradera" -> {
                    if (nivel <= 10) this.nivel = nivel;
                }
                case "Jungla" -> {
                    if (nivel <= 30) this.nivel = nivel;
                }
                case "Desierto" -> {
                    if (nivel <= 60) this.nivel = nivel;
                }
                case "Montaña" -> {
                    if (nivel <= 90) this.nivel = nivel;
                }
                case "Mazmorra" -> this.nivel = nivel;
                default -> System.err.println("No hay un nombre de bioma adecuado: " + bioma);
            }
        } else {
            System.err.println("El nivel del área " + name + " debe ser un número entre 1 y 105 (se ha intentado " + nivel + ")");
        }
    }

    /**
     * Establece el nombre del área.
     * <p>
     * El nombre no puede estar vacío ni contener espacios.
     * </p>
     *
     * @param nameSetter Nombre del área
     */
    public void setName(String nameSetter) {
        if (nameSetter.isEmpty() || nameSetter.contains(" ")) {
            System.err.println("El nombre no puede estar vacío ni contener espacios");
        } else {
            name = nameSetter;
        }
    }

    /**
     * Establece el bioma del área.
     *
     * @param biomaSetter Bioma del área
     */
    public void setBioma(String biomaSetter) {
        if (!biomaSetter.equals("Pradera") && !biomaSetter.equals("Jungla") && !biomaSetter.equals("Desierto") && !biomaSetter.equals("Montaña") && !biomaSetter.equals("Mazmorra")) {
            System.err.println("El bioma introducido es incorrecto, ten en cuenta las mayúsculas.");
        } else {
            bioma = biomaSetter;
        }
    }

    /**
     * Genera una poción según el bioma del área.
     *
     * @return Valor de curación de la poción
     */
    public int generarPocion() {
        Random r = new Random();
        switch (bioma) {
            case "Pradera":
                return 15;
            case "Jungla":
                return r.nextInt(15, 25 + 1);
            case "Desierto":
                return r.nextInt(1, 30 + 1);
            case "Montaña":
                return 30;
            case "Mazmorra":
                int percent = r.nextInt(0, 100 + 1);
                if (percent <= 20) return 10;
                else return 20;
        }
        return -1;
    }

    /**
     * Genera una trampa en función del bioma del área.
     *
     * @param t Categoría de la trampa
     * @param p Perjuicio de la trampa
     * @param f Probabilidad de fracaso
     * @param a Área en la que se genera la trampa
     */
    public static void generaTrampa(String t, int p, double f, Area a) {
        Trampa trampa = new Trampa();
        switch (a.getBioma()) {
            case "Pradera", "Montaña":
                trampa.setCategoria(t);
                trampa.setPerjuicio(p);
                trampa.setFracaso(f);
                break;
            case "Jungla":
                trampa.setCategoria(t);
                trampa.setPerjuicio(p);
                switch (trampa.getCategoria()) {
                    case "Serpiente":
                        trampa.setFracaso(f - 10);
                        break;
                    default:
                        trampa.setFracaso(f);
                        break;
                }
            case "Desierto":
                trampa.setCategoria(t);
                trampa.setPerjuicio(p);
                switch (trampa.getCategoria()) {
                    case "Brea":
                        trampa.setFracaso(f - 15);
                        break;
                    default:
                        trampa.setFracaso(f);
                        break;
                }
            case "Mazmorra":
                trampa.setCategoria(t);
                switch (trampa.getCategoria()) {
                    case "Pinchos":
                        trampa.setPerjuicio(p + 5);
                        break;
                    default:
                        trampa.setPerjuicio(p);
                        break;
                }
                trampa.setFracaso(f);
                break;
        }
    }

    /**
     * Crea una copia del área.
     *
     * @return Copia del área
     */
    public Area clone() {
        Area clon = new Area();
        clon.setNivel(getNivel());
        clon.setName(getName());
        clon.setBioma(getBioma());
        return clon;
    }

    /**
     * Representación textual del área.
     *
     * @return Información del área
     */
    public String toString() {
        return ("Nombre: " + getName() + "\nBioma: " + getBioma() + "\nNivel: " + getNivel());
    }
}