package Misc;

import Characters.*;
import Combat.*;
import GameMap.*;
import Misc.*;

import Combat.Combate;

public class Main {
    public static void main(String[] args) {
        Personaje c1 = new Personaje("Cristian",18,50,25,25,10,10);
        Personaje c2 = new Personaje("Alejandro",18,25,50,25,10,10);
        Combate.combatir(c1, c2);
    }
}