namespace Lab9_MAP;

public class AbstractTaskRunner(ITaskRunner taskRunner) : ITaskRunner
{
    public void ExecuteOneTask()
    {
        taskRunner.ExecuteOneTask();
    }

    public void ExecuteAll()
    {
        while (HasTask())
        {
            ExecuteOneTask();
        }
    }

    public void AddTask(Task t)
    {
        taskRunner.AddTask(t);
    }

    public bool HasTask()
    {
        return taskRunner.HasTask();
    }
}