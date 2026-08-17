public class Pessoa {
    public int identificador;
    public String nome;

    public Pessoa(int identificador, String nome) {
        this.identificador = identificador;
        this.nome = nome;
    }

    public int getId() {
        return identificador;
    }

    public void setId(int identificador) {
        this.identificador = identificador;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}