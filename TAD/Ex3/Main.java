public class Main {
    public static void main(String[] args) {
        Saco<String> lista = new Lista<>();
        lista.inserir("Maçã");
        lista.inserir("Banana");
        lista.inserir("Laranja");

        for (String fruta : lista) {
            System.out.println(fruta);
        }
    }
}