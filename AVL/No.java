public class No {
    public int dado;
    public No esq;
    public No dir;
    public int bal;

    public No() {
        this.dado = 0;
        this.esq = null;
        this.dir = null;
        this.bal = 0;
    }

    public No(int v) {
        this.dado = v;
        this.esq = null;
        this.dir = null;
        this.bal = 1;
    }

    public No(int v, No esq, No dir, int bal) {
        this.dado = v;
        this.esq = esq;
        this.dir = dir;
        this.bal = bal;
    }

    public No(int v, No esq, No dir) {
        this.dado = v;
        this.esq = esq;
        this.dir = dir;
        this.bal = 0;
    }
}