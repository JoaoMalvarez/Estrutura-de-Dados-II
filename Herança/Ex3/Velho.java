public class Velho extends Imovel {
    public double desconto;

    public Velho(String endereco, double preco, double desconto) {
        super(endereco, preco);
        this.desconto = desconto;
    }

    public double getDesconto() {
        return desconto;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }

    public double calcularPreco() {
        double total = getPreco() - getDesconto();
        return total;
    }
}