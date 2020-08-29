package sci.travel_app.walkthebear.controller;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sci.travel_app.walkthebear.model.entities.Place;
import sci.travel_app.walkthebear.service.PlacesServiceImp;

import javax.validation.Valid;
@Controller
public class MyProfileController {

    @Autowired
    private PlacesServiceImp placesService;
    private static org.apache.logging.log4j.Logger logger = LogManager.getLogger(MyProfileController.class);


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

    /**
     *  Method is used to return all the places searched by certain name
     * @param placeName
     * @param model
     * @return "adminplace"
     */
    @GetMapping("/adminplace")
    public String showAdminPlace(@RequestParam(value = "placeSearch", required = false) String placeName, Model model) {
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

    /**
     * Method used to delete a place from the database
     * @param id - place ID
     * @param model
     * @return
     * @throws IllegalArgumentException
     */
    @GetMapping("/deleteplaceadmin/{id}")
    public String erasePlace(@PathVariable("id") long id, Model model) throws IllegalArgumentException {
        Place place = placesService.getPlaceById(id);
        //   .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        placesService.deletePlace(id);
        model.addAttribute("place", placesService.getAllPlaces());
        return "adminplace";
    }
}
