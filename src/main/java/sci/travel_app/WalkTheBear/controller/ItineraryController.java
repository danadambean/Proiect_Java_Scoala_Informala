package sci.travel_app.WalkTheBear.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import sci.travel_app.WalkTheBear.model.entities.Itinerary;
import sci.travel_app.WalkTheBear.repository.ItineraryRepository;
import sci.travel_app.WalkTheBear.service.ItineraryService;

@Controller
public class ItineraryController {

    @Autowired
    private ItineraryRepository itineraryRepository;

//    @PostMapping("addItinerary")
//public String addNewItinerary (Itinerary itinerary, Model model) {
//        ItineraryService.createItinerary();
//        return "add-itinerary";
//    }
}
