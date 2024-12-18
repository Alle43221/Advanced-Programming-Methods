namespace Lab9_MAP;
using System;

public class PrinterTaskRunner(ITaskRunner taskRunner) : AbstractTaskRunner(taskRunner)
{
    private static readonly string Formatter = "HH:mm";

    public new void ExecuteOneTask()
    {
        base.ExecuteOneTask();
        Console.WriteLine($"Executed at {DateTime.Now.ToString(Formatter)}");
    }
}