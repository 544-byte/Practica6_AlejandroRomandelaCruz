package Misc;

import Characters.*;
import Combat.*;
import GameMap.*;
import Misc.*;

import Combat.Combate;
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
     * Crea instancias de distintos personajes y un enemigo, y llama al método
     * {@link Combat.Combate#combatir(Personaje, Personaje)} para simular un combate.
     *
     * @param args Argumentos de línea de comandos (no utilizados).
     */
    public static void main(String[] args) {
    //    Personaje guerrero = new Guerrero("Guerrero",100,15,10,10,10,10,false,0);
        Personaje mago = new Mago("Bvbelita",1,100,15,10,10,10,10,1);
    //    Personaje ladron = new Ladron("Ladron",1,100,15,10,10,10,0);
        Personaje cazador = new Cazador("alejandrito",1,100,15,10,10,10,2,"Toby",1);
    //    Personaje paladin = new Paladin("Paladin",1,100,15,10,10,10,0,10);
    //    Personaje clerigo = new Clerigo("Clerigo",1,100,15,10,10,10,0,10);
    //    Personaje enemigo = new Monstruo("Orco",1,100,10,10,10,10,3);


        mago.setEsJugador();
        cazador.setEsJugador();
        Combate.combatir(mago,cazador);
    }
}