package pl.coderslab.model;

public class DayName {

    private int id;
    private String name;
    private int displayOrder;

    public DayName(int id) {
        this.id = id;
    }

    public DayName(String name) {
        this.name = name;
    }

    public DayName(int id, String name, int displayOrder) {
        this.id = id;
        this.name = name;
        this.displayOrder = displayOrder;
    }

    public DayName() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getDisplayOrder() {
        return displayOrder;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDisplayOrder(int displayOrder) {
        this.displayOrder = displayOrder;
    }

    public void DayNam(int displayOrder) {
        this.displayOrder = displayOrder;
    }

}