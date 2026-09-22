public class NoB {
    public int dado;
    public NoB esq;
    public NoB dir;

    public NoB() {
        this.dado = 0;
        this.esq = null;
        this.dir = null;
    }

    public NoB(int x) {
        this.dado = x;
        this.esq = null;
        this.dir = null;
    }
}