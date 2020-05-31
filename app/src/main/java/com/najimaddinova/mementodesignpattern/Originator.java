package com.najimaddinova.mementodesignpattern;

public class Originator {

    private String article;

    public void setArticle(String article) {
        System.out.println("From Originator: Current Version of Article\n"+article+ "\n");
        this.article = article;
    }

    public String getArticle() {
        return article;
    }

    public Memento createMemento() {
        System.out.println("From Originator: Saving to Memento");
        return new Memento(article);
    }
    public void restore(Memento memento) {
        article = memento.getArticle();
        System.out.println("From Originator: Previous Article Saved in Memento\n"+article + "\n");
    }


}
