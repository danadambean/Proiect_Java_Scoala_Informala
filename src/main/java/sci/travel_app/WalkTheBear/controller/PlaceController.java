package sci.travel_app.WalkTheBear.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import sci.travel_app.WalkTheBear.model.entities.Place;
import sci.travel_app.WalkTheBear.model.misc.Category;
import sci.travel_app.WalkTheBear.service.PlacesService;
import sci.travel_app.WalkTheBear.service.PlacesServiceImp;

import javax.validation.Valid;;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.List;

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
    @GetMapping("/addplaceadmin")
    public String showNewPlaceFormAdmin(Model model) {
        model.addAttribute("place", new Place());
        return "addplaceadmin";
    }

    @PostMapping("/addplaceadmin")
    public String addNewPlaceAdmin(@Valid Place place, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "addplaceadmin";
        }

        placesService.addPlace(place);
        model.addAttribute("place", placesService.getAllPlaces());
        return "redirect:adminplace";
    }
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
    @GetMapping("/view")
    public String showPlace(Model model, String placeName) {
        model.addAttribute("place", placesService.getPlaceByName(placeName));
        return "searchresults";
    }
   /*@GetMapping("/adminplace")
    public String showAdminPlace(Model model, String placeName) {
        model.addAttribute("placeSearch", placesService.getPlaceByName(placeName));
        return "adminplace";
    } */
    @GetMapping("/adminplace")
   public String showAdminPlace(@RequestParam (value = "placeSearch", required = false) String placeName, Model model) {
       model.addAttribute("placeSearch", placesService.getPlaceByName(placeName));
       return "adminplace";
   }
  /* @RequestMapping("/adminplace")
   public ModelAndView search(@RequestParam String keyword) {
       List<Place> result = placesService.search(keyword);
       ModelAndView mav = new ModelAndView("search");
       mav.addObject("result", result);

       return mav;
   } */
    @GetMapping("/category")
    public String showPlacesByCategory(Model model, Category category) {
        model.addAttribute("category", placesService.getPlaceByCategory(category));
        return "categoryresults";
    }
    @GetMapping("/editplaceadmin/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Place place = placesService.getPlaceById(id);
               // .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

        model.addAttribute("place", place);
        return "editplaceadmin";
    }
    @PostMapping("/editplaceadmin/{id}")
    public String changePlace(@PathVariable("id") long id, @Valid Place place,
                              BindingResult result, Model model) {
        if (result.hasErrors()) {
            place.setId(id);
            return "editplaceadmin";
        }

        placesService.updatePlace(place);
        model.addAttribute("place", placesService.getAllPlaces());
        return "adminplace";
    }

    @GetMapping("/deleteplaceadmin/{id}")
    public String erasePlace(@PathVariable("id") long id, Model model) throws IllegalArgumentException {
        Place place = placesService.getPlaceById(id);
        //   .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        placesService.deletePlace(id);
        model.addAttribute("place", placesService.getAllPlaces());
        return "adminplace";
    }

}
