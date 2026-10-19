/* ============================================
Notas:
    > Colocar os valores dentro do espaço em uma
    lista encadeada
============================================ */

public class Espaco {
    private int chave; // chave de identificação 
    private ListaEncadeada<Integer> dados; // dados satélites 

    public Espaco(int chave) {
        this.chave = chave;
        /* Cria a lista encadeada de cada Espaço */
        this.dados = new LinkedList<>(); 
    }

    public adiciona(int chave) {
        if(!dados.IsFull()) {

        }
    }
}