public class No {
    public int chave = 0;
    public No esq = null;
    public No dir = null;

    public No() {
        this.chave = 0;
        this.esq = null;
        this.dir = null;
    }

    public No(int v) {
        this.chave = v;
        this.esq = null;
        this.dir = null;
    }

    public No(int v, No esq, No dir) {
        this.chave = v;
        this.esq = esq;
        this.dir = dir;
    }

    public int getChave() {
        return chave;
    }

    public No getEsq() {
        return esq;
    }

    public No getDir() {
        return dir;
    }

    public void setChave(int v) {
        this.chave = v;
    }

    public void setEsq(No esq) {
        this.esq = dir;
    }

    public void setDir(No dir) {
        this.dir = dir;
    }
}