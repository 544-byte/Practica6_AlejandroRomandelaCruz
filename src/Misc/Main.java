package Misc;

import Characters.*;
import Combat.*;
import GameMap.*;
import Misc.*;

import Combat.Combate;

public class Main {
    public static void main(String[] args) {
        System.out.println("prueba");
        Personaje p1 = new Guerrero("P1",1,100,8,20,30,5,false);
        Personaje p2 = new Personaje("P2",1,100,10,10,40,6);
        Combate.combatir(p1,p2);
    }
}