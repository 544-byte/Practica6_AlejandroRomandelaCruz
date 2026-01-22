package Misc;

import Characters.*;
import Combat.*;
import GameMap.*;
import Misc.*;

import Combat.Combate;

public class Main {
    public static void main(String[] args) {
        Personaje p1 = new Paladin("P1",1,100,8,20,30,5,0,20);
        Personaje p2 = new Cazador("Cazador",1,100,8,20,30,5,0,"Toby",1);
        Personaje p3 = new Ladron("Ladron",1,100,8,20,30,5,0);
        Personaje p4 = new Mago("Mago",1,100,8,20,30,5,0);
        Personaje p5 = new Guerrero("Guerrero",1,100,8,20,30,5,0);
        Personaje e1 = new Guerrero("e1",1,100,10,10,40,6);
        Combate.combatir(p1,e1);
    }
}