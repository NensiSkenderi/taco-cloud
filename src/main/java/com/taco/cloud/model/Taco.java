package com.taco.cloud.model;

import java.util.List;

public class Taco {

    private String name;
    private List<String> ingredientsMatchingWithHtmlPage;

    public Taco() {
    }

    public Taco(String name, List<String> ingredientsMatchingWithHtmlPage) {
        this.name = name;
        this.ingredientsMatchingWithHtmlPage = ingredientsMatchingWithHtmlPage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIngredientsMatchingWithHtmlPage(List<String> ingredientsMatchingWithHtmlPage) {
        this.ingredientsMatchingWithHtmlPage = ingredientsMatchingWithHtmlPage;
    }

    public List<String> getIngredientsMatchingWithHtmlPage() {
        return ingredientsMatchingWithHtmlPage;
    }

    @Override
    public String toString() {
        return "Taco{" +
                "name='" + name + '\'' +
                ", ingredientsMatchingWithHtmlPage=" + ingredientsMatchingWithHtmlPage +
                '}';
    }
}
