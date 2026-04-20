package GameMap;

import Characters.Monstruo;
import Gear.Arma;
import Gear.Armadura;
import Gear.Artefacto;
import Gear.Equipamiento;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Mazmorra {
    private String nombre;
    private HashSet<Monstruo> monstruos;
    private int nivel;

    public Mazmorra(){

    }

    public Mazmorra(File f){
        HashSet<Monstruo> monstruos = new HashSet<>();
        try {
            String l = "";
            BufferedReader br = new BufferedReader(new FileReader(f));
            l = br.readLine();
            setNombre(l.split(",")[0]);
            setNivel(Integer.parseInt(l.split(",")[1]));






            // todo Me he quedado por aqui








            while ((l = br.readLine()) != null){
                ArrayList<String> linea;
                if (l.split("\"")[1].contains(",")){
                    linea = new ArrayList<>(Set.of(l.split("\"")));
                    linea.remove(0);
                    String resto = linea.getLast();
                    linea.removeLast();
                    linea.addAll(Set.of(resto.split("\"")));
                } else {
                    linea = new ArrayList<>(Set.of(l.split(",")));
                }

            }
            br.close();
        } catch (IOException e) {
            System.err.println(e);
        }
        this.monstruos = new HashSet<>(monstruos);
    }

    //region Setter&Getters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public HashSet<Monstruo> getMonstruos() {
        return monstruos;
    }

    public void setMonstruos(HashSet<Monstruo> monstruos) {
        this.monstruos = monstruos;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    //endregion
}
