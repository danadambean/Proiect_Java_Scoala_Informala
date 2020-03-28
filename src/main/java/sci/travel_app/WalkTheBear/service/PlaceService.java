package sci.travel_app.WalkTheBear.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sci.travel_app.WalkTheBear.model.entities.Place;
import sci.travel_app.WalkTheBear.repository.PlacesRepository;

@Service
public class PlaceService {

    //placeholder class
    @Autowired
    private PlacesRepository placesRepository;


    public PlaceService(PlacesRepository placesRepository) {
        this.placesRepository = placesRepository;
    }

    public Place getPlaceById(long id){
        return  placesRepository.findById(id);
    }

}