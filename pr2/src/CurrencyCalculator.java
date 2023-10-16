import java.util.*;
import java.io.*;
import java.math.*;

public class CurrencyCalculator {
    private static String lastCurrency;

    public void run() {
        BigDecimal exchangeRate;
        try {
            Scanner configScanner = new Scanner(new File("E:\\Gordei\\practica\\pr2\\src\\config.txt"));
            exchangeRate = new BigDecimal(configScanner.nextLine());
            configScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Configuration file not found.");
            return;
        }

        CurrencyConverter converter = new CurrencyConverter(exchangeRate);

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        scanner.close();

        String[] operations = input.split("\\+");
        BigDecimal result = BigDecimal.ZERO;

        for (String operation : operations) {
            operation = operation.trim();
            if (operation.startsWith("toDollars")) {
                result = result.add(converter.toDollars(operation));
                lastCurrency = "$";
            } else if (operation.startsWith("toRubles")) {
                result = result.add(converter.toRubles(operation));
                lastCurrency = "р";
            } else if (operation.endsWith("р")) {
                result = result.add(new BigDecimal(operation.substring(0, operation.length() - 1)));
                lastCurrency = "р";
            } else if (operation.startsWith("$")) {
                result = result.add(new BigDecimal(operation.substring(1)));
                lastCurrency = "$";
            }
        }

        if (lastCurrency.equals("$")) {
            System.out.println("$" + result.setScale(2, RoundingMode.HALF_UP));
        } else {
            System.out.println(result.setScale(2, RoundingMode.HALF_UP) + "р");
        }
    }
}