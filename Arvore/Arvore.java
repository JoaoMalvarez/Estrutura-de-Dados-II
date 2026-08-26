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

    private int contaInterno() {
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
    public void percorreNivel(No p) {
        No p = raiz;
        int nivel = 1;
        for(int i = 0; i < contaNo(); i++) {
            for(int j = 0; j < nivel; j++) {
            nivel = nivel * 2;
            while(i == )
            }
            System.out.println("Nivel: " + i);
        }
    }


}