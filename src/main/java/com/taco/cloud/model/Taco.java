package com.taco.cloud.model;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

public class Taco {

    private Long id;

    private Date createdAt;

    @NotBlank(message = "City is required")
    private String name;

    private List<TacoIngredient> ingredientsMatchingWithHtmlPage;

    public Taco() {
    }

    public Taco(String name, List<TacoIngredient> ingredientsMatchingWithHtmlPage) {
        this.name = name;
        this.ingredientsMatchingWithHtmlPage = ingredientsMatchingWithHtmlPage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIngredientsMatchingWithHtmlPage(List<TacoIngredient> ingredientsMatchingWithHtmlPage) {
        this.ingredientsMatchingWithHtmlPage = ingredientsMatchingWithHtmlPage;
    }

    public List<TacoIngredient> getIngredientsMatchingWithHtmlPage() {
        return ingredientsMatchingWithHtmlPage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Taco{" +
                "name='" + name + '\'' +
                ", ingredientsMatchingWithHtmlPage=" + ingredientsMatchingWithHtmlPage +
                '}';
    }
}
