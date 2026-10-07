import java.util.InputMismatchException;
import java.util.Scanner;

public class Velha extends Jogo {

    static final String GREEN = "\u001B[32m";
    static final String YELLOW = "\u001B[33m";
    static final String RESET = "\u001B[0m";

    static char[][] tabuleiro = new char[3][3];

    public Velha() {
        super("Jogo da Velha");
    }

    @Override
    public void iniciar() {
        Scanner sc = new Scanner(System.in);

        limparTabuleiro();

        System.out.println("\n--------------------------");
        System.out.println("      JOGO DA VELHA");
        System.out.println("--------------------------");

        char jogadorAtual = 'X';
        boolean jogoAtivo = true;

        while (jogoAtivo) {
            exibirTabuleiro();

            try {
                System.out.println("Jogador " + jogadorAtual + ", escolha uma linha (1-3): ");
                int linha = sc.nextInt();
                System.out.println("Jogador " + jogadorAtual + ", escolha uma coluna (1-3): ");
                int coluna = sc.nextInt();

                if (tabuleiro[linha - 1][coluna - 1] == ' ') {
                    tabuleiro[linha - 1][coluna - 1] = jogadorAtual;

                    if (checarVitoria(jogadorAtual)) {
                        exibirTabuleiro();
                        System.out.println(GREEN + "Parabéns, jogador " + jogadorAtual + "! Você venceu!" + RESET);
                        jogoAtivo = false;
                    } else if (checarEmpate()) {
                        exibirTabuleiro();
                        System.out.println(YELLOW + "Empate!" + RESET);
                        jogoAtivo = false;
                    } else {
                        jogadorAtual = (jogadorAtual == 'X') ? 'O' : 'X';
                    }
                } else {
                    System.out.println("Posição já ocupada! Tente novamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida! Digite apenas números inteiros (1 a 3).");
                sc.nextLine();
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Posição fora do tabuleiro! Escolha números de 1 a 3.");
            }
        }
    }

    static void limparTabuleiro() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tabuleiro[i][j] = ' ';
            }
        }
    }

    static void exibirTabuleiro() {
        for (char[] linha : tabuleiro) {
            for (char coluna : linha) {
                System.out.print("|" + coluna);
            }
            System.out.println("|");
        }
    }

    static boolean checarVitoria(char jogador) {
        for (int i = 0; i < 3; i++) {
            if (tabuleiro[i][0] == jogador && tabuleiro[i][1] == jogador && tabuleiro[i][2] == jogador) {
                return true;
            }
            if (tabuleiro[0][i] == jogador && tabuleiro[1][i] == jogador && tabuleiro[2][i] == jogador) {
                return true;
            }
        }

        if (tabuleiro[0][0] == jogador && tabuleiro[1][1] == jogador && tabuleiro[2][2] == jogador) {
            return true;
        }
        return tabuleiro[0][2] == jogador && tabuleiro[1][1] == jogador && tabuleiro[2][0] == jogador;
    }

    static boolean checarEmpate() {
        for (char[] linha : tabuleiro) {
            for (char coluna : linha) {
                if (coluna == ' ') {
                    return false;
                }
            }
        }
        return true;
    }
}