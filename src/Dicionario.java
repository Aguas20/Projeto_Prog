import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Dicionario {
    private Set<String> palavrasValidas = new HashSet<>();
    private List<String> listaParaSorteio = new ArrayList<>();
    private Random random = new Random();

    public Dicionario() {
        carregarPalavras();
    }

    private void carregarPalavras() {
        // Vai buscar o ficheiro diretamente à pasta src sem depender de caminhos relativos
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("palavras.txt")) {
            if (is == null) {
                System.out.println("ERRO: O ficheiro palavras.txt não foi encontrado dentro de src!");
                return;
            }

            try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
                String linha;
                while ((linha = br.readLine()) != null) {
                    linha = linha.trim().toUpperCase();
                    if (linha.length() == 5) {
                        palavrasValidas.add(linha);
                        listaParaSorteio.add(linha);
                    }
                }
            }

            System.out.println("Sucesso: Foram carregadas " + palavrasValidas.size() + " palavras.");

        } catch (Exception e) {
            System.out.println("Erro ao carregar o ficheiro: " + e.getMessage());
        }
    }

    public boolean ehPalavraValida(String palavra) {
        return palavrasValidas.contains(palavra.toUpperCase());
    }

    public String sortearPalavra() {
        if (listaParaSorteio.isEmpty()) {
            return "MANTA";
        }
        return listaParaSorteio.get(random.nextInt(listaParaSorteio.size()));
    }
}