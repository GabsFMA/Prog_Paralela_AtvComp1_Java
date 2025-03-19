package application;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;

public class PrimeNumberTask {

    public static void listarPrimos(List<Integer> numeros, List<Integer> indices, List<int[]> resultado, Lock lock) {
        List<int[]> listaPrimos = new ArrayList<>();
        for (int i = 0; i < numeros.size(); i++) {
            int numero = numeros.get(i);
            int indice = indices.get(i);
            if (PrimeNumberFinder.isPrime(numero)) {
                listaPrimos.add(new int[]{indice, numero});
            }
        }

        lock.lock();
        try {
            resultado.addAll(listaPrimos);
        } finally {
            lock.unlock();
        }
    }
}
