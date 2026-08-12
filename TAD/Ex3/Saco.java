import java.util.Iterator;

public interface Saco<T> extends Iterable<T>{
    public void inserir(T item);
    public boolean vazio();
}