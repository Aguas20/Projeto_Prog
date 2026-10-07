import java.util.Random;
import java.util.Scanner;

public class JogoSenha extends Jogo {

    private static final int TAMANHO_SENHA = 4;
    private static final int MAX_TENTATIVAS = 10;

    public JogoSenha() {
        super("Jogo da Senha");
    }

    @Override
    public void iniciar() {

        Scanner scanner = new Scanner(System.in);

        int[] senhaSecreta = gerarSenha();
        int tentativasRestantes = MAX_TENTATIVAS;
        boolean venceu = false;

        System.out.println("\n=================================");
        System.out.println("        JOGO DA SENHA");
        System.out.println("=================================");
        System.out.println("Descubra a senha de 4 numeros em "
                + MAX_TENTATIVAS + " tentativas.");
        System.out.println("Os numeros da senha nao se repetem.");
        System.out.println();

        while (tentativasRestantes > 0 && !venceu) {

            System.out.print(
                    "Tentativa (" +
                    (MAX_TENTATIVAS - tentativasRestantes + 1) +
                    "/" + MAX_TENTATIVAS +
                    "): "
            );

            String entrada = scanner.nextLine().trim();

            if (!entrada.matches("\\d{4}")) {

                System.out.println(
                        "Digite exatamente 4 numeros!\n"
                );

                continue;
            }

            if (possuiRepetidos(entrada)) {

                System.out.println(
                        "Os numeros nao podem se repetir!\n"
                );

                continue;
            }

            int[] palpite = converterParaArray(entrada);

            if (senhaCorreta(palpite, senhaSecreta)) {

                venceu = true;
                break;
            }

            exibirResultado(palpite, senhaSecreta);

            tentativasRestantes--;
        }

        System.out.println("\n---------------------------------");

        if (venceu) {

            System.out.println(
                    "PARABENS! Voce descobriu a senha: "
                    + senhaParaTexto(senhaSecreta)
            );

        } else {

            System.out.println(
                    "Fim das tentativas! A senha era: "
                    + senhaParaTexto(senhaSecreta)
            );
        }

        System.out.println("---------------------------------");

        System.out.print(
                "\nPressione ENTER para voltar ao menu..."
        );

        scanner.nextLine();
    }

    private int[] gerarSenha() {

        Random random = new Random();
        int[] senha = new int[TAMANHO_SENHA];

        for (int i = 0; i < TAMANHO_SENHA; i++) {

            int numero;
            boolean repetido;

            do {

                numero = random.nextInt(10);
                repetido = false;

                for (int j = 0; j < i; j++) {

                    if (senha[j] == numero) {

                        repetido = true;
                        break;
                    }
                }

            } while (repetido);

            senha[i] = numero;
        }

        return senha;
    }

    private boolean possuiRepetidos(String entrada) {

        for (int i = 0; i < entrada.length(); i++) {

            for (int j = i + 1; j < entrada.length(); j++) {

                if (entrada.charAt(i) == entrada.charAt(j)) {

                    return true;
                }
            }
        }

        return false;
    }

    private int[] converterParaArray(String entrada) {

        int[] numeros = new int[TAMANHO_SENHA];

        for (int i = 0; i < TAMANHO_SENHA; i++) {

            numeros[i] =
                    Character.getNumericValue(
                            entrada.charAt(i)
                    );
        }

        return numeros;
    }

    private boolean senhaCorreta(
            int[] palpite,
            int[] senhaSecreta) {

        for (int i = 0; i < TAMANHO_SENHA; i++) {

            if (palpite[i] != senhaSecreta[i]) {

                return false;
            }
        }

        return true;
    }

    private void exibirResultado(
            int[] palpite,
            int[] senhaSecreta) {

        int posicoesCorretas = 0;
        int numerosPosicaoErrada = 0;

        for (int i = 0; i < TAMANHO_SENHA; i++) {

            if (palpite[i] == senhaSecreta[i]) {

                posicoesCorretas++;

            } else {

                for (int j = 0; j < TAMANHO_SENHA; j++) {

                    if (palpite[i] == senhaSecreta[j]) {

                        numerosPosicaoErrada++;
                        break;
                    }
                }
            }
        }

        System.out.println(
                "Numeros na posicao correta: "
                + posicoesCorretas
        );

        System.out.println(
                "Numeros corretos na posicao errada: "
                + numerosPosicaoErrada
        );

        System.out.println();
    }

    private String senhaParaTexto(int[] senha) {

        String resultado = "";

        for (int numero : senha) {

            resultado += numero;
        }

        return resultado;
    }
}
