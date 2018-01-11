package com.khelenyuk.model;

import java.io.Serializable;
import java.util.Objects;

public class Sex implements Serializable{
    private int id;
    private String name;

    public Sex() {
    }

    public Sex(int id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sex sex = (Sex) o;
        return id == sex.id &&
                Objects.equals(name, sex.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Sex{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
