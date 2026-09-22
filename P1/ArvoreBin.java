import java.util.*;

public class ArvoreBin {
    public NoB raiz;

    public ArvoreBin() {
        this.raiz = null;
    }

    // Insere - Recursão
    private NoB insert(int n, NoB x) {
        if(x == null) return new NoB(n);
        if(n > x.dado) insert(n, x.dir);
        else if(n < x.dado) insert(n, x.esq);
        else return; // caso ja tenha um com o mesmo dado na arvore
    }

    public void insert(int dado) {
        insert(dado, raiz);
    }

    // Insere - Sem Recursão
    public void insercao(int n) {
        if(raiz == null) raiz = new NoB(n);
        else {
            NoB p = raiz;
            NoB q = null;
            while(p != null) {
                q = p;
                if(n > p.dado) {
                    p = p.dir;
                } else if(n < p.dado) {
                    p = p.esq;
                } else {
                    System.out.printf("Chave %d já existe!\n", n);
                    return;
                }
            }
            if (q.esq == p) {
                q.esq = new NoB(n);
            } else if (q.dir == p) {
                q.dir = new NoB(n);
            }
        }
    }

    // Busca - Recursão
    private NoB busca(NoB p, int dado) {
        if (p != null) {
            if (dado < p.dado) return busca(p.esq, dado);
            else if (dado > p.dado) return busca(p.dir, dado);
        }
        return p;
    }

    public NoB busca(int dado) {
        NoB p = busca(raiz, dado);
        return p;
    }

    // Busca - Sem Recursão
    public NoB buscar(int dado) {
        if (raiz == null) return null;
        NoB x = raiz;
        while(x != null && x.dado != dado) {
            if(x.dado < dado) {
                x = x.dir;
            }
            else if(x.dado > dado) {
                x = x.esq;
            }
        }
        return x;
    }

    // Pre Ordem - Recursão (raiz > esq > dir)
    private void preOrdem(NoB p) {
        if (p != null) {
            System.out.printf("%d ", p.dado);
            preOrdem(p.esq);
            preOrdem(p.dir);
        }
    }

    public void preOrdem() {
        preOrdem(raiz);
    }

    // Pre Ordem - Sem Recursão (raiz > esq > dir)
    public void pre_Ordem() {
        if (raiz == null) return;

        Deque<NoB> pilha = new ArrayDeque<>();
        pilha.push(raiz);

        while(!pilha.isEmpty()) {
            NoB atual = pilha.pop();
            System.out.printf("%d, ", atual.dado);

            if(atual.dir != null) pilha.push(atual.dir);
            if(atual.esq != null) pilha.push(atual.esq);
        }
    }

    // In Ordem - Recursão (esq > raiz > dir)
    private void inOrdem(NoB p) {
        if (p != null) {
            inOrdem(p.esq);
            System.out.printf("%d, ", p.dado);
            inOrdem(p.dir);
        }
    }

    public void inOrdem() {
        inOrdem(raiz);
    }

    // In Ordem - Sem Recursão (esq > raiz > dir)
    public void in_Ordem() {
        if(raiz == null) return;
        Deque<NoB> pilha = new ArrayDeque<>();
        NoB atual = raiz;

        while(atual != null || !pilha.isEmpty()) {
            while (atual != null) {
                pilha.push(atual);
                atual = atual.esq;
            }
            atual = pilha.pop();
            System.out.printf("%d, ", atual.dado);
            atual = atual.dir;
        }
    }

    // Pós Ordem - Recursão (esq > dir > raiz)
    private void posOrdem(NoB p) {
        if(p != null) {
            posOrdem(p.esq);
            posOrdem(p.dir);
            System.out.printf("%d, ", p.dado);
        }
    }

    public void posOrdem() {
        posOrdem(raiz);
    }

    // Pos Ordem - Sem Recursão (esq > dir > raiz)
    public void pos_Ordem() {
        if(raiz == null) return;
        Deque<NoB> pilha1 = new ArrayDeque<>();
        Deque<NoB> pilha2 = new ArrayDeque<>();
        pilha1.push(raiz);

        while(!pilha1.isEmpty()) {
            NoB atual = pilha1.pop();
            pilha2.push(atual);

            if(atual.esq != null) pilha1.push(atual.esq);
            if(atual.dir != null) pilha1.push(atual.dir);
        }
        while(!pilha2.isEmpty()) {
            System.out.printf("%d, ", pilha2.pop().dado);
        }
    }

    // Menor Elemento - Recursão
    private NoB menor(NoB p) {
        // verifica se o proximo é nulo, pq se for, é o atual o menor
        if(p.esq != null) {
            return menor(p.esq);
        }
        return p;
    }

    public NoB menor() {
        return menor(raiz);
    }

    // Maior Elemento
    private NoB maior(NoB p) {
        // verifica se o proximo é nulo, pq se for, é o atual o maior
        if(p != null && p.dir != null) {
            return menor(p.dir);
        }
        return p;
    }

    public NoB maior() {
        return maior(raiz);
    }

    // Conta a quantidade de Nós - Recursão
    private int contaNo(NoB p) {
        if(p == null) return 0;
        int cEsq = contaNo(p.esq);
        int cDir = contaNo(p.dir);
        return cEsq + cDir + 1;
    }

    private int contaNoAlt(NoB p) {
        return contaNo(p.esq) + contaNo(p.dir) + 1;
    }

    public int contaNo() {
        int a = contaNo(raiz);
        int b = contaNoAlt(raiz);
        System.out.printf("Normal: %d\n", a);
        System.out.printf("Alternativo: %d\n", b);
    }

    // Conta a quantidade de Folhas - Recursão
    private int contaFolha(NoB p) {
        if(p == null) return 0;
        if(p.esq == null && p.dir == null) return 1;
        int e = contaFolha(p.esq);
        int d = contaFolha(p.dir);
        return e + d; // ou return contaFolha(p.dir) + contaFolha(p.esq);
    }

    public int contaFolha() {
        return contaFolha(raiz);
    }

    // Conta a quantidade de Nós internos sem serem Folhas - Recursão
    private int contaInt(NoB p) {
        if (p == null) return 0;
        if (p.esq == null && p.dir == null) return 0; // folha
        return 1 + contaInt(p.esq) + contaInt(p.dir);
    }

    public int contaInt() {
        return contaInt(raiz);
    }

    // Altura - Recursão
    private int altura(NoB p) {
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