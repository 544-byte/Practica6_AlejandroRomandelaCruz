package Misc;
import Characters.*;

import java.util.Arrays;

public class Alianza {
    public static Alianza[] alianzas;
    private int idAlianza;
    private Personaje[] aliados;

    public Alianza(int idAlianza){
        if (alianzas != null){
            for (int i = 0; i < alianzas.length; i++) {
                if (alianzas[i].getIdAlianza() == idAlianza) {
                    System.err.println("Esta alianza ya existe");
                }
            }
        } else {
            setIdAlianza(idAlianza);
            addAlianza(idAlianza);
        }
    }

    public int getIdAlianza() {
        return idAlianza;
    }

    public void setIdAlianza(int idAlianza) {
        this.idAlianza = idAlianza;
    }

    public Personaje[] getAliados() {
        return aliados;
    }

    public boolean esAliado(Personaje personaje){
        for (int i = 0 ; i < aliados.length ; i++){
            if ( aliados[i].equals(personaje)){
                return true;
            }
        }
        return false;
    }

    public void addAliado(Personaje aliado) {
        if (aliados == null){
            aliados = new Personaje[1];
            aliados[0] = aliado;
        } else {
            Personaje[] supportArray = new Personaje[aliados.length + 1];
            for (int i = 0; i < supportArray.length; i++) {
                supportArray[i] = aliados[i];
            }
            supportArray[supportArray.length] = aliado;
            aliados = new Personaje[supportArray.length];
            for (int i = 0; i < supportArray.length; i++) {
                aliados[i] = supportArray[i];
            }
        }
    }

    public void addAlianza(int idAlianza) {
        if (alianzas == null){
            alianzas = new Alianza[1];
            alianzas[0] = new Alianza(idAlianza);
        } else {
            Alianza[] supportArray = new Alianza[alianzas.length + 1];
            for (int i = 0; i < supportArray.length; i++) {
                supportArray[i] = alianzas[i];
            }
            supportArray[supportArray.length] = new Alianza(idAlianza);
            alianzas = new Alianza[supportArray.length];
            for (int i = 0; i < supportArray.length; i++) {
                alianzas[i] = supportArray[i];
            }
        }
    }



    @Override
    public String toString() {
        return "Alianza{" +
                "idAlianza=" + idAlianza +
                ", aliados=" + Arrays.toString(aliados) +
                '}';
    }
}
