import java.util.Scanner;

public class Wordle extends Jogo {

    // Códigos ANSI para colorir no terminal
    public static final String ANSI_RESET    = "\u001B[0m";
    public static final String ANSI_VERDE    = "\u001B[42m\u001B[30m"; // Fundo verde, letra preta
    public static final String ANSI_AMARELO  = "\u001B[43m\u001B[30m"; // Fundo amarelo, letra preta
    public static final String ANSI_CINZA    = "\u001B[47m\u001B[30m"; // Fundo cinzento, letra preta

    private static final int MAX_TENTATIVAS = 6;
    private Dicionario dicionario;

    public Wordle() {
        super("Wordle");
        this.dicionario = new Dicionario();
    }

    @Override
    public void iniciar() {
        Scanner scanner = new Scanner(System.in);
        String palavraSecreta = dicionario.sortearPalavra();
        int tentativasRestantes = MAX_TENTATIVAS;
        boolean venceu = false;

        System.out.println("\n=================================");
        System.out.println("       BEM-VINDO AO WORDLE!      ");
        System.out.println("=================================");
        System.out.println("Descubra a palavra de 5 letras em " + MAX_TENTATIVAS + " tentativas.");
        System.out.println("Legenda: " + ANSI_VERDE + " CERTA " + ANSI_RESET + " "
                + ANSI_AMARELO + " LUGAR ERRADO " + ANSI_RESET + " "
                + ANSI_CINZA + " NÃO EXISTE " + ANSI_RESET + "\n");

        while (tentativasRestantes > 0 && !venceu) {
            System.out.print("Tentativa (" + (MAX_TENTATIVAS - tentativasRestantes + 1) + "/" + MAX_TENTATIVAS + "): ");
            String palpite = scanner.nextLine().trim().toUpperCase();

            // 1. Validação de tamanho
            if (palpite.length() != 5) {
                System.out.println("⚠️  A palavra deve ter exatamente 5 letras!\n");
                continue; // Não gasta tentativa
            }

            // 2. Validação se a palavra existe no dicionário
            if (!dicionario.ehPalavraValida(palpite)) {
                System.out.println("⚠️  Palavra não reconhecida no vocabulário!\n");
                continue; // Não gasta tentativa
            }

            // 3. Exibir o feedback com cores
            exibirResultado(palpite, palavraSecreta);
            tentativasRestantes--;

            // 4. Verificação de vitória
            if (palpite.equals(palavraSecreta)) {
                venceu = true;
                break;
            }
        }

        // Fim de jogo
        System.out.println("\n---------------------------------");
        if (venceu) {
            System.out.println("🎉 PARABÉNS! Acertou na palavra: " + palavraSecreta);
        } else {
            System.out.println("💀 Fim das tentativas! A palavra era: " + palavraSecreta);
        }
        System.out.println("---------------------------------");
        System.out.print("\nPressione ENTER para voltar ao menu...");
        scanner.nextLine();
    }

    private void exibirResultado(String palpite, String secreta) {
        String[] resultado = new String[5];
        char[] secretaChars = secreta.toCharArray();
        boolean[] usada = new boolean[5];

        // 1ª Passagem: Marcar as posições exatas (Verde)
        for (int i = 0; i < 5; i++) {
            if (palpite.charAt(i) == secretaChars[i]) {
                resultado[i] = ANSI_VERDE + " " + palpite.charAt(i) + " " + ANSI_RESET;
                usada[i] = true;
            }
        }

        // 2ª Passagem: Marcar letras fora de posição (Amarelo) ou ausentes (Cinza)
        for (int i = 0; i < 5; i++) {
            if (resultado[i] != null) {
                continue; // Já foi verde
            }

            char c = palpite.charAt(i);
            boolean encontrou = false;

            for (int j = 0; j < 5; j++) {
                if (!usada[j] && secretaChars[j] == c) {
                    encontrou = true;
                    usada[j] = true;
                    break;
                }
            }

            if (encontrou) {
                resultado[i] = ANSI_AMARELO + " " + c + " " + ANSI_RESET;
            } else {
                resultado[i] = ANSI_CINZA + " " + c + " " + ANSI_RESET;
            }
        }

        // Imprime a linha estilizada
        System.out.print("Resultado: ");
        for (String bloco : resultado) {
            System.out.print(bloco + " ");
        }
        System.out.println("\n");
    }
}