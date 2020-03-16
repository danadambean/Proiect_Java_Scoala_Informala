package sci.travel_app.WalkTheBear.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import sci.travel_app.WalkTheBear.service.PlaceService;
import sci.travel_app.WalkTheBear.service.RatingService;

@Controller
public class RatingController {
    @Autowired
    private RatingService ratingService;
    @Autowired
    private PlaceService placeService;



    //not working. need to figure out the mapping

    @GetMapping(value="/ratings/{id}")
    public String getRatingForPlace(@PathVariable("id") long id, Model model) {

//        Place place = placeService.getPlaceById(id);
        model.addAttribute("rating", ratingService.getAllRatingsOfPlace(placeService.getPlaceById(id)));
        return "placedetail";
    }
}
