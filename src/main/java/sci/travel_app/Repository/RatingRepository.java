package sci.travel_app.Repository;
import org.springframework.data.repository.CrudRepository;
import sci.travel_app.Model.Places;
import sci.travel_app.Model.Rating;

import java.util.List;


public interface RatingRepository extends CrudRepository<Rating, Long> {
    List<Rating> findByPlace(Places ratedPlace);

    Rating findById(long id);

}