public class Range {
    private int[] vetor;
    private int tamanho;
    private int[] somaP;
    
    // Sparse Table para o Mínimo: stMin[i][j] guarda o menor elemento 
    // começando em 'i' com tamanho de intervalo igual a 2^j
    private int[][] stMin;
    private int[] logTable;

    public Range(int[] v) {
        this.vetor = v;
        this.tamanho = v.length;
        
        // 1. Inicializa a Soma de Prefixos
        this.somaP = new int[tamanho];
        criaSoma();

        // 2. Inicializa e constrói a Sparse Table para RMQ
        if (tamanho > 0) {
            int K = (int) (Math.log(tamanho) / Math.log(2)) + 1;
            this.stMin = new int[tamanho][K];
            
            // Pré-calcula logaritmos para consultas O(1)
            this.logTable = new int[tamanho + 1];
            for (int i = 2; i <= tamanho; i++) {
                logTable[i] = logTable[i / 2] + 1;
            }
            
            criaMin();
        }
    }

    // ======================================
    // Range Sum Query (Soma de Prefixos)
    // ======================================
    private void criaSoma() {
        if (tamanho == 0) return;
        somaP[0] = vetor[0];
        for (int i = 1; i < tamanho; i++) {
            somaP[i] = somaP[i - 1] + vetor[i];
        }
    }

    public int soma(int a, int b) {
        if (a < 0 || b < 0 || a >= tamanho || b >= tamanho || a > b) return 0;
        
        // Se a == 0, a soma é simplesmente somaP[b]
        if (a == 0) {
            return somaP[b];
        }
        return somaP[b] - somaP[a - 1];
    }

    // ======================================
    // Range Min Query (Sparse Table)
    // ======================================
    private void criaMin() {
        int K = stMin[0].length;

        // 1. Intervalos de tamanho 2^0 = 1
        for (int i = 0; i < tamanho; i++) {
            stMin[i][0] = vetor[i];
        }

        // 2. Constrói para potências maiores de 2
        for (int j = 1; j < K; j++) {
            for (int i = 0; i + (1 << j) <= tamanho; i++) {
                stMin[i][j] = Math.min(stMin[i][j - 1], stMin[i + (1 << (j - 1))][j - 1]);
            }
        }
    }

    public int min(int a, int b) {
        if (a < 0 || b < 0 || a >= tamanho || b >= tamanho || a > b) return 0;

        // Tamanho do intervalo [a, b]
        int length = b - a + 1;
        int k = logTable[length];

        // O intervalo [a, b] é coberto por dois blocos de tamanho 2^k
        int ans = Math.min(stMin[a][k], stMin[b - (1 << k) + 1][k]);
        return ans;
    }

    // ======================================
    // Maior valor (Opcional / Força Bruta ou Sparse Table adicional)
    // ======================================
    public int max(int a, int b) {
        if (a < 0 || b < 0 || a >= tamanho || b >= tamanho || a > b) return 0;
        
        // Exemplo usando força bruta para o max
        int maior = vetor[a];
        for (int i = a + 1; i <= b; i++) {
            if (vetor[i] > maior) {
                maior = vetor[i];
            }
        }
        return maior;
    }
}