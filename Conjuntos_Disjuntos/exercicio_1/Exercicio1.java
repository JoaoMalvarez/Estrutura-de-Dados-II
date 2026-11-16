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
    
    public Exercicio1(int x) {
        this.parent = new int[n]; 
        // usuários 
        this.size = new int[n];

        // cada usuário começa no próprio grupo
        for(int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = i;
        }
    }

    public int find()
}