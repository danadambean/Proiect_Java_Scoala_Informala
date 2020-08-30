package sci.travel_app.walkthebear.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import sci.travel_app.walkthebear.data_utils.dto.AppUserDetails;
import sci.travel_app.walkthebear.model.entities.AppUser;
import sci.travel_app.walkthebear.model.entities.Favorite;
import sci.travel_app.walkthebear.model.entities.Rating;
import sci.travel_app.walkthebear.repository.AppUserRepository;
import sci.travel_app.walkthebear.service.AppUserServiceImp;
import sci.travel_app.walkthebear.service.FavoritesServiceImpl;
import sci.travel_app.walkthebear.service.RatingServiceImpl;

import java.util.List;

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

}
