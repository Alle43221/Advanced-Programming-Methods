import java.time.LocalDateTime;
import java.util.ArrayList;

public class SortingTask extends Task{

    private ArrayList<Integer> list;
    public SortingTask(String description, String id, ArrayList<Integer> arrayList) {
        super(description, id);
            list = arrayList;
    }

    public void sort(SortingStrategy strategy) {
        SorterFactory factory=SorterFactory.getInstance();
        AbstractSorter<Integer> sorter=factory.getSorter(strategy);
        sorter.sort(list);
    }

    @Override
    public void execute() {
        for (Integer integer : list) {
            System.out.println(integer);
        }
    }
}
