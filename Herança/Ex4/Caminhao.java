public class Caminhao extends Veiculo {
    public float comprimento;

    public Caminhao(int ano, float preco, Motor motor, float comprimento) {
        super(ano, preco, motor);
        this.comprimento = comprimento;
    }

    public float getComprimento() {
        return comprimento;
    }

    public void setComprimento(float comprimento) {
        this.comprimento = comprimento;
    }


    public void exibirDadosCaminhao() {
        exibirDados();
        System.out.println("Comprimento: " + comprimento);
    }
}