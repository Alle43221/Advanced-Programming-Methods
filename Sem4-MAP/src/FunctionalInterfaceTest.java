import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FunctionalInterfaceTest {

    public static <E> void printArie(List<E> l, Arie<E> f){
        l.forEach(x-> System.out.println(f.calculeaza(x)));
    }

    public static void main(String[] args) {
        Arie<Cerc> arieCerc = x->Math.PI*x.getRaza()*x.getRaza();
        Cerc cerc1=new Cerc(2);
        Cerc cerc2=new Cerc(3);
        Cerc cerc3=new Cerc(4);
        Cerc cerc4=new Cerc(5);
        List<Cerc> cercList= Arrays.asList(cerc1,cerc2,cerc3,cerc4);

        printArie(cercList, arieCerc);

        Arie<Patrat> ariePatrat = x->x.getLatura()*x.getLatura();
        Patrat patrat1=new Patrat(2);
        Patrat patrat2=new Patrat(3);
        Patrat patrat3=new Patrat(4);
        Patrat patrat4=new Patrat(5);
        List<Patrat> patratList= Arrays.asList(patrat1,patrat2,patrat3,patrat4);

        printArie(patratList, ariePatrat);
    }
}
