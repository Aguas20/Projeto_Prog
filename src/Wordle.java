import java.util.Scanner;

public class Wordle extends Jogo {

    public Wordle() {
        super("Wordle");
    }

    @Override
    public void iniciar() {
        System.out.println("\n--- Bem-vindo ao Wordle! ---");
        // Aqui entra a lógica do Wordle (tentativas, palavras, etc.)
        System.out.println("Jogo em desenvolvimento... Pressiona ENTER para voltar ao menu.");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }
}