import java.util.LinkedList;

public class Bucket {
    public int chave;                        // Índice/identificador do bucket na tabela
    public ListaEncadeada<Integer> dados;    // Lista encadeada própria (se usar sua classe ListaEncadeada)
    public LinkedList<Integer> dadosNativos; // Lista nativa do Java (se usar java.util.LinkedList)

    // Construtor corrigido para bater com o nome da classe 'Bucket'
    public Bucket(int chave) {
        this.chave = chave;
        this.dados = new ListaEncadeada<>();
        this.dadosNativos = new LinkedList<>();
    }

    // Adiciona um valor na lista encadeada própria do bucket
    public void adiciona(int valor) {
        dados.insertTail(valor);
    }

    // Adiciona um valor na LinkedList nativa do Java
    public void adicionaNativo(int valor) {
        dadosNativos.add(valor);
    }
}