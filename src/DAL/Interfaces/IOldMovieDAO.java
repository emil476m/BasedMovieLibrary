package DAL.Interfaces;

import BE.Movie;

import java.util.List;

public interface IOldMovieDAO {
        /**
         * Return a list of Movie objects from the database.
         * @return A list of all Movies.
         * @throws Exception throws exception if it fails to return a list of Movie objects.
         */
        List<Movie> getAllMovies() throws Exception;

        void deleteMovie(Movie movie) throws Exception;

        void deleteAllMovies() throws Exception;
    }