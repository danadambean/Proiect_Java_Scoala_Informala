package sci.travel_app.WalkTheBear.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sci.travel_app.WalkTheBear.model.entities.Rating;
import sci.travel_app.WalkTheBear.repository.RatingRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    public List<Rating> getAllRated(){
        List<Rating> rated = new ArrayList<>();
        ratingRepository.findAll().forEach(rated::add);
        return rated;
    }

    @Override
    public Rating findById(long id) {
        return ratingRepository.findById(id);
    }

    @Override
    public Rating create(Rating rating) {
        return ratingRepository.save(rating);

    }

}
