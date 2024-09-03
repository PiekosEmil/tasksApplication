package com.emilpiekos.tasksapplication;

import java.util.List;

public enum Category {
    HOME_DUTIES("Obowiązki domowe"), WORK("Praca"), TRAINING("Szkolenie");

    private String name;

    Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Category> listOfCategories() {
        return List.of(Category.values());
    }
}
