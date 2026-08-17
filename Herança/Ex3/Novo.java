public class Novo extends Imovel {
    public double adicional;

    public Novo(String endereco, double preco, double adicional) {
        super(endereco, preco);
        this.adicional = adicional;
    }

    public double getAdicional() {
        return adicional;
    }

    public void setAdicional(double adicional) {
        this.adicional = adicional;
    }

    public double calcularPreco() {
        double total = getPreco() + getAdicional();
        return total;
    }
}