package com.neu.pojo;

import java.util.HashSet;
import java.util.Set;

public class Category {

    private long id;
    private String title;
    private Set adverts = new HashSet();

    public Category(String title, Set adv) {
        this.title = title;
        this.adverts = adv;
    }

    Category() {
    }

    public Set getAdverts() {
        return adverts;
    }

    public void setAdverts(Set adverts) {
        this.adverts = adverts;
    }

    public void addAdvert(Advert advert) {
        getAdverts().add(advert);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    protected long getId() {
        return id;
    }

    protected void setId(long id) {
        this.id = id;
    }
}