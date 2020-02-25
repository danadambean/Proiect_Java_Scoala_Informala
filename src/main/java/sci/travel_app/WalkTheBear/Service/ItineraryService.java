package sci.travel_app.WalkTheBear.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sci.travel_app.WalkTheBear.model.entities.DailySchedule;
import sci.travel_app.WalkTheBear.model.entities.Place;
import sci.travel_app.WalkTheBear.repository.ItineraryRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItineraryService {
    @Autowired
    private ItineraryRepository tripRepository;


    public List<DailySchedule> Schedule = new ArrayList<>();
    public  List<Place> unplannedPlaces = new ArrayList<>();


    public void createItinerary(){

    }

    public void deleteItinerary(){

    }
}
