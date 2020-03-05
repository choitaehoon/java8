package example;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

public class practice {

    public static void main(String[] args) {
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

        List<Transaction> exam1 = transactions.stream()
                .filter(year -> year.getYear() == 2011)
                .sorted(comparing(Transaction::getValue))
                .collect(toList());

        Set<String> exam2 = transactions.stream()
                .map(trader -> trader.getTrader().getCity())
                .collect(toSet());

        List<Trader> exam3 = transactions.stream()
                .map(Transaction::getTrader)
                .filter(trader -> trader.getCity().equals("Cambridge"))
                .distinct()
                .sorted(comparing(Trader::getName))
                .collect(toList());

        String exam4 = transactions.stream()
                .map(trader -> trader.getTrader().getName())
                .distinct()
                .sorted()
                .collect(Collectors.joining());

        boolean exam5 = transactions.stream()
                .map(Transaction::getTrader)
                .anyMatch(trader -> trader.getCity().equals("Milan"));

        List<String> exam6 = transactions.stream()
                .map(Transaction::getTrader)
                .filter(trader -> trader.getCity().equals("Cambridge"))
                .distinct()
                .map(Trader::getName)
                .collect(toList());

        int max = transactions.stream()
                .map(Transaction::getValue)
                .sorted()
                .reduce(Integer::max)
                .get();

        int min = transactions.stream()
                .map(Transaction::getValue)
                .reduce((t1, t2) -> t1 < t2 ? t1 : t2)
                .get();

        int min1 = transactions.stream()
                .min(comparing(Transaction::getValue))
                .get()
                .getValue();

        int sum = transactions.stream()
                .mapToInt(Transaction::getValue)
                .sum();

    }

}
