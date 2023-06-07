import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
public class Menu {
    static Scanner scanner = new Scanner(System.in);
    public static int getSelectedOption() {
        System.out.println("Qual opção você deseja realizar? ");
        System.out.println("""
                    1 - Consultar Saldo
                    2 - Sacar
                    3 - Depositar
                """);


        int selectedOption = scanner.nextInt();

        return selectedOption;
    }

    public static BigDecimal getValue() {
        System.out.println("Digite o valor desejado: ");
        BigDecimal valueInput = scanner.nextBigDecimal();

        return valueInput;
    }

    public static LocalDateTime getOperationDate() {
        Scanner dateScanner = new Scanner(System.in);
        System.out.println("Digite a data e horário no formato dd/MM/yyyy HH:mm:ss: ");
        String dateInput = dateScanner.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        try {
            return LocalDateTime.parse(dateInput, formatter);
        } catch (Exception e) {
            System.out.println("Data e horário inválidos.");
            return getOperationDate();
        }
    }
}
