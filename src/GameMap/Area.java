package GameMap;

import Characters.*;
import Combat.*;
import GameMap.*;
import Misc.*;

import java.util.Random;

public class Area {
    private int nivel;
    private String name;
    private String bioma;
    private Trampa trampa;

    public Area() {
        name = "???";
        bioma = "Pradera";
        nivel = 1;
    }

    public Area(String name, String bioma) {
        setName(name);
        setBioma(bioma);
        randomNivel();
    }

    public Area(Area area) {
        this.setName(area.getName());
        this.setBioma(area.getBioma());
        this.setNivel(area.getNivel());
    }

    public int getNivel() {
        return nivel;
    }

    public String getName() {
        return name;
    }

    public String getBioma() {
        return bioma;
    }

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

    public void setName(String nameSetter) {
        if (nameSetter.isEmpty() || nameSetter.contains(" ")) {
            System.err.println("El nombre no puede estar vacío ni contener espacios");
        } else {
            name = nameSetter;
        }
    }

    public void setBioma(String biomaSetter) {
        if (!biomaSetter.equals("Pradera") && !biomaSetter.equals("Jungla") && !biomaSetter.equals("Desierto") && !biomaSetter.equals("Montaña") && !biomaSetter.equals("Mazmorra")) {
            System.err.println("El bioma introducido es incorrecto, ten en cuenta las mayúsculas.");
        } else {
            bioma = biomaSetter;
        }
    }

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

    public Area clone() {
        Area clon = new Area();
        clon.setNivel(getNivel());
        clon.setName(getName());
        clon.setBioma(getBioma());
        return clon;
    }

    public String toString() {
        return ("Nombre: " + getName() + "\nBioma: " + getBioma() + "\nNivel: " + getNivel());
    }
}




    /*
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        byte opt;
        String name;
        String bioma = null;
        System.out.println("Si en cualquier momento escribes Salir se terminará el programa.");
        do {
            System.out.println("Introduce un nombre");
            name = scan.nextLine();
            if (!name.equals("Salir")) {
                do {
                    System.out.println("Introduce uno de los siguientes biomas:\n-Pradera\n-Jungla\n-Desierto\n-Montaña\n-Mazmorra");
                    bioma = scan.nextLine();
                    if (!bioma.equals("Pradera") && !bioma.equals("Jungla") && !bioma.equals("Desierto") && !bioma.equals("Montaña") && !bioma.equals("Mazmorra") && !bioma.equals("Salir")) {
                        System.err.println("El bioma introducido es incorrecto, ten en cuenta las mayúsculas.");
                    } else {
                        switch (bioma) {
                            case "Pradera":
                                System.out.println("Tierra fértil y tranquila, hogar de aldeanos y criaturas pacíficas.");
                                break;
                            case "Jungla":
                                System.out.println("Selva densa y húmeda, llena de vida y misterios ocultos.");
                                break;
                            case "Desierto":
                                System.out.println("Extensión árida y hostil, donde solo los fuertes sobreviven.");
                                break;
                            case "Montaña":
                                System.out.println("Picos fríos y escarpados, refugio de bestias y antiguos secretos.");
                                break;
                            case "Mazmorra":
                                System.out.println("Oscuro laberinto subterráneo repleto de trampas y peligros.");
                                break;
                        }
                    }
                } while (!bioma.equals("Pradera") && !bioma.equals("Jungla") && !bioma.equals("Desierto") && !bioma.equals("Montaña") && !bioma.equals("Mazmorra") && !bioma.equals("Salir"));
            }
        } while (!name.equals("Salir") && !bioma.equals("Salir"));
        System.out.println("Saliendo del programa . . . ");
    } */
