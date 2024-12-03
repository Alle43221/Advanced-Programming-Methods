package com.example.scheletseminar9.util.paging;

import com.example.scheletseminar9.domain.Entity;

public class Page<E> {
    private Iterable<E> elements;
    private int totalElements;

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
