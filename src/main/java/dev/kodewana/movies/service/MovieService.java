package dev.kodewana.movies.service;

import dev.kodewana.movies.domain.Movie;
import dev.kodewana.movies.repository.MovieRepository;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    private final MovieRepository movieRepo;

    public MovieService(MovieRepository movieRepo) {
        this.movieRepo = movieRepo;
    }
    public List<Movie> getAllMovies() {
        return movieRepo.findAll();
    }

    public Optional<Movie> getMovie(String imdbId) {
        return movieRepo.findByImdbId(imdbId);
    }
}
