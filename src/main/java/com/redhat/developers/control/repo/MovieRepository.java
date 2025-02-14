package com.redhat.developers.control.repo;

import com.redhat.developers.entity.Movie;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.bson.Document;

import java.util.List;
import java.util.stream.StreamSupport;

@ApplicationScoped
public class MovieRepository implements PanacheMongoRepository<Movie> {

    public List<Movie> getRandomMovies(final Integer count) {
        var aggIter = mongoCollection().aggregate(List.of(new Document("$sample",
                        new Document("size", count))),
                Movie.class);
        return StreamSupport.stream(aggIter.spliterator(), false).toList();
    }
}
