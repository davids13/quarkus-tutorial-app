package com.redhat.developers.control;

import com.redhat.developers.control.mapper.MovieMapper;
import com.redhat.developers.control.repo.MovieRepository;
import com.redhat.developers.dto.MovieDTO;
import jakarta.enterprise.context.ApplicationScoped;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class MovieService {
   /*  MovieMapper movieMapper;

     MovieRepository movieRepository;

    public MovieService(MovieMapper movieMapper, MovieRepository movieRepository) {
        this.movieMapper = movieMapper;
        this.movieRepository = movieRepository;
    }

    public List<MovieDTO> getAllMovies() {
        return movieRepository.listAll().stream()
                .map(movieMapper::toDTO)
                .collect(Collectors.toList());
    }

    public MovieDTO getMovieById(String id) {
        return movieMapper.toDTO(movieRepository.findById(new ObjectId(id)));
    }

    public MovieDTO addMovie(MovieDTO dto) {
        var movie = movieMapper.toEntity(dto);
        //movie.id = null;
        movieRepository.persist(movie);
        return movieMapper.toDTO(movie);
    }

    public List<MovieDTO> getRandomMovies(Integer count) {
        return movieRepository.getRandomMovies(count).stream()
                .map(movieMapper::toDTO).toList();
    }
*/
}
