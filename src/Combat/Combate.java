package Combat;

import Characters.*;
import Combat.*;
import GameMap.*;
import Misc.*;

import Characters.Personaje;
import GameMap.Trampa;

import java.util.Random;

/**
 * Clase encargada de gestionar los combates entre personajes.<br>
 * Controla el orden de los turnos según la velocidad, la ejecución
 * de los ataques y la determinación del ganador del combate.
 */
public class Combate {
    /**
     * Inicia un combate entre un jugador y un enemigo.<br>
     * El orden de los turnos se determina por la velocidad de los personajes.
     * El combate continúa hasta que uno de los dos muere.
     *
     * @param jugador Personaje controlado por el jugador
     * @param enemigo Personaje enemigo
     */
    public static void combatir(Personaje jugador, Personaje enemigo) {
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
                segundo.realizarTurno(segundo);
            }
        } while (!primero.estaMuerto() && !segundo.estaMuerto());
        if (segundo.estaMuerto()) imprimirGanador(primero);
        else imprimirGanador(segundo);
    }

    /**
     * Imprime por consola el nombre del personaje ganador del combate.
     *
     * @param ganador Personaje que ha ganado el combate
     */
    public static void imprimirGanador(Personaje ganador) {
        System.out.println("El  jugador " + ganador.getNombre() + " ha ganado.");
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
        if (r.nextInt(0, 100 + 1) <= 5) {
            switch (r.nextInt(1, 3 + 1)) {
                case 1:
                    trampa.setCategoria("Pinchos");
                    break;
                case 2:
                    trampa.setCategoria("Brea");
                    break;
                case 3:
                    trampa.setCategoria("Serpientes");
                    break;
            }
            trampa.setPerjuicio(r.nextInt(5, 20 + 1));
            trampa.setFracaso(r.nextDouble(0, 75 + 1));
            afectado.caerTrampa(trampa);
        }
    }
}
