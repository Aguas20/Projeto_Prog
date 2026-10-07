import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Dicionario {
    // Lista dinâmica (ArrayList) com todas as palavras de 5 letras do ficheiro
    private ArrayList<String> palavras = new ArrayList<>();

    public Dicionario() {
        carregarPalavras();
    }

    private void carregarPalavras() {
        // Verifica se o ficheiro existe (classe File)
        // Tenta primeiro na pasta do projeto e depois dentro de src
        String nomeArquivo = "palavras.txt";
        File arquivo = new File(nomeArquivo);

        if (!arquivo.exists()) {
            nomeArquivo = "src/palavras.txt";
            arquivo = new File(nomeArquivo);
        }

        if (!arquivo.exists()) {
            System.out.println("ERRO: O ficheiro palavras.txt não foi encontrado!");
            return;
        }

        // try-with-resources: o BufferedReader é fechado automaticamente
        try (BufferedReader leitor = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;

            // Lê o ficheiro linha a linha
            while ((linha = leitor.readLine()) != null) {
                linha = linha.trim().toUpperCase();

                if (linha.length() == 5) {
                    palavras.add(linha);
                }
            }

            System.out.println("Sucesso: Foram carregadas " + palavras.size() + " palavras.");

        } catch (IOException e) {
            System.out.println("Erro ao carregar o ficheiro: " + e.getMessage());
        }
    }

    public boolean ehPalavraValida(String palavra) {
        // contains() do ArrayList
        return palavras.contains(palavra.toUpperCase());
    }

    public String sortearPalavra() {
        if (palavras.isEmpty()) {
            return "MANTA";
        }

        int indice = (int) (Math.random() * palavras.size());
        return palavras.get(indice);
    }
}