public abstract class Jogo {
    private String nome;

    public Jogo(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    // Cada jogo que herdar desta classe é obrigado a ter o seu próprio método iniciar()
    public abstract void iniciar();
}