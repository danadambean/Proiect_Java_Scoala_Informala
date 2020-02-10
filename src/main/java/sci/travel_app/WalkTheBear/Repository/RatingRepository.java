package sci.travel_app.WalkTheBear.Repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sci.travel_app.WalkTheBear.Model.Entities.Place;
import sci.travel_app.WalkTheBear.Model.Entities.Rating;

import java.util.List;

@Repository
public interface RatingRepository extends CrudRepository<Rating, Long> {
    List<Rating> findByPlace(Place ratedPlace);

    Rating findById(long id);

}