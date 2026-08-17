public class Veiculo {
    public int ano;
    public float preco;

    public Veiculo (int ano, float preco, Motor motor) {
        Motor(motor);
        this.ano = ano;
        this.preco = preco;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public Motor getMotor() {
        return exibirDadosMotor();
    }

    public void setMotor(Motor motor) {
        this.motor = motor;
    }

    public void exibirDados() {
        System.out.println("Ano: " + ano);
        System.out.println("Preço: " + preco);
        exibirDadosMotor();
    }
}