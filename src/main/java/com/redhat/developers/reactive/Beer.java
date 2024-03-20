package com.redhat.developers.reactive;

import jakarta.json.bind.annotation.JsonbCreator;

public class Beer {

    /*
        Mutiny
        Quarkus provides a novel reactive API called Mutiny, with the goal of easing the development of highly scalable, resilient, and asynchronous systems.
    */

    private final String name;
    private final String tagline;
    private final double abv;

    public Beer(String name, String tagline, double abv) {
        this.name = name;
        this.tagline = tagline;
        this.abv = abv;
    }

    @JsonbCreator
    public static Beer of(String name, String tagline, double abv) {
        return new Beer(name, tagline, abv);
    }

    public String getName() {
        return name;
    }

    public String getTagline() {
        return tagline;
    }

    public double getAbv() {
        return abv;
    }
}
