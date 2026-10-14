public class NoInterno<T extends Comparable<T>> extends No<T> {
    public NoBPlus<T>[] filhos; // Ponteiros para outros nós (internos ou folhas)

    public NoInterno(int ordem) {
        super(ordem);
        // O vetor de filhos tem tamanho 'ordem + 1'
        this.filhos = new NoBPlus[ordem + 1];
        this.folha = false;
    }
}