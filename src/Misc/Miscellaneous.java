package Misc;

import Characters.*;

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

public abstract class Miscellaneous {
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


}
