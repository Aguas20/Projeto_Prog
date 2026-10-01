import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Jogo wordle = new Wordle();
        int opcao = 0;

        while (opcao != 4) {
            System.out.println("\n========================");
            System.out.println("     MENU DE JOGOS      ");
            System.out.println("========================");
            System.out.println("1. " + wordle.getNome());
            System.out.println("2. Jogo 2 (Em breve)");
            System.out.println("3. Jogo 3 (Em breve)");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");

            if (scanner.hasNextInt()) {
                opcao = scanner.nextInt();
                scanner.nextLine(); // Limpa o buffer do teclado

                switch (opcao) {
                    case 1:
                        wordle.iniciar();
                        break;
                    case 2:
                        System.out.println("\nEm desenvolvimento...");
                        break;
                    case 3:
                        System.out.println("\nEm desenvolvimento...");
                        break;
                    case 4:
                        System.out.println("\nA encerrar o programa. Até à próxima!");
                        break;
                    default:
                        System.out.println("\nOpção inválida! Escolha um número de 1 a 4.");
                }
            } else {
                System.out.println("\nPor favor, digite um número inteiro válido!");
                scanner.nextLine();
            }
        }

        scanner.close();
    }
}