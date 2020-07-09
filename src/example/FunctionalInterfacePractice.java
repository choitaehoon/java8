package example;

public class FunctionalInterfacePractice {

    public static void main(String[] args) {
        System.out.println(calculate(Integer::sum, 3, 5));
        System.out.println(calculate((a1, a2) -> a1 - a2, 10, 7));
    }

    public static int calculate(Calculate calculate, int compareOne, int compareTwo) {
        return calculate.calculate(compareOne, compareTwo);
    }

}

interface Calculate {
    int calculate(int compareOne, int compareTwo);
}
