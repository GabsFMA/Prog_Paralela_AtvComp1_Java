package application;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

    public static List<Integer> readNumbersFromFile(String filePath) {
        List<Integer> numeros = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                try {
                    numeros.add(Integer.parseInt(linha.trim()));
                } catch (NumberFormatException e) {
                    System.err.println("Erro ao converter número: " + linha);
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler arquivo: " + filePath);
            e.printStackTrace();
        }
        return numeros;
    }

    public static void writeNumbersToFile(String path, List<Integer> numbers) {
        File file = new File(path);
        try {
            File parentDir = file.getParentFile();
            if (!parentDir.exists()) {
                boolean dirsCreated = parentDir.mkdirs();
                if (dirsCreated) {
                    System.out.println("Diretório criado: " + parentDir.getAbsolutePath());
                } else {
                    System.out.println("Falha ao criar diretório: " + parentDir.getAbsolutePath());
                    return; 
                }
            }
            FileWriter writer = new FileWriter(file, false);
            for (Integer num : numbers) {
                writer.write(num + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.err.println("Erro ao escrever no arquivo: " + path);
            e.printStackTrace();
        }
    }
}

