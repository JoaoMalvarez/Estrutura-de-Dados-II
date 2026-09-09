public class Arvore {
    private No raiz;
    private int he;
    private int hd;

    public Arvore() {
        this.raiz = null;
        this.he = 0;
        this.hd = 0;
    }

    public int altura() {
        if (he > hd) return he + 1;
        else if (hd > he) return hd + 1;
        else return he + 1;
    }

    public No insere(No p, int valor) {
        if (p == null) p = new No(valor);
        else if (valor < p.dado) { // esquerda
            p.esq = insere(p.esq, valor);
            switch(p.bal) {
                case 1: p.bal = 0;  break;
                case 2: p.bal = -1; break;
                case -1: p = caso1(p);  break;
            }
        }
        else if (valor > p.dado) {
            p.dir = insere(p.esq, valor);
            switch(p.bal) {
                case 1: p.bal = 0;  break;
                case 2: p.bal = -1; break;
                case -1: p = caso2(p);  break;
            }
        }
        return p;
    }

    public No caso1(No p) {
        No u, v;
        u = p.esq;

        //caso 1.1
        if(u.bal == -1) {
            p.esq = u.dir;
            u.dir = p;
            p.bal = 0; // ajustar o balanco de p
            p = u;
        }
        // caso 1.2 
        else {
            v = u.dir;
            u.dir = v.esq;
            p.esq = v.dir;
            v.esq = u;
            v.dir = p;

            if (v.bal == -1) p.bal = 1;
            else p.bal = 0;
            if (v.bal == 1) u.bal = -1;
            else u.bal = 0;
            p = v;
        }
        p.bal = 0;
        return p;
    }

    public No caso2(No p) {
        No z, y;
        z = p.dir;

        //caso 2.1
        if (z.bal == 1) {
            p.dir = z.esq;
            z.esq = p;
            p.bal = 0;
            p = z;
        }
        else {
            y = z.esq;
            z.esq = y.dir;
            p.dir = y.esq;
            y.esq = p;
            y.dir = z;

            if (y.bal == 1) p.bal = -1;
            else p.bal = 0;

            if (y.bal == -1) p.bal = 1;
            else p.bal = 0;
            p = y;
        }
        p.bal = 0;
        return p;
    }


}