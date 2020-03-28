package sci.travel_app.WalkTheBear.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sci.travel_app.WalkTheBear.model.entities.Place;
import sci.travel_app.WalkTheBear.model.entities.Rating;
import sci.travel_app.WalkTheBear.repository.DailyScheduleRepository;
import sci.travel_app.WalkTheBear.repository.PlacesRepository;
import sci.travel_app.WalkTheBear.repository.RatingRepository;

import java.util.List;

@Service
public class RatingService {
    @Autowired
    private RatingRepository ratingRepository;
    @Autowired
    private PlacesRepository placesRepository;



    public List<Rating> getAllRatingsOfPlace(Place place){
        return ratingRepository.findByPlace(place);
    }
    public List<Rating> getAllRatingsOfPlaceById(long id){
        return ratingRepository.findByPlace(placesRepository.findById(id));
    }
}
