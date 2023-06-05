package br.com.bank.entities;
import java.math.BigDecimal;

public class Account {
    private BigDecimal balance = BigDecimal.ZERO;

    public Account(BigDecimal value) throws Exception {
        this.setBalance(value);
    }

    public BigDecimal getBalance() {
        return this.balance;
    }

    public void setBalance(BigDecimal value) throws Exception {
        if (value.compareTo(BigDecimal.ZERO) < 0) {
            throw new Exception("O valor não pode ser negativo");
        }

        this.balance = value;
    }
}