package sci.travel_app.Repository;

import org.springframework.data.repository.CrudRepository;
import sci.travel_app.Model.Places;

import java.util.List;

public interface PlacesRepository extends CrudRepository<Places, Long> {
    List<Places> findByName(String name);

    Places findById(long id);

}