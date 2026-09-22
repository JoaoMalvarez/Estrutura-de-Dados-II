public class NoA {
    private int dado;
    private NoA esq;
    private NoA dir;
    private int balanceamento;

    public NoA() {
        this.dado = 0;
        this.esq = null;
        this.dir = null;
        this.balanceamento = 0;
    }

    public NoA(int v) {
        this.dado = v;
        this.esq = null;
        this.dir = null;
        this.bal = 1;
    }
}