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

    public int getNome() {
        return nome;
    }

    public void setNome(Sting nome) {
        this.nome = nome;
    }
}