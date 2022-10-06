package com.taco.cloud.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
public class Taco {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private Date createdAt;

    @NotBlank(message = "City is required")
    private String name;

    /*
    A Taco can have many Ingredient objects,
    and an Ingredient can be a part of many Taco
     */
    @ManyToMany(targetEntity=TacoIngredient.class)
    @Size(min=1, message="You must choose at least 1 ingredient")
    private List<TacoIngredient> ingredientsMatchingWithHtmlPage;

    public Taco() {
    }

    public Taco(Long id, Date createdAt, String name, List<TacoIngredient> ingredientsMatchingWithHtmlPage) {
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

    public List<TacoIngredient> getIngredientsMatchingWithHtmlPage() {
        return ingredientsMatchingWithHtmlPage;
    }

    public void setIngredientsMatchingWithHtmlPage(List<TacoIngredient> ingredientsMatchingWithHtmlPage) {
        this.ingredientsMatchingWithHtmlPage = ingredientsMatchingWithHtmlPage;
    }

    @PrePersist // before Taco is persisted
    void createdAt() {
        this.createdAt = new Date();
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
