package sci.travel_app.walkthebear.controller;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sci.travel_app.walkthebear.model.entities.AppUser;
import sci.travel_app.walkthebear.model.entities.Place;
import sci.travel_app.walkthebear.model.misc.Category;
import sci.travel_app.walkthebear.repository.AppUserRepository;
import sci.travel_app.walkthebear.service.PlacesServiceImp;


import javax.validation.Valid;

;import java.security.Principal;

@Controller
public class PlaceController {

    @Autowired
    private PlacesServiceImp placesService;
    @Autowired
    private AppUserRepository appUserRepository;

    private static org.apache.logging.log4j.Logger logger = LogManager.getLogger(PlaceController.class);
//    @Autowired
//    private RatingServiceImpl ratingService;


//    //path example: http://localhost:8080/places/1
//    @GetMapping(value="/places/{id}")
//    public String getPlace(@PathVariable("id") long id, Model model) {
//        Place place = placesService.getPlaceById(id);
//        List<Rating> ratingList = ratingService.getAllRatingsOfPlaceById(id);
//        model.addAttribute("place", place);
//        model.addAttribute("ratingList", ratingList);
//        return "placedetail";
//    }

    /* @PostMapping(value = "/addplace")
      public String addNewPlace( @ModelAttribute Place place )
      {
      placesService.addPlace(place);
          return "redirect:placemanager";
      } */

//    @GetMapping("/placemanager")
//    public String showPlaceManager(Model model, Principal principal) {
//        model.addAttribute("placeSearch", placesService.getAllPlaces());
//        return "placemanager";
//    }

    @GetMapping("/addplace")
    public String showNewPlaceForm(Model model) {
        model.addAttribute("place", new Place());
        return "addplace";
    }

    @PostMapping("/addplace")
    public String addNewPlace(@Valid Place place, BindingResult result, Model model, RedirectAttributes redirectAttributes, Principal principal) {
        AppUser currentUser = appUserRepository.findByUserName(principal.getName());
        if (result.hasErrors()) {
            return "addplace";
        }

        placesService.addUserPlace(place, currentUser);
        model.addAttribute("place", placesService.getAllUserPlaces(currentUser));
        redirectAttributes.addFlashAttribute("place", place);
        redirectAttributes.addFlashAttribute("message", "Place saved!");
        return "redirect:placemanager";
    }

    @GetMapping("/category")
    public String showPlacesByCategory(Model model, Category category) {
        model.addAttribute("category", placesService.getPlaceByCategory(category));
        return "categoryresults";
    }
}