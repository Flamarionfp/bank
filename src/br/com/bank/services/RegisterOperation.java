package br.com.bank.services;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class RegisterOperation {
    private static Lock fileLock = new ReentrantLock();

    public static void handle(String operationLabel, BigDecimal value) throws IOException {
        String filePath = "registro_operacoes.txt";

        try {
            fileLock.lock(); // Bloqueia para as outras instâncias antes de escrever no arquivo
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true));
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")); // estou usando o timestamp em vez da data informada pelo usuário para registrar exatamente o horário de gravação no arquivo
            String data = timestamp + " - " + operationLabel + ": " + value.toString();
            writer.write(data);
            writer.newLine();
            writer.close();
        } catch (IOException exception) {
            throw new IOException("Erro ao registrar operação no arquivo de registro", exception);
        } finally {
            fileLock.unlock(); // Libera o bloqueio após escrever no arquivo, liberando para as outras instâncias
        }
    }
}
