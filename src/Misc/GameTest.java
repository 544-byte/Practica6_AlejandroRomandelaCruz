package Misc;

import Characters.*;
import Combat.*;
import GameMap.*;
import Misc.*;

public class GameTest {
    public static Personaje inicializa(String n, int lvl, int pv, int atq, int arm, int res, int vel) {
        return new Personaje(n,lvl,pv,atq,arm, res, vel);
    }

    public static Personaje randomizaPersonaje(String name){
        return new Personaje(name);
    }
}