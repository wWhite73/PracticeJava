import java.math.*;

public class CurrencyConverter {
    private final BigDecimal exchangeRate;

    public CurrencyConverter(BigDecimal exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public BigDecimal toDollars(String operation) {
        String amountStr = operation.substring(operation.indexOf("(") + 1, operation.indexOf("р"));
        BigDecimal amount = new BigDecimal(amountStr);
        return amount.divide(exchangeRate, 2, RoundingMode.HALF_UP);
    }

    public BigDecimal toRubles(String operation) {
        String amountStr = operation.substring(operation.indexOf("$") + 1, operation.indexOf(")"));
        BigDecimal amount = new BigDecimal(amountStr);
        return amount.multiply(exchangeRate);
    }
}