package Misc;

import Characters.*;
import Combat.*;
import GameMap.*;
import Misc.*;

import Combat.Combate;

public class Main {
    public static void main(String[] args) {
        Personaje guerrero = new Guerrero("Guerrero",1,120,22,30,15,8,false,0);
        Personaje mago = new Mago("Mago",1,80,28,10,35,7,20,0);
        Personaje ladron = new Ladron("Ladron",1,90,24,15,15,18,0);
        Personaje cazador = new Cazador("Cazador",1,100,23,18,18,14,0,"Toby",1);
        Personaje paladin = new Paladin("Paladin",1,130,21,28,25,6,0,25);
        Personaje clerigo = new Clerigo("Clerigo",1,100,19,15,35,6,0,30);

        Personaje enemigo = new Monstruo("Orco",1,140,26,25,10,5,3);
        Combate.combatir(guerrero,enemigo);
    }
}