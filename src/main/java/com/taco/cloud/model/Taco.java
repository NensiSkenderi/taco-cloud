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

    private List<String> ingredientsMatchingWithHtmlPage = new ArrayList<>();

    public Taco() {
    }

    public Taco(Long id, Date createdAt, String name, List<String> ingredientsMatchingWithHtmlPage) {
        this.id = id;
        this.createdAt = createdAt;
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

    public List<String> getIngredientsMatchingWithHtmlPage() {
        return ingredientsMatchingWithHtmlPage;
    }

    public void setIngredientsMatchingWithHtmlPage(List<String> ingredientsMatchingWithHtmlPage) {
        this.ingredientsMatchingWithHtmlPage = ingredientsMatchingWithHtmlPage;
    }

    @Override
    public String toString() {
        return "Taco{" +
                "id=" + id +
                ", createdAt=" + createdAt +
                ", name='" + name + '\'' +
                ", ingredientsMatchingWithHtmlPage=" + ingredientsMatchingWithHtmlPage +
                '}';
    }
}
