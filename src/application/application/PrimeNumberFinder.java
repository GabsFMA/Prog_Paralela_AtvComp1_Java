package application;

import java.util.ArrayList;
import java.util.List;

public class PrimeNumberFinder {

    public static void listarPrimos() {
        List<Integer> listaPrimos = FileUtils.readNumbersFromFile("src/resources/Entrada01.txt");
        listaPrimos.removeIf(numero -> numero < 2 || !isPrime(numero));
        FileUtils.writeNumbersToFile("src/resources/output/Saida_01.txt", listaPrimos);
    }

    public static void soloThread() {
        long tempoInicial = System.currentTimeMillis();
        Thread thread = new Thread(PrimeNumberFinder::listarPrimos);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
        long tempoFinal = System.currentTimeMillis();
        System.out.println("Processo concluído com 1 Thread em " + (tempoFinal - tempoInicial) / 1000.0 + "s");
    }

    public static void multiThread(int qntThreads, String outputFile) {
        long tempoInicial = System.currentTimeMillis();
        List<Integer> numeros = FileUtils.readNumbersFromFile("src/resources/Entrada01.txt");
        List<Integer> indices = Helper.createIndexList(numeros.size());
        int qntPorThread = numeros.size() / qntThreads;
    
        List<List<Integer>> partesNumeros = Helper.splitList(numeros, qntThreads, qntPorThread);
        List<List<Integer>> partesIndices = Helper.splitList(indices, qntThreads, qntPorThread);
    
   
        Integer[] primosOrdenados = new Integer[numeros.size()];
        List<Thread> listaThreads = new ArrayList<>();
    
        for (int i = 0; i < qntThreads; i++) {
            int finalI = i;
            Thread thread = new Thread(() -> {
                List<Integer> numerosParte = partesNumeros.get(finalI);
                List<Integer> indicesParte = partesIndices.get(finalI);
    
                for (int j = 0; j < numerosParte.size(); j++) {
                    int numero = numerosParte.get(j);
                    int indiceOriginal = indicesParte.get(j);
    
                    if (isPrime(numero)) {
                       
                        synchronized (primosOrdenados) {
                            primosOrdenados[indiceOriginal] = numero;
                        }
                    }
                }
            });
            listaThreads.add(thread);
            thread.start();
        }
    
        for (Thread thread : listaThreads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
        }
    
 
        List<int[]> listaPrimosFinal = new ArrayList<>();
        for (int i = 0; i < primosOrdenados.length; i++) {
            if (primosOrdenados[i] != null) {
                listaPrimosFinal.add(new int[]{i, primosOrdenados[i]});
            }
        }
    
        List<Integer> primosSemIndices = new ArrayList<>();
            for (int[] par : listaPrimosFinal) {
                primosSemIndices.add(par[1]); 
            }

        FileUtils.writeNumbersToFile(outputFile, primosSemIndices);
        long tempoFinal = System.currentTimeMillis();
        System.out.println("Processo concluído com " + qntThreads + " Threads em " + (tempoFinal - tempoInicial) / 1000.0 + "s");
    }

    public static void multiThread5() { multiThread(5, "src/resources/output/Saida_05.txt"); }
    public static void multiThread10() { multiThread(10, "src/resources/output/Saida_10.txt"); }

    static boolean isPrime(int numero) {
        if (numero < 2) return false;
        for (int i = 2; i <= Math.sqrt(numero); i++) {
            if (numero % i == 0) {
                return false;
            }
        }
        return true;
    }
}
