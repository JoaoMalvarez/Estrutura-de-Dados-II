public class Arvore {
    private No raiz;

    public Arvore() {
        this.raiz = null;
    }

    public void insere(int chave) {
        if (raiz == null)
            raiz = new No(chave);
        else {
            No p = raiz;
            No q = null;
            while (p != null) {
                q = p;
                if (chave < p.chave)
                    p = p.esq;
                else if (chave > p.chave)
                    p = p.dir;
                else if (chave == p.chave) {
                    System.out.printf("Chave %d já existe!\n", chave);
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
            System.out.printf("%d ", p.chave);
            preOrdem(p.esq);
            preOrdem(p.dir);
        }
    }

    public void preOrdem() {
        preOrdem(raiz);
        System.out.println();
    }

/* exercícios (árvores binárias) */

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
        return contaInterno(p.esq) + contaInterno(p.dir) + 1;
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
        while(p != null || !pilha.vazia()) {
            if (p != null) {
                System.out.printf("%d ", p.chave);
                pilha.push(p);
                p = p.esq;
            } else {
                p = pilha.pop();
                p = p.dir;
            }
        }
    }

/*ex 5*/
    public void percorreNivel() { // Ordem por nível
        No p = raiz;
        if (p == null) return;
        Fila<No> fila = new Fila<>();
        fila.push(p);
        for (int i = 0; i < contaNo(); i++) {
            if (fila.vazia()) break;
            p = fila.pop(); 
            System.out.printf("%d ", p.chave);
            if (p.esq != null) fila.push(p.esq);
            if (p.dir != null) fila.push(p.dir);
        }
    }

/*ex 6*/
    private No recInsere(int chave, No p) {
        if (p == null) return new No(chave);
        else {
            if (chave < p.chave) p.esq = recInsere(chave, p.esq);
            else if (chave > p.chave) p.dir = recInsere(chave, p.dir);
            else { System.out.printf("Chave %d já existe!\n", chave); }
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

/* extras */ 

    private No menorNo(No p) {
        while (p.esq != null) p = p.esq;
        return p;
    }

    private No maiorNo(No p) {
        while (p.dir != null) p = p.dir;
        return p;
    }

/* exercicios */

/* remoção recursiva */
    private No retirar(No p, int chave) {
        if (p == null) {
            System.out.printf("Chave não encontrada!\n");
            return null;
        } else {
            if(chave < p.chave) p.esq = retirar(p.esq, chave);
            else if(chave > p.chave) p.dir = retirar(p.dir, chave);
            else {
                if (p.dir == null && p.esq == null) return null;
                else if (p.dir == null) return p.esq;
                else if (p.esq == null) return p.dir;
                else {
                    No sucessor = menorNo(p.dir);
                    p.chave = sucessor.chave;
                    p.dir = retirar(p.dir, sucessor.chave);
                }
            }
            return p;
        }
    }

    public void remoçao(int chave) {
        raiz = retirar(raiz, chave);
    }

/* remoção iterativa */

    public void remocaoIterativa(int chave) {
        No pai = null;
        No p = raiz;
        while (p != null && p.chave != chave) {
            pai = p;
            if (chave < p.chave) p = p.esq;
            else p = p.dir;
        }
        if (p == null) {
            System.out.printf("Chave %d não existe!\n", chave);
            return;
        }
        if (p.esq != null && p.dir != null) {
            No paiSucessor = p;
            No sucessor = p.dir;
            while (sucessor.esq != null) {
                paiSucessor = sucessor;
                sucessor = sucessor.esq;
            }
            p.chave = sucessor.chave; 
            p = sucessor;
            pai = paiSucessor;
        }
        No filho = (p.esq != null) ? p.esq : p.dir;
        if (pai == null) {
            raiz = filho; 
        } else if (pai.esq == p) {
            pai.esq = filho;
        } else {
            pai.dir = filho;
        }
    }

/* Implementar o diagrama de barras, para mostrar os elementos da árvore binária de busca */

    private void imprimeBarra(No p, int nivel) {
        if (p == null) return;
        imprimeBarra(p.dir, nivel + 1);
        for (int i = 0; i < nivel; i++) System.out.print("    ");
        System.out.println(p.chave);
        imprimeBarra(p.esq, nivel + 1);
    }

    public void imprimeBarra() {
        imprimeBarra(raiz, 0);
    }
}