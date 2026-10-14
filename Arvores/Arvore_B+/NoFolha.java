public class NoFolha<T extends Comparable<T>, D> extends No<T> {
    public D[] dados;            // Vetor com os registros reais (ex: o objeto completo do cliente)
    public NoFolha<T, D> proximo; // A "cordinha" ligando à próxima folha da direita

    public NoFolha(int ordem) {
        super(ordem);
        this.dados = (D[]) new Object[ordem];
        this.proximo = null;
        this.folha = true;
    }
}