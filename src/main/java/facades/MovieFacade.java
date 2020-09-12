package facades;

import entities.Movie;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class MovieFacade {

    private static MovieFacade instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private MovieFacade() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static MovieFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new MovieFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
      public Movie getMovieById(long id){
        try{
            Movie movie = getEntityManager().find(Movie.class,id);
            return movie;
        }finally {
            getEntityManager().close();
        }
    }
    
       public Movie getMovieByName(String name){
        try{
         TypedQuery<Movie> query = getEntityManager().createQuery("SELECT c FROM Movie c where c.title = ?1", Movie.class);
         query.setParameter(1, name);
         Movie movie = query.getSingleResult();
         return movie;
        }finally {
            getEntityManager().close();
        }
    }
    public List<Movie> getAllMovies(){
        try{

         TypedQuery<Movie> query = getEntityManager().createQuery("SELECT c FROM Movie c", Movie.class);
         List<Movie> movies = query.getResultList();
            return movies;
        }finally {
            getEntityManager().close();
        }
    }
       
    public void createMovie(Movie movie){
        try{
            this.getEntityManager().persist(movie);
        }finally {
            getEntityManager().close();
        }
    }}