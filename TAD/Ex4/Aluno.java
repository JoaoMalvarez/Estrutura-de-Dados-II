public class Aluno<T> implements ListaNota<T> {
    private int ra;
    private String nome;
    private String turma;
    private T nota;

    public Aluno(int ra, String nome, String turma, T nota) {
        this.ra = ra;
        this.nome = nome;
        this.turma = turma;
        this.nota = nota;
    }

    public int getRa() {
        return ra;
    }

    public String getNome() {
        return nome;
    }

    public String getTurma() {
        return turma;
    }

    public T getNota() {
        return nota;
    }

    public void setRa(int ra) {
        this.ra = ra;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTurma(String turma) {
        this.turma = turma;
    }

    public void setNota(T nota) {
        this.nota = nota;
    }

    public void inserirNota(T nota) {
        // inserir uma nota na lista de notas do aluno
        ListaNota<T> novaNota = new ListaNota<>(nota);
        novaNota.setProximo(this.getProximo());
        this.setProximo(novaNota);        
    }
}