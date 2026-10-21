public class Hash {
    public int mTotal;       // Tamanho total da tabela (Base + Overflow)
    public int mBase;        // Tamanho da Zona Primária (Base)
    public int mOverflow;    // Tamanho da Zona de Colisão (Overflow)
    public Espaco[] arm;     // Vetor que guarda os espaços (contendo as listas)
    public int[] prox;       // Vetor de ponteiros para o encadeamento interior das colisões
    public boolean[] ocupado;// Vetor auxiliar para controlar se o espaço está ocupado
    public int livreOverflow;// Ponteiro para o próximo espaço livre na zona de overflow
    public float A;          // Constante da multiplicação (ex: 0.618033)

    public Hash(int mBase, int mOverflow, float A) {
        this.mBase = mBase;
        this.mOverflow = mOverflow;
        this.mTotal = mBase + mOverflow;
        this.A = A;
        this.arm = new Espaco[mTotal];
        this.prox = new int[mTotal];
        this.ocupado = new boolean[mTotal];
        
        for (int i = 0; i < mTotal; i++) {
            arm[i] = new Espaco(i);
            prox[i] = -1;       // -1 = fim da cadeia
            ocupado[i] = false;
        }
        this.livreOverflow = mBase;
    }

    // Função Hash por Multiplicação (aplicada sobre a Zona Base)
    public int getCasa(int dado) {
        if (dado < 0) return -1;
        float h = dado * A;
        h = h % 1;
        h = h * mBase;
        return (int) h;
    }

    // ----------------------------------------------------
    // INSERÇÃO
    // ----------------------------------------------------
    public boolean inserir(int chave) {
        if (chave < 0) return false;
        int casa = getCasa(chave);
        // Caso 1: A posição primária está vazia
        if (!ocupado[casa]) {
            arm[casa].chave = chave;
            arm[casa].adiciona(new Espaco(chave));
            ocupado[casa] = true;
            prox[casa] = -1;
            return true;
        }
        // Caso 2: Colisão! Percorre o encadeamento para achar o fim ou chave duplicada
        int atual = casa;
        while (true) {
            if (arm[atual].chave == chave) {
                return false; 
            }
            if (prox[atual] == -1) {
                break;
            }
            atual = prox[atual];
        }
        if (livreOverflow >= mTotal) {
            System.out.println("Erro: Zona de colisão (Overflow) esgotada!");
            return false;
        }
        int novoIndice = livreOverflow;
        arm[novoIndice].chave = chave;
        arm[novoIndice].adiciona(new Espaco(chave));
        ocupado[novoIndice] = true;
        prox[novoIndice] = -1;
        prox[atual] = novoIndice;
        while (livreOverflow < mTotal && ocupado[livreOverflow]) {
            livreOverflow++;
        }
        return true;
    }

    // ----------------------------------------------------
    // BUSCA
    // ----------------------------------------------------
    public Espaco buscar(int chave) {
        if (chave < 0) return null;
        int casa = getCasa(chave);
        int atual = casa;
        while (atual != -1) {
            if (ocupado[atual] && arm[atual].chave == chave) {
                return arm[atual]; 
            }
            atual = prox[atual];
        }
        return null; 
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
            if (ocupado[atual] && arm[atual].chave == chave) {
                if (atual < mBase) {
                    ocupado[atual] = false;
                    arm[atual].chave = -1;
                    arm[atual].dados.clear();
                } else {
                    if (anterior != -1) {
                        prox[anterior] = prox[atual];
                    }
                    ocupado[atual] = false;
                    arm[atual].chave = -1;
                    arm[atual].dados.clear();
                    prox[atual] = -1;
                    if (atual < livreOverflow) {
                        livreOverflow = atual;
                    }
                }
                return true;
            }
            anterior = atual;
            atual = prox[atual];
        }
        return false;
    }
}