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
        this.arm = new Espaco[m];
        for(int i = 0; i < m; i++) {
            arm[i] = new Espaco(i);
        }
    }

    public int getCasa(int dado) {
        if(dado < 0) return -1;
        return dado % m;
    }

    public boolean inserir(Espaco x) {
        if(x.chave < 0) return false;
        int chega = getCasa(x.chave)
        if(!arm[chega].dados.isFull()) {
            arm[chega].adiciona(x);
            return true;
        } 
        System.out.printf("Não tem espaço sobrando onde quer adicionar (no espaço: %d).", chega);
        return false;
    }

    public boolean remover(Espaco x) {
        if(x.chave < 0) return false;
        int casa = getCasa(x.chave);
        if(!arm[casa].dados.isEmpty()) {
            return arm[casa].dados.remove(x.chave);
        }
        return false;

    }
    // public Espaco buscar(chave) {} // tem que ir até o espaço e ver se: o valor armazenado é a chave e se tem coisa dentro
}