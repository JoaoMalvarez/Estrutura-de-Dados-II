/* ============================================ 
* Objeto Nó
* Vai ser usado dentro da árvore b
============================================ */

public class No<T> {
    public int m;               // 'm' é para a ordem dada 
    public int atual;           // 'atual' é a quantidade atual de chaves no nó
    public boolean folha;       // 'folha' indica se o nó é uma folha (true = é uma // false = nao é)
    public Integer[] chaves;    // 'chaves' é um vetor que armazena as chaves guardados dentro do nó
    public No[] filhos;         // 'filhos' é um vetor que leva para cada nó filho

    public No(int m, boolean folha) {
        this.m = m;                                 // tamanho total dado pelo usuario
        this.folha = folha;                         // indica se o nó é uma folha pelo usuário
        this.atual = 0;                             // no esta sem chaves
        this.chaves = (T[]) new Integer[2 * m - 1]; // cria o vetor de chaves sendo 2m - 1 de tamanho (tamanho total de chaves)
        this.filhos = new No<T>[2 * m];             // cria o vetor de filhos, com o tamanho total sendo de 2m 
    }

// =============================================
// Função auxiliar para achar o index da chave desejada
// =============================================
    public int achaChave(int chave) {
        int index = 0;                                  // cria um index 0
        while(index < atual && chaves[index] < chave) { 
            index++;                                    // aumenta o index até ele chegar no limite ou achar
        }
        return index;                                   // retorna o index, nao o valor
    }
}