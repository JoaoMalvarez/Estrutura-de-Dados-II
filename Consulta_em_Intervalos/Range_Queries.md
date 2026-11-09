# Consulta em Intervalos

> Vamos discutir estruturas de dados que permitam processar consultas em intervalos (range queries).
> O objetivo é calcular valores armazenados em vetores. Mais especificamente, queremos computar valores armazenados em subvetores que estejam delimitados por um intervalo [a, b].

As consultas em intervalos incluem:

- soma(a, b): calcular soma de valores no intervalo [a, b]
- min(a, b): encontrar o menor valor no intervalo [a, b]
- max(a, b): encontrar o maior valor no intervalo [a, b]

> Exemplo:

Considere o intervalo [3, 6] no seguinte vetor v:

| 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 |
|:---:|:---:|:---:|:---:|:---:|:---:|:---:|:---:|
| 1 | 3 | 8 | **4** | **6** | **1** | **3** | 2 |

Neste caso, 
- soma(3, 6) = 14
- min(3, 6) = 1
- max(3, 6) = 6.

---

## Soma

Vamos utilizar um loop para processar a soma de valores no intervalo [a, b]:
> Força Bruta

```
int soma(int a, int b) {
    int s = 0;
    for (int i = a; i <= b; i++) {
        s += v[i];
    }
    return s;
}
```

Consumo de tempo: O(n)
Processar q consultas: O(qn), que ´e lento, para valores grandes para q e n.

--- 

## Consultas em vetores estáticos

Considere um vetor estático A, ou seja, um vetor cujos elementos não são modificados entre consultas.

Neste caso, vamos utilizar um vetor de prefixos P para responder às consultas de forma eficiente.

Cada valor em P[k] armazena o resultado da aplicação de uma determinada operação (soma, max, min, . . .) sobre os elementos de A desde a primeira posiçãoo at´e a posição k.

Dado um vetor A de n elementos A[0], A[1], · · · , A[n − 1]. A computação de prefixos calcula valores para um vetor de prefixos P, tal que:
```
P[0] = A[0]
P[1] = A[0] op A[1]
P[2] = A[0] op A[1] op A[2]
· · ·
P[n − 1] = A[0] op A[1] op A[2] · · · op A[n − 1]
```

Onde op é uma operação binária e associativa.

> Exemplo: op é a adição

Vetor de entrada: A = [3, 7, 5, 1, 2, 4, 0, 9]
Vetor de Prefixo: P = [3, 10, 15, 16, 18, 22, 22, 31]

Um valor na posição P[k] é a soma(0, k).
O vetor P pode ser construído em tempo O(n).

Uma vez construído o vetor de prefixo, podemos consultar a soma dos valores no intervalo [a, b] da seguinte maneira:

```soma(a, b) = P[b] − P[a − 1]```

No caso em que a = 0, soma(a, b) = P[b].

---

## Processamento em matrizes 2D

E possível generalizar para matrizes de dimensões maiores.

<table style="border-collapse: collapse; text-align: center; font-family: sans-serif;">
  <!-- Linha 0 -->
  <tr>
    <td style="border: 1px solid #888; width: 35px; height: 35px;"></td>
    <td style="border: 1px solid #888; width: 35px; height: 35px;"></td>
    <td style="border: 1px solid #888; width: 35px; height: 35px;"><i>D</i></td>
    <td style="border: 1px solid #888; width: 35px; height: 35px;"></td>
    <td style="border: 1px solid #888; width: 35px; height: 35px;"></td>
    <td style="border: 1px solid #888; width: 35px; height: 35px;"><i>C</i></td>
    <td style="border: 1px solid #888; width: 35px; height: 35px;"></td>
    <td style="border: 1px solid #888; width: 35px; height: 35px;"></td>
    <td style="border: 1px solid #888; width: 35px; height: 35px;"></td>
  </tr>
  <!-- Linha 1 (Início da área sombreada) -->
  <tr>
    <td style="border: 1px solid #888; height: 35px;"></td>
    <td style="border: 1px solid #888;"></td>
    <td style="border: 1px solid #888; background-color: #b0b0b0;"></td>
    <td style="border: 1px solid #888; background-color: #b0b0b0;"></td>
    <td style="border: 1px solid #888; background-color: #b0b0b0;"></td>
    <td style="border: 1px solid #888; background-color: #b0b0b0;"></td>
    <td style="border: 1px solid #888;"></td>
    <td style="border: 1px solid #888;"></td>
    <td style="border: 1px solid #888;"></td>
  </tr>
  <!-- Linha 2 -->
  <tr>
    <td style="border: 1px solid #888; height: 35px;"></td>
    <td style="border: 1px solid #888;"></td>
    <td style="border: 1px solid #888; background-color: #b0b0b0;"></td>
    <td style="border: 1px solid #888; background-color: #b0b0b0;"></td>
    <td style="border: 1px solid #888; background-color: #b0b0b0;"></td>
    <td style="border: 1px solid #888; background-color: #b0b0b0;"></td>
    <td style="border: 1px solid #888;"></td>
    <td style="border: 1px solid #888;"></td>
    <td style="border: 1px solid #888;"></td>
  </tr>
  <!-- Linha 3 (Fim da área sombreada com B e A) -->
  <tr>
    <td style="border: 1px solid #888; height: 35px;"></td>
    <td style="border: 1px solid #888;"></td>
    <td style="border: 1px solid #888; background-color: #b0b0b0;"><i>B</i></td>
    <td style="border: 1px solid #888; background-color: #b0b0b0;"></td>
    <td style="border: 1px solid #888; background-color: #b0b0b0;"></td>
    <td style="border: 1px solid #888; background-color: #b0b0b0;"><i>A</i></td>
    <td style="border: 1px solid #888;"></td>
    <td style="border: 1px solid #888;"></td>
    <td style="border: 1px solid #888;"></td>
  </tr>
  <!-- Linha 4 -->
  <tr>
    <td style="border: 1px solid #888; height: 35px;"></td>
    <td style="border: 1px solid #888;"></td>
    <td style="border: 1px solid #888;"></td>
    <td style="border: 1px solid #888;"></td>
    <td style="border: 1px solid #888;"></td>
    <td style="border: 1px solid #888;"></td>
    <td style="border: 1px solid #888;"></td>
    <td style="border: 1px solid #888;"></td>
    <td style="border: 1px solid #888;"></td>
  </tr>
  <!-- Linha 5 -->
  <tr>
    <td style="border: 1px solid #888; height: 35px;"></td>
    <td style="border: 1px solid #888;"></td>
    <td style="border: 1px solid #888;"></td>
    <td style="border: 1px solid #888;"></td>
    <td style="border: 1px solid #888;"></td>
    <td style="border: 1px solid #888;"></td>
    <td style="border: 1px solid #888;"></td>
    <td style="border: 1px solid #888;"></td>
    <td style="border: 1px solid #888;"></td>
  </tr>
