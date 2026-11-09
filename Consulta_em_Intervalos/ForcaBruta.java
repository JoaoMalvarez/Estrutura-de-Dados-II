public class ForcaBruta {
    private int[] vetor;
    private int tamanho;

    public ForcaBruta(int[] v) {
        this.vetor = v;
        this.tamanho = v.length;
    }

    /* As formas de Força Bruta de fazê-las, olhando dentro do vetor cada uma: */
    public int soma(int a, int b) {
        if(a < 0 || b < 0 || a > tamanho || b > tamanho || a > b) return 0;
        else {
            int soma = 0;
            for(int i = a; i <= b; i++) {
                soma += vetor[i];
            }
            return soma;
        }
    }

    public int min(int a, int b) {
        if(a < 0 || b < 0 || a > tamanho || b > tamanho || a > b) return 0;
        else {
            int menor = vetor[a];
            for(int i = a; i <= b; i++) {
                if(menor > vetor[i]) {
                    menor = vetor[i];
                }
            }
            return menor;
        }
    }

    public int max(int a, int b) {
        if(a < 0 || b < 0 || a > tamanho || b > tamanho || a > b) return 0;
        else {
            int maior = vetor[a];
            for(int i = a; i <= b; i++) {
                if(maior < vetor[i]) {
                    maior = vetor[i];
                }
            }
            return maior;
        }
    }
}