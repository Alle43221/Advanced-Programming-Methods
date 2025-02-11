package ubb.scs.map.template.utils.paging;

public class Page<E> {
    private final Iterable<E> elements;
    private final int totalElements;

    public Page(Iterable<E> elements, int totalElements) {
        this.elements = elements;
        this.totalElements = totalElements;
    }

    public Iterable<E> elementsOnPage() {
        return elements;
    }

    public int getTotalNumberOfElements() {
        return totalElements;
    }

}
