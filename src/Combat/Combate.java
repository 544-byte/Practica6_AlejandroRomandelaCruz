package Combat;

import Characters.*;
import Combat.*;
import GameMap.*;
import Gear.Arma;
import Gear.Armadura;
import Gear.Artefacto;
import Gear.Equipamiento;
import Misc.*;

import Characters.Personaje;
import GameMap.Trampa;

import java.io.*;
import java.util.*;

/**
 * Clase encargada de gestionar los combates entre personajes.<br>
 * Controla el orden de los turnos según la velocidad, la ejecución
 * de los ataques y la determinación del ganador del combate.
 */
public class Combate {
    private static ArrayList<Equipamiento> tesoros = recuperarEquipamiento();
    private static final ArrayList<File> EQUIPAMIENTO_FILES = new ArrayList<>(Set.of(new File("/Ficheros/Tesoros/armadura.csv"),new File("/Ficheros/Tesoros/armas.csv"),new File("/Ficheros/Tesoros/artefactos.csv")));
    /**
     * Inicia un combate entre un jugador y un enemigo.<br>
     * El orden de los turnos se determina por la velocidad de los personajes.
     * El combate continúa hasta que uno de los dos muere.
     *
     * @param jugadores Personajes controlados por el jugador
     * @param enemigos Personajes enemigos
     */
    public static void combatir(ArrayList<Personaje> jugadores, ArrayList<Personaje> enemigos) {
        for (Personaje p : jugadores){
            p.setEsJugador();
        }
        Misc.ordenarPorNivel(jugadores);
        Misc.ordenarPorNivel(enemigos);
        ArrayList<Personaje> primero;
        ArrayList<Personaje> segundo;
        if (jugadores.getFirst().getVel() >= enemigos.getFirst().getVel()) {
            primero = jugadores;
            segundo = enemigos;
        } else {
            primero = enemigos;
            segundo = jugadores;
        }
        do {
            primero.realizarTurno(segundo);
            llamarTrampa(primero);
            if (!segundo.estaMuerto() && !primero.estaMuerto()) {
                segundo.realizarTurno(primero);
            }
        } while (!primero.estaMuerto() && !segundo.estaMuerto());
        if (segundo.estaMuerto()) imprimirGanador(primero);
        else imprimirGanador(segundo);
    }

    public static void otorgarPremio(Personaje personaje){
        Random r = new Random();
        int i = r.nextInt(0,tesoros.size());
        Misc.happen(personaje.getNombre() + " ha conseguido " + tesoros.get(i).getNombre());
        personaje.añadirEquipamiento(tesoros.remove(i));
    }

    /**
     * Imprime por consola el nombre del personaje ganador del combate.
     *
     * @param ganador Personaje que ha ganado el combate
     */
    public static void imprimirGanador(Personaje ganador) {
        Misc.happen(ganador.getNombre() + " ha ganado.");
    }

    /**
     * Intenta activar una trampa sobre un personaje.
     * <p>
     * Existe una probabilidad del 5% de que el personaje caiga en una trampa.
     * Si se activa, se genera una trampa aleatoria y se aplica al personaje.
     * </p>
     *
     * @param afectado Personaje que puede verse afectado por la trampa
     */
    private static void llamarTrampa(Personaje afectado) {
        Trampa trampa = new Trampa();
        Random r = new Random();
        if (r.nextInt(0, 100) < 60) {
            switch (r.nextInt(3)) {
                case 0 -> trampa.setCategoria("Pinchos");
                case 1 -> trampa.setCategoria("Brea");
                case 2 -> trampa.setCategoria("Serpientes");
            }
            trampa.setPerjuicio(r.nextInt(5, 20 + 1));
            trampa.setFracaso(r.nextDouble(0, 75 + 1));
            afectado.caerTrampa(trampa);
        }
    }

    /**
     * Este metodo sirve para coger to el equipamiento de los archivos armadura armas y artefactos y añadirlo a la coleccion estática de la clase.
     * @return Un arraylist con to el equipamiento
     */
    public static ArrayList<Equipamiento> recuperarEquipamiento(){
        ArrayList<Equipamiento> eqpmnt = new ArrayList<>();
        try {
            int i = 0;
            for (File f : EQUIPAMIENTO_FILES) {
                String l = "";
                BufferedReader br = new BufferedReader(new FileReader(f));
                br.readLine();
                while ((l = br.readLine()) != null){
                    ArrayList<String> linea;
                    if (l.split("\"")[1].contains(",")){
                        linea = new ArrayList<>(Set.of(l.split("\"")));
                        linea.remove(0);
                        String resto = linea.getLast();
                        linea.removeLast();
                        linea.addAll(Set.of(resto.split("\"")));
                    } else {
                        linea = new ArrayList<>(Set.of(l.split(",")));
                    }
                    switch (i) { // LO PODRIA HACER MUCHO MEJOR Y MODULARIZADO PERO COMO NO SE SI ME DA TIEMPO DE MOMENTO SE QUEDA ASI, SI SIGUE ASI ES PORQUE O SE ME HA OLVIDADO O NO HE PODIDO
                        case 0 -> {
                            ArrayList<String> estats = new ArrayList<>(Set.of(linea.get(4).split("-")));
                            HashMap<String,Integer> stats = new HashMap<>();
                            stats.put("Ar",Integer.parseInt(estats.get(0)));
                            stats.put("RM",Integer.parseInt(estats.get(1)));
                            stats.put("V",Integer.parseInt(estats.get(2)));
                            eqpmnt.add(new Armadura(linea.get(0),linea.get(1),linea.get(2),linea.get(3),stats,Integer.parseInt(linea.get(5))));
                        }
                        case 1 -> {
                            ArrayList<String> estats = new ArrayList<>(Set.of(linea.get(3).split("-")));
                            HashMap<String,Integer> stats = new HashMap<>();
                            stats.put("Fu",Integer.parseInt(estats.get(0)));
                            stats.put("Ve",Integer.parseInt(estats.get(1)));
                            stats.put("Ma",Integer.parseInt(estats.get(2)));
                            stats.put("Fe",Integer.parseInt(estats.get(3)));
                            eqpmnt.add(new Arma(linea.get(0),linea.get(1),linea.get(2),stats,Integer.parseInt(linea.get(4))));
                        }
                        case 2 -> {
                            ArrayList<String> estats = new ArrayList<>(Set.of(linea.get(3).split("-")));
                            HashMap<String,Integer> stats = new HashMap<>();
                            stats.put("Fu",Integer.parseInt(estats.get(0)));
                            stats.put("Ve",Integer.parseInt(estats.get(1)));
                            stats.put("Ma",Integer.parseInt(estats.get(2)));
                            stats.put("Fe",Integer.parseInt(estats.get(3)));
                            stats.put("Ar",Integer.parseInt(estats.get(4)));
                            stats.put("RM",Integer.parseInt(estats.get(5)));
                            stats.put("V",Integer.parseInt(estats.get(6)));
                            eqpmnt.add(new Artefacto(linea.get(0),linea.get(1),linea.get(2),stats,Integer.parseInt(linea.get(4))));
                        }
                    }
                    i++;
                }
                br.close();
            }
        } catch (IOException e) {
            System.err.println(e);
        }
        return eqpmnt;
    }
}
