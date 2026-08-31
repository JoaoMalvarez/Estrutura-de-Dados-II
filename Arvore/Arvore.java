public class Arvore {
  private No raiz;

    public Arvore() {
        this.raiz = null
    }

    public void insere(int chave) {
        if (raiz = null)
            raiz = new No(chave);
        else {
            No p = raiz;
            while (p != null) {
                q = p;
                if (chave < p.chave)
                    p = p.esq;
                else if (chave > p.chave)
                    p = p.dir;
                else if (chave == p.chave) {
                    System.out.prinf("Chave %d já existe!\n", chave);
                    return;
                }
            }
            if (chave < q.chave)
                q.esq = new No(chave);
            else if (chave > q.chave)
                q.dir = new No(chave);
        }
    }

    public No busca(No p, int chave) {
        if (p != null) {
        if (chave < p.chave)
            return busca(p.esq, chave);
        else if (chave > p.chave)
            return busca(p.dir, chave);
        }
        return p;
    }

    public Boolean busca(int chave){
        return busca(raiz, chave) != null;
    }

    private void preOrdem(No p) {
        if (p != null) {
            System.out.prinf("%d", p.chave);
            preOrdem(p.esq);
            preOrdem(p.dir);
        }
    }

    public void preOrdem() {
        preOrdem(raiz);
    }

/*ex 1*/
    private int contaNo(No p) {
        if (p == null) return 0;
        int contaEsq = contaNo(p.esq);
        int contaDir = contaNo(p.dir);
        return contaEsq + contaDir + 1;    
    }

    private int contaAlternativo(No p) {
        if (p == null) return 0;
        return contaNo(p.esq) + contaNo(p.dir) + 1;
    }

    public int contaNo() {
        return contaNo(raiz);
    }
    
/*ex 2*/
    private int contaFolha(No p) {
        if (p == null) return 0;
        if (p.esq == null && p.dir == null) return 1;
        return contaFolha(p.esq) + contaFolha(p.dir);
    }

    public int contaFolha() {
        return contaFolha(raiz);
    }

/*ex 3*/
    private int contaInterno(No p) {
        if(p == null) return 0;
        if (p.esq == null && p.dir == null) return 0;
        return contaInterno(p.esq) + contaFolha(p.dir) + 1;
    }

    private int contaInternoAlternativo(No p) {
        return contaNo() - contaFolha(); 
    }

    public int contaInterno() {
        return contaInterno(raiz);
    }
/*ex 4*/
    public void preOrdemI() {
        No p = raiz;
        Pilha<No> pilha = new Pilha<>();
        while(p != null || pilha.vazia()) {
            System.out.println("%d", p.chave) {
                pilha.push(p);
                p = p.esq;
            } else {
                p = pilha.pop();
                p = p.dir;
            }
        }
    }

/*ex 5*/
    public void percorreNivel(No p) { // Ordem: 25, 10, 30, 5, 20, 27, 50, 2, 29
        No p = raiz;
        Fila<No> fila = new Fila<>();
        push(p); // coloca na fila a raiz
        for (int i = 0; i < contaNo(); i++) {
            p = fila.pop(p); 
            System.out.println("%d", p.chave);
            if (p.esq != null) push(p.esq); // coloca na fila o filho da esquerda
            if (p.dir != null) push(p.dir); // coloca na fila o filho da direita
        }
    }

/*ex 6*/
    private No recInsere(int chave, No p) {
        if (p == null) return new No(chave);
        else {
            if (chave < p.chave) p.esq = recInsere(chave, p.esq);
            else if (chave > p.chave) p.dir = recInsere(chave, p.dir);
            else { System.out.prinf("Chave %d já existe!\n", chave); }
        }
        return p;
    }

    public void insercao(int chave) {
        raiz = recInsere(chave, raiz);
    }

/*ex 7*/
    private int altura(No p) {
        if (p == null) return 0;
        int hesq, hdir;
        hesq = altura(p.esq);
        hdir = altura(p.dir);
        if (hesq > hdir) return hesq + 1;
        else return hdir + 1;
    }

    public int altura() {
        return altura(raiz);
    }
}