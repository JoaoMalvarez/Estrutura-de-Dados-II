public class Arvore {
    private No raiz;

    public Arvore() {
        this.raiz = null;
    }

    private void rotacaoEsq(No x) {
        No y = x.dir;
        x.dir = y.esq;
        if (y.esq != null) y.esq.pai = x;
        y.pai = x.pai;
        if (x.pai == null) raiz = y;
        else if (x == x.pai.esq) x.pai.esq = y;
        else x.pai.dir = y;
        y.esq = x;
        x.pai = y;
    }

    private void rotacaoDir(No y) {
        No x = y.esq;
        y.esq = x.dir;
        if (x.dir != null) x.dir.pai = y;
        x.pai = y.pai;
        if (y.pai == null) raiz = x;
        else if (y == y.pai.esq) y.pai.esq = x;
        else y.pai.dir = x;
        x.dir = y;
        y.pai = x;
    }

    private void corrigir(No x) {
        while (x.pai != null && x.pai.cor == true) { // pai vermelho
            No avo = x.pai.pai;
            if (x.pai == avo.esq) {
                No tio = avo.dir;
                if (tio != null && tio.cor == true) { // caso 1: tio vermelho
                    x.pai.cor = false;
                    tio.cor = false;
                    avo.cor = true;
                    x = avo;
                } else {
                    if (x == x.pai.dir) { // caso 2: tio preto, x filho direito
                        x = x.pai;
                        rotacaoEsq(x);
                    }
                    x.pai.cor = false; // caso 3: tio preto, x filho esquerdo
                    avo.cor = true;
                    rotacaoDir(avo);
                }
            } else { // espelhado: pai é filho direito do avô
                No tio = avo.esq;
                if (tio != null && tio.cor == true) { // caso 1
                    x.pai.cor = false;
                    tio.cor = false;
                    avo.cor = true;
                    x = avo;
                } else {
                    if (x == x.pai.esq) { // caso 2
                        x = x.pai;
                        rotacaoDir(x);
                    }
                    x.pai.cor = false; // caso 3
                    avo.cor = true;
                    rotacaoEsq(avo);
                }
            }
        }
        raiz.cor = false; // raiz sempre preta
    }

    public void inclusao(No x) {
        x.cor = true; // novo nó sempre entra vermelho
        x.esq = null;
        x.dir = null;

        if (raiz == null) {
            raiz = x;
            x.pai = null;
        } else {
            No p = raiz;
            No q = null;
            while (p != null) {
                q = p;
                if (x.chave < p.chave) p = p.esq;
                else if (x.chave > p.chave) p = p.dir;
                else {
                    System.out.printf("Nó de chave %d já existe!\n", x.chave);
                    return;
                }
            }
            x.pai = q;
            if (x.chave < q.chave) q.esq = x;
            else q.dir = x;
        }
        corrigir(x);
    }
}
