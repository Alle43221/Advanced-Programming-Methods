namespace Lab9_MAP;

using System.Collections.Generic;

public class StackContainer : IContainer
{
    private readonly List<Task> _tasks = [];

    public Task Remove()
    {
        if (_tasks.Count == 0)
            return null;
        var task = _tasks[^1];
        _tasks.RemoveAt(_tasks.Count - 1);
        return task;
    }

    public void Add(Task task)
    {
        _tasks.Add(task);
    }

    public int Size()
    {
        return _tasks.Count;
    }

    public bool IsEmpty()
    {
        return _tasks.Count == 0;
    }
}
