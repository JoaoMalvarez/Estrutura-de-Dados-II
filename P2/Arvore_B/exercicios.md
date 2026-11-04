# Exercícios Árvore B

## 1. Explique o que é uma árvore B e descreva suas principais propriedades (ordem, número de chaves por nó, balanceamento).
> A árvore B é uma estrutura de dados auto-balanceada em formato de árvore, feita para armazenar dados de forma orednada e permitir a inserção, remoção e busca desses dados de forma veloz. Sendo o diferencial dessa árvore que cada nó pode ter mais de um valor e multiplos filhos, sendo uma árvore multidirecional, além de que todas as folhas ficam com os dados, além de ficarem todas no mesmo nível.

---

## 2. Uma árvore B de ordem 2 armazena até quantas chaves em cada nó? Qual é o número mínimo e máximo de filhos que um nó pode ter (exceto a raiz)?
> Ela pode ter até (2t - 1 = 3) 3 chaves por nó. Além de que o número mínimo de filhos que a árvore pode ter é (t) 2, e o máximo é de (2t) 4.

---

## 3. Considere uma árvore B de ordem 2 inicialmente vazia. Insira as chaves na seguinte ordem: 10, 20, 5, 6, 12, 30, 7, 17. Desenhe a árvore B resultante após cada inserção, mostrando onde ocorrem divisões de nós.
arvore:
              [ 10 | 20 ]
[ 5 | 6 | 7 ] [ 12 | 17 ] [ 30 ]

> Passo a passo: 10, 20 e 5 sao inseridos na raiz, após tentar adicionar o 6, o nó é dividido, com o médio (sendo 10) ficando na raiz e separando os outros dois em outros 2 nos, e depois adicionando o 6, na qual ficou no nó '5', após inserir 6, 12, 30 e 7 sem problemas, ambos os nós ficaram cheios, e ao adicionar o 17, foi dividida novamente, com o intermediário (sendo 20) subindo para o nó pai e criando mais um nó filho, na qual seria os numeros entre os dois números do nó pai, com 12 e 17 ficandoo nesse e o 30 ficando no superior a 20.
---

## 4. Dada a árvore B de ordem 2 construída com as chaves: 10, 20, 30, 40, 50, 60, 70. Remova as chaves 30 e 50, mostrando passo a passo como ficam os nós após cada remoção.
arvore antes:
    [ 20 | 40 ]
[ 10 ] [ 30 ] [ 50 | 60 | 70 ]

arvore depois:
    [ 20, 60 ]
[ 10 ] [ 40 ] [ 70 ]

> Antes de remover ele vai ver se ele é o ultimo elemento, como o '12' é o ultimo elemento do seu nó ele procura para ver se alguem pode se emprestar para ficar no lugar dele, primeiro olha a esquerda, mas o '10' também é o único elemento, então olha para a direita, e vê se pode remover o mais proximo, e pode, o '50' não é o ultimo elemento desse nó, ainda tem o '60' e o '70', então pegamos ele emprestado, ele sobe e pega o lugar do '40', e o '40' pega o lugar do 30, e o 30 é apagado. Agora para remover o '50': vemos se podemos substituir algum valor: primeiro vemos o mais próximo entre o '20' e o '50', que tem o '40' sozinho, então não, depois vemos os superiores a '50', e temos 2 valores, então podemos remover o '60' de lá, o '60' sobe e substitui o valor do '50' e ele é apagado. 
---

## 5. Escreva o algoritmo de busca em uma árvore B. Em seguida, considerando a árvore B abaixo (ordem 2):
```
        [  20  |  50  ]  
       /       |       \
[5 | 10] [25 | 35 | 45] [60 | 70]
```
Mostre o caminho percorrido para buscar a chave 35.

Algoritmo:
```
    public No busca(No no, int k) {
        int i = 0;
        
        // 1. Percorre as chaves do nó atual enquanto forem menores que k
        while (i < no.atual && k > no.chaves[i]) {
            i++;
        }
        
        // 2. Se encontrou a chave no nó atual, retorna o nó
        if (i < no.atual && k == no.chaves[i]) {
            return no;
        }
        
        // 3. Se não encontrou e o nó é uma folha, a chave não existe na árvore
        if (no.ehFolha) {
            return null;
        }
        
        // 4. Caso contrário, desce recursivamente para o filho correto
        return busca(no.filhos[i], k);
    }

```

> O caminho percorrido analisa os nós de cima para baixo. Os elementos testados e comparados pelo algoritmo foram: Nó Raiz [ 20 | 50 ] -> Nó Filho [ 25 | 35 | 45 ]

---

## 6. Suponha uma árvore B de ordem 2 com 1024 chaves. Qual é a altura mínima e máxima possível da árvore? (Lembre-se: cada nó pode ter entre d e 2d chaves, onde d é a ordem.)
> Mínimo: para minimizar a altura, cada nó tem que ter o número máximo de dados dentro dele, ou seja: 
h = 0 : 1(nó) x 4(chaves) = 4 chaves
h = 1 : (4 + 1) x 4 = 20 + 4 = 24 chaves
h = 2 : (24 + 1) x 4 = 100 + 24 = 124 chaves
h = 3 : (124 + 1) x 4 = 500 + 124 = 624 chaves
h = 4 : (624 + 1) x 4 = 2500 + 624 = 3124 chaves 
> logo altura mínima = 4

> Máximo: para maximizar a altura, cada nó tem que ter o número minimo de dados dentro dele, ou seja:
h = 0 : 1(nó) x 1(chave) = 1 chave
h = 1 : 2 x 2 = 4 + 1 = 5 chaves
h = 2 : 6 x 2 = 12 + 5 = 17 chaves
h = 3 : 18 x 2 = 36 + 17 = 53 chaves
h = 4 : 54 x 2 = 108 + 53 = 161 chaves
h = 5 : 162 x 2 = 324 + 161 = 485 chaves
h = 6 : 486 x 2 = 972 + 485 = 1457 chaves
> logo altura máxima = 6