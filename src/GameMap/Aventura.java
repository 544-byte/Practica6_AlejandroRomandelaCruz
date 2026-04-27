package GameMap;

import Characters.Monstruo;
import Characters.Personaje;
import Combat.Combate;
import Misc.GameLogger;
import Misc.Misc;

import java.util.ArrayList;
import java.util.Random;

/**
 * Clase que representa una Aventura en la cual tienen lugar 10 combates con monstruos
 * de la mazmorra pasada por parámetros, cuando termina el combate se guarda un log.
 */
public class Aventura {

    /**
     * El metodo en el que se ejecuta tdo
     * @param party La party de personajes que van a luchar
     * @param mazmorra La mazmorra en la que se aventuran
     */
    public static void empezarAventura(ArrayList<Personaje> party,Mazmorra mazmorra){
        int i;
        for (i = 0; i < 10 && !party.isEmpty(); i++){
            ArrayList<Personaje> monstruos = new ArrayList<>();
            int j;
            for (j = 0; j < new Random().nextInt(1,3);j++){
                monstruos.add(mazmorra.combateAleatorio());
            }
            Combate.combatir(party,monstruos);
        }

        if (!party.isEmpty()){
            Misc.happen("El equipo de " + party.getFirst().getNombre() + " ha logrado completar la aventura!");
        } else {
            Misc.happen("Los terribles monstruos de " + mazmorra.getNombre() + " han acabado con nuestros aventureros.");
        }

        GameLogger.guardarLog();
    }
}
