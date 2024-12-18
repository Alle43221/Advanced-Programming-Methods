namespace Lab9_MAP;

using System;

public class TaskContainerFactory : IFactory
{
    public  IContainer CreateContainer(Strategy strategy)
    {
        switch (strategy)
        {
            case Strategy.FIFO:
                return new QueueContainer();
            case Strategy.LIFO:
                return new StackContainer();
            default:
                return null;
        }
    }
}
