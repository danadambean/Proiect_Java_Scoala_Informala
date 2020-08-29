package sci.travel_app.walkthebear.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sci.travel_app.walkthebear.model.entities.AppUser;
import sci.travel_app.walkthebear.model.entities.Place;
import sci.travel_app.walkthebear.model.misc.Category;

import java.util.List;

public interface PlacesService {
    Place getPlaceById(long placeId);
    Place getUserPlaceById(long placeId, AppUser user);
    List<Place>  getPlaceByName(String name);
    List<Place> getAllPlaces();
    List<Place> getAllUserPlaces(AppUser user);
    public void addUserPlace(Place place, AppUser user);
    List<Place> getPlaceByCategory(Category category);
    void addPlace(Place place);
    void updatePlace(Place place);
    void updateUserPlace(Place place, AppUser user);
    void deletePlace(long placeId);
    public List<Place> findPlaceByUser(AppUser user);
    Page<Place> getPaginatedPlaceList(Pageable pageable, Category category);
}
