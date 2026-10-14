public class Arvore {
    private No raiz;
    private int d; // grau mínimo (conforme os slides, d)

    public Arvore(int d) {
        this.d = d;
        this.raiz = new No(d, true); // Raiz começa vazia e sendo folha
    }

    // ==========================================
    // 1. MÉTODOS DE BUSCA E SUPORTE
    // ==========================================

    // Encontra o índice da primeira chave maior ou igual a 'k'
    private int buscaIndiceChave(No no, int k) {
        int idx = 0;
        while (idx < no.atual && no.chaves[idx] < k) {
            idx++;
        }
        return idx;
    }

    // ==========================================
    // 2. ALGORITMO DE INSERÇÃO
    // ==========================================

    public void inserir(int k) {
        No r = raiz;
        // Se a raiz estiver cheia (2*d chaves), a árvore cresce em altura
        if (r.atual == 2 * d) {
            No s = new No(d, false);
            raiz = s;
            s.filhos[0] = r;
            dividirFilho(s, 0, r);
            inserirNaoCheio(s, k);
        } else {
            inserirNaoCheio(r, k);
        }
    }

    private void inserirNaoCheio(No no, int k) {
        int i = no.atual - 1;

        if (no.folha) {
            // Move as chaves maiores para a frente para abrir espaço
            while (i >= 0 && no.chaves[i] > k) {
                no.chaves[i + 1] = no.chaves[i];
                i--;
            }
            no.chaves[i + 1] = k;
            no.atual++;
        } else {
            // Encontra o filho que descerá
            while (i >= 0 && no.chaves[i] > k) {
                i--;
            }
            i++;
            if (no.filhos[i].atual == 2 * d) {
                dividirFilho(no, i, no.filhos[i]);
                if (no.chaves[i] < k) {
                    i++;
                }
            }
            inserirNaoCheio(no.filhos[i], k);
        }
    }

    // Divisão
    private void dividirFilho(No pai, int i, No filho) {
        No z = new No(d, filho.folha);
        z.atual = d; // O filho cheio tem 2d chaves, divide ficando com d cada

        // Copia a metade superior de 'filho' para 'z'
        for (int j = 0; j < d; j++) {
            z.chaves[j] = filho.chaves[j + d];
        }

        // Se não for folha, copia também os ponteiros dos filhos
        if (!filho.folha) {
            for (int j = 0; j <= d; j++) {
                z.filhos[j] = filho.filhos[j + d];
            }
        }

        filho.atual = d; // O filho original fica com 'd' chaves

        // Abre espaço nos filhos do pai
        for (int j = pai.atual; j >= i + 1; j--) {
            pai.filhos[j + 1] = pai.filhos[j];
        }

        pai.filhos[i + 1] = z;

        // Move as chaves do pai para abrir espaço para a chave do meio que sobe
        for (int j = pai.atual - 1; j >= i; j--) {
            pai.chaves[j + 1] = pai.chaves[j];
        }

        pai.chaves[i] = filho.chaves[d]; // A chave do meio sobe para o pai
        pai.atual++;
    }

    // ==========================================
    // 3. ALGORITMO DE REMOÇÃO (Esqueleto Teórico)
    // ==========================================

    public void remover(int k) {
        // Implementação baseada na lógica dos slides (Busca -> Tratamento de Folha/Não-Folha -> Underflow)
        removerDe(raiz, k);
        
        // Se a raiz ficar vazia após uma remoção e tiver filhos, o primeiro filho se torna a nova raiz
        if (raiz.atual == 0 && !raiz.folha) {
            raiz = raiz.filhos[0];
        }
    }

    private void removerDe(No no, int k) {
        int idx = buscaIndiceChave(no, k);

        // Caso 1: A chave está presente neste nó
        if (idx < no.atual && no.chaves[idx] == k) {
            if (no.folha) {
                // Passo 2 dos slides: se está na folha, basta remover (puxar elementos para trás)
                for (int i = idx; i < no.atual - 1; i++) {
                    no.chaves[i] = no.chaves[i + 1];
                }
                no.chaves[no.atual - 1] = null;
                no.atual--;
            } else {
                // Se não está na folha, substitui pelo predecessor/sucessor e remove na folha
                // (Aqui entraria a lógica de pegar o maior elemento da sub-árvore esquerda ou menor da direita)
            }
        } 
        // Caso 2: A chave não está no nó atual, descemos para o filho correspondente
        else {
            if (no.folha) {
                return; // Chave não encontrada na árvore
            }

            boolean ultimaFilho = (idx == no.atual);
            
            // Verifica se o filho possui menos que 'd' chaves (Underflow) para tratar com Concatenação/Redistribuição
            if (no.filhos[idx].atual < d) {
                // Aqui seriam aplicadas as regras de empréstimo do irmão esquerdo/direito ou concatenação
            }

            removerDe(no.filhos[idx], k);
        }
    }
}