package com.example.PaginationExample.service.impl;

import com.example.PaginationExample.models.Movie;
import com.example.PaginationExample.repository.MovieRepository;
import com.example.PaginationExample.service.MoviesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MoviesServiceImpl implements MoviesService {
    @Autowired
    private MovieRepository movieRepository;

    @Override
    public Movie uploadMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public Movie getMovieDetails(Long id) {
       return movieRepository.findById(id)
               .orElseThrow(() -> new RuntimeException("No movie found with id"+ id));
    }
}
