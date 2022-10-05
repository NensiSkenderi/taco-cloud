package com.taco.cloud.model;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Taco {

    private Long id;

    private Date createdAt;

    @NotBlank(message = "City is required")
    private String name;

    private List<TacoIngredient> ingredientsMatchingWithHtmlPage = new ArrayList<>();

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

    public List<TacoIngredient> getIngredientsMatchingWithHtmlPage1() {
        return ingredientsMatchingWithHtmlPage;
    }

    public void setIngredientsMatchingWithHtmlPage(List<TacoIngredient> ingredientsMatchingWithHtmlPage) {
        this.ingredientsMatchingWithHtmlPage = ingredientsMatchingWithHtmlPage;
    }

}
