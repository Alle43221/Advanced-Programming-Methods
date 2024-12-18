using System;
namespace Lab9_MAP;

class Program
{
    private static void Main(string[] args)
    {
        var tasks = new MessageTask[5];
        tasks[0] = new MessageTask("mail", "1", "cv", "arthur", "employer", DateTime.Now);
        tasks[1] = new MessageTask("mail", "2", "resignation letter", "steffen", "employer", DateTime.Now);
        tasks[2] = new MessageTask("mail", "3", "cv", "steve", "employer", DateTime.Now);
        tasks[3] = new MessageTask("mail", "4", "cv", "mary", "employer", DateTime.Now);
        tasks[4] = new MessageTask("mail", "5", "cv", "margaret", "employer", DateTime.Now);

        // foreach (var task in tasks)
        // {
        //     Console.WriteLine(task);
        // }

        var strategy = Enum.Parse<Strategy>(args[0], true); // Parse the strategy argument
        var strategyTaskRunner = new StrategyTaskRunner(strategy);

        foreach (var task in tasks)
        {
            strategyTaskRunner.AddTask(task);
        }

        strategyTaskRunner.ExecuteAll();

        var printerTaskRunner = new PrinterTaskRunner(strategyTaskRunner);
        // printerTaskRunner.ExecuteAll();

        var delayTaskRunner = new DelayTaskRunner(new DelayTaskRunner(printerTaskRunner));
        delayTaskRunner.ExecuteAll();
    }
}