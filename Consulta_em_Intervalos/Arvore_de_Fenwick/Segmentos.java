public class Segmentos {
    private int[] arvore;
    private int n;

    public Segmentos(int[] v) {
        this.n = v.length;
        this.arvore = new int[2 * n];

        constroi(v, this.arvore); // Constoi a arvore
    }

    public void constroi(int []v, int []tree) {
        for(int i = 0; i < n; i++) tree[i+n] = v[i];
        for(int i = n-1; i >= 1; i--) tree[i] = tree[2*i] + tree[2*i+1];
    }

    public int soma(int a, int b) {
        a += n; b += n;
        int s = 0;
        while (a <= b){
            if (a%2 == 1) s += tree[a++];
            if (b%2 == 0) s += tree[b--];
            a/=2; b/=2;
        }
        return s;
        }
}