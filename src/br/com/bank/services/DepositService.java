package br.com.bank.services;
import br.com.bank.entities.Account;
import java.math.BigDecimal;

public class DepositService {
    private Account sourceAccount;
    private Account destinationAccount;
    public DepositService(Account sourceAccount, Account destinationAccount) {
        this.sourceAccount = sourceAccount;
        this.destinationAccount = destinationAccount;
    }
    public void handle(BigDecimal value) throws Exception {
        BigDecimal sourceAccountBalance = sourceAccount.getBalance();

        if (value.compareTo(BigDecimal.ZERO) < 0) {
            throw new Exception("O valor do depósito não pode ser negativo.");
        }

        if (sourceAccountBalance.compareTo(value) < 0) {
            throw new Exception("Saldo insuficiente para realizar a transação");
        }

        BigDecimal updatedDestinationAccountBalance = destinationAccount.getBalance().add(value);
        BigDecimal updatedSourceAccountBalance = sourceAccount.getBalance().subtract(value);

        synchronized (sourceAccount) { // isso é usado para garantir as integridade dos dados na transação, tratando a concorrência
            synchronized (destinationAccount) {
                destinationAccount.setBalance(updatedDestinationAccountBalance);
                sourceAccount.setBalance(updatedSourceAccountBalance);

                RegisterOperation.handle("Depósito", value);
            }
        }
    }
}
