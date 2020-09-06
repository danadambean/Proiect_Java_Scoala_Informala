package sci.travel_app.walkthebear.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import sci.travel_app.walkthebear.data_utils.AppUserDetails;
import sci.travel_app.walkthebear.model.entities.AppUser;
import sci.travel_app.walkthebear.model.entities.Favorite;
import sci.travel_app.walkthebear.model.entities.Rating;
import sci.travel_app.walkthebear.repository.AppUserRepository;
import sci.travel_app.walkthebear.service.AppUserServiceImp;
import sci.travel_app.walkthebear.service.FavoritesServiceImpl;
import sci.travel_app.walkthebear.service.PlacesServiceImp;
import sci.travel_app.walkthebear.service.RatingServiceImpl;

import java.util.List;

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
    private AppUserServiceImp appUserServiceImp;
    @Autowired
    private AppUserRepository userRepository;
    @Autowired
    private RatingServiceImpl ratingService;
    @Autowired
    private FavoritesServiceImpl favoritesService;
    @Autowired
    private PlacesServiceImp placesService;
    @Autowired
    private AppUserRepository appUserRepository;
    private static org.apache.logging.log4j.Logger logger = LogManager.getLogger(MyProfileController.class);

    @GetMapping(value = "/profileinfo")
    public String userprofile(@AuthenticationPrincipal AppUserDetails currentUser, Model model){
        AppUser appuser = userRepository.findByUserName(currentUser.getUsername());
        System.out.println(currentUser);
        AppUser user = appUserServiceImp.findById(appuser.getId());
        model.addAttribute("currentUser", user);

        //String name = principal.getName(); //get logged in username
        //model.addAttribute("username", name);

        return "profileinfo";
    }

    @GetMapping("/profileratings")
    public String getAllRated(@AuthenticationPrincipal AppUserDetails currentUser, Model model) {
        AppUser user = userRepository.findByUserName(currentUser.getUsername());
        //model.addAttribute("currentUser", user);
        List<Rating> allRated = ratingService.findByUser(user.getId());
        System.out.println(allRated);
        model.addAttribute("allRated", allRated);
        return "profileratings";
    }

    @GetMapping("/profilefav")
    public String getAllFavorites(@AuthenticationPrincipal AppUserDetails currentUser, Model model) {
        AppUser user = userRepository.findByUserName(currentUser.getUsername());
        //model.addAttribute("currentUser", user);
        List<Favorite> allFavorite = favoritesService.findByUser(user.getId());
        System.out.println(allFavorite);
        model.addAttribute("allFavorite", allFavorite);
        return "profilefav";
    }

    @GetMapping("/adminplace")
    public String showAdminPlace(Model model, String keyword){
        if(keyword!=null) {
            model.addAttribute("places", placesService.findByKeyword(keyword));
        } else {
            model.addAttribute("places", placesService.getAllPlaces());
        }
        return "adminplace";
    }

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
        model.addAttribute("places", placesService.getAllPlaces());
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

    @GetMapping("/adminuser")
    public String showAdminUser(Model model, String keyword){
        if(keyword!=null) {
            model.addAttribute("users", appUserServiceImp.findUsersByKeyword(keyword));
        } else {
            model.addAttribute("users", appUserServiceImp.findAllUsers());
        }
        return "adminuser";
    }


    @GetMapping("/edituseradmin/{id}")
    public String showUpdateUserForm(@PathVariable("id") long id, Model model) {
        AppUser user = appUserServiceImp.findById(id);
        // .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

        model.addAttribute("user", user);
        return "edituseradmin";
    }

    @PostMapping("/edituseradmin/{id}")
    public String changeUser(@PathVariable("id") long id, @Valid AppUser user,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            user.setId(id);
            return "edituseradmin";
        }

        appUserServiceImp.save(user);
        model.addAttribute("users", appUserServiceImp.findAllUsers());
        return "adminuser";
    }

}
