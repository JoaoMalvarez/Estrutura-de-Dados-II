# Exercícios Tabelas Hash

## 1. Defina o que é uma tabela hash e explique seu objetivo.

> Tabela hash é uma estrutura de dados que associa 'valores' com 'chaves', o que permite buscas, inserções e remoções muito rápidas (com o tempo médio de O(1). Servindo para guardar tais valores e achar eles em uma velocidade muito rápida. 

---

## 2. Descreva a diferença entre função hash e tabela hash.
> A tabela hash é a estrutura de dados, enquanto a função hash é a conta matemática que calcula onde cada valor vai parar dentro da tabela.

---

## 3. Em uma tabela hash com 10 posições, qual seria o índice gerado pela função h(x) = x mod 10 para os valores:
### a) 7
| x = 7 | -> h(7) = 7 mod 10 = | 7 |
> O índice gerado pela função para o valor '7' seria: 7

### b) 23
| x = 23 | -> h(23) = 23 mod 10 = | 3 |
> O índice gerado pela função para o valor '23' seria: 3

### c) 91
| x = 91 | -> h(91) = 91 mod 10 = | 1 |
> O índice gerado pela função para o valor '91' seria: 1

---

## 4. Explique o que é uma colisão em tabelas hash.
> Colisão em tabela hash é quando o índice pegado pela função hash se repete enquanto já tem um valor (ou não cabe mais valores dentro do espaço).

---

## 5. Cite dois critérios importantes para uma boa função hash.
> Uma função hash boa o que causaria uma distribuição uniforme, e uma velocidade computacional muito rápida.

---

## 6. Explique a diferença entre encadeamento externo e interno?
> O encadeamento externo usa estruturas fora da tabela, com cada posição da tabela guardando um ponteiro para o ínicio de uma lista encadeada. Já o encadeamento interno guarda tudo dentro da tabela, tudo dentro do próprio vetor da tabela.

---

## 7. Em uma tabela hash com encadeamento, insira os valores 12, 44, 13, 88, 23, usando a função h(x) = x mod 5.
### a) Mostre o estado da tabela usando encadeamento externo;
tabela:
[0] - [ 0 ]
[1] - [ 0 ]
[2] - [ 12 ]
[3] - [ 13 ] -> [ 88 ] -> [ 23 ]
[4] - [ 44 ]

> Passo a passo: a inserção do 12 vai para o índice 2; 44 vai para o 4; 13 vai para o 3; 88 deveria ir para o índice 3, mas já tem o 13 ocupando o espaço, logo acontece uma colisão e é inserido no final da lista do índice 3; 23 deveria ir para o índice 3, mas já tem o 13 e o 88 ocupando o espaço, logo acontece uma colisão e é inserido no final da lista do índice 3;

### b) Mostre o estado da tabela usando encadeamento interno;
tabela:
[0] - [ 88 ]
[1] - [ 23 ]
[2] - [ 12 ]
[3] - [ 13 ]
[4] - [ 44 ]

> Passo a passo: a inserção do 12 vai para o índice 2; 44 vai para o 4; 13 vai para o 3; 88 deveria ir para o índice 3, mas já tem o 13 ocupando o espaço, logo acontece uma colisão e acontece o seguinte: a soldagem linear, ou seja, vai tentar a próxima vaga, se estiver vaga, ele está lá, e o próximo espaço livre é o '0'; 23 deveria ir para o índice 3, mas já tem o 13 ocupando o espaço, logo acontece uma colisão e acontece o seguinte: a soldagem linear, ou seja, vai tentar a próxima vaga, se estiver vaga, ele está lá, e o próximo espaço livre é o '1'; 

---

## 8. Usando endereçamento aberto com sondagem linear, insira na tabela (tamanho 7) os valores 10, 17, 24, 31. Use a função h(x) = x mod 7.
tabela:
[0] - [ 0 ]
[1] - [ 0 ]
[2] - [ 0 ]
[3] - [ 10 ]
[4] - [ 17 ]
[5] - [ 24 ]
[6] - [ 31 ]

> Todos os numeros inseridos deram o índice 3, então com soldagem linear eles foram para o valor seguinte dentro da tabela, com elas colidindo diversas vezes, como por exemplo o valor 31 colidindo com todos os valores.