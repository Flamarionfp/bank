package br.com.bank.helpers;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class FormatBrazilianCurrency {
    public static String format(BigDecimal value) {
        Locale brazilianLocale = new Locale("pt", "BR");
        NumberFormat formatter = NumberFormat.getCurrencyInstance(brazilianLocale);
        return formatter.format(value);
    }
}