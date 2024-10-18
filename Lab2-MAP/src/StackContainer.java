import java.util.ArrayList;
import java.util.List;

public class StackContainer extends AbstractContainer {
    public StackContainer() {
        super();
    }

    @Override
    public Task remove() {
        if(this.tasks.isEmpty())
            return null;
        return tasks.remove(tasks.size()-1);
    }


}
