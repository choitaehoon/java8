package example;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Test {

    public static void main(String[] args) {
        List<Dish> menu = Arrays.asList(
          new Dish("pork", false, 800, Dish.Type.MEAT),
          new Dish("beef", false, 700, Dish.Type.MEAT),
          new Dish("chicken", false, 400, Dish.Type.MEAT),
          new Dish("french fries", true, 530, Dish.Type.OTHER),
          new Dish("rice", true, 350, Dish.Type.OTHER),
          new Dish("season", true, 120, Dish.Type.OTHER),
          new Dish("pizza", true, 550, Dish.Type.OTHER),
          new Dish("prawns", false, 300, Dish.Type.FISH),
          new Dish("salmon", false, 450, Dish.Type.FISH)
        );

        System.out.println(example1(menu));
    }

    private static List<String> example1(List<Dish> menu) {
        return menu.stream()
                .filter(d -> threeHundredCaloriesGreaterThanCheck(d.getCalories()))
                .map(Dish::getName)
                .limit(3)
                .collect(toList());
    }

    private static boolean threeHundredCaloriesGreaterThanCheck(int calories) {
        return calories > 300;
    }

    private static void example2() {
        List<String> title = Arrays.asList("Java8", "In", "Action");
        Stream<String> s = title.stream();
        s.forEach(System.out::println);

        // 스트림이 이미 소비 되었기때문에 IllegalStateException 발생
        s.forEach(System.out::println);
    }

}