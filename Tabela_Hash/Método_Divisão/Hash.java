/* ============================================
Notas:
    > Para identificar em qual lugar do espaçamento 
    irá ficar se usa um 'mod m' na qual a chave vai 
    ser usada no mod

============================================ */

public class Hash {
    public int m; // tamanho do Hash
    public Espaco[] arm; // onde guarda os espaços

    public Hash(int m) {
        this.m = m;
        /* Cria o espaço */
        this.arm = new Espaco[m];
        for(int i = 0; i < m; i++) {
            arm[i] = new Espaco(i);
        }
    }

    public int getCasa(int dado) {
        if(dado < 0) return -1;
        return dado % m;
    }

    // Forma de resolução com os arquivos 'ListaEncadeada.java' e 'Node.java':

    public boolean inserir(Espaco x) {
        if(x.chave < 0) return false;
        int chega = getCasa(x.chave);
        if(!arm[chega].dados.isFull()) {
            arm[chega].adiciona(x);
            return true;
        } 
        System.out.printf("Não tem espaço sobrando onde quer adicionar (no espaço: %d).", chega);
        return false;
    }

    public boolean remover(Espaco x) {
        if(x.chave < 0) return false;
        int casa = getCasa(x.chave);
        if(!arm[casa].dados.isEmpty()) {
            return arm[casa].dados.remove(x.chave);
        }
        return false;

    }
    public Espaco buscar(int chave) { // tem que ir até o espaço e ver se: o valor armazenado é a chave e se tem coisa dentro
        if(chave < 0) return null;
        int casa = getCasa(chave);
        if(!arm[casa].dados.isEmpty()) {
            if(arm[casa].dados.search(chave) != null) return arm[casa];
        }
        return null;
    } 

    // Forma de resolução sem esses arquivos:
    //      > consideramos que cada espaço suporte 5 itens na lista

    // Exemplo de como checar se o espaço está cheio (limite de 5)
    public boolean cheio(Espaco x) {
        int casa = getCasa(x.chave);
        if (casa == -1) return false;
        
        // Verifica se a quantidade de elementos na LinkedList nativa chegou a 5
        return arm[casa].dados.size() >= 5;
    }

    // Inserção usando a regra manual de limite de 5
    public boolean insereNativo(Espaco x) {
        if (x.chave < 0) return false;
        int casa = getCasa(x.chave);

        // Se não estiver cheio (tamanho menor que 5)
        if (arm[casa].dados.size() < 5) {
            arm[casa].dados.add(x.chave); // Adiciona no final
            return true;
        }
        return false; // Espaço cheio (atingiu o limite de 5)
    }

    public boolean remover(int chave) {
    if (chave < 0) return false;
    int casa = getCasa(chave);
    
    if (arm[casa].dados != null) {
        // Usamos Integer.valueOf() para o Java saber que é o VALOR, 
        // e não a posição/índice da lista.
        return arm[casa].dados.remove(Integer.valueOf(chave));
    }
    
    return false;
}

    public Espaco buscar(int chave) {
    if (chave < 0) return null;
    int casa = getCasa(chave);
    
    // .contains(chave) verifica se o valor existe na LinkedList nativa
    if (arm[casa].dados != null && arm[casa].dados.contains(chave)) {
        return arm[casa]; // Retorna o espaço onde a chave foi encontrada
    }
    
    return null; // Não encontrou
}
}