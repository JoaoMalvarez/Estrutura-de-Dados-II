public class ArvoreAVL {
    private NoA raiz;

    public ArvoreAVl() {
        this.raiz = null;
    }


    // Altura - Recursão
    private int altura(NoA p) {
        if(p == null) return 0;
        int h = altura(p.dir);
        return h;
    }

    public int altura() {
        return altura(raiz);
    }

    // Insere - Recursão
    public NoA insere(NoA p, int n) {
        if(p == null) return new NoA(n);
        if(p.dado > n) {
            p.esq = insere(p.esq, n);
            p.balanceamento = altura(p.dir) - altura(p.esq);
            if (p.balanceamento == -2) 
                p = caso1(p);
        } else if(p.dado < n) {
            p.dir = insere(p.dir, n);
            p.balanceamento = altura(p.dir) - altura(p.esq);
            if(p.balanceamento == 2)
                p = caso2(p);
        }
        p.balanceamento = altura(p.dir) - altura(p.esq);
        return p;
    }

    // Rotações
    public No caso1(No p) {
        No u = p.esq;
        
        // caso 1.2 (Esquerda-Direita / Rotação Dupla)
        if (p.esq != null && p.esq.balanceamento == 1) {
            No v = u.dir;
            u.dir = v.esq;
            p.esq = v.dir;
            v.esq = u;
            v.dir = p;
            
            u.balanceamento = altura(u.dir) - altura(u.esq);
            p.balanceamento = altura(p.dir) - altura(p.esq);
            v.balanceamento = altura(v.dir) - altura(v.esq);
            p = v;
        } 
        // caso 1.1 (Esquerda-Esquerda / Rotação Simples)
        else {
            p.esq = u.dir;
            u.dir = p;
            
            p.balanceamento = altura(p.dir) - altura(p.esq);
            u.balanceamento = altura(u.dir) - altura(u.esq);
            p = u;
        }
        
        return p;
    }

    public No caso2(No p) {
        No z = p.dir;
        
        // caso 2.2 (Direita-Esquerda / Rotação Dupla)
        if (p.dir != null && p.dir.balanceamento == -1) {
            No y = z.esq;
            z.esq = y.dir;
            p.dir = y.esq;
            y.esq = p;
            y.dir = z;
            
            p.balanceamento = altura(p.dir) - altura(p.esq);
            z.balanceamento = altura(z.dir) - altura(z.esq);
            y.balanceamento = altura(y.dir) - altura(y.esq);
            p = y;
        } 
        // caso 2.1 (Direita-Direita / Rotação Simples)
        else {
            p.dir = z.esq;
            z.esq = p;
            
            p.balanceamento = altura(p.dir) - altura(p.esq);
            z.balanceamento = altura(z.dir) - altura(z.esq);
            p = z;
        }
        
        return p;
    }
}