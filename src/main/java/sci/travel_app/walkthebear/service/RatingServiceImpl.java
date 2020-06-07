package sci.travel_app.walkthebear.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sci.travel_app.walkthebear.model.entities.Place;
import sci.travel_app.walkthebear.model.entities.Rating;
import sci.travel_app.walkthebear.repository.PlacesRepository;
import sci.travel_app.walkthebear.repository.RatingRepository;

import java.util.Date;
import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRepository ratingRepository;
    @Autowired
    private PlacesRepository placesRepository;

    @Override
    public List<Rating> findAll() {

        return ratingRepository.findAll();
    }

    @Override
    public Rating findById(long id) {

        return ratingRepository.findById(id);
    }

    @Override
    public Rating create(Rating rating, Place place) {

        rating.setCreated(new Date());
        rating.setPlace(place);

        return ratingRepository.save(rating);
    }

    @Override
    public void deleteRating(long id) {

        ratingRepository.delete(findById(id));
    }

    public void updateRating(long id) {
        ratingRepository.save(findById(id));
    }


    public List<Rating> getAllRatingsOfPlace(Place place) {

        return ratingRepository.findByPlace(place);
    }


    public List<Rating> getAllRatingsOfPlaceById(long id) {
        return ratingRepository.findByPlace(placesRepository.findById(id));
    }

}
