public class Cofre <Moeda> {
    private Lista<Moeda> moedas; // lista de moedas no cofre

    public Cofre() {
        this.moedas = null;
    }

    public void adicionar(Moeda moeda) {
        // adicionar uma moeda na lista de moedas do cofre
        Lista<Moeda> novaMoeda = new Lista<>(moeda);
        novaMoeda.setProximo(this.moedas);
        this.moedas = novaMoeda;
    }

    public float calcularTotal() {
        // soma dos valores de todas as moedas do cofre
        Lista<Moeda> atual = this.moedas;
        float soma = 0;
        while (atual != null) {
            soma += (float) atual.getMoeda().getValor();
            atual = atual.getProximo();
        }
        return soma; 
    }
}