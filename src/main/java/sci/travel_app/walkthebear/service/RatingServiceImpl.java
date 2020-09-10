package sci.travel_app.walkthebear.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sci.travel_app.walkthebear.model.entities.AppUser;
import sci.travel_app.walkthebear.model.entities.Place;
import sci.travel_app.walkthebear.model.entities.Rating;
import sci.travel_app.walkthebear.repository.AppUserRepository;
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
    @Autowired
    private AppUserRepository userRepository;

    /**
     * Finds all the ratings
     *
     * @return all the ratings
     */
    @Override
    public List<Rating> findAll() {

        return ratingRepository.findAll();
    }

    /**
     * Finds a rating by ID
     *
     * @param id - unique ID for a specific rating
     * @return - rating specific for an ID
     */
    @Override
    public Rating findById(long id) {

        return ratingRepository.findById(id);
    }

    /**
     * Creates a new rating for a place
     *
     * @param rating - user rating for a place
     * @param place  - the place the rating is for
     * @param user   - the user that provides the rating
     * @return - saves the rating for the place
     */
    @Override
    public Rating create(Rating rating, Place place, AppUser user) {
        rating.setUser(user);
        rating.setCreated(new Date());
        rating.setPlace(place);

        return ratingRepository.save(rating);
    }

    /**
     * Deletes a rating
     *
     * @param id - rating ID
     */
    @Override
    public void deleteRating(long id) {
        ratingRepository.delete(findById(id));
    }

    /**
     * Delete rating by admin
     * Method is not used anymore
     *
     * @param rating - place rating
     */
    @Override
    public void deleteRatingAdmin(Rating rating) {
        ratingRepository.delete(rating);
    }


    /**
     * Updates the place rating
     *
     * @param id -rating ID
     */
    @Override
    public void updateRating(long id) {
        ratingRepository.save(findById(id));
    }

    /**
     * Find all ratings for a specific place
     *
     * @param place - rated place
     * @return all ratings for a place
     */
    @Override
    public List<Rating> getAllRatingsOfPlace(Place place) {
        return ratingRepository.findByPlace(place);
    }

    /**
     * Finds all ratings for a place using the ID
     *
     * @param id - place ID
     * @return all the ratings for a specific place
     */
    @Override
    public List<Rating> getAllRatingsOfPlaceById(long id) {
        return ratingRepository.findByPlace(placesRepository.findById(id));
    }

    /**
     * Finds all ratings saved by a user
     *
     * @param id - user ID
     * @return all the ratings provided by an user
     */
    public List<Rating> findByUser(long id) {

        return ratingRepository.findByUser(userRepository.findById(id));
    }

    /**
     * Checks if a rating is already added by a user for a place
     *
     * @param place - place the rating for provided to
     * @param user  - user that provide the rating
     * @return - returns false if the user didn't rate a place.
     */
    @Override
    public boolean isAdded(Place place, AppUser user) {
        if (user == null) return false;
        else
            return ratingRepository.findByPlaceAndUser(place, user) != null;
    }

}
