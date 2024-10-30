import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class StreamTest {
    public static <E> List<E> filterGeneric(List<E> lista, Predicate<E> p){
        return lista.stream().filter(p).collect(Collectors.toList());
    }
    public static <E> List<E> filterGeneric(List<E> lista, Predicate<E> p, Comparator<E> comparator){
        return lista.stream().filter(p).sorted(comparator).collect(Collectors.toList());
    }
    public static void main(String[] args) {
        List<Integer> lista = Arrays.asList(5, 6, 7, 8, 9, 1, 2, 3, 4, 10);
        Predicate<Integer> predicate = x->x%2==0;
        Comparator<Integer> comparator = Integer::compare;
        System.out.println(filterGeneric(lista, predicate));
        System.out.println(filterGeneric(lista, predicate, comparator));
    }
}
