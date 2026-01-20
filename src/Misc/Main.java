package Misc;

import Characters.*;
import Combat.*;
import GameMap.*;
import Misc.*;

import Combat.Combate;

public class Main {
    public static void main(String[] args) {
        Personaje p1 = new Guerrero("P1",1,100,8,20,30,5);
        Personaje p2 = new Mago("P1",1,100,8,20,30,5,0,new Alianza(0) );
        Personaje e1 = new Personaje("P2",1,100,10,10,40,6);
        Combate.combatir(p1,e1);
    }
}