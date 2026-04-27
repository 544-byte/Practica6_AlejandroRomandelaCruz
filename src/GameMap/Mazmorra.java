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

    /**
     * constructor por defecto de la mazmorra
     */
    public Mazmorra(){
        nombre = "";
        monstruos = new HashSet<>();
        nivel = -1;
    }

    /**
     * constructor que crea una mazmorra a partir de un fichero de texto
     * @param f archivo de texto que contiene los datos de la mazmorra y sus monstruos
     */
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

    /**
     * constructor de copia de la mazmorra
     * @param m objeto Mazmorra a copiar
     */
    public Mazmorra(Mazmorra m){
        setNombre(m.getNombre());
        setMonstruos(m.getMonstruos());
        setNivel(m.getNivel());
    }

    //endregion

    //region Métodos de objeto

    /**
     * selecciona un monstruo al azar del conjunto de monstruos de la mazmorra para iniciar un combate
     * @return una copia de un monstruo aleatorio de la lista
     */
    public Monstruo combateAleatorio(){
        Random r = new Random();
        return new Monstruo((Monstruo)monstruos.toArray()[r.nextInt(0,monstruos.size())]);
    }

    //endregion

    //region Setter&Getters

    /**
     * getter del nombre de la mazmorra
     * @return nombre de la mazmorra
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * setter del nombre de la mazmorra
     * @param nombre cadena con el nuevo nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * getter del conjunto de monstruos que habitan la mazmorra
     * @return HashSet con los monstruos
     */
    public HashSet<Monstruo> getMonstruos() {
        return monstruos;
    }

    /**
     * setter del conjunto de monstruos de la mazmorra
     * @param monstruos nuevo HashSet de monstruos
     */
    public void setMonstruos(HashSet<Monstruo> monstruos) {
        this.monstruos = monstruos;
    }

    /**
     * añade un monstruo a la mazmorra si su nivel está dentro del rango permitido (+-3 niveles)
     * @param m monstruo a intentar añadir
     */
    public void addMonstruo(Monstruo m){
        if (m.getNivel() >= getNivel()-3 && m.getNivel() <= getNivel()+3)
            monstruos.add(m);
    }

    /**
     * getter del nivel de dificultad de la mazmorra
     * @return nivel de la mazmorra
     */
    public int getNivel() {
        return nivel;
    }

    /**
     * setter del nivel de dificultad de la mazmorra
     * @param nivel nuevo nivel entero
     */
    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    //endregion

    //region Overrides

    /**
     * sobreescritura del metodo toString
     * @return un String con toda la información de la mazmorra conteniendo este su nombre, nivel y los detalles de cada uno de sus monstruos
     */
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