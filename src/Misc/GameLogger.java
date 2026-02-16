package Misc;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 */
public class GameLogger {
    private static final String Saves = "Saves/";
    private static final String GameLogs = "GameLogs/";
    private static final String TempGameLog = "TempGameLog/";

    /**
     * Guarda el log actual Temporal
     */
    public static void guardarLog() {
        String thisGameLog = GameLogs + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yy - HH.mm.ss"));
        File GameLog = new File(TempGameLog + "GameLog.log");
        File TempGameLog = new File(thisGameLog + "GameLog.log");
        File CharactersCSV = new File(TempGameLog + "Characters.csv");
        File TempCharactersCSV = new File(thisGameLog + "Characters.csv");
        File CharactersStatusCSV = new File(TempGameLog + "CharactersStatus.csv");
        File TempCharactersStatusCSV = new File(thisGameLog + "CharactersStatus.csv");

        copiar(TempGameLog,GameLog);
        copiar(TempCharactersCSV,CharactersCSV);
        copiar(TempCharactersStatusCSV,CharactersStatusCSV);

    }

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
