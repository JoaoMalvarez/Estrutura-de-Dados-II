public class ListaNota<T> {
    private T nota;
    private ListaNota<T> proximo;

    public ListaNota(T nota) {
        this.nota = nota;
        this.proximo = null;
    }

    public T getNota() {
        return nota;
    }

    public ListaNota<T> getProximo() {
        return proximo;
    }

    public void setProximo(ListaNota<T> proximo) {
        this.proximo = proximo;
    }

    public void setNota(T nota) {
        this.nota = nota;
    }
}