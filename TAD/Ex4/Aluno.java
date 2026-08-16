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

    public void calcularMedia() {
        // calcular  e retornar a média aritmética das notas do aluno
        ListaNota<T> atual = this.getProximo();
        double soma = 0;
        int count = 0;
        while (atual != null) {
            soma += (double) atual.getNota();
            count++;
            atual = atual.getProximo();
        }
        if (count > 0) {
            double media = soma / count;
            System.out.println("Média do aluno " + this.nome + ": " + media);
        } else {
            System.out.println("O aluno " + this.nome + " não possui notas.");
        }
    }
}