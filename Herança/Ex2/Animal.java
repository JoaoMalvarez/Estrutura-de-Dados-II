public class Animal {
    public String nome;
    public String cor;
    public int numeroPatas;

    public Animal (String nome, String cor, int numeroPatas) {
        this.nome = nome;
        this.cor = cor;
        this.numeroPatas = numeroPatas;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public int getNumeroPatas() {
        return numeroPatas;
    }

    public void setNumeroPatas(int numeroPatas) {
        this.numeroPatas = numeroPatas;
    }

    public void exibirDados() {
        System.out.println("Nome: " + nome);
        System.out.println("Cor: " + cor);
        System.out.println("Número de Patas: " + numeroPatas);
    }
}