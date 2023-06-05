import br.com.bank.entities.Account;
import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        try {
            Account account = new Account(new BigDecimal(100));
            System.out.println(account.getBalance());
        } catch (Exception exception) {
            System.out.print(exception.getMessage());
        }
    }
}