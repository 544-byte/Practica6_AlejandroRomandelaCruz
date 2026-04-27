package GameMap;

import Characters.Monstruo;
import Characters.Personaje;
import Combat.Combate;
import Misc.GameLogger;
import Misc.Misc;

import java.util.ArrayList;
import java.util.Random;

public class Aventura {

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
