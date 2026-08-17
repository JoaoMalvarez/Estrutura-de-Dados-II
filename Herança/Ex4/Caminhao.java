public class Caminhao extends Veiculo {
    public float comprimento;

    public Carro(Veiculo ano, Veiculo preco, Motor motor, float comprimento) {
        Veiculo(ano, preco);
        Motor(motor);
        this.comprimento = comprimento;
    }

    public String getComprimento() {
        return comprimento;
    }

    public void setComprimento(float comprimento) {
        this.comprimento = comprimento;
    }


    public void exibirDadosCaminhao() {
        exibirDados();
        exibirDadosMotor();
        System.out.println("Comprimento: " + comprimento);
    }
}