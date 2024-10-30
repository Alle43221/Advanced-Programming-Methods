import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class SuplierTest {
    public static void main(String[] args) {
        Supplier<List> supplier=()-> new ArrayList<String>();
        Supplier<List> supplier1 =ArrayList::new;
        Supplier<LocalDate> supplier2 = LocalDate::now;
        Supplier<LocalDate> supplier3 = ()->LocalDate.now();

        System.out.println(supplier.get());
        System.out.println(supplier1.get());
        System.out.println(supplier2.get());
        System.out.println(supplier3.get());

    }
}
