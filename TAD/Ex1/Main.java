public class Main {
    public static void main(String[] args) {
        Carro carro1 = new Carro("Toyota", "Corolla", "ABC-1234");
        Carro carro2 = new Carro("Honda", "Civic", "XYZ-5678");

        System.out.println("Dados do Carro 1:");
        carro1.exibirDados();

        System.out.println("\nDados do Carro 2:");
        carro2.exibirDados();
    }
}