public class HashDobraEncadeamentoInterno {
    public int m;            // Tamanho total da tabela
    public Elemento[] tab;   // Tabela única
    public int livre;        // Ponteiro para o próximo espaço livre (começa no final da tabela)

    public HashDobraEncadeamentoInterno(int m) {
        this.m = m;
        this.tab = new Elemento[m];
        for (int i = 0; i < m; i++) {
            tab[i] = new Elemento(-1);
        }
        this.livre = m - 1; // Começa a buscar espaços livres a partir do último índice
    }

    // ----------------------------------------------------
    // FUNÇÃO HASH: MÉTODO DA DOBRA (Folding)
    // ----------------------------------------------------
    public int getCasa(int dado) {
        if (dado < 0) return -1;
        String s = String.valueOf(dado);
        int tamanhoBloco = 2; 
        int soma = 0;
        for (int i = 0; i < s.length(); i += tamanhoBloco) {
            int fim = Math.min(i + tamanhoBloco, s.length());
            String bloco = s.substring(i, fim);
            soma += Integer.parseInt(bloco);
        }
        return soma % m;
    }

    // ----------------------------------------------------
    // INSERÇÃO
    // ----------------------------------------------------
    public boolean inserir(int chave) {
        if (chave < 0) return false;
        int casa = getCasa(chave);
        // Caso 1: A posição hash calculada está vazia
        if (!tab[casa].ocupado) {
            tab[casa].chave = chave;
            tab[casa].ocupado = true;
            tab[casa].prox = -1;
            return true;
        }
        // Caso 2: Colisão! Percorre a cadeia para verificar duplicatas e achar o último nó
        int atual = casa;
        while (true) {
            if (tab[atual].chave == chave) {
                return false; // Chave duplicada não permitida
            }
            if (tab[atual].prox == -1) {
                break;
            }
            atual = tab[atual].prox;
        }
        while (livre >= 0 && tab[livre].ocupado) {
            livre--;
        }
        if (livre < 0) {
            System.out.println("Erro: Tabela Hash totalmente cheia!");
            return false;
        }
        int novoIndice = livre;
        tab[novoIndice].chave = chave;
        tab[novoIndice].ocupado = true;
        tab[novoIndice].prox = -1;
        tab[atual].prox = novoIndice;
        return true;
    }

    // ----------------------------------------------------
    // BUSCA
    // ----------------------------------------------------
    public int buscar(int chave) {
        if (chave < 0) return -1;
        int casa = getCasa(chave);
        int atual = casa;
        while (atual != -1) {
            if (tab[atual].ocupado && tab[atual].chave == chave) {
                return atual; 
            }
            atual = tab[atual].prox;
        }
        return -1; 
    }

    // ----------------------------------------------------
    // REMOÇÃO
    // ----------------------------------------------------
    public boolean remover(int chave) {
        if (chave < 0) return false;
        int casa = getCasa(chave);

        int atual = casa;
        int anterior = -1;

        while (atual != -1) {
            if (tab[atual].ocupado && tab[atual].chave == chave) {
                if (anterior != -1) {
                    tab[anterior].prox = tab[atual].prox;
                } 
                tab[atual].ocupado = false;
                tab[atual].chave = -1;
                tab[atual].prox = -1;
                if (atual > livre) {
                    livre = atual;
                }
                return true;
            }
            anterior = atual;
            atual = tab[atual].prox;
        }
        return false;
    }
}