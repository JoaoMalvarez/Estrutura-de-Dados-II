public class Arvore {
    private No raiz;

    public Arvore() {
        this.raiz = null;
    }

    // coloca mais um nó na arvore
    public void inclusao(No x) {
        x.cor = true; // novo nó sempre entra vermelho
        x.esq = null; // cria os filhos como null
        x.dir = null;

        if (raiz == null) { // se a árvore não tem nó (nula) - cria com o nó novo
            raiz = x;
            x.pai = null;
        } else { // se a árvore não é nula
            No p = raiz; // cria caminhantes
            No q = null;
            while (p != null) { // até o caminhante da frente chegar num nó abaixo de uma folha
                q = p;
                if (x.chave < p.chave) p = p.esq; // se a chave é atual é maior: anda esq
                else if (x.chave > p.chave) p = p.dir; // se a chave atual é menor: anda dir
                else { // se a chave já existe: retorna 
                    System.out.printf("Nó de chave %d já existe!\n", x.chave);
                    return;
                }
            }
            x.pai = q; // set o q como o nó pai (já que ele está acima / atrás do nó p)
            if (x.chave < q.chave) q.esq = x; // se p estiver na esquerda: x = filho esquerda
            else q.dir = x; // se p estiver na direita: x = filho direita
        }
        corrigir(x); // vai corrigir as cores
    }

    private void corrigir(No x) {
        while (x.pai != null && x.pai.cor == true) { // enquanto o pai for vermelho e x nao for raiz
            No avo = x.pai.pai; // cria avo
            if (x.pai == avo.esq) { // se o pai for o filho esquerdo do avo
                No tio = avo.dir; // cria o tio, que está na direita
                if (tio != null && tio.cor == true) { // caso 1: tio vermelho
                    x.pai.cor = false; // pai preto
                    tio.cor = false; // tio preto
                    avo.cor = true; // avo vermelho
                    x = avo; // agora repete só que x agora é o avo
                } else {
                    if (x == x.pai.dir) { // caso 1.1: tio preto, x filho direito
                        x = x.pai;
                        rotacaoEsq(x);
                    }
                    x.pai.cor = false; // caso 1.2: tio preto, x filho esquerdo
                    avo.cor = true;
                    rotacaoDir(avo);
                }
            } else { // espelhado: pai é filho direito do avô
                No tio = avo.esq;
                if (tio != null && tio.cor == true) { // caso 1: tio vermelho
                    x.pai.cor = false;
                    tio.cor = false;
                    avo.cor = true;
                    x = avo;
                } else {
                    if (x == x.pai.esq) { // caso 1.2: tio preto, x filho esquerdo
                        x = x.pai;
                        rotacaoDir(x);
                    }
                    x.pai.cor = false; // caso 1.1: tio preto, x filho direito
                    avo.cor = true;
                    rotacaoEsq(avo);
                }
            }
        }
        raiz.cor = false; // raiz sempre preta
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

}
