package dev.kodewana.movies.controller;

import dev.kodewana.movies.domain.Movie;
import dev.kodewana.movies.domain.Review;
import dev.kodewana.movies.service.MovieService;
import dev.kodewana.movies.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/movies")
public class MovieController {

    private final MovieService movieSvc;
    private final ReviewService reviewSvc;

    public MovieController(MovieService movieSvc, ReviewService reviewSvc) {
        this.movieSvc = movieSvc;
        this.reviewSvc = reviewSvc;
    }

    @GetMapping()
    public ResponseEntity<List<Movie>> getAllMovies() {
       return Optional.of(movieSvc.getAllMovies())
               .map(ResponseEntity::ok)
               .orElse(ResponseEntity.internalServerError().build());
    }

    @GetMapping("/{imdbId}")
    public ResponseEntity<Movie> getMovie(@PathVariable String imdbId) {
        return movieSvc.getMovie(imdbId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }

    @PostMapping("/add-review")
    public ResponseEntity<Review> addReview(@RequestBody Map<String, String> payload) {
        return Optional.ofNullable(reviewSvc.createReview(payload.get("reviewBody"), payload.get("imdbId")))
                .map((r) -> new ResponseEntity<>(r, HttpStatus.CREATED))
                .orElse(ResponseEntity.internalServerError().build());
    }

}
