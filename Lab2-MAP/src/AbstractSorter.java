import java.util.ArrayList;

public abstract class AbstractSorter<E extends Comparable<E>> {
    abstract void sort(ArrayList<E> arrayList);
}
