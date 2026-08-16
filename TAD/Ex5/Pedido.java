public class Pedido <Produto> {
    private <Produto> produto; // lista de produtos do pedido

    public Pedido() {
        this.produto = null;
    }

    public <Produto> adicionarProduto(Produto produto) {
        // adicionar um produto na lista de produtos do pedido
        ListaProduto<Produto> novoProduto = new ListaProduto<>(produto);
        novoProduto.setProximo(this.produto);
        this.produto = novoProduto;
    }

    public float calcularValor() {
       // soma dos valores de todos os produtos do pedido
        ListaProduto<Produto> atual = this.produto;
        float soma = 0;
        while (atual != null) {
            soma += (float) atual.getProduto().getPreco();
            atual = atual.getProximo();
        }
        return soma; 
    }
}