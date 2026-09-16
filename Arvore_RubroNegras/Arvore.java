public class Arvore {
    private No raiz;

    public Arvore() {
        this.raiz = raiz;
    }

    private void rotacaoEsq(No x) {
        No y = x.dir;
        x.dir = y.esq;

        if (y.esq != null) y.esq.pai = x;
        y.pai = x.pai;
        if(x.pai == null) raiz = y;
        else {
            if (x == x.pai.esq) x.pai.esq = y;
            else x.pai.dir = y;
        }
        y.esq = x;
        x.pai = y;
    }

    private void rotacaoDir(No y) {
        No x =  y.esq;
        y.esq = x.dir;

        if (x.dir != null) x.dir.pai = y;
        x.pai = y.pai;
        if(y.pai == null) raiz = x;
        else {
            if (y == y.pai.esq) y.pai.esq = x;
            else y.pai.dir = x;
        }
        x.dir = y;
        y.pai = x;
    }

    private void casos(No x) {
        No avo = x.pai.pai
        if (avo.esq == x.pai) { // tio na direita
            No tio = avo.dir;
        } else No tio = avo.esq; // tio na esquerda

        // caso 1:
        if (tio.cor == true) { // se tio é vermelho
            tio.cor = false;
            x.pai.cor = false;
            avo.cor = true;
            x = avo;
        }

        // caso 2:
        if (tio.cor == false && x == x.pai.dir) { // se tio é preto e o x é o filho direito
            x = x.pai;
            if (tio == null) {
                rotacaoDir(x);
                rotacaoEsq(x.pai);
            }
            rotacaoEsq(x);
        }
        
        // caso 3:
        if (tio.cor == false && x == x.pai.esq) {// se tio é preto e o x é o filho esquerdo
            x.pai.cor = false;
            avo.cor = true;
            if (tio == null) {
                rotacaoEsq(avo);
                if (avo.pai != null)    rotacaoDir(avo.pai);
            }
            rotacaoDir(avo);
        }

    }

    public void inclusao(No x) {
        if (raiz == null)
            x = raiz;
        else {
            No p = raiz;
            No q = null;
            while (p != null) {
                q = p;
                if (x.chave < p.chave)
                    p = p.esq;
                else if (x.chave > p.chave)
                    p = p.dir;
                else if (x.chave == p.chave) {
                    System.out.printf("Nó de chave %d já existe!\n", x.chave);
                    return;
                }
            }
            if (x.chave < q.chave)
                q.esq = x;
                x.pai = q;
            else if (chave > q.chave)
                q.dir = x;
                x.pai = q;
            if (x.pai.cor = false) casos(No x);
        }
    }
}