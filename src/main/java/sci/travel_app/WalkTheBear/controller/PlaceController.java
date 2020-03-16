package sci.travel_app.WalkTheBear.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sci.travel_app.WalkTheBear.model.entities.Place;
import sci.travel_app.WalkTheBear.service.PlaceService;

@Controller
public class PlaceController {

    @Autowired
    private PlaceService placeService;

    //working variant; path: http://localhost:8080/places/1

    @GetMapping(value="/places/{id}")
    public String getPlace(@PathVariable("id") long id, Model model) {
        Place place = placeService.getPlaceById(id);
        model.addAttribute("place", place);
        return "placedetail";
    }

//    variant with path; url would be: http://localhost:8080/placedetail/1

//    @RequestMapping("/placedetail/{id}")
//    @ResponseBody
//    public Place getPlace(@PathVariable long id) {
//        return placeService.getPlaceById(id);
//    }

//    variant with param instead of path; url would be: http://localhost:8080/placedetail?id=1
//
//    @RequestMapping("/placedetail")
//    public Place getPlace(@RequestParam long id) {
//        return placeService.getPlaceById(id);
//    }


//    @Controller     variant

//    @RequestMapping(value = "/placedetail", method = RequestMethod.GET)
//    @ResponseBody
//    public Place getPlace(@PathVariable long id) {
//        return placeService.getPlaceById(id);
//    }


}