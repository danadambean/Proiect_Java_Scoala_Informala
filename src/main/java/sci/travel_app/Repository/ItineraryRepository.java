package sci.travel_app.Repository;

        import sci.travel_app.Model.AppUser;
        import org.springframework.data.repository.CrudRepository;
        import sci.travel_app.Model.Itinerary;

        import java.util.List;


public interface ItineraryRepository extends CrudRepository<Itinerary, Long> {
    List<Itinerary> findByUser(AppUser createdBy);

    Itinerary findById(long id);

}