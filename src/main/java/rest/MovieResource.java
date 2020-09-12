package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.Movie;
import utils.EMF_Creator;
import facades.MovieFacade;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//Todo Remove or change relevant parts before ACTUAL use
@Path("movie")
public class MovieResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    
    //An alternative way to get the EntityManagerFactory, whithout having to type the details all over the code
    //EMF = EMF_Creator.createEntityManagerFactory(DbSelector.DEV, Strategy.CREATE);
    
    private static final MovieFacade FACADE =  MovieFacade.getFacadeExample(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
     @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }
            
  @GET
    @Path("id/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getById(@PathParam("id") long id) {
        Movie mid = FACADE.getMovieById(id);
       return GSON.toJson(mid);
    }
    @GET
    @Path("all")
    @Produces({MediaType.APPLICATION_JSON})
    public String getAll() {
        List<Movie> am = FACADE.getAllMovies();
       return GSON.toJson(am);
    }
            
    @GET
    @Path("name/{name}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getByName(@PathParam("name") String name) {
       Movie bc = FACADE.getMovieByName(name);
       return GSON.toJson(bc);
    }
    
    
}
