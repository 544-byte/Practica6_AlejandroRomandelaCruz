package Combat;

import Characters.*;
import Combat.*;
import GameMap.*;
import Misc.*;

import Characters.Personaje;
import GameMap.Trampa;

import java.util.Random;

public class Combate {

    private static Personaje primerP(Personaje c1, Personaje c2) {
        Random r = new Random();
        if (c1.getNivel() > c2.getNivel()) return c1;
        else if (c1.getNivel() < c2.getNivel()) return c2;
        else {
            if (r.nextInt(0, 1 + 1) == 0) return c1;
            else return c2;
        }
    }

    public static void combatir(Personaje c1, Personaje c2) {
        Personaje primero;
        Personaje segundo;
        if (c1.equals(primerP(c1,c2))) {
            primero = c1;
            segundo = c2;
        } else {
            primero = c2;
            segundo = c1;
        }
        do {
            llamarTrampa(primero);
            primero.ataca(segundo);
            if (!segundo.estaMuerto()) {
                llamarTrampa(segundo);
                segundo.ataca(primero);
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
