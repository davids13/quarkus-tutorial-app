package com.redhat.developers.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

import java.util.List;

@Entity
public class Fruit extends PanacheEntity { // ActiveRecord pattern (PanacheEntity)

    public String name;
    public String season;

    public static List<Fruit> findBySeason(String season) {
        return find("season", season).list();
    }

    /*
        Notice that we’re not providing an @Id, nor we’re creating the getters and setters.
        Don’t worry. It’s a Panache feature. By extending PanacheEntity, we’re using the Active Record persistence pattern instead of a DAO.
        This means that all persistence methods are blended with our own Entity.

        What is Panache ?
            Hibernate ORM is the de facto JPA implementation and offers you the full breadth of an Object Relational Mapper. It makes complex mappings possible, but it does not make simple and common mappings trivial. Hibernate ORM with Panache focuses on making your entities trivial and fun to write in Quarkus.
            Fore more information please refer to the Panache Guide https://quarkus.io/guides/hibernate-orm-panache-guide
    */
}
