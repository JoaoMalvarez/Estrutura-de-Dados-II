# Arvore de Fenwick

A árvore de Fenwick, também conhecida por **BIT (binary indexed tree)**, foi apresentada por P. M. Fenwick em 1994 como uma estrutura para facilitar consultas e atualizações em vetores.
Essa estrutura permite calcular somas de prefixos e atualizar valores de forma eficiente, sem precisar recalcular todas as somas a cada alteração.

## Estrutura 

Permite duas operações em tempo O(lg n):
- consulta de soma em intervalos; e
- atualizações de valores.

A árvore de Fenwick é uma árvore represenetada em um vetor iniciando no índice 1.

Seja p(k) o valor da maior potência de 2 que divide k.
Armazenamos uma árvore binária indexada como um vetor tal que:
```tree[k] = sum(k − p(k) + 1, k)```
cada posiçãao k contém somas de valores em um intervalo do vetor original cujo tamanho é p(k) e termina na posição k.

---

## Exemplo

Considere o seguinte vetor v:
| 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 |
|:---:|:---:|:---:|:---:|:---:|:---:|:---:|:---:|
| 1 | 3 | 8 | 4 | 6 | 1 | 3 | 2 |

A árvore binária indexada correspondente á a seguinte:
| 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 |
|:---:|:---:|:---:|:---:|:---:|:---:|:---:|:---:|
| 1 | 4 | 4 | 16 | 6 | 7 | 4 | 29 |

> Exemplo: p(6) = 2, tree[6] contém o valor de soma(5, 6).

---

A figura a seguir mostra como cada valor na árvore binária indexada corresponde ao intervalo no vetor original.

<table style="border-collapse: collapse; text-align: center; font-family: sans-serif; margin: auto;">
  <!-- Índices superiores -->
  <tr style="font-weight: bold; color: #000;">
    <td style="width: 40px;">1</td>
    <td style="width: 40px;">2</td>
    <td style="width: 40px;">3</td>
    <td style="width: 40px;">4</td>
    <td style="width: 40px;">5</td>
    <td style="width: 40px;">6</td>
    <td style="width: 40px;">7</td>
    <td style="width: 40px;">8</td>
  </tr>
  
  <!-- Array Principal -->
  <tr>
    <td style="border: 1px solid #000; padding: 10px; font-size: 16px;">1</td>
    <td style="border: 1px solid #000; padding: 10px; font-size: 16px;">4</td>
    <td style="border: 1px solid #000; padding: 10px; font-size: 16px;">4</td>
    <td style="border: 1px solid #000; padding: 10px; font-size: 16px;">16</td>
    <td style="border: 1px solid #000; padding: 10px; font-size: 16px;">6</td>
    <td style="border: 1px solid #000; padding: 10px; font-size: 16px;">7</td>
    <td style="border: 1px solid #000; padding: 10px; font-size: 16px;">4</td>
    <td style="border: 1px solid #000; padding: 10px; font-size: 16px;">29</td>
  </tr>
  
  <!-- Setas apontando para cima -->
  <tr style="font-size: 14px; line-height: 1;">
    <td>↑</td>
    <td>↑</td>
    <td>↑</td>
    <td>↑</td>
    <td>↑</td>
    <td>↑</td>
    <td>↑</td>
    <td>↑</td>
  </tr>
  
  <!-- Nível 1: Blocos de tamanho 1 -->
  <tr>
    <td style="border: 1px solid #000; height: 18px;"></td>
    <td></td>
    <td style="border: 1px solid #000; height: 18px;"></td>
    <td></td>
    <td style="border: 1px solid #000; height: 18px;"></td>
    <td></td>
    <td style="border: 1px solid #000; height: 18px;"></td>
    <td></td>
  </tr>
  
  <!-- Nível 2: Blocos de tamanho 2 -->
  <tr>
    <td colspan="2" style="border: 1px solid #000; height: 18px;"></td>
    <td colspan="2"></td>
    <td colspan="2" style="border: 1px solid #000; height: 18px;"></td>
    <td colspan="2"></td>
  </tr>
  
  <!-- Nível 3: Blocos de tamanho 4 -->
  <tr>
    <td colspan="4" style="border: 1px solid #000; height: 18px;"></td>
    <td colspan="4"></td>
  </tr>
  
  <!-- Nível 4: Bloco completo (Tamanho 8) -->
  <tr>
    <td colspan="8" style="border: 1px solid #000; height: 18px;"></td>
  </tr>
</table>

---

## Soma de valores no intervalo

Com a árvore binária indexada:
- O cálculo do valor de soma(1, k) pode ser executado em tempo O(lg n);
- O intervalo [1, k] pode ser sempre dividido em O(lg n) intervalos cujas somas estão na árvore.

---

Para calcular o valor da soma(a, b) onde a > 1, podemos usar o mesmo método usado em vetor de soma de prefixos. 
```soma(a, b) = soma(1, b) − soma(1, a − 1)```
O tempo de execução é O(lg n).

---

## Implementação

As operações de uma árvore binária indexada (binary indexed tree) podem ser implementadas usando operaçõoes de bits. Contudo, podemos calcular o valor de p(k) usando a fórmula:
```p(k) = k& − k.```
Esta operação é um truque de manipulação de bits que serve para isolar o bit menos significativo (Least Significant Bit, ou LSB). O resultando é uma potência de 2 que divide k.

### Cálculo da soma no intervalo

O trecho de código a seguir calcula o valor da soma(1, k).
```
int soma(int k) {
    int s = 0;
    while (K >= 1){
        s += tree[k];
        k -= k&-k;
    }
    return s;
}
```
> Consumo de tempo: O(lg n)

---

### Atualização de um valor no intervalo

A seguinte função aumenta o valor do vetor na posição k por x (x pode ser positivo ou negativo).
```
void aumenta(int k, int x) {
    int s = 0;
    while (K <= n){
        tree[k] += x;
        k += k&-k;
    }
}
```
> Consumo de tempo: O(lg n)

---
