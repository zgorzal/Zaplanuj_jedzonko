package pl.coderslab.model;

public class Plan {
    private int id;
    private String name;
    private String description;
    private String created;
    private int adminId;
    private int recipeId;

    private int dayId;
    private String mealName;
    private String recipeDescription;

    public Plan(){}

    public Plan(String name, String description, int adminId) {
        this.name = name;
        this.description = description;
        this.adminId = adminId;
    }

    public Plan(int dayId, String mealName, String recipeDescription, int recipeId) {
        this.dayId = dayId;
        this.mealName = mealName;
        this.recipeDescription = recipeDescription;
        this.recipeId = recipeId;
    }

    public void setId(int id) { this.id = id; }

    public void setAdminId(int adminId) { this.adminId = adminId; }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public void setDayId(int dayId) {
        this.dayId = dayId;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public void setRecipeDescription(String recipeDescription) {
        this.recipeDescription = recipeDescription;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getCreated() {
        return created;
    }

    public int getAdminId() {
        return adminId;
    }

    public int getDayId() {
        return dayId;
    }

    public String getMealName() {
        return mealName;
    }

    public String getRecipeDescription() {
        return recipeDescription;
    }

    public int getRecipeId() {
        return recipeId;
    }
}
