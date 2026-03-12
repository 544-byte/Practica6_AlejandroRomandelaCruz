package Misc;

import Characters.Guerrero;
import Characters.Monstruo;
import Characters.Personaje;

import javax.annotation.processing.FilerException;
import javax.swing.plaf.basic.BasicDirectoryModel;
import java.io.*;
import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 *
 */
public class GameLogger {
    private static final String SAVES = "Saves/";
    private static final String GAME_LOGS = "GameLogs/";
    private static final String TEMP_GAME_LOG = "TempGameLog/";
    private static final File GAME_LOG = new File(TEMP_GAME_LOG + "GameLog.log");
    private static final File CHARACTERS_CSV = new File(TEMP_GAME_LOG + "Characters.csv");
    private static final File CHARACTERS_STATUS_CSV = new File(TEMP_GAME_LOG + "CharactersStatus.csv");

    /**
     * es el metodo del ejercicio 1
     *
     * @param ficha la ficha del personaje
     * @throws Exception Checkea si la clase del personaje es correcta
     * @since practica6
     */
    public static void soutCharFile(File ficha)throws Exception{
        System.out.println(Misc.importarPersonaje(ficha).toString());
    }

    /**
     * metodo del ejercicio 2
     * @param personajes array de personajes que se van a guardar
     */
    public static void guardarParty(Personaje [] personajes){
        Scanner scan = new Scanner(System.in);
        String nombreFichero;
        String contenidoFichero = "";
        Arrays.sort(personajes);
        for (Personaje personaje : personajes) {
            contenidoFichero = contenidoFichero.concat(personaje.toString() + "\n" + "-".repeat(30) + "\n");
        }
        System.out.println("Elige un nombre para el fichero en el que se va a guardar la party:");
        System.out.print("Nombre Fichero: ");
        nombreFichero = scan.nextLine();
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("./" + nombreFichero + ".party"));
            bw.write(contenidoFichero);
            bw.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * metodo del ejercicio 8
     * @param combate el combate del cual se va a sacar el ganador
     * @param personajes Lista de personajes entre los cuales se va a comprobar cual es el ganador.
     */
    public static void subirNivelGanador(File combate,ArrayList<Personaje> personajes){
        try {
            BufferedReader br = new BufferedReader(new FileReader(combate));
            String linea;
            String lineaGanador = "";
            while ((linea = br.readLine()) != null){
                lineaGanador = linea;
            }
            for (Personaje personaje : personajes) {
                if (lineaGanador.contains(" - " + personaje.getNombre() + " ha ganado.")) {
                    personaje.subirNivel();
                }
            }
            br.close();
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    /**
     * Metodo del ejercicio 5
     *
     * @param name nombre del personaje a buscar
     * @param archivos array con los archivos en los cuales se va a buscar el personaje.
     * @return true si existe un personaje con ese nombre en los archivos
     */
    public static boolean personajeExistsIn(String name,File [] archivos){
        try{
            for( File f : archivos){
                BufferedReader br = new BufferedReader(new FileReader(f));
                String l="";
                while ((l = br.readLine()) != null){
                    if (l.split(",")[2].equals(name)){
                        br.close();
                        return true;
                    }
                }
                br.close();
            }
            return false;
        } catch (IOException e) {
            System.err.println(e);
        }
        throw new RuntimeException();
    }

    /**
     * Metodo del ejercicio 6
     *
     * @param archivos un array de archivos en los cuales se va a comprobar la existencia de una clase duplicada
     * @return true si hay una clase que se repite.
     */
    public static boolean duplicatedClassIn(File [] archivos){
        try{
            ArrayList<String> Clases = new ArrayList<>();
            for( File f : archivos){
                BufferedReader br = new BufferedReader(new FileReader(f));
                String l="";
                while ((l = br.readLine()) != null){
                    if (Clases.contains(l.split(",")[0])){
                        br.close();
                        return true;
                    }
                    Clases.add(l.split(",")[0]);
                }
                br.close();
            }
            return false;
        } catch (IOException e) {
            System.err.println(e);
        }
        throw new RuntimeException();
    }


    public static void subirNivelGanador(File combate,Personaje [] personajes){
        subirNivelGanador(combate,new ArrayList<>(List.of(personajes)));
    }



    /**
     * Guarda el log actual Temporal
     */
    public static void guardarLog() {
        String thisGameLog = GAME_LOGS + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yy_HH.mm.ss")) + "/";
        File TempDir = new File(thisGameLog);
        if (!TempDir.exists()){
            TempDir.mkdir();
        }
        File SavedGameLog = new File(thisGameLog + "GameLog.log");
        File SavedCharactersCSV = new File(thisGameLog + "Characters.csv");
        File SavedCharactersStatusCSV = new File(thisGameLog + "CharactersStatus.csv");
        copiar(GAME_LOG,SavedGameLog);
        copiar(CHARACTERS_CSV,SavedCharactersCSV);
        copiar(CHARACTERS_STATUS_CSV,SavedCharactersStatusCSV);

    }

    /**
     * Copia el contenido del archivo de origen en el archivo de destino pasado por parámetros.
     * Funciona leyendo y copiando línea por línea
     * @param origen El archivo de origen del cual se va a sacar el contenido
     * @param destino El archivo de destino donde se va a volcar el contenido
     */
    public static void copiar(File origen,File destino){
        try {
            BufferedReader br = new BufferedReader(new FileReader(origen));
            BufferedWriter bw = new BufferedWriter(new FileWriter(destino));
            String l;
            while((l = br.readLine()) != null) {
                bw.write(l + "\n");
            }
            bw.close();
            br.close();
            new FileWriter(GAME_LOG);
        }
        catch (IOException e){
            System.out.println(e);
        }
    }


    public static File getGameLog(){
        return GAME_LOG;
    }

    /**
     * todo Hacer un método que coja todos los personajes y los meta en un archivo, prefiero hacer esto en la siguiente práctica ya que tendremos mejor organizados los personajes
     */
    public static void setCharactersCsv(){

    }
    public static File getCharactersCsv(){
        return CHARACTERS_CSV;
    }

    /**
     * todo Hacer un método que coja todos los personajes y los meta en un archivo, prefiero hacer esto en la siguiente práctica ya que tendremos mejor organizados los personajes
     */
    public static void setCharactersStatusCsv(){
    }
    public static File getCharactersStatusCsv(){
        return CHARACTERS_STATUS_CSV;
    }


}
