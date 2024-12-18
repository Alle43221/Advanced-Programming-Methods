namespace Lab9_MAP;

public interface IFactory {
    IContainer CreateContainer(Strategy strategy);
}