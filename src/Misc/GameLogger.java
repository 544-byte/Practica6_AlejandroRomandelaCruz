package Misc;

import Characters.Personaje;

import javax.swing.plaf.basic.BasicDirectoryModel;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
        Misc.importarPersonaje(ficha).toString();
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