</table>

> Podemos computar a soma de prefixos para calcular a soma em qualquer matriz retangular.

A soma da região cinza pode ser calculada usando a fórmula:
```soma = S(A) − S(B) − S(C) + S(D)```

--- 

## Tabela Esparsa (sparse table)

Sparse table é uma estrutura de dados que permite a consultas em intervalos de um vetor estático.
É útil para problemas como **RMQ (Range Minimum Query)**, nos quais desejamos encontrar o menor elemento em um intervalo.

---

### RMQ (Range Minimum Query)

Com sparse table computar o RMQ tem custos computacionais:

- tempo de pré-processamento: O(n lg n)
- tempo de consulta: O(1)
- custo de memória: O(n lg n)

---

pré-processamento: calcular todos os valores **min(a, b)**, onde 'b − a + 1' (tamanho do intervalo) é potencia de 2.

<table border="0" style="width:100%; border-collapse: collapse;">
<tr>
<td valign="top">

| $a$ | $b$ | $\min(a,b)$ |
| :---: | :---: | :---: |
| 0 | 0 | 1 |
| 1 | 1 | 3 |
| 2 | 2 | 4 |
| 3 | 3 | 8 |
| 4 | 4 | 6 |
| 5 | 5 | 1 |
| 6 | 6 | 4 |
| 7 | 7 | 2 |

</td>
<td valign="top">

| $a$ | $b$ | $\min(a,b)$ |
| :---: | :---: | :---: |
| 0 | 1 | 1 |
| 1 | 2 | 3 |
| 2 | 3 | 4 |
| 3 | 4 | 6 |
| 4 | 5 | 1 |
| 5 | 6 | 1 |
| 6 | 7 | 2 |

</td>
<td valign="top">

| $a$ | $b$ | $\min(a,b)$ |
| :---: | :---: | :---: |
| 0 | 3 | 1 |
| 1 | 4 | 3 |
| 2 | 5 | 1 |
| 3 | 6 | 1 |
| 4 | 7 | 1 |
| 0 | 7 | 1 |

</td>
</tr>
</table>


> Notas do João: Ele compara entre os numeros dados e dá o menor, e guarda na Sparse table. Ou seja (o que eu acho que é): ele vai preencher a tabela, e com vai comparar em cada espaço a linha e a coluna, e retorna qual for o menor, e quando usar a função, ela vai entrar na tabela, no espaço desejado e vai retornar qual é o menor valor de lá

Os valores podem ser calculados pela fórmula:

```min(a, b) = min(min(a, a + w − 1), min(a + w, b))```

onde 'b − a + 1' é potência de 2 e 'w = (b − a + 1)/2'.

O número de valores pré-calculados é O(n lg n), pois existem O(lg n) tamanhos de intervalos que são potência de 2.


> Consultas: podemos calcular o valor do 'min(a, b)' usando a fórmula:

```min(a, b) = min(min(a, a + k − 1), min(b − k + 1, b))```

O intervalo [a, b] é representado pela união de intervalos [a, a + k − 1] e [b − k + 1, b], ambos de tamanho k.

---
> Exemplo 

Considere o intervalo [1, 6]:

| 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 |
|:---:|:---:|:---:|:---:|:---:|:---:|:---:|:---:|
| 1 | **3** | **8** | **4** | **6** | **1** | **4** | 2 |

O tamanho do intervalo é 6 e a maior potência de 2 que não excede 6 é 4.

| 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 |
|:---:|:---:|:---:|:---:|:---:|:---:|:---:|:---:|
| 1 | **3** | **8** | **4** | **6** | 1 | 4 | 2 |

| 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 |
|:---:|:---:|:---:|:---:|:---:|:---:|:---:|:---:|
| 1 | 3 | 8 | **4** | **6** | **1** | **4** | 2 |

O intervalo [1, 6] é a união dos intervalos [1, 4] e [3, 6].
Como o min(1, 4) = 3 e min(3, 6) = 1, conclu´ımos que min(1, 6) = 1.