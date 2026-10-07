import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // 1. Instancie o novo jogo aqui:
        Jogo wordle = new Wordle();
        Jogo jogoSenha = new JogoSenha();
        Jogo Velha = new Velha(); 
        
        int opcao = 0;

        while (opcao != 4) {
            System.out.println("\n========================");
            System.out.println("     MENU DE JOGOS      ");
            System.out.println("========================");
            System.out.println("1. " + wordle.getNome());
            System.out.println("2. " + jogoSenha.getNome());
            // 2. Atualize a exibição do menu:
            System.out.println("3. " + Velha.getNome()); 
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
                        jogoSenha.iniciar();
                        break;
                    case 3:
                        // 3. Chame o método iniciar() no case 3:
                       Velha.iniciar(); 
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