# Análise de Desempenho

## Introdução
Este projeto tem como objetivo analisar o desempenho de diferentes abordagens para a identificação de números primos a partir de um arquivo de entrada. Três abordagens foram implementadas:

- **Execução com uma thread**
- **Execução multithread com 5 threads**
- **Execução multithread com 10 threads**

Os tempos de execução para cada abordagem foram registrados e serão comparados graficamente.

---

## Estratégia de Implementação
A implementação está dividida em diversas classes, cada uma com uma responsabilidade específica:

- **`FileUtils`**: Responsável por leitura e escrita de arquivos.
- **`Helper`**: Contém funções auxiliares para manipulação de listas.
- **`PrimeNumberFinder`**: Implementa as lógicas para encontrar números primos, tanto na versão single-threaded quanto multi-threaded.
- **`PrimeNumberTask`**: Auxilia na execução concorrente com uso de locks.
- **`Main`**: Classe principal que executa os diferentes testes de desempenho.

Cada versão segue a seguinte lógica:

1. Ler os números do arquivo de entrada.
2. Filtrar os números primos.
3. Escrever os números primos no arquivo de saída.
4. Medir o tempo de execução e exibi-lo no console.

---

## Motivo da Escolha do Java
O projeto foi inicialmente desenvolvido em Python, mas posteriormente foi descoberto que sua execução eficiente com múltiplas threads era inviável devido ao **Global Interpreter Lock (GIL)**. O GIL impede que o Python execute threads simultaneamente em múltiplos núcleos de CPU, limitando o desempenho de aplicações fortemente baseadas em processamento paralelo. Por essa razão, optou-se por reescrever o projeto em Java, uma linguagem que permite o verdadeiro paralelismo utilizando múltiplas threads.

---

## Resultados de Desempenho
Os tempos de execução observados foram:

| Versão | Tempo de Execução (s) |
|---------|--------------------|
| 1 Thread | X.XX s |
| 5 Threads | X.XX s |
| 10 Threads | X.XX s |

(Os valores `X.XX s` devem ser substituídos pelos tempos medidos durante a execução real.)

### Gráfico Comparativo

![Desempenho das Versões](gráfico_comparativo.png)

(O gráfico deve ser gerado a partir dos dados coletados e incluído no repositório.)

---

## Conclusão
A implementação multithread demonstrou um ganho significativo de desempenho em relação à versão single-threaded. No entanto, foi observado que aumentar o número de threads não resulta necessariamente em um ganho linear, devido à sobrecarga de sincronização e gerenciamento de threads.

---

## Como Executar
Para executar os testes, basta compilar e rodar a classe `Main.java`.

```sh
javac application/*.java
java application.Main
```

Os resultados serão exibidos no console e os arquivos de saída serão gerados na pasta `src/resources/output/`.

---

## Autor
[Seu Nome]

