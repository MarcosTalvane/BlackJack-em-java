package Baralho;

import java.util.Objects;

public class Carta {
    String valor;
    String naipe;

    //Cria uma carta
    Carta(String valor, String naipe){
        this.valor = valor;
        this.naipe = converter_naipe(naipe);
    }

    //Transforma o nome do naipe em símbolos
    String converter_naipe(String in){
        if (Objects.equals(in, "copas")) return "♥️";
        if (Objects.equals(in,"ouros") ) return "♦️";
        if (Objects.equals(in, "paus")) return "♣️";
        return "♠️";
    }

    //Mostra a carta
    void mostrar(){
        System.out.print(valor + naipe);
    }

    //Define como a carta aparece quando convertida para texto
    public String toString(){
        return valor + naipe;
    }

}
