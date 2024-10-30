import java.util.function.Function;

public class FunctionTest {
    public static void main(String[] args) {
        Function<String, Integer> stringToInt = x->Integer.valueOf(x);
        Function<String, Integer> stringToInt1 = Integer::valueOf;

        System.out.println(stringToInt.apply("123"));
        System.out.println(stringToInt1.apply("123"));

        Function<Integer, Integer> square = x->x*x;
        System.out.println(stringToInt.andThen(square).apply("13"));
    }
}
