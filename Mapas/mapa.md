# 🗺️ Mapas

> Mapa é uma coleção de dados do tipo chave-valor (key-value).

> Recuperamos um valor com base na sua chave correspondente.
>- Cada chave é única.
>- Valores podem ser repetidos

Mapa também é conhecido por:
- Tabela de dispersão (hash table)
- Dicionário (dictionary)
- Vetor associativo (associative array )
- Coleção chave-valor (key-value collection)
- Tabela de símbolos (symbol table)
> Conceito é o mesmo: Estrutura que associa chaves a valores.

## Mapa em Java

Java oferece algumas estruturas (interfaces e classes);
- Pacote: java.util (Java Collections Framework)
- Interface: Map
- Algumas classes:
    - HashMap
    - LinkedHashMap
    - TreeMap

## Interface 

> Interface: Map
- Uma interface pode ser entendido como um "contrato de programação";
- Ao implementar a interface é como assinar o contrato e com isso, assume-se a obrigação de realizar determinadas tarefas (faz parte do contrato);
- Essas tarefas são as operações (métodos) previstos no contrato, ou seja, declarados na interface (mas não implemenadas nela).

---

# HashMap

> O HashMap é uma implementação da interface Map que armazena (chave, valor) e permite acesso rápido aos valores por meio das chaves.

---

classe: **HashMap**
- Implementação: tabela hash
- Desempenho: Acesso (get), inserção (put), remoção (remove), checagem de existência (containsKey) ocorre em média em tempo O(1).
- Características:
    - Não mantém ordem dos elementos;
    - Permite chave null e múltiplos valores null.
    - Métodos não sincronizados, não é thread safe;
- Principais operações:
    - put(key, value): insere ou atualiza um par;
    - get(key): recupera o valor associado;
    - remove(key): remove o par correspondente;

---

### Imports

```
import java.util.Map;
import java.util.HashMap;
```

---

# LinkedHashMap

> O LinkedHashMap é uma implementação da interface Map que combina as características de um HashMap e de uma lista duplamente encadeada.

---

classe: **LinkedHashMap**
- Implementaçãao: tabela hash
- Características:
    - Utiliza lista duplamente encadeada para manter a ordem de inserçãao dos elementos.
    - Pode ser configurado para manter a ordem de acesso (elementos mais recentemente acessados vão para o final), útil para implementar cache LRU.
    - Métodos não sincronizados, não é thread safe.
- Principais operações:
    - put(key, value): insere ou atualiza um par;
    - get(key): recupera o valor associado;
    - remove(key): remove o par correspondente;
    - A iteração ( for (var entry: map.entrySet()) ) segue a ordemdefinida.

--- 

### Imports

```
import java.util.LinkedHashMap;
import java.util.Map;
```

### CacheLRU

```
public class CacheLRU<K, V> extends LinkedHashMap<K, V> {
    private final int capacidade;

    public CacheLRU(int capacidade) {
        super(capacidade, 0.75f, true); // "true" = ordem por acesso
        this.capacidade = capacidade;
    }
    
    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > capacidade;
    }
}
```

---

# TreeMap

> O TreeMap é uma implementaçãao da interface Map do Java que armazena os elementos ordenados por suas chaves, utilizando uma árvore de busca balanceada (Red-Black Tree) como estrutura interna.

---

classe: **TreeMap**
- As chaves são mantidas em ordem crescente;
- Implementção: árvore rubro-negra
- Desempenho:
    - Inserção (put): O(log n)
    - Busca (get): O(log n)
    - Remoção (remove): O(log n)

---

```
import java.util.TreeMap;
import java.util.Map;
```

---

# HashMap vs LinkedHashMap vs TreeMap

Pontos | *HashMap* | *LinkedHashMap* | *TreeMap* |
--- | --- | --- | --- |
**Positivos** | Melhor quando desempenho das operações do mapa é importante | Ordem de inserção dos elementos é mantida | Ordem crescente (ou personalizada, via Comparator) dos elementos é garantida |
**Negativos** | Não garante a ordem dos elementos | Desempenho próximo ao HashMap | Pode ser mais lento do que as outras duas implementações de mapa.