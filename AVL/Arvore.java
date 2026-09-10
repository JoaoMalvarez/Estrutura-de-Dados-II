public class Arvore {
    private No raiz;

    public Arvore() {
        this.raiz = null;
    }

    public int altura() {
        return altura(raiz);
    }

    private int altura(No p) {
        if (p == null) return -1;
        int hesq = altura(p.esq);
        int hdir = altura(p.dir);
        return (hesq > hdir) ? hesq + 1 : hdir + 1;
    }

    public No insere(No p, int valor) {
        if (p == null) {
            return new No(valor);
        }
        
        if (valor < p.dado) {
            p.esq = insere(p.esq, valor);
            p.bal = altura(p.dir) - altura(p.esq);
            if (p.bal == -2) {
                p = caso1(p);
            }
        } else if (valor > p.dado) {
            p.dir = insere(p.dir, valor);
            p.bal = altura(p.dir) - altura(p.esq);
            if (p.bal == 2) {
                p = caso2(p);
            }
        }
        
        p.bal = altura(p.dir) - altura(p.esq);
        return p;
    }

    public No caso1(No p) {
        No u = p.esq;
        
        // caso 1.2 (Esquerda-Direita / Rotação Dupla)
        if (p.esq != null && p.esq.bal == 1) {
            No v = u.dir;
            u.dir = v.esq;
            p.esq = v.dir;
            v.esq = u;
            v.dir = p;
            
            u.bal = altura(u.dir) - altura(u.esq);
            p.bal = altura(p.dir) - altura(p.esq);
            v.bal = altura(v.dir) - altura(v.esq);
            p = v;
        } 
        // caso 1.1 (Esquerda-Esquerda / Rotação Simples)
        else {
            p.esq = u.dir;
            u.dir = p;
            
            p.bal = altura(p.dir) - altura(p.esq);
            u.bal = altura(u.dir) - altura(u.esq);
            p = u;
        }
        
        return p;
    }

    public No caso2(No p) {
        No z = p.dir;
        
        // caso 2.2 (Direita-Esquerda / Rotação Dupla)
        if (p.dir != null && p.dir.bal == -1) {
            No y = z.esq;
            z.esq = y.dir;
            p.dir = y.esq;
            y.esq = p;
            y.dir = z;
            
            p.bal = altura(p.dir) - altura(p.esq);
            z.bal = altura(z.dir) - altura(z.esq);
            y.bal = altura(y.dir) - altura(y.esq);
            p = y;
        } 
        // caso 2.1 (Direita-Direita / Rotação Simples)
        else {
            p.dir = z.esq;
            z.esq = p;
            
            p.bal = altura(p.dir) - altura(p.esq);
            z.bal = altura(z.dir) - altura(z.esq);
            p = z;
        }
        
        return p;
    }
}