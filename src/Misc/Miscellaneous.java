package Misc;

public class Miscellaneous {
    private static String conjunto = "";
    private static String espaciado="";

    public static String opcionesJugador(String body){
        conjunto = " ┌────────────────────♥────────────────────┐ ";
        String[] lineas = body.split("\n");
        String cuerpo = "";

        for (int i = 0 ; i < lineas.length ; i++){
            cuerpo = cuerpo + "¤┤"
                    + calcEspaciado(lineas[i].trim())
                    + lineas[i].trim()
                    + calcEspaciado(lineas[i].trim())
                    + calcImpar(lineas[i].trim())
                    + "├¤\n";
        }
        conjunto = conjunto + "\n" + cuerpo + " └────────────────────○────────────────────┘ ";

        return conjunto;

    }

    private static String calcEspaciado(String body){
        espaciado = "";
        for (int i = 0; i <= ((conjunto.trim().length() - body.length()) / 2) - 2; i++) {
            espaciado = espaciado + " ";
        }
        return espaciado;
    }

    private static String calcImpar(String body){
        if (body.trim().length()%2 == 0){
            return " ";
        } else {
            return  "";
        }
    }
}
