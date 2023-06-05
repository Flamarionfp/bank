import br.com.bank.entities.Account;
import br.com.bank.services.DepositService;
import br.com.bank.services.WithdrawService;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        // Apenas para testes, não representa o fluxo do programa

        try {
            Account sourceAccount = new Account(new BigDecimal(100));
            Account destinationAccount = new Account(BigDecimal.ZERO);

            DepositService depositService = new DepositService(sourceAccount, destinationAccount);
            depositService.handle(new BigDecimal(40));

            WithdrawService withdrawService = new WithdrawService(sourceAccount);

            System.out.println("SourceAccount Account " + sourceAccount.getBalance());
            System.out.println("Destination Account " + destinationAccount.getBalance());

            withdrawService.handle(new BigDecimal(10));
            System.out.println("Source Account " + sourceAccount.getBalance());

        } catch (Exception exception) {
            System.out.print(exception.getMessage());
        }
    }
}