package br.com.bank.services;
import br.com.bank.entities.Account;
import java.math.BigDecimal;

public class WithdrawService {
    Account account;

    public WithdrawService(Account account) {
        this.account = account;
    }

    public BigDecimal handle(BigDecimal value) throws Exception {
        if (value.compareTo(BigDecimal.ZERO) < 0) {
            throw new Exception("O valor de saque não pode ser negativo.");
        }

       BigDecimal accountBalance = account.getBalance();

        if (accountBalance.compareTo(value) < 0) {
           throw new Exception("Valor indisponível para saque");
        }

        BigDecimal updatedAccountBalance = accountBalance.subtract(value);
        this.account.setBalance(updatedAccountBalance);

        return updatedAccountBalance;
    }
}
