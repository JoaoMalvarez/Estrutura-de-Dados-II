/* ===================================================================
1. Uma plataforma permite que usuários formem grupos de estudo. 
Inicialmente, cada usuário pertence a um grupo individual.
Implemente um programa que processe os seguintes comandos:

- UNION A B: une os grupos de A e B.
- SAME A B: verifica se A e B pertencem ao mesmo grupo (SIM ou NAO).
- SIZE A: informa a quantidade de usuários no grupo de A. 
=================================================================== */

public class Exercicio1 {
    private int[] parent;
    private int[] size;
    
    public Exercicio1(int n) {
        this.parent = new int[n + 1]; 
        // usuários 
        this.size = new int[n + 1];

        // cada usuário começa no próprio grupo
        for(int i = 0; i <= n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    public int find(int x) {
        if(parent[x] != x) parent[x] = find(parent[x]);
        return parent[x];
    }

    public void makeSet(int x) {
        parent[x] = x;
        size[x] = 1;
    }

    public void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a == b) return;
        if (size[a] < size[b]) {
            int temp = a;
            a = b;
            b = temp;
        }
        parent[b] = a;
        size[a] += size[b];
    }

    public void same(int a, int b) {
        a = find(a);
        b = find(b);
        if(a == b) {
            System.out.println("SIM");
            return;
        }
        System.out.println("NAO");
    }

    public int size(int a) {
        return size[find(a)];
    }
}