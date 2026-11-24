public class Arvore {
    private No raiz;    // 'raiz' é para ser o No raiz da árvore
    private int m;      // 'm' é o parâmetro de tamanho/ordem usado na árvore

    public Arvore(int ordem) {
        this.m = ordem;
        this.raiz = new No(m, true); // no começo a raiz é uma folha
    }

    // ==========================================
    // 1. MÉTODO DE BUSCA
    // ==========================================

    private int buscaIndiceChave(No no, int k) {
        int idx = 0;
        // Avança enquanto não passar das chaves válidas ('atual') e a chave atual for menor que 'k'
        while (idx < no.atual && no.chaves[idx] < k) {
            idx++;
        }
        // Retorna a posição onde 'k' está ou onde ele deveria entrar
        return idx;
    }

    // ou pode usar como ja feito: o 'no.achaChave(chave)' pq é a mesma coisa

    // ==========================================
    // 2. ALGORITMO DE INSERÇÃO
    // ==========================================

    public void inserir(int k) {
        No r = raiz;                    // cria um nó que começa na raiz
        if (r.atual == 2 * m) {         // se a raiz estiver cheia com o limite '2 * m':
            No s = new No(m, false);    // cria uma nova raiz vazia e que não é folha (vai ter filhos)
            raiz = s;                   // atualiza a raiz da árvore para o novo nó 's'
            s.filhos[0] = r;            // o primeiro filho da nova raiz passa a ser a antiga raiz 'r'
            dividirFilho(s, 0, r);      // divide 'r' ao meio e sobe uma chave para 's'
            inserirNaoCheio(s, k);      // insere 'k' a partir da nova raiz 's' (que já tem espaço)
        } else {
            inserirNaoCheio(r, k);      // se a raiz não está cheia, insere direto nela
        }
    }

    private void inserirNaoCheio(No no, int k) {    // funcao de insercao quando o no nao esta cheio
        int i = no.atual - 1;                       // começa olhando da última chave ocupada do nó

        if (no.folha) {                             // se o no for uma folha
            while (i >= 0 && no.chaves[i] > k) {    // enquanto i for válido e a chave em 'i' for maior que 'k'
                no.chaves[i + 1] = no.chaves[i];    // empurra a chave uma posição para a direita para abrir espaço
                i--;                                // anda para a chave anterior (à esquerda)
            }
            no.chaves[i + 1] = k;                   // insere 'k' na posição correta encontrada
            no.atual++;                             // aumenta o contador de chaves ocupadas no nó
        } else {                                    // se o nó for interno (não é folha)
            while (i >= 0 && no.chaves[i] > k) {    // procura da direita para a esquerda qual subárvore contém 'k'
                i--;
            }
            i++;                                    // ajusta o índice do filho correspondente ao intervalo de 'k'
            if (no.filhos[i].atual == 2 * m) {      // se o filho escolhido já estiver cheio:
                dividirFilho(no, i, no.filhos[i]);  // divide esse filho antes de descer nele (garante espaço)
                if (no.chaves[i] < k) {             // após a divisão, se 'k' for maior que a chave promovida ao pai:
                    i++;                            // desce para o novo filho à direita em vez do esquerdo
                }
            }
            inserirNaoCheio(no.filhos[i], k);      // continua a inserção recursivamente no filho escolhido
        }
    }

    // Divisão
    private void dividirFilho(No pai, int i, No filho) {
        No z = new No(m, filho.folha);              // cria um novo nó 'z' (irmão à direita de 'filho')
        z.atual = m;                                // define que 'z' terá 'm' chaves

        for (int j = 0; j < m; j++) {               // copia a metade direita das chaves de 'filho' para 'z'
            z.chaves[j] = filho.chaves[j + m];
        }

        if (!filho.folha) {                         // se o nó dividido não for folha, também precisa mover os filhos
            for (int j = 0; j <= m; j++) {          // copia os ponteiros de filhos correspondentes para 'z'
                z.filhos[j] = filho.filhos[j + m];
            }
        }

        filho.atual = m;                            // atualiza a quantidade de chaves que restaram em 'filho'

        for (int j = pai.atual; j >= i + 1; j--) {  // abre espaço no vetor 'filhos' do pai para inserir 'z'
            pai.filhos[j + 1] = pai.filhos[j];
        }

        pai.filhos[i + 1] = z;                      // liga 'z' como filho do 'pai' logo após 'filho'

        for (int j = pai.atual - 1; j >= i; j--) {  // abre espaço no vetor 'chaves' do pai para a chave promovida
            pai.chaves[j + 1] = pai.chaves[j];
        }

        pai.chaves[i] = filho.chaves[m];            // promove a chave da posição 'm' para o nó 'pai'
        pai.atual++;                                // incrementa a quantidade de chaves no 'pai'
    }

    // ==========================================
    // 3. ALGORITMO DE REMOÇÃO (Esqueleto Teórico)
    // ==========================================

    public void remover(int k) {
        removerDe(raiz, k);                         // inicia a remoção recursiva a partir da raiz
        
        if (raiz.atual == 0 && !raiz.folha) {       // se a raiz perdeu todas as chaves mas ainda tem filhos:
            raiz = raiz.filhos[0];                  // o primeiro filho vira a nova raiz (diminui altura da árvore)
        }
    }

    private void removerDe(No no, int k) {
        int idx = buscaIndiceChave(no, k);          // encontra o índice onde 'k' está ou deveria estar neste nó

        // Caso 1: A chave está presente neste nó
        if (idx < no.atual && no.chaves[idx] == k) {
            if (no.folha) {
                // Passo 2 dos slides: se está na folha, basta remover (puxar elementos para trás)
                for (int i = idx; i < no.atual - 1; i++) {
                    no.chaves[i] = no.chaves[i + 1];
                }
                no.chaves[no.atual - 1] = null;     // limpa a última posição duplicada após o deslocamento
                no.atual--;                         // reduz a contagem de chaves do nó
            } else {
                // Se não está na folha, substitui pelo predecessor/sucessor e remove na folha
                // (Aqui entraria a lógica de pegar o maior elemento da sub-árvore esquerda ou menor da direita)
            }
        } 
        // Caso 2: A chave não está no nó atual, descemos para o filho correspondente
        else {
            if (no.folha) {
                return;                             // chegou a uma folha e não achou 'k': a chave não existe na árvore
            }

            boolean ultimaFilho = (idx == no.atual); // indica se 'idx' aponta para o último filho do nó atual
            
            if (no.filhos[idx].atual < m) {         // se o filho para onde vamos descer está abaixo do mínimo 'm':
                // Aqui seriam aplicadas as regras de empréstimo do irmão esquerdo/direito ou concatenação
            }

            removerDe(no.filhos[idx], k);           // desce recursivamente no filho adequado para continuar a remoção
        }
    }
}