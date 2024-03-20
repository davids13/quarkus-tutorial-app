package com.redhat.developers.boundary;

import com.redhat.developers.dao.FruitRepository;
import com.redhat.developers.dto.FruitDTO;
import com.redhat.developers.entity.Fruit;
import com.redhat.developers.restclient.FruityViceService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;
import java.util.stream.Collectors;

@Path("/fruit")
public class FruitResource {

    @RestClient
    @Inject
    FruityViceService fruityViceService;


    // Using Repository instead of ActiveRecord pattern (PanacheEntity)
    FruitRepository fruitRepository;

    public FruitResource(FruitRepository fruitRepository) {
        this.fruitRepository = fruitRepository;
    }

    // ActiveRecord pattern
    /*@GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Fruit> fruits(@QueryParam("season") String season) {
        if (season != null) {
            return Fruit.findBySeason(season);
        }
        return Fruit.listAll();
    }*/

    // Repository pattern
    /*@GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Fruit> fruits(@QueryParam("season") String season) {
        if (season != null) {
            Log.infof("Searching for %s fruits", season);
            return fruitRepository.findBySeason(season);
        }
        return Fruit.listAll();
    }*/

    // DTO | REST client
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<FruitDTO> fruits(@QueryParam("season") String season) {
        if (season != null) {
            return Fruit.findBySeason(season).stream()
                    .map(fruit -> FruitDTO.of(fruit, fruityViceService.getFruitByName(fruit.name)))
                    .collect(Collectors.toList());
        }
        return Fruit.<Fruit>listAll().stream()
                .map(fruit -> FruitDTO.of(fruit, fruityViceService.getFruitByName(fruit.name)))
                .collect(Collectors.toList());
    }

    @Transactional
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response newFruit(Fruit fruit) {
        fruit.id = null;
        fruit.persist();
        return Response.status(Response.Status.CREATED).entity(fruit).build();
    }

}
