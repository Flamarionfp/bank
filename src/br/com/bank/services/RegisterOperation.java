package br.com.bank.services;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RegisterOperation {
    public static void handle(String operationLabel, BigDecimal value) throws IOException {
        String filePath = "registro_operacoes.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")); // receber data por parâmetro posteriormente
            String data = timestamp + " - " + operationLabel + ": " + value.toString();
            writer.write(data);
            writer.newLine();
        } catch (IOException exception) {
            throw new IOException("Erro ao registrar operação no arquivo de registro", exception);
        }
    }
}
