package example;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

@SuppressWarnings("all")
public class ReducingAndStream {


    public static void main(String[] args) {
//        example1();
//        example2();

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

        List<Transaction> result1 = transactions.stream()
                    .filter(day -> day.getYear() == 2011)
                    .sorted((value1, value2) -> value1.getValue() - value2.getValue())
                    .collect(Collectors.toList());

        System.out.println(result1);

        Set<String> result2 = transactions.stream()
                    .map(city -> city.getTrader().getCity())
                    .collect(toSet());

        System.out.println(result2);

        List<String> result3 = transactions.stream()
                    .filter(city -> city.getTrader().getCity().equals("Cambridge"))
                    .map(name -> name.getTrader().getName())
                    .sorted(String::compareTo)
                    .distinct()
                    .collect(Collectors.toList());

        System.out.println(result3);

        String result4 = transactions.stream()
                    .map(name -> name.getTrader().getName())
                    .sorted((name1, name2) -> name1.compareTo(name2))
                    .distinct()
                    .collect(Collectors.joining());

        System.out.println(result4);

        boolean result5 = transactions.stream()
                    .anyMatch(transaction -> transaction.getTrader().getCity().equals("Milan"));

        System.out.println(result5);

        List<String> result6 = transactions.stream()
                    .map(Transaction::getTrader)
                    .filter(city -> city.getCity().equals("Cambridge"))
                    .map(name -> name.getName())
                    .distinct()
                    .collect(toList());

        System.out.println(result6);

        Optional<Integer> result7 = transactions.stream()
                    .map(Transaction::getValue)
                    .reduce(Integer::max);

        Optional<Integer> result8 = transactions.stream()
                    .map(Transaction::getValue)
                    .reduce(Integer::min);

        Optional<Transaction> test8 = transactions.stream()
                    .min(Comparator.comparing(Transaction::getValue));
    }

    private static void example1() {
        List<Integer> someNumbers = Arrays.asList(1, 2, 3, 4, 5);
        Optional<Integer> firstSquareDivisibleByThree = someNumbers.stream()
                    .map(x -> x * x)
                    .filter(x -> x % 3 == 0)
                    .findFirst();
    }

    private static void example2() {
        List<Integer> numbers = Arrays.asList(4, 5, 3, 9);
        int sum = numbers.stream()
                .reduce(0, Integer::sum);
        System.out.println(sum);

        Optional<Integer> maxNumber = numbers.stream()
                    .reduce(Integer::max);

        long count = numbers.stream()
                .count();
    }

}
