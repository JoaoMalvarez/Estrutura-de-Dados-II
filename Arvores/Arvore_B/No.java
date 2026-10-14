public class No {
    public int m; // grau minimo (exemplo 4)
    public int atual; // numero atual de chaves dentro do nó
    public boolean folha; // sera true se for o nó folha
    public Integer[] chaves; // vetor de chaves
    public No[] filhos; // vetor de ponteiros dos filhos presentes no nó

    public No(int m, boolean folha) {
        this.m = m;
        this.folha = folha;
        this.atual = 0;

        // Em uma árvore B de grau t:
        // - O número máximo de chaves é (2 * t - 1)
        // - O número máximo de filhos é (2 * t)
        this.chaves = (T[]) new Integer[2 * t - 1];
        this.filhos = new No[2 * t];
    }

    public int achaChave(int chave) {
        int index = 0;
        // Avança o índice enquanto estiver dentro do limite 
        // e a chave procurada for maior que a chave atual do vetor
        while(index < atual && chaves[index] < chave) {
            index++;
        }
        return index;
    }
}