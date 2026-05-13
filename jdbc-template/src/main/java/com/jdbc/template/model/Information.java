package com.jdbc.template.model;

public class Information {

    private int id;
    private String name;
    private String place;
    private String info;

    private int refreshedId;

    public Information(int id, String name, String place, String info) {
        this.id = id;
        this.name = name;
        this.place = place;
        this.info = info;
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

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getRefreshedId() {
        return refreshedId;
    }

    public void setRefreshedId(int refreshedId) {
        this.refreshedId = refreshedId;
    }

    @Override
    public String toString() {
        return "Information{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", place='" + place + '\'' +
                ", info=" + info +
                '}';
    }
}
