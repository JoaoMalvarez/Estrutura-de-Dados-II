public abstract class No<T extends Comparable<T>> {
    public int atual;        // Número atual de chaves no nó
    public T[] chaves;       // Vetor de chaves
    public boolean folha;

    public No(int ordem) {
        // Na Árvore B+, a ordem define a capacidade máxima
        this.chaves = (T[]) new Comparable[ordem];
        this.atual = 0;
    }
}