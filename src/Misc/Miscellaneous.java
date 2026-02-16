package Misc;

import Characters.*;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Clase abstracta que contiene métodos utilitarios para mostrar texto con formatos decorativos en la consola.
 * <p>
 * Esta clase no puede instanciarse, ya que todos sus métodos son estáticos y sirven de apoyo para funcionalidades misceláneas
 * relacionadas con la presentación de información del jugador.
 * </p>
 *
 * <p>Ejemplo de uso:</p>
 * <pre>
 * System.out.println(Miscellaneous.opcionesJugador("1- Atacar\n2- Defender"));
 * System.out.println(Miscellaneous.estadisticasJugador("Vida: 100\nAtaque: 20"));
 * </pre>
 */




public class Miscellaneous {
    public static final String Codigo = "\u001B[";
    public static final String Reset = "\u001B[0m";
    public static final String m = "m";

    // Estilos
    public static final String Negrita = "1";
    public static final String Cursiva = "3";
    public static final String Subrayado = "4";
    public static final String Tachado = "9";

    // colore
    public static final String Rojo = "31";
    public static final String Verde = "32";
    public static final String Amarillo = "33";
    public static final String Azul = "34" ;
    public static final String Rosa = "35" ;
    public static final String Cian = "36" ;

    // colore brillantes
    public static final String Gris = "90";
    public static final String RojoB = "91";
    public static final String VerdeB = "92";
    public static final String AmarilloB = "93";
    public static final String AzulB = "94" ;
    public static final String RosaB = "95" ;
    public static final String CianB = "96" ;
    public static final String Brillante = "97" ;

    /**
     * Cadena utilizada como contenedor base para construir los marcos decorativos.
     */
    private static String conjunto = "";
    /**
     * Cadena auxiliar que representa el espaciado calculado para centrar el texto.
     */
    private static String espaciado = "";

    /**
     * Genera un marco decorativo para mostrar opciones del jugador.<br>
     * El texto recibido se divide por líneas y cada línea es centrada automáticamente dentro de un marco ASCII decorativo.
     *
     * @param body Texto que contiene las opciones del jugador separadas por saltos de línea.
     * @return Una cadena formateada con un marco visual para mostrar opciones.
     */
    public static String opcionesJugador(String body) {
        conjunto = " ───────────═≡✦✧ ▾ ✧✦≡═─────────── ";
        String[] lineas = body.split("\n");
        String cuerpo = "";

        for (int i = 0; i < lineas.length; i++) {
            cuerpo = cuerpo + "✦✧" + calcEspaciado(lineas[i].trim()) + lineas[i].trim() + calcEspaciado(lineas[i].trim()) + calcImpar(lineas[i].trim()) + "✧✦\n";
        }
        conjunto = conjunto + "\n" + cuerpo + " ───────────═≡✦✧ ▾ ✧✦≡═─────────── ";

        return conjunto;

    }


    /**
     * Genera un marco decorativo para mostrar las estadísticas del jugador.<br>
     * Cada línea del texto recibido se centra dentro de un recuadro ASCII diseñado específicamente para estadísticas.
     *
     * @param body Texto con las estadísticas del jugador separadas por saltos de línea.
     * @return Una cadena formateada con un marco visual para estadísticas.
     */
    public static String estadisticasJugador(String body) {
        conjunto = " ┌────────────────────©────────────────────┐ ";
        String[] lineas = body.split("\n");
        String cuerpo = "";

        for (int i = 0; i < lineas.length; i++) {
            cuerpo = cuerpo + "¤┤" + calcEspaciado(lineas[i].trim()) + lineas[i].trim() + calcEspaciado(lineas[i].trim()) + calcImpar(lineas[i].trim()) + "├¤\n";
        }
        conjunto = conjunto + "\n" + cuerpo + " └────────────────────○────────────────────┘ ";
        return conjunto;
    }

    /**
     * Calcula el espaciado necesario para centrar un texto dentro del marco actual.
     *
     * @param body Texto al cual se le calculará el espaciado lateral.
     * @return Una cadena compuesta por espacios en blanco.
     */
    private static String calcEspaciado(String body) {
        espaciado = "";
        for (int i = 0; i <= ((conjunto.trim().length() - body.length()) / 2) - 2; i++) {
            espaciado = espaciado + " ";
        }
        return espaciado;
    }

    /**
     * Ajusta el centrado cuando la longitud del texto es impar.
     *
     * @param body Texto a evaluar.
     * @return Un espacio adicional si la longitud es par, o una cadena vacía si es impar.
     */
    private static String calcImpar(String body) {
        if (body.trim().length() % 2 == 0) {
            return " ";
        } else {
            return "";
        }
    }

    public static String formato(String formato,String texto){
        return "\u001B[" + formato + "m" + texto + "\u001B[0m";
    }

    public static void alert(String string) {
        System.out.println(string);
        try{
        FileWriter fw = new FileWriter("GameLog.log");
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(string);}
        catch (IOException e){
            System.err.println(e);
        }
    }


}
