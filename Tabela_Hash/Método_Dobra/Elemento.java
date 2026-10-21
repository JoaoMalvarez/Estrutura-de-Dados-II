/* ============================================
Notas:
    > Colocar os valores dentro do espaço em uma
    lista encadeada
============================================ */

public static class Elemento {
        public int chave;
        public int prox;       // Ponteiro para o próximo elemento da colisão (-1 se fim)
        public boolean ocupado;// Indica se a posição está preenchida
        
        public Elemento(int chave) {
            this.chave = chave;
            this.prox = -1;
            this.ocupado = false;
        }
}