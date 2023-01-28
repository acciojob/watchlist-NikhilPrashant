package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("movies")
@RestController
public class MovieController {
    @Autowired
    MovieService movieService;

    @PostMapping("/add_movie")
    public ResponseEntity addMovie(@RequestBody Movie movie){
        String res = movieService.addMovie(movie);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @PostMapping("/add_director")
    public ResponseEntity addDirector(@RequestBody Director movie){
        String res = movieService.addDirector(movie);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @PutMapping("/add_movie_director_pair")
    public ResponseEntity addMovieDirectorPair(@RequestParam("movieName") String movieName, @RequestParam("directorName") String directorName){
        String res = movieService.addMovieDirectorPair(movieName, directorName);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @GetMapping("/get_movie_by_name/{name}")
    public ResponseEntity getMovieByName(@PathVariable("name") String name){
        Movie res = movieService.getMovieByName(name);
        return new ResponseEntity<>(res, HttpStatus.FOUND);
    }

    @GetMapping("/get_director_by_name/{name}")
    public ResponseEntity getDirectorByName(@PathVariable("name") String name){
        Director res = movieService.getDirectorByName(name);
        return new ResponseEntity<>(res, HttpStatus.FOUND);
    }

    @GetMapping("/get_movies_by_director_name/{director}")
    public ResponseEntity getMoviesByDirectorName(@PathVariable("director") String director){
        List<String> res = movieService.getMoviesByDirectorName(director);
        return new ResponseEntity<>(res, HttpStatus.FOUND);
    }

    @GetMapping("/get_all_movies")
    public ResponseEntity findAllMovies(){
        List<String> res = movieService.findAllMovies();
        return new ResponseEntity<>(res, HttpStatus.FOUND);
    }

    @DeleteMapping("/delete_director_by_name")
    public ResponseEntity deleteDirectorByName(@RequestParam("directorName") String directorName){
        String res = movieService.deleteDirectorByName(directorName);
        return new ResponseEntity<>(res, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete_all_directors")
    public ResponseEntity deleteAllDirectors(){
        String res = movieService.deleteAllDirectors();
        return new ResponseEntity<>(res, HttpStatus.ACCEPTED);
    }
}
