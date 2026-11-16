import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Passo 1: ler a entrada:
        Scanner sc = new Scanner(System.in);

        // Passo 2: ler a linha 1 (N e Q) e criar o objeto:
        int n = sc.nextInt();
        int q = sc.nextInt();
        Exercicio1 grupos = new Exercicio1(n);

        // Passo 3: repetir Q vezes:
        for(int i = 0; i < q; i++) {
            String comando = sc.next();
            // se cmd for "UNION": leia dois números e chame union
            if(comando.equals("UNION")) {
                int a = sc.nextInt();
                int b = sc.nextInt();
                grupos.union(a, b);
            }
            // se cmd for "SAME": leia dois números e chame same
            else if(comando.equals("SAME")) {
                int a = sc.nextInt();
                int b = sc.nextInt();
                grupos.same(a, b);
            }
            // se cmd for "SIZE": leia um número e imprima size
            else if(comando.equals("SIZE")) {
                int a = sc.nextInt();
                System.out.println(grupos.size(a));
            }
        }
    }
}