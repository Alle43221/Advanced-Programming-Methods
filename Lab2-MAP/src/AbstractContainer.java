import java.util.ArrayList;
import java.util.List;

public abstract class AbstractContainer implements Container {
    protected List<Task> tasks;
    public AbstractContainer(){
        this.tasks=new ArrayList<>();
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public int size() {
        return tasks.size();
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }
}
