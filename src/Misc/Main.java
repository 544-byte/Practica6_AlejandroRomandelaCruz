package Misc;

import Characters.*;
import Combat.*;
import GameMap.*;
import Misc.*;

import Combat.Combate;

public class Main {
    public static void main(String[] args) {
        System.out.println("prueba");
        Personaje p1 = new Personaje("P1",1,3,1,0,0,1);
        Personaje p2 = new Personaje("P2",1,1,1,0,0,5);
        Combate.combatir(p1,p2);
    }
}