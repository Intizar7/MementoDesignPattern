package com.najimaddinova.mementodesignpattern;

import java.util.ArrayList;
import java.util.List;

public class Caretaker {

    // Tüm Mementoları kaydedildiği yer
    private List mementoList = new ArrayList<Memento>();

    protected int cursor = -1;

    // ArrayList'e Memento ekler
    public void addMemento(Memento m) {
        cursor++;
        mementoList.add(m);
    }

    // ArrayList elemanlarını cursor'den itibaren siler ve yeni Memento ekler
    public void addMemento(Memento m, boolean newMemento) {

        cursor++;
        if (newMemento && cursor > 0){
            mementoList = mementoList.subList(0, cursor);
        }

        mementoList.add(m);
    }

    // ArrayList'ten istenen hatayı alır
    public Memento getMemento(int index)
    {
        if((index + 1) > mementoList.size() ){
            return null;
        }

        cursor = index;

        return (Memento) mementoList.get(index);
    }

}
