public class Arvore {
    private No raiz;
    private int d; // grau minimo

    public Arvore(int d) {
        this.d = d;
        this.raiz = new NoFolha(d); // No início, a árvore vazia possui uma raiz que é uma FOLHA
    }

    // ==========================================
    // 1. MÉTODOS DE BUSCA E SUPORTE
    // ==========================================

    // Encontra o índice da primeira chave maior ou igual a 'k'
    // procura dentro do No dado
    private int buscaChaveFolha(NoFolha no, int k) {
        int index = 0;
        while(no.atual > index && no.chaves[index] < k) {
            index++;
        }
        return index;
    }

    private int buscaChaveInterno(NoInterno no, int k) {
        int index = 0;
        while(no.atual > index && no.chaves[index] < k) {
            index++;
        }
        return index;
    }
    
    // Aqui é o mesmo codigo que o de cima, mas acima é mais legivel
    // Aqui faz a mesma coisa, pois as coisas que precisa está 
    // no qual ambos pegam a herança
    private int buscaChave(No no, int k) {
        int index = 0;
        while (no.atual > index && no.chaves[index] < k) {
            index++;
        }
        return index;
    }

    // Método auxiliar para buscar uma chave na árvore e retornar a folha onde ela está (ou deveria estar)
    public NoFolha buscarNoFolha(int k) {
        No no = raiz;
        // enquanto não chegarmos nas folhas:
        while(!no.folha) {
            NoInterno interno = (NoInterno) no;
            int idx = buscaChave(no, k);
            no = interno.filhos[idx];
        }
        // quando sai do while, significa que chegou na folha
        return (NoFolha) no;
    }

    // Método público para verificar se a chave existe na árvore
    public boolean buscar(int k) {
        NoFolha no = buscarNoFolha(k);
        int idx = buscaChave(no, k);
        // verifica se realmente está no nó
        if(no.dados[idx] == k && idx < no.atual) { // o 'idx < no.atual' é uma verificação extra
            return true;
        }
        return false;
    }

    // ==========================================
    // 2. ALGORITMO DE INSERÇÃO
    // ==========================================

    public void insere(int k) {
        if (buscar(k)) {
            System.out.printf("O dado %d já existe na árvore.\n", k);
            return;
        }

        // Variáveis globais temporárias para segurar a chave e o novo nó caso ocorra divisão na raiz
        ChaveHolder holder = new ChaveHolder();
        No novoFilhoDireita = insereRecursivo(raiz, k, holder);

        // Se a raiz estourou e dividiu, um novo nó foi retornado, logo criamos uma nova raiz
        if (novoFilhoDireita != null) {
            NoInterno novaRaiz = new NoInterno(d);
            novaRaiz.chaves[0] = holder.chave;
            novaRaiz.filhos[0] = raiz;
            novaRaiz.filhos[1] = novoFilhoDireita;
            novaRaiz.atual = 1;
            novaRaiz.folha = false;
            this.raiz = novaRaiz;
        }
    }

    // Pequena classe interna estática apenas para passar por referência a chave que sobe na divisão
    // (Se quiser eliminar até isso, bastaria retornar um array de int ou objeto, mas o Holder é limpo).
    private static class ChaveHolder {
        int chave;
    }

    private No insereRecursivo(No atual, int k, ChaveHolder holder) {
        if (atual.folha) {
            NoFolha folha = (NoFolha) atual;
            
            // Insere ordenado na folha
            int i = folha.atual - 1;
            while (i >= 0 && folha.chaves[i] > k) {
                folha.chaves[i + 1] = folha.chaves[i];
                folha.dados[i + 1] = folha.dados[i];
                i--;
            }
            folha.chaves[i + 1] = k;
            folha.dados[i + 1] = k; // Armazenando o dado
            folha.atual++;

            // Se estourou o limite (2 * d)
            if (folha.atual > (2 * d)) {
                return divideFolha(folha, holder);
            }
            return null;

        } else {
            NoInterno interno = (NoInterno) atual;
            int idx = buscaChave(interno, k);

            No novoFilhoDireita = insereRecursivo(interno.filhos[idx], k, holder);

            // Se o filho de baixo dividiu, precisamos inserir a chave que subiu no nó interno atual
            if (novoFilhoDireita != null) {
                insereNoInterno(interno, holder.chave, novoFilhoDireita);

                // Se o nó interno atual também estourar, dividimos ele
                if (interno.atual > (2 * d)) {
                    return divideInterno(interno, holder);
                }
            }
            return null;
        }
    }

    private No divideFolha(NoFolha folha, ChaveHolder holder) {
        NoFolha novaFolha = new NoFolha(d);
        
        int meio = (folha.atual + 1) / 2;
        int numChavesNova = folha.atual - meio;

        for (int i = 0; i < numChavesNova; i++) {
            novaFolha.chaves[i] = folha.chaves[meio + i];
            novaFolha.dados[i] = folha.dados[meio + i];
            folha.chaves[meio + i] = 0;
            folha.dados[meio + i] = 0;
        }

        novaFolha.atual = numChavesNova;
        folha.atual = meio;

        // Ajusta o ponteiro 'proximo' (a cordinha da folha)
        novaFolha.proximo = folha.proximo;
        folha.proximo = novaFolha;

        // Na B+, a menor chave da nova folha é copiada para cima (Copy-up) e permanece na folha
        holder.chave = novaFolha.chaves[0];

        return novaFolha;
    }

    private void insereNoInterno(NoInterno interno, int chave, No filhoDireita) {
        int i = interno.atual - 1;
        while (i >= 0 && interno.chaves[i] > chave) {
            interno.chaves[i + 1] = interno.chaves[i];
            interno.filhos[i + 2] = interno.filhos[i + 1];
            i--;
        }
        interno.chaves[i + 1] = chave;
        interno.filhos[i + 2] = filhoDireita;
        interno.atual++;
    }

    private No divideInterno(NoInterno interno, ChaveHolder holder) {
        NoInterno novoInterno = new NoInterno(d);
        
        int meio = interno.atual / 2;
        holder.chave = interno.chaves[meio]; // Chave do meio sobe

        int numChavesNova = interno.atual - meio - 1;

        for (int i = 0; i < numChavesNova; i++) {
            novoInterno.chaves[i] = interno.chaves[meio + 1 + i];
            interno.chaves[meio + 1 + i] = 0;
        }

        for (int i = 0; i <= numChavesNova; i++) {
            novoInterno.filhos[i] = interno.filhos[meio + 1 + i];
            interno.filhos[meio + 1 + i] = null;
        }

        novoInterno.atual = numChavesNova;
        interno.atual = meio;
        interno.chaves[meio] = 0;

        return novoInterno;
    }
}