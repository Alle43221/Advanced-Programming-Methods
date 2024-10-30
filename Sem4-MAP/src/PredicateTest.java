import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class PredicateTest {
    public static<E> void print(List<E> list, Predicate<E> p) {
        list.forEach(x-> {
            if(p.test(x))
            {
                System.out.println(x);
            }
        });
    }
    public static void main(String[] args) {
        Cerc cerc1=new Cerc(2);
        Cerc cerc2=new Cerc(13);
        Cerc cerc3=new Cerc(3);
        Cerc cerc4=new Cerc(15);
        List<Cerc> cercList= Arrays.asList(cerc1,cerc2,cerc3,cerc4);

        Predicate<Cerc> predicateCerc = x->{
            return x.getRaza() < 10;
        };
        Predicate<Cerc> predicateCerc1 = predicateCerc.negate();

        Predicate<Cerc> predicateCerc2 = predicateCerc.and(x->x.getRaza()%2==1);
        print(cercList, predicateCerc);
        System.out.println();
        print(cercList, predicateCerc1);
        System.out.println();
        print(cercList, predicateCerc2);
    }
}
