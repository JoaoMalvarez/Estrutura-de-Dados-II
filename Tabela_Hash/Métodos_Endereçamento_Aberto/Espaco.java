/* ============================================
Notas:
    > Colocar os valores dentro do espaço em uma
    lista encadeada
============================================ */

public class Espaco {
    public int chave; // chave de identificação 
    public boolean comDado;

    public Espaco(int chave) {
        this.chave = chave;
        this.comDado = true;
    }

    public EspacoVazio(int chave) {
        this.chave = chave;
        this.comDado = false;
    }
}