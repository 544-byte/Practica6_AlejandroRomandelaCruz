package Misc;

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
     * Guarda el log actual Temporal
     */
    public static void guardarLog() {
        String thisGameLog = GAME_LOGS + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yy_HH.mm.ss")) + "/";
        File TempGameLog = new File(thisGameLog + "GameLog.log");
        File TempCharactersCSV = new File(thisGameLog + "Characters.csv");
        File TempCharactersStatusCSV = new File(thisGameLog + "CharactersStatus.csv");
        copiar(TempGameLog, GAME_LOG);
        copiar(TempCharactersCSV, CHARACTERS_CSV);
        copiar(TempCharactersStatusCSV, CHARACTERS_STATUS_CSV);
    }

    /**
     * Copia el contenido del archivo de origen en el archivo de destino pasado por parámetros.
     * Funciona leyendo y copiando línea por línea
     * @param origen El archivo de origen del cual se va a sacar el contenido
     * @param destino El archivo de destino donde se va a volcar el contenido
     */
    public static void copiar(File origen,File destino){
        try {
            FileReader fr = new FileReader(origen);
            FileWriter fw = new FileWriter(destino);
            BufferedReader br = new BufferedReader(fr);
            BufferedWriter bw = new BufferedWriter(fw);
            String l;
            while((l = br.readLine()) != null) {
                bw.write(l + "\n");
            }
            bw.close();
            br.close();
            fw.close();
            fr.close();
        }
        catch (IOException e){
            System.out.println(e);
        }
    }
}
