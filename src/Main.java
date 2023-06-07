import br.com.bank.entities.Account;
import br.com.bank.services.WithdrawService;
import br.com.bank.services.DepositService;
import br.com.bank.helpers.FormatBrazilianCurrency;
import java.math.BigDecimal;

public class Main {
    public static final int NUMBER_OF_OPERATIONS = 2;

    public static void main(String[] args) {
        try {
            System.out.println("Banco Internacional");
            System.out.println("-------------------");

            BigDecimal initialUserBalance = new BigDecimal(100);
            Account userAccount = new Account(initialUserBalance);
            BigDecimal operationValue;

            for (int i = 0; i < NUMBER_OF_OPERATIONS; i++){
                int selectedOption = Menu.getSelectedOption();

                switch (selectedOption) {
                    case 1: {
                        System.out.println("Seu saldo é de " + FormatBrazilianCurrency.format(userAccount.getBalance()));
                        break;
                    }
                    case 2: {
                        WithdrawService withDrawService = new WithdrawService(userAccount);
                        operationValue = Menu.getValue();
                        withDrawService.handle(operationValue);
                        break;
                    }
                    default: {
                        Account destinationAccount = new Account(BigDecimal.ZERO); // criado de maneira fixa apenas para seguir o contrato da implementação, como é apenas uma simulação
                        DepositService depositService = new DepositService(userAccount, destinationAccount);
                        operationValue = Menu.getValue();
                        depositService.handle(operationValue);
                    }
                }
            }
        } catch (Exception exception) {
            System.out.print(exception.getMessage());
        }
    }
}