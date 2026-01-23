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
        Personaje guerrero = new Guerrero("Guerrero",1,120,22,30,15,8,false,0);
        Personaje mago = new Mago("Mago",1,80,28,10,35,7,20,0);
        Personaje ladron = new Ladron("Ladron",1,90,24,15,15,18,0);
        Personaje cazador = new Cazador("Cazador",1,100,23,18,18,14,0,"Toby",1);
        Personaje paladin = new Paladin("Paladin",1,130,21,28,25,6,0,25);
        Personaje clerigo = new Clerigo("Clerigo",1,100,19,15,35,6,0,30);

        Personaje enemigo = new Monstruo("Orco",1,140,26,25,10,5,3);
        Combate.combatir(ladron,enemigo);
    }
}