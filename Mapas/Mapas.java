/* 
Crie um projeto Java que lê um arquivo texto (pode ser indicado diretamente no código) e, 
em seguida, exiba a frequência (quantidade) de ocorrência das palavras que existem no arquivo 
texto. Altere o código para que o programa exiba a frequência de ocorrência das palavras em ordem decrescente, 
no formato: palavra (frequência) Por exemplo: 

frequência (4) 
que (4) 
a (3) 
arquivo (3) ... 
*/

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Comparator;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Mapas {

    public static void main(String[] args) {
        String arquivo = "texto.txt";

        // 1. Chama a função que lê o arquivo e devolve todas as palavras processadas
        List<String> palavras = lerEProcessarArquivo(arquivo);

        // 2. Chama a função que conta a frequência usando um Map
        Map<String, Integer> frequencias = contarPalavras(palavras);

        // 3. Chama a função que ordena da maior para a menor frequência
        List<Map.Entry<String, Integer>> listaOrdenada = ordenarPorFrequencia(frequencias);

        // 4. Chama a função que exibe no formato exigido
        exibirResultados(listaOrdenada);
    }

    // ==========================================
    // OS MÉTODOS MESMO:
    // ==========================================

    public static List<String> lerEProcessarArquivo(String caminho) {
        // O try-with-resources abre o arquivo e garante que ele será fechado no final
        List<String> listaDePalavras = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            
            // Lê o arquivo linha por linha até chegar ao fim (null)
            while ((linha = br.readLine()) != null) {
                // Deixa tudo minúsculo para "A" e "a" serem contados como a mesma palavra
                linha = linha.toLowerCase();
            
                // Quebra a linha em palavras usando espaços em branco
                String[] palavrasDaLinha = linha.split("\\s+");
                
                // Joga cada palavra na  lista principal
                for(String p : palavrasDaLinha) {
                    if(!p.isBlank()) {
                        listaDePalavras.add(p);
                    }
                }
            }
            
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }
        return listaDePalavras; 
    }

    public static Map<String, Integer> contarPalavras(List<String> palavras) {
        HashMap<String, Integer> mapa = new HashMap<>();

        if (palavras != null && !palavras.isEmpty()) {
            // O for-each passa por cada palavra da lista uma por uma, sem precisar dar remove
            for (String palavra : palavras) {
                mapa.put(palavra, mapa.getOrDefault(palavra, 0) + 1);
            }
            return mapa;
        }
        return null;
    }

    public static List<Map.Entry<String, Integer>> ordenarPorFrequencia(Map<String, Integer> mapa) {
        if (mapa == null || mapa.isEmpty()) {
            return new ArrayList<>();
        }

        // 1. Joga todos os pares do mapa (entrySet) para dentro de uma ArrayList
        List<Map.Entry<String, Integer>> freq = new ArrayList<>(mapa.entrySet());

        // 2. Ordena do maior para o menor (decrescente) usando o .getValue()
        freq.sort((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));

        return freq;
    }

    public static void exibirResultados(List<Map.Entry<String, Integer>> lista) {
        if (lista != null) {
            // Percorre a lista ordenada e imprime no formato exato pedido: palavra (frequencia)
            for (Map.Entry<String, Integer> entry : lista) {
                System.out.println(entry.getKey() + " (" + entry.getValue() + ")");
            }
        }
    }
}