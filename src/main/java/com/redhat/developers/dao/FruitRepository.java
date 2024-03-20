package com.redhat.developers.dao;

import com.redhat.developers.entity.Fruit;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class FruitRepository implements PanacheRepository<Fruit> {

    /*
        Using Repository instead of ActiveRecord pattern (PanacheEntity)

        Is PanacheEntity too opinionated for you?
        Maybe you prefer the traditional Repository pattern?
        Don’t worry: we’ve got you covered.
    */

    public List<Fruit> findBySeason(String season) {
        return find("upper(season)", season.toUpperCase()).list();
    }
}
