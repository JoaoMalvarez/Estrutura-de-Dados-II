public class Carro extends Veiculo {
    public String cor;
    public String modelo;

    public Carro(Veiculo ano, Veiculo preco, Motor motor, String cor, String modelo) {
        Veiculo(ano, preco);
        Motor(motor);
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
        exibirDadosMotor();
        System.out.println("Cor: " + cor);
        System.out.println("Modelo: " + modelo);
    }
}