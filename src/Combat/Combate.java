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
    private static final ArrayList<File> EQUIPAMIENTO_FILES = new ArrayList<>(Arrays.asList(
            new File("Ficheros/Tesoros/armadura.csv"),
            new File("Ficheros/Tesoros/armas.csv"),
            new File("Ficheros/Tesoros/artefactos.csv")
    )
    );
    private static ArrayList<Equipamiento> tesoros = recuperarEquipamiento();

    /**
     * Inicia un combate entre un grupo de jugadores y enemigos.<br>
     * Los personajes se ordenan por nivel y se enfrentan en duelos individuales
     * hasta que uno de los dos equipos es eliminado por completo.
     * Al finalizar, si el jugador gana, se reparten tesoros entre los supervivientes.
     *
     * @param jugadores lista de personajes controlados por el usuario
     * @param enemigos lista de personajes enemigos
     */
    public static void combatir(ArrayList<Personaje> jugadores, ArrayList<Personaje> enemigos) {
        Misc.ordenarPorNivel(jugadores);
        Misc.ordenarPorNivel(enemigos);
        int nEnemigos = enemigos.size();
        while (!jugadores.isEmpty() && !enemigos.isEmpty()) {
            Personaje eliminado = bucleJugable(jugadores.getFirst(),enemigos.getFirst());
            if (jugadores.contains(eliminado)) {
                jugadores.remove(eliminado);
            }
            else {
                enemigos.remove(eliminado);
            }
        }
        if (jugadores.isEmpty()) {
            imprimirGanador(enemigos.getFirst());
        }
        else {
            imprimirGanador(jugadores.getFirst());
            int i;
            for (i = 0; i < nEnemigos; i++){
                Equipamiento e;
                int j = i;
                if ((e = otorgarPremio(jugadores.get(i))) != null) {
                    while ((e = otorgarPremio(jugadores.get(j), e)) != null && j+1 < jugadores.size()){
                        j++;
                    }

                }
            }
        }

    }

    /**
     * gestiona el duelo 1 contra 1 entre un jugador y un enemigo
     * @param jugador personaje del bando del jugador
     * @param enemigo personaje del bando enemigo
     * @return el personaje que ha sido derrotado (ha muerto)
     */
    public static Personaje bucleJugable(Personaje jugador, Personaje enemigo){
        jugador.setEsJugador();
        Personaje primero;
        Personaje segundo;
        if (jugador.getVel() >= enemigo.getVel()) {
            primero = jugador;
            segundo = enemigo;
        } else {
            primero = enemigo;
            segundo = jugador;
        }
        do {
            primero.realizarTurno(segundo);
            llamarTrampa(primero);
            if (!segundo.estaMuerto() && !primero.estaMuerto()) {
                segundo.realizarTurno(primero);
            }
        } while (!primero.estaMuerto() && !segundo.estaMuerto());
        if (segundo.estaMuerto()) {
            Misc.happen(segundo.getNombre() + " ha muerto a manos de " + primero.getNombre());
            return segundo;
        }
        else {
            Misc.happen(primero.getNombre() + " ha muerto a manos de " + segundo.getNombre());
            return primero;
        }
    }

    /**
     * selecciona un equipamiento aleatorio de la lista de tesoros y trata de dárselo a un personaje
     * @param personaje personaje que recibirá el premio
     * @return el equipamiento si no pudo ser equipado, null si tuvo éxito
     */
    public static Equipamiento otorgarPremio(Personaje personaje){
        Random r = new Random();
        int i = r.nextInt(0,tesoros.size());
        return otorgarPremio(personaje,tesoros.get(i));
    }

    /**
     * intenta equipar un objeto específico a un personaje y lo elimina de la lista global de tesoros si lo logra
     * @param personaje personaje que recibirá el objeto
     * @param e equipamiento a entregar
     * @return el equipamiento e si no se pudo equipar, null si se equipó correctamente
     */
    public static Equipamiento otorgarPremio(Personaje personaje, Equipamiento e){
        if (personaje.añadirEquipamiento(e) != null){
            Misc.info(personaje.getNombre() + " no se ha podido equipar " + e.getNombre());
            return e;
        } else {
            tesoros.remove(e);
            return null;
        }
    }

    /**
     * Imprime por consola el nombre del personaje ganador del combate.
     *
     * @param ganador Personaje que ha ganado el combate
     */
    public static void imprimirGanador(Personaje ganador) {
        Misc.happen("Ha ganado el equipo de " + ganador.getNombre() + ".");
    }

    /**
     * Intenta activar una trampa sobre un personaje.
     * <p>
     * Existe una probabilidad del 60% de que el personaje caiga en una trampa.
     * Si se activa, se genera una trampa aleatoria (Pinchos, Brea o Serpientes) y se aplica al personaje.
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
            for (File f : EQUIPAMIENTO_FILES) {
                String l = "";
                BufferedReader br = new BufferedReader(new FileReader(f));
                l = br.readLine();
                String [] estadisticas = l.split("\\(")[1].split("\\)")[0].split("-");
                int statsIndex = -1;
                int i = 0;
                for (String campo : l.split(",")){
                    if (campo.contains(l.split("\\(")[1].split("\\)")[0])){
                        statsIndex = i;
                    } else i++;
                }
                while ((l = br.readLine()) != null){
                    ArrayList<String> linea;
                    if (l.split("\"").length > 1 && l.split("\"")[1].contains(",")){
                        linea = new ArrayList<>(Arrays.asList(l.split("\"")));
                        linea.remove(0);
                        String resto = linea.getLast();
                        linea.removeLast();
                        resto = resto.substring(1);
                        linea.addAll(Arrays.asList(resto.split(",")));
                    } else {
                        linea = new ArrayList<>(Arrays.asList(l.split(",")));
                    }
                    ArrayList<Integer> estats = new ArrayList<>();
                    for (String s : linea.get(i).split("-")){
                        estats.add(Integer.parseInt(s));
                    }
                    HashMap<String,Integer> stats = new HashMap<>();
                    int j;
                    for (j = 0; j < estats.size();j++){
                        stats.put(estadisticas[j],estats.get(j));
                    }
                    if (estats.size() < estadisticas.length){
                        for (j = estats.size(); j < estadisticas.length;j++){
                            stats.put(estadisticas[j],0);
                        }
                    }
                    switch (f.getName()){
                        case "armadura.csv" -> eqpmnt.add(new Armadura(linea.get(0),linea.get(1),linea.get(2),linea.get(3),stats,Integer.parseInt(linea.get(5))));
                        case "armas.csv" -> eqpmnt.add(new Arma(linea.get(0),linea.get(1),linea.get(2),stats,Integer.parseInt(linea.get(4))));
                        case "artefactos.csv" -> eqpmnt.add(new Artefacto(linea.get(0),linea.get(1),linea.get(2),stats,Integer.parseInt(linea.get(4))));
                    }
                }
                br.close();
            }
        } catch (IOException e) {
            System.err.println(e);
        }
        return eqpmnt;
    }
}