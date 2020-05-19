package sci.travel_app.walkthebear.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sci.travel_app.walkthebear.model.entities.Place;
import sci.travel_app.walkthebear.model.entities.Rating;
import sci.travel_app.walkthebear.service.PlacesServiceImp;
import sci.travel_app.walkthebear.service.RatingServiceImpl;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;


@Controller
public class RatingController {

    @Autowired
    private RatingServiceImpl ratingService;
    @Autowired
    private PlacesServiceImp placeService;

    private static final Logger log = Logger.getLogger(String.valueOf(RatingController.class));

    @GetMapping(value="/placedetail/{pid}")
    public String newRating(@PathVariable("pid") long id, Model model) {
        Place place = placeService.getPlaceById(id);
        model.addAttribute("place", place);
        Rating rating = new Rating();
        rating.setPlace(place);
        model.addAttribute("rating", rating);
        List<Rating> ratingList = ratingService.getAllRatingsOfPlaceById(id);
        model.addAttribute("ratingList", ratingList);
        return "placedetail";
    }


    @PostMapping(value="/placedetail/{pid}/sendReview")
    public String sendRating(@PathVariable("pid") long id, @ModelAttribute("rating") Rating rating, BindingResult result,
                             RedirectAttributes redirectAttributes)   {

        rating.setCreated(new Date());
        rating.setPlace(placeService.getPlaceById(id));
        ratingService.create(rating);
        redirectAttributes.addFlashAttribute("message", "Success");
        return "redirect:/placedetail/" + id;
    }

}