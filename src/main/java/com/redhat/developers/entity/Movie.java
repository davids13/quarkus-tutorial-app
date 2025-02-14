package com.redhat.developers.entity;

import io.quarkus.mongodb.panache.common.MongoEntity;

import java.util.List;

@MongoEntity(collection = "movies")
public class Movie {

    public String title;
    public List<String> genre;
    public Integer duration;
    public Boolean released;
    public Integer year;
}
