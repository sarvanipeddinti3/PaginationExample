package com.example.PaginationExample.service;

import com.example.PaginationExample.models.Movie;

public interface MoviesService {
    Movie uploadMovie(Movie movie);

    Movie getMovieDetails(Long id);
}
