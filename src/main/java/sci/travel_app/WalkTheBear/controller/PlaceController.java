package sci.travel_app.WalkTheBear.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import sci.travel_app.WalkTheBear.model.entities.Place;
import sci.travel_app.WalkTheBear.service.PlacesServiceImp;

import javax.validation.Valid;

@Controller
public class PlaceController {

    @Autowired
    private PlacesServiceImp placesService;

    /* @PostMapping(value = "/addplace")
     public String addNewPlace( @ModelAttribute Place place )
     {
     placesService.addPlace(place);
         return "redirect:placemanager";
     } */
    @GetMapping("/addplace")
    public String showNewPlaceForm(Model model) {
        model.addAttribute("place", new Place());
        return "addplace";
    }

    @PostMapping("/addplace")
    public String addNewPlace(@Valid Place place, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "addplace";
        }

        placesService.addPlace(place);
        model.addAttribute("place", placesService.getAllPlaces());
        return "redirect:placemanager";
    }
    /*  @GetMapping("view")
      public String showPlace(Model model) {
          model.addAttribute("place", placesService.getPlaceByName());
          return "placemanager";
      } */
    @PostMapping("/update/{id}")
    public String changePlace(@PathVariable("id") long id, @Valid Place place,
                              BindingResult result, Model model) {
        if (result.hasErrors()) {
            place.setId(id);
            return "update-place";
        }

        placesService.updatePlace(place);
        model.addAttribute("place", placesService.getAllPlaces());
        return "placemanager";
    }

    @GetMapping("/delete/{id}")
    public String erasePlace(@PathVariable("id") long id, Model model) {
        Place place = placesService.getPlaceById(id);
        //   .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        placesService.deletePlace(id);
        model.addAttribute("place", placesService.getAllPlaces());
        return "placemanager";
    }
}
