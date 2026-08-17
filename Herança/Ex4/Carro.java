public class Carro extends Veiculo {
    public String cor;
    public String modelo;

    public Carro(int ano, float preco, Motor motor, String cor, String modelo) {
        super(ano, preco, motor);
        this.cor = cor;
        this.modelo = modelo;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void exibirDadosCarro() {
        exibirDados();
        System.out.println("Cor: " + cor);
        System.out.println("Modelo: " + modelo);
    }
}