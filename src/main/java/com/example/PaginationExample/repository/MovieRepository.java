package com.example.PaginationExample.repository;

import com.example.PaginationExample.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie,Long> {
}
