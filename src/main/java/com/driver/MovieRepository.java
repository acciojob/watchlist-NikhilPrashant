package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Repository
public class MovieRepository {
    HashMap<String, Movie> movieMap = new HashMap<>();
    HashMap<String, Director> directorMap = new HashMap<>();
    HashMap<String, List<String>> movieDirectorPairMap = new HashMap<>();

    public String addMovie(Movie movie) {
        String movieName = movie.getName();
        movieMap.put(movieName, movie);
        return "Movie Added";
    }

    public String addDirector(Director director) {
        String directorName = director.getName();
        directorMap.put(directorName, director);
        return "Director Added";
    }

    public  String addMovieDirectorPair(String movieName, String directorName) {
        if (!movieMap.containsKey(movieName)) return "Movie Not Found";
        if (!directorMap.containsKey(directorName)) return "Director Not Found";
        if (movieDirectorPairMap.containsKey(directorName)) movieDirectorPairMap.get(directorName).add(movieName);
        else {
            movieDirectorPairMap.put(directorName, new ArrayList<>(Arrays.asList(movieName)));
        }
        return "Pair Added";
    }

    public Movie getMovieByName(String movieName) {
        if (movieMap.containsKey(movieName)) return movieMap.get(movieName);
        return null;
    }

    public Director getDirectorByName(String directorName) {
        if (directorMap.containsKey(directorName)) return directorMap.get(directorName);
        return null;
    }

    public List<String> getMoviesByDirectorName(String directorName) {
        return movieDirectorPairMap.get(directorName);
    }

    public List<String> findAllMovies() {
        List<String> movieList = new ArrayList<>();
        for (String movie: movieMap.keySet()) movieList.add(movie);
        return movieList;
    }

    public String deleteDirectorByName(String directorName) {
        List<String> movies = movieDirectorPairMap.get(directorName);
        for (String movie: movies) movieMap.remove(movie);
        movieDirectorPairMap.remove(directorName);
        directorMap.remove(directorName);
        return "Deleted Successfully";
    }

    public String deleteAllDirectors() {
        for (String directorName: directorMap.keySet()) {
            List<String> movies = movieDirectorPairMap.get(directorName);
            for (String movie: movies) movieMap.remove(movie);
            movieDirectorPairMap.remove(directorName);
            directorMap.remove(directorName);
        }
        return "Deleted Successfully";
    }
}
