/* =========================================================================
 * TABELA HASH COM ENCADEAMENTO EXTERIOR (SEPARATE CHAINING)
 *
 * Onde entram as variações pedidas:
 * - DOBRA e MULTIPLICAÇÃO: mudam a função de hash (calcula o índice inicial
 *   no lugar de apenas `chave % m`).
 * - ENDEREÇAMENTO ABERTO: muda a estrutura e a resolução de colisão
 *   (em vez de lista encadeada por posição, guarda direto no vetor e usa
 *   sondagem linear, quadrática ou hash duplo quando a posição está ocupada).
 * ========================================================================= */

import java.util.LinkedList;

public class Hash {
    public int m;          // Tamanho da tabela hash (quantidade de buckets)
    public Bucket[] arm;   // Vetor de buckets onde ficam as listas de elementos

    public Hash(int m) {
        this.m = m;
        this.arm = new Bucket[m];
        for (int i = 0; i < m; i++) {
            arm[i] = new Bucket(i); // Inicializa cada posição com um Bucket vazio
        }
    }

    // =====================================================================
    // FUNÇÃO DE HASH (Cálculo do índice)
    //
    // [DIFERENÇA - MÉTODO DA DOBRA]:
    // Em vez de `dado % m`, você divide os dígitos/blocos de `dado` (ex.:
    // pares ou trincas de algarismos), soma esses blocos e aplica `% m`
    // no resultado final.
    //
    // [DIFERENÇA - MÉTODO DA MULTIPLICAÇÃO]:
    // Multiplica `dado` por uma constante real `A` (geralmente entre 0 e 1,
    // ex.: 0.6180339887), pega apenas a parte fracionária `(dado * A) % 1`
    // e multiplica por `m` (truncando para inteiro): `(int) (m * ((dado * A) % 1))`.
    // =====================================================================
    public int getCasa(int dado) {
        if (dado < 0) return -1; // Evita índice negativo ou chave inválida neste exemplo
        return dado % m;         // Método da divisão padrão
    }

    // =====================================================================
    // 1. RESOLUÇÃO USANDO 'ListaEncadeada.java' E 'Node.java'
    // =====================================================================

    public boolean inserir(int chave) {
        if (chave < 0) return false;
        int casa = getCasa(chave);
        if (casa < 0 || casa >= m) return false;

        // [DIFERENÇA - ENDEREÇAMENTO ABERTO]:
        // Aqui não haveria `if (!arm[casa].dados.isFull())` nem lista encadeada.
        // Se `tabela[casa]` já estiver ocupada, você testaria próximas posições
        // usando sondagem: `(casa + i) % m` (linear), `(casa + i*i) % m`
        // (quadrática) ou `(h1(chave) + i * h2(chave)) % m` (hash duplo).
        if (!arm[casa].dados.isFull()) {
            arm[casa].adiciona(chave);
            return true;
        }

        System.out.printf("Não tem espaço sobrando no bucket %d.%n", casa);
        return false;
    }

    public boolean remover(int chave) {
        if (chave < 0) return false;
        int casa = getCasa(chave);
        if (casa < 0 || casa >= m) return false;

        // [DIFERENÇA - ENDEREÇAMENTO ABERTO]:
        // Em endereçamento aberto, não basta apagar o valor e deixar `null` seco,
        // porque isso quebraria buscas de chaves inseridas depois por sondagem.
        // Costuma-se marcar a posição como "removida / lápide" (tombstone / DELETED).
        if (!arm[casa].dados.isEmpty()) {
            return arm[casa].dados.remove(chave);
        }
        return false;
    }

    public Bucket buscar(int chave) {
        if (chave < 0) return null;
        int casa = getCasa(chave);
        if (casa < 0 || casa >= m) return null;

        // [DIFERENÇA - ENDEREÇAMENTO ABERTO]:
        // Em vez de buscar apenas dentro da lista de `arm[casa]`, você percorre
        // a sequência de sondagem enquanto a posição não estiver vazia
        // (parando apenas ao achar a chave ou encontrar um slot nunca ocupado).
        if (!arm[casa].dados.isEmpty()) {
            if (arm[casa].dados.search(chave) != null) {
                return arm[casa];
            }
        }
        return null;
    }

    // =====================================================================
    // 2. RESOLUÇÃO COM 'LinkedList' NATIVA (Limite manual de 5 itens)
    // =====================================================================

    public boolean cheioNativo(int chave) {
        int casa = getCasa(chave);
        if (casa < 0 || casa >= m) return false;

        // Verifica se o bucket atingiu o limite máximo definido de 5 elementos
        return arm[casa].dadosNativos.size() >= 5;
    }

    public boolean insereNativo(int chave) {
        if (chave < 0) return false;
        int casa = getCasa(chave);
        if (casa < 0 || casa >= m) return false;

        // Se ainda há espaço (menos de 5 itens), insere no final da lista
        if (arm[casa].dadosNativos.size() < 5) {
            arm[casa].adicionaNativo(chave);
            return true;
        }
        return false; // Bucket cheio
    }

    public boolean removerNativo(int chave) {
        if (chave < 0) return false;
        int casa = getCasa(chave);
        if (casa < 0 || casa >= m) return false;

        if (arm[casa].dadosNativos != null) {
            // Integer.valueOf(chave) garante que remove o VALOR `chave`,
            // e não o elemento que estiver no índice `chave` da lista
            return arm[casa].dadosNativos.remove(Integer.valueOf(chave));
        }
        return false;
    }

    public Bucket buscarNativo(int chave) {
        if (chave < 0) return null;
        int casa = getCasa(chave);
        if (casa < 0 || casa >= m) return null;

        if (arm[casa].dadosNativos != null && arm[casa].dadosNativos.contains(chave)) {
            return arm[casa];
        }
        return null;
    }
}