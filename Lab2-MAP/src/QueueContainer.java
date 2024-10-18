import java.util.ArrayList;
import java.util.List;

public class QueueContainer extends AbstractContainer{

    @Override
    public Task remove() {
        if(tasks.isEmpty())
            return null;
        return tasks.remove(0);
    }

}
