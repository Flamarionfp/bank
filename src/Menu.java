import java.math.BigDecimal;
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
}
