import br.com.bank.entities.Account;
import br.com.bank.services.WithdrawService;
import br.com.bank.services.DepositService;
import br.com.bank.helpers.FormatBrazilianCurrency;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Main {
    public static final int NUMBER_OF_OPERATIONS = 6;

    public static void main(String[] args) {
        try {
            System.out.println("Banco Internacional");
            System.out.println("-------------------");

            BigDecimal initialUserBalance = new BigDecimal(100);
            Account userAccount = new Account(initialUserBalance);
            BigDecimal operationValue = BigDecimal.ZERO;

            for (int i = 0; i < NUMBER_OF_OPERATIONS; i++) {
                int selectedOption = Menu.getSelectedOption();

                if (selectedOption != 1) {
                    operationValue = Menu.getValue();
                }

                if (selectedOption == 2 || selectedOption == 3) {
                    LocalDateTime operationDate = null;
                    boolean validDate = false;

                    while (!validDate) {
                        try {
                            operationDate = Menu.getOperationDate();
                            LocalDateTime currentTime = LocalDateTime.now();

                            if (currentTime.isAfter(operationDate)) {
                                System.out.println("A data de operação informada já passou");
                            } else {
                                validDate = true;
                                while (currentTime.isBefore(operationDate)) {
                                    currentTime = LocalDateTime.now();
                                }
                            }
                        } catch (DateTimeParseException e) {
                            System.out.println("Data e horário inválidos. Tente novamente.");
                        }
                    }
                }

                switch (selectedOption) {
                    case 1: {
                        System.out.println("Seu saldo é de " + FormatBrazilianCurrency.format(userAccount.getBalance()));
                        break;
                    }
                    case 2: {
                        WithdrawService withDrawService = new WithdrawService(userAccount);
                        withDrawService.handle(operationValue);
                        break;
                    }
                    case 3: {
                        Account destinationAccount = new Account(BigDecimal.ZERO);
                        DepositService depositService = new DepositService(userAccount, destinationAccount);
                        depositService.handle(operationValue);
                        break;
                    }
                }
            }
        } catch (Exception exception) {
            System.out.print(exception.getMessage());
        }
    }
}
