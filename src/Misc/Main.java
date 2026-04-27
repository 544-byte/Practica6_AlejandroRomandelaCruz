package Misc;

/**
 * Practica 6 Terminada :)
 */

import Characters.*;
import Combat.*;
import GameMap.*;
import Misc.*;

import Combat.Combate;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Set;


/**
 * Clase principal del juego.
 * <p>
 * Se encarga de inicializar los personajes y enemigos, y de iniciar un combate de prueba.
 * Contiene el metodo {@code main} que sirve como punto de entrada de la aplicación.
 * <p>
 * Ejemplo de uso:
 * <pre>
 * Personaje guerrero = new Guerrero("Guerrero",1,120,22,30,15,8,false,0);
 * Personaje enemigo = new Monstruo("Orco",1,140,26,25,10,5,3);
 * Combate.combatir(guerrero, enemigo);
 * </pre>
 */
public class Main {
    /**
     * Punto de entrada de la aplicación.
     * <p>
     * Crea instancias de distintos personajes y un enemigo, y llama al metodo
     * {@link Combat.Combate#combatir(Personaje, Personaje)} para simular un combate.
     *
     * @param args Argumentos de línea de comandos (no utilizados).
     */
    public static void main(String[] args) throws Exception {

        Personaje guerrero = new Guerrero(2,"Iván",1,10,10,10,10,10,false,0,false);
        Mago mago = new Mago(2,"alejandrito el mago con varita",1,10,10,10,10,10,10,0,true);
        Personaje ladron = new Ladron(1,"Ladron",1,10,10,10,10,10,0,true);
        Personaje cazador = new Cazador(2,"alejandrito",1,10,10,10,10,10,0,true,"Toby",3);
        Personaje paladin = new Paladin(3,"Alejandro",1,10,10,10,10,10,10,0,false);
        Personaje clerigo = new Clerigo(1,"Clerigo",1,10,10,10,10,10,10,0,false);
        Personaje enemigo = new Monstruo(3,"Isabel la pequeña gigante (en ve de 1,50 mide 1,65)",1,10,10,10,10,10,6,false);

        ArrayList<Personaje> aliados = new ArrayList<>(Arrays.asList(guerrero,mago,ladron,cazador,paladin,clerigo));

        for (Personaje p : aliados){
            Random r = new Random();
            int i;
            for (i=0;i < r.nextInt(98,100);i++){
                p.subirNivel();
            }
        }


/*
        Personaje p = Misc.importarPersonaje("Mago:2:alejandrito el mago con varita:1:100.0:15.0:10.0:10.0:10.0:10.0:1:true".split(":"));
        Personaje enemigo = Misc.importarPersonaje(new File("./Prueba.csv"));

        Combate.combatir(p,ladron);
        GameLogger.guardarLog();
        GameLogger.subirNivelGanador(new File("GameLogs/02-03-26_19.07.42/GameLog.log"),new Personaje[]{ guerrero, mago, ladron, cazador, paladin, clerigo, enemigo });
        GameLogger.guardarParty(new Personaje[]{ guerrero, mago, ladron, cazador, paladin, clerigo, enemigo });

*/

        Mazmorra m = new Mazmorra(new File("Ficheros/Mazmorras/hogarDelHacedor.csv"));

        Aventura.empezarAventura(aliados,m);
    }
}