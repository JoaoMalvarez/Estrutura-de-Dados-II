public class Cachorro extends Animal {
    public String raca;

    public Cachorro (String nome, String cor, int numeroPatas, String raca) {
        super(nome, cor, numeroPatas);
        this.raca = raca;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public void exibirDadosCachorro() {
        exibirDados();
        System.out.println("Raça: " + raca);
    }
}