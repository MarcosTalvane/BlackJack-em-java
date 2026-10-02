package Baralho;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BlackJack {
    Baralho deck;
    ArrayList<Carta> mao;
    ArrayList<Carta> maoCasa;
    int pontos;
    int pontosCasa;

    //Inicia a rodada
    BlackJack() {
        System.out.println("============= Novo Jogo =============");
        System.out.println();

        deck = new Baralho("sim");
        mao = new ArrayList<>();
        maoCasa = new ArrayList<>();

        puxar();
        puxar();

        puxarCasa();
        puxarCasa();

        exibir_estado();

        if (fim_de_jogo()) {
            System.out.println("Fim de Jogo");
        } else {
            executar();
        }
    }

    //Verifica se o jogador já terminou
    boolean fim_de_jogo() {
        if (pontos > 21) {
            System.out.println("Pontuação maior que 21. Você perdeu!!!");
            return true;
        }

        if (pontos == 21) {
            System.out.println("BlackJack! Você venceu!");
            return true;
        }

        return false;
    }

    //O jogador puxa uma carta
    void puxar() {
        mao.add(deck.puxar());
    }

    //A casa puxa uma carta
    void puxarCasa() {
        maoCasa.add(deck.puxar());
    }

    //Calcula os pontos do Jogador
    void calcular_pontos() {
        int pontos = 0;
        int num_A = 0;

        for (Carta c : mao) {
            if (c.valor.equals("A")) {
                num_A++;
                pontos++;
            } else if (List.of("J", "Q", "K").contains(c.valor)) {
                pontos += 10;
            } else {
                pontos += Integer.parseInt(c.valor);
            }
        }

        for (int i = 0; i < num_A; i++) {
            if (pontos + 10 <= 21) {
                pontos += 10;
            }
        }

        this.pontos = pontos;
    }

    //Calcula os pontos da Casa
    void calcular_pontos_casa() {
        int pontos = 0;
        int num_A = 0;

        for (Carta c : maoCasa) {
            if (c.valor.equals("A")) {
                num_A++;
                pontos++;
            } else if (List.of("J", "Q", "K").contains(c.valor)) {
                pontos += 10;
            } else {
                pontos += Integer.parseInt(c.valor);
            }
        }

        for (int i = 0; i < num_A; i++) {
            if (pontos + 10 <= 21) {
                pontos += 10;
            }
        }

        this.pontosCasa = pontos;
    }

    //Mostra as mãos e a pontuação
    void exibir_estado() {
        calcular_pontos();
        calcular_pontos_casa();

        System.out.println("--------- Estado da Rodada ---------");
        System.out.println("Jogador: " + mao + " Pontos: " + pontos);
        System.out.println("Casa: " + maoCasa + " Pontos: " + pontosCasa);
    }

    //A casa joga até atingir pelo menos 17 pontos
    void jogarCasa() {
        calcular_pontos_casa();

        while (pontosCasa < 17) {
            puxarCasa();
            calcular_pontos_casa();
        }
    }

    //Compara Jogador e Casa. Retorna true se deu empate
    boolean resultado_mao() {
        calcular_pontos();
        calcular_pontos_casa();

        if (pontosCasa > 21) {
            System.out.println("A casa estourou. Você venceu!");
            return false;
        }

        if (pontos > pontosCasa) {
            System.out.println("Você venceu!");
        } else if (pontos < pontosCasa) {
            System.out.println("Você perdeu!");
        } else {
            System.out.println("Jogador e Casa empataram.");
            System.out.println("Reiniciando o Jogo...");
            return true;
        }

        return false;
    }

    //Caso empate, descarta as cartas e distribui novas
    void reiniciar() {

        for (Carta carta : mao) {
            deck.descartar(carta);
        }

        for (Carta carta : maoCasa) {
            deck.descartar(carta);
        }

        mao.clear();
        maoCasa.clear();

        puxar();
        puxar();

        puxarCasa();
        puxarCasa();

        exibir_estado();
    }

    //Controla as ações do jogador
    void executar() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("Você deseja puxar mais uma carta? (s/n)");
            String op = sc.nextLine();

            if (op.equalsIgnoreCase("n")) {
                jogarCasa();
                exibir_estado();

                if (resultado_mao()) {
                    reiniciar();

                    if (fim_de_jogo()) {
                        break;
                    }
                } else {
                    break;
                }
            } else if (op.equalsIgnoreCase("s")) {
                puxar();
                exibir_estado();

                if (fim_de_jogo()) {
                    break;
                }
            } else {
                System.out.println("Opção inválida. Digite s ou n.");
            }
        }

        System.out.println("Sua pontuação final foi: " + pontos);
    }

    public static void main(String[] args) {
        BlackJack jogo = new BlackJack();
    }
}