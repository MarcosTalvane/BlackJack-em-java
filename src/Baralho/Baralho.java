package Baralho;

import java.util.ArrayList;
import java.util.Collections;

public class Baralho {
    ArrayList<Carta> deck;
    ArrayList<Carta> descarte;

    //Cria um baralho vazio
    Baralho(){
        deck = new ArrayList<>();
        descarte = new ArrayList<>();
    }

    //Cria, gera e embaralha o baralho
    Baralho(String pronto){
        deck = new ArrayList<>();
        descarte = new ArrayList<>();
        if(pronto.equals("sim")){
            this.gerar_baralho();
            this.embaralhar();
        }
    }

    //Cria as 52 cartas
    void gerar_baralho(){
        String[] naipes = {"copas","ouros","paus","espadas"};
        for(String naipe:naipes){
            gerar_13_cartas(naipe);
        }
    }

    //Cria as 13 cartas de um naipe
    void gerar_13_cartas(String naipe){
        for(int i=1; i<14; i++){
            String valor = ""+i;
            if (i==1){ valor = "A";}
            if (i==11){valor = "J";}
            if (i==12){valor = "Q";}
            if (i==13){valor = "K";}
            deck.add(new Carta(valor,naipe));
        }
    }

    //Embaralha o baralho
    void embaralhar(){
        Collections.shuffle(deck);
    }

    //Puxa uma carta do baralho
    Carta puxar(){
        //Se o baralho acabou, o descarte volta para ele
        if(deck.isEmpty()){
            deck.addAll(descarte);
            descarte.clear();
            embaralhar();
        }
        return deck.removeFirst();
    }

    //Puxa várias cartas do baralho
    ArrayList<Carta> puxar(int n){
        ArrayList<Carta> L = new ArrayList<>();
        for(int i=0; i<n; i++){
            L.add(puxar());
        }
        return L;
    }

    //Coloca uma carta no descarte
    void descartar(Carta C){
        descarte.add(C);
    }

}