package sci.travel_app.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sci.travel_app.Model.Entities.Place;

import java.util.List;

@Repository
public interface PlacesRepository extends CrudRepository<Place, Long> {
    List<Place> findByName(String name);

    Place findById(long id);

}