/* ============================================
Notas:
    > Para identificar em qual lugar do espaçamento 
    irá ficar se usa um 'mod m' na qual a chave vai 
    ser usada no mod

============================================ */

public class Hash {
    public int m; // tamanho do Hash
    public Espaco[] arm; // onde guarda os espaços

    public Hash(int m) {
        this.m = m;
        /* Cria o espaço */
        for(int i = 0; i < m; i++) {
            arm[i] = Espaco(i);
        }
    }

    public boolean inserir(Espaco x) {
        if(x < 0) return false;
        int chega = x % m;
        if(!arm[chega].dados.isFull()) {
            arm[chega].dados.insert(x, 100) // coloca um valor alto como 'pos' para sempre ser o ultimo na lista
            return true;
        } 
        System.out.println("Não tem espaço sobrando onde quer adicionar (no espaço: %d).", chega);
        return false;
    }
    
    // public boolean remover(Espaco) {}
    // public Espaco buscar(chave) {} // tem que ir até o espaço e ver se: o valor armazenado é a chave e se tem coisa dentro
}