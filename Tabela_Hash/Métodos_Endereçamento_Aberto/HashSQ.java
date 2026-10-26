/* ============================================
Notas:
    > Sondagem Quadratica: Se uma posiçãoo estiver ocupada, tente a
    próxima!
    > Não se utiliza Listas

============================================ */

public class HashSL {
    public int m; // tamanho do Hash
    public Espaco[] arm; // onde guarda os espaços
    public int c1;
    public int c2;

    public HashSL(int m, int c1, int c2) {
        this.m = m;
        /* Cria o espaço */
        this.arm = new Espaco[m];
        for(int i = 0; i < m; i++) {
            arm[i] = new EspacoVazio(0);
        }
        this.c1 = c1;
        if(c2 != 0) this.c2 = c2;
        else this.c2 = 1;
    }

/* calcula a posição inicial por uma conta, 
   usarei o de metodo de divisao, mas pode usar qlqr conta */
    private int h(int chave) { 
        if(dado < 0) return -1;
        return chave % m;
    }

    // A rosolução da Sondagem Linear
    public int getCasa(int dado) {
        if(dado < 0) return -1;
        for(int i = 0; i < m; i++) {
            int h = h(dado);
            int j = c1 * i;
            int k = i * i;
            k = k * c2;
            h = h + j + k;
            h = h % m;
            if(arm[h].comDado == false) { // se não tiver um dado dentro
                return h;
            }
        }
    }

    public int pegaCasa(int dado) {
        if(dado < 0) return -1;
        for (int i = 0; i < m; i++) {
            int h = h(dado);
            int j = c1 * i;
            int k = i * i;
            k = k * c2;
            h = h + j + k;
            h = h % m;
            if(arm[h].comDado == true) { // se tiver dado dentro
                if(arm[h].chave == chave) { // e se o dado for a chave 
                    return h;
                }
            }
        }
    }

    public boolean insere(int x) {
        if (x < 0) return false;
        int casa = getCasa(x);
        // Se não estiver cheio 
        if (arm[casa].comDado == false) { // umaa verificação extra (embora dentro do getCasa já faz isso e pega a pos)
            arm[casa].chave = x; // adiciona a chave
            arm[casa].comDado = true; // fala que tem dado
            return true;
        }
        return false; // Espaço cheio
    }

    public boolean remover(int chave) {
        if (chave < 0) return false;
        int j = pegaCasa(chave);
        if(arm[j].comDado == true) { // se tiver dado dentro
            if(arm[j].chave == chave) { // e se o dado for a chave remove
                arm[j].chave = 0;
                arm[j].comDado = false;
                return true;
            }
        }
        return false;
    }

    public Espaco buscar(int chave) {
        if (chave < 0) return null;
        int casa = pegaCasa(chave);
        
        if(arm[casa].comDado == true) { // se tiver dado dentro
            if(arm[casa].chave == chave) { // e se o dado for a chave
            return arm[casa];
            }
        }
        return null; // Não encontrou
    }
}