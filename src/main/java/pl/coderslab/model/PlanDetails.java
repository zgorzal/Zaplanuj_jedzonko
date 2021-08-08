package pl.coderslab.model;

import java.util.ArrayList;
import java.util.List;

public class PlanDetails {
    private String name;
    private List<String> monday = new ArrayList<>();
    private List<String> tuesday = new ArrayList<>();
    private List<String> wednesday = new ArrayList<>();
    private List<String> thursday = new ArrayList<>();
    private List<String> friday = new ArrayList<>();
    private List<String> saturday = new ArrayList<>();
    private List<String> sunday = new ArrayList<>();


    public PlanDetails(){}

    public PlanDetails(String name, List<String> monday, List<String> tuesday, List<String> wednesday, List<String> thursday, List<String> friday, List<String> saturday, List<String> sunday) {
        this.name = name;
        this.monday = monday;
        this.tuesday = tuesday;
        this.wednesday = wednesday;
        this.thursday = thursday;
        this.friday = friday;
        this.saturday = saturday;
        this.sunday = sunday;
    }

    public String getName() {
        return name;
    }

    public List<String> getMonday() {
        return monday;
    }

    public List<String> getTuesday() {
        return tuesday;
    }

    public List<String> getWednesday() {
        return wednesday;
    }

    public List<String> getThursday() {
        return thursday;
    }

    public List<String> getFriday() {
        return friday;
    }

    public List<String> getSaturday() {
        return saturday;
    }

    public List<String> getSunday() {
        return sunday;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "PlanDetails{" +
                "name='" + name + '\'' +
                ", monday=" + monday +
                ", tuesday=" + tuesday +
                ", wednesday=" + wednesday +
                ", thursday=" + thursday +
                ", friday=" + friday +
                ", saturday=" + saturday +
                ", sunday=" + sunday +
                '}';
    }
}
