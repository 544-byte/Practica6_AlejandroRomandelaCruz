package Combat;

import Characters.*;
import Combat.*;
import GameMap.*;
import Misc.*;

import Characters.Personaje;
import GameMap.Trampa;

import java.util.Random;

public class Combate {
    // el combate va a pasar de ser un personaje vs un personaje a ser un array de personajes vs un array de personajes
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

            if (!segundo.estaMuerto()) {
                segundo.realizarTurno(primero);
            }
        } while (!primero.estaMuerto() && !segundo.estaMuerto());
        if ( segundo.estaMuerto()) imprimirGanador(primero);
        else imprimirGanador(segundo);
    }

    public static void imprimirGanador(Personaje ganador) {
        System.out.println("El  jugador " + ganador.getNombre() + " ha ganado.");
    }

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
