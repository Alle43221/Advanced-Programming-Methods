import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ComparatorTest {
    public static void main(String[] args) {
        Comparator<Integer> c = (x, y) -> Integer.compare(y, x);
        Comparator<Integer> c1 = Integer::compare;

        List<Integer> numere= Arrays.asList(7,8,4,5,1,2,3,6,9);
        numere.sort(c);
        System.out.println(numere);

        numere.sort(c1.reversed());
        System.out.println(numere);
    }
}
