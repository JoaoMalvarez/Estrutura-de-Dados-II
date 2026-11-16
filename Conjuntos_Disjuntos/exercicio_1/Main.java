import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        // Com argumento: lê do arquivo (java Main entrada.txt)
        // Sem argumento: lê da entrada padrão (teclado / redirecionamento)
        BufferedReader br = (args.length > 0)
                ? new BufferedReader(new FileReader(args[0]))
                : new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        UnionFind uf = new UnionFind(n);
        StringBuilder saida = new StringBuilder();

        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();
            int a = Integer.parseInt(st.nextToken());

            switch (cmd) {
                case "UNION":
                    uf.union(a, Integer.parseInt(st.nextToken()));
                    break;
                case "SAME":
                    int b = Integer.parseInt(st.nextToken());
                    saida.append(uf.same(a, b) ? "SIM" : "NAO").append('\n');
                    break;
                case "SIZE":
                    saida.append(uf.size(a)).append('\n');
                    break;
            }
        }
        br.close();
        System.out.print(saida);
    }
}