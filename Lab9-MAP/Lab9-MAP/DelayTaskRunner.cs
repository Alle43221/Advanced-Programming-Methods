namespace Lab9_MAP;
using System;
using System.Threading;

public class DelayTaskRunner(ITaskRunner taskRunner) : AbstractTaskRunner(taskRunner)
{
    private static readonly string Formatter = "HH:mm";

    public new void ExecuteOneTask()
    {
        try
        {
            Thread.Sleep(3000); // Sleep for 3 seconds
        }
        catch (ThreadInterruptedException e)
        {
            throw new Exception("Thread interrupted", e);
        }
        base.ExecuteOneTask();
    }
}