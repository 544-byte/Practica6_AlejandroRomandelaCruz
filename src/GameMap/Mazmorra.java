package GameMap;

import Characters.Monstruo;
import Gear.Arma;
import Gear.Armadura;
import Gear.Artefacto;
import Gear.Equipamiento;
import Misc.Misc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Random;


public class Mazmorra {
    private String nombre;
    private HashSet<Monstruo> monstruos;
    private int nivel;

    //region Constructores

    public Mazmorra(){
        nombre = "";
        monstruos = new HashSet<>();
        nivel = -1;
    }

    public Mazmorra(File f){
        HashSet<Monstruo> monstruos = new HashSet<>();
        try {
            String l = "";
            BufferedReader br = new BufferedReader(new FileReader(f));
            l = br.readLine();
            setNombre(l.split(",")[0]);
            setNivel(Integer.parseInt(l.split(",")[1].trim()));
            while ((l = br.readLine()) != null){
                Random r = new Random();
                monstruos.add(new Monstruo(l.split(",")[0],l.split(",")[1],r.nextInt(getNivel()-3,getNivel()+4)));
            }
            br.close();
        } catch (IOException e) {
            System.err.println(e);
        }
        setMonstruos(monstruos);
    }

    public Mazmorra(Mazmorra m){
        setNombre(m.getNombre());
        setMonstruos(m.getMonstruos());
        setNivel(m.getNivel());
    }

    //endregion

    //region Métodos de objeto

    public Monstruo combateAleatorio(){
        Random r = new Random();
        return (Monstruo)monstruos.toArray()[r.nextInt(0,monstruos.size())];
    }

    //endregion

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

    public void addMonstruo(Monstruo m){
        if (m.getNivel() >= getNivel()-3 && m.getNivel() <= getNivel()+3)
        monstruos.add(m);
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    //endregion

    //region Overrides

    @Override
    public String toString() {
        String l = getNombre() +
                    "\n Nivel: " + getNivel();
        for (Monstruo m : monstruos) {
            l+="\n────────────────────○────────────────────\n";
            l+=m.toString();
        }
        return Misc.estadisticasJugador(l);
    }


    //endregion
}
