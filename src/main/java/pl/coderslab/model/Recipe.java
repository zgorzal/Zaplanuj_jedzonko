package pl.coderslab.model;

import java.util.Date;

public class Recipe {
    private int id;
    private String name;
    private String ingredients;
    private String description;
    private Date created;
    private Date updated;
    private int preparationTime;
    private String preparation;
    private int adminId;

    public Recipe(){}

    public Recipe(int id, String name,
                  String ingredients, String description,
                  Date created, Date updated,
                  int preparationTime, String preparation,
                  int adminId){
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
        this.description = description;
        this.created = created;
        this.updated = updated;
        this.preparationTime = preparationTime;
        this.preparation = preparation;
        this.adminId = adminId;
    }
    public Recipe(String name,
                  String ingredients, String description,
                  int preparation_time, String preparation){

        this.name = name;
        this.ingredients = ingredients;
        this.description = description;
        this.preparationTime = preparation_time;
        this.preparation = preparation;

    }
    public Recipe(String name,
                  String ingredients, String description,
                  int preparation_time, String preparation,
                  int adminId){

        this.name = name;
        this.ingredients = ingredients;
        this.description = description;
        this.preparationTime = preparation_time;
        this.preparation = preparation;
        this.adminId = adminId;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public int getPreparationTime() {
        return preparationTime;
    }

    public void setPreparationTime(int preparationTime) {
        this.preparationTime = preparationTime;
    }

    public String getPreparation() {
        return preparation;
    }

    public void setPreparation(String preparation) {
        this.preparation = preparation;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }
}


