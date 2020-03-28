package sci.travel_app.WalkTheBear.service;

import sci.travel_app.WalkTheBear.model.entities.AppUser;
import sci.travel_app.WalkTheBear.model.entities.Rating;

public interface RatingService {

    public Rating findById(long id);

    public Rating create(Rating rating);



}
