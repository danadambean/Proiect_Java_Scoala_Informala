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
     * finds all the rating objects
     * @return list of rating objects
     */
    @Override
    public List<Rating> findAll() {

        return ratingRepository.findAll();
    }

    /**
     * Finds a rating by ID
     * finds a rating object by id
     * @param id of a rating object
     * @return a rating object
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
     * deletes one rating object
     * @param id id of the rating object to be deleted
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
     * updates an existing rating object
     * @param id id of rating object to be updated
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
     * finds all the rating objects from a user
     * @param id id of the logged-in user
     * @return list of rating objects
     */
    public List<Rating> findByUser(long id) {

        return ratingRepository.findByUser(userRepository.findById(id));
    }

    /**
     * checks if current user already rated a place
     * @param place place to check if added
     * @param user current user
     * @return true if logged-in user has rated the place, false if place is not rated or user is null
     */

    @Override
    public boolean isAdded(Place place, AppUser user) {
        if (user == null) return false;
        else
            return ratingRepository.findByPlaceAndUser(place, user) != null;
    }

}
