public class No {
    public int chave = 0;
    public No esq = null;
    public No dir = null;
    public Boolean cor = false; // false = negra  / e /  true = vermelha
    public No pai = null;

    public No() {
        this.chave = 0;
        this.esq = null;
        this.dir = null;
        this.cor = false;
        this.pai = null;
    }

    public No(int v) {
        this.chave = v;
        this.esq = null;
        this.dir = null;
        this.cor = false;
        this.pai = null;
    }
}

/* Propriedades:

    - Todo nó da arvore ou é vermelho ou é preto
    - A  raiz é preta
    - As folhas (null) são pretas
    - Se um nó é vermelho significa que os seus filhos são pretos
    - Para todo nó, todos os caminhos do nó até a folha descendentes
      contem a mesma quantidade de nós pretos

*/