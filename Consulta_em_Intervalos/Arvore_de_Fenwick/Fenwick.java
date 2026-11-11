public class Fenwick {
    private int[] arvore;
    private int n;

    public Fenwick(int[] v) {
        this.n = v.length;
        this.arvore = new(int[this.n + 1]);

        for(int i = 0; i < n; i++) {
            aumenta(i + 1, v[i]);
        }
    }

    public int soma(int k) {
        int s = 0;
        while (k >= 1){
            s += tree[k];
            k -= k&-k;
        }
        return s;
    }

    public void aumenta(int k, int x) {
        while (k <= n){
            tree[k] += x;
            k += k&-k;
        }
    }

    // Método opcional para consultar um intervalo
    public int intervalo(int l, int r) {
        return soma(r) - soma(l - 1);
    }
}