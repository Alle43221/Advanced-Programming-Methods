public class SorterFactory {

    private SorterFactory() {}

    private static SorterFactory instance;

    public static SorterFactory getInstance() {
        if(instance == null) {
            instance = new SorterFactory();
        }
        return instance;
    }

    public <E extends Comparable<E>> AbstractSorter<E> getSorter(SortingStrategy strategy) {
        if(strategy.equals(SortingStrategy.MERGE)){
            return new MergeSorter<>();
        }
        else {
            return new BubbleSorter<>();
        }
    }
}
