/* ============================================
Notas:
    > Colocar os valores dentro do espaço em uma
    lista encadeada
============================================ */

public class Espaco {
    public int chave; // chave de identificação 
    public ListaEncadeada<Integer> dados; // dados satélites 

    public Espaco(int chave) {
        this.chave = chave;
        /* Cria a lista encadeada de cada Espaço */
        this.dados = new ListaEncadeada<>(); 
    }

    public void adiciona(Espaco x) {
        dados.insertTail(x.chave);
    }
}