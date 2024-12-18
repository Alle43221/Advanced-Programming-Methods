namespace Lab9_MAP;

public interface ITaskRunner {
    void ExecuteOneTask();
    void ExecuteAll();
    void AddTask(Task t);
    bool HasTask();
}