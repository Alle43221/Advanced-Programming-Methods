namespace Lab9_MAP;

public class StrategyTaskRunner(Strategy strategy) : ITaskRunner
{
    private readonly IContainer _container = new TaskContainerFactory().CreateContainer(strategy);

    public void ExecuteOneTask()
    {
        var t = _container.Remove();
        t.Execute();
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
        _container.Add(t);
    }

    public bool HasTask()
    {
        return !_container.IsEmpty();
    }
}
