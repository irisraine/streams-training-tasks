package org.aston.homework3;

import java.util.*;
import java.util.stream.Stream;

public class PuttingIntoPractice {

    public static void main(String... args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        System.out.println("=== 1. Найти все транзакции за 2011 год и отсортировать их по сумме (от меньшей к большей). ===");
        List<Transaction> transactionsAtSpecificYearSorted = transactions.stream()
                .filter(transaction -> transaction.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .toList();
        for (Transaction transaction : transactionsAtSpecificYearSorted)
            System.out.println(transaction);

        System.out.println();
        System.out.println("=== 2. Вывести список неповторяющихся городов, в которых работают трейдеры. ===");
        List<String> distinctCitiesWhereTradersWork = Stream.of(raoul, mario, alan, brian)
                .map(Trader::getCity)
                .distinct()
                .toList();
        for (String city : distinctCitiesWhereTradersWork)
            System.out.println(city);

        System.out.println();
        System.out.println("=== 3. Найти всех трейдеров из Кембриджа и отсортировать их по именам. ===");
        List<Trader> cambridgeTradersSorted = Stream.of(raoul, mario, alan, brian)
                .filter(trader -> trader.getCity().equals("Cambridge"))
                .sorted(Comparator.comparing(Trader::getName))
                .toList();
        for (Trader trader : cambridgeTradersSorted)
            System.out.println(trader);

        System.out.println();
        System.out.println("=== 4. Вернуть строку со всеми именами трейдеров, отсортированными в алфавитном порядке. ===");
        List<String> allTradersNamesSorted = Stream.of(raoul, mario, alan, brian)
                .map(Trader::getName)
                .sorted()
                .toList();
        System.out.println(allTradersNamesSorted);

        System.out.println();
        System.out.println("=== 5. Выяснить, существует ли хоть один трейдер из Милана. ===");
        boolean isTraderFromMilanExists = Stream.of(raoul, mario, alan, brian)
                .anyMatch(trader -> trader.getCity().equals("Milan"));
        System.out.println(isTraderFromMilanExists);

        System.out.println();
        System.out.println("=== 6. Вывести суммы всех транзакций трейдеров из Кембриджа. ===");
        int cambridgeTradersTransactionsSum = transactions.stream()
                .filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
                .mapToInt(Transaction::getValue)
                .sum();
        System.out.println(cambridgeTradersTransactionsSum);

        System.out.println();
        System.out.println("=== 7. Какова максимальная сумма среди всех транзакций? ===");
        OptionalInt maxTransactionSum = transactions.stream()
                .mapToInt(Transaction::getValue)
                .max();
        System.out.println(maxTransactionSum.getAsInt());

        System.out.println();
        System.out.println("=== 8. Найти транзакцию с минимальной суммой. ===");
        Optional<Transaction> transactionWithMinSum = transactions.stream()
                .min(Comparator.comparing(Transaction::getValue));

        System.out.println(transactionWithMinSum.get());
    }
}