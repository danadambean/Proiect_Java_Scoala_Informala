package sci.travel_app.walkthebear.controller;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sci.travel_app.walkthebear.data_utils.AppUserDetails;
import sci.travel_app.walkthebear.data_utils.UploadService;
import sci.travel_app.walkthebear.model.entities.AppUser;
import sci.travel_app.walkthebear.model.entities.Favorite;
import sci.travel_app.walkthebear.model.entities.Place;
import sci.travel_app.walkthebear.model.entities.Rating;
import sci.travel_app.walkthebear.model.entities.*;
import sci.travel_app.walkthebear.repository.AppUserRepository;
import sci.travel_app.walkthebear.service.AppUserServiceImp;
import sci.travel_app.walkthebear.service.FavoritesServiceImpl;
import sci.travel_app.walkthebear.service.PlacesServiceImp;
import sci.travel_app.walkthebear.service.RatingServiceImpl;

import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
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
    @Autowired
    private PlacesServiceImp placesService;
    @Autowired
    private AppUserRepository appUserRepository;
    @Autowired
    private UploadService uploadService;
    private static org.apache.logging.log4j.Logger logger = LogManager.getLogger(MyProfileController.class);


    @GetMapping(value = "/profileinfo")
    public String userprofile(@AuthenticationPrincipal AppUserDetails currentUser, Model model) {
        AppUser user = appUserServiceImp.findById(appUserServiceImp.findByUserName(currentUser.getUsername()).getId());
        model.addAttribute("currentUser", user);

        //String name = principal.getName(); //get logged in username
        //model.addAttribute("username", name);

        return "profileinfo";
    }

    @GetMapping("/editprofile/{id}")
    public String showEditProfileInfoForm(@PathVariable("id") long id, Model model) {
        model.addAttribute("appUser", appUserServiceImp.findById(id));

        return "editprofile";
    }

    @PostMapping("/editprofile/{id}/send")
    public String changeProfileInfo(@PathVariable("id") long id, @Valid AppUser user, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("message", "Could not update");
            return "editprofile";
        }

        model.addAttribute("appUser", appUserServiceImp.findById(id));
        appUserServiceImp.update(user, id);

        return "redirect:/profileinfo";
    }

    @GetMapping("/profileratings")
    public String getAllRated(@AuthenticationPrincipal AppUserDetails currentUser, Model model) {
        AppUser user = appUserServiceImp.findByUserName(currentUser.getUsername());
        //model.addAttribute("currentUser", user);
        List<Rating> allRated = ratingService.findByUser(user.getId());
        System.out.println(allRated);
        model.addAttribute("allRated", allRated);
        return "profileratings";
    }

    //delete place from rating
    @GetMapping("/profileratings/{id}/delete")
    public String deleteRatings(@PathVariable(value = "id") long id, RedirectAttributes redirectAttributes, Model model, Principal principal) {

        ratingService.deleteRating(id);

        AppUser user = appUserServiceImp.findByUserName(principal.getName());
        List<Favorite> allFavorite = favoritesService.findByUser(user.getId());
        model.addAttribute("allFavorite", allFavorite);
        redirectAttributes.addFlashAttribute("message", "Rating was deleted");

        return "redirect:/profileratings";
    }

    @GetMapping("/profilefav")
    public String getAllFavorites(@AuthenticationPrincipal AppUserDetails currentUser, Model model) {
        AppUser user = appUserServiceImp.findByUserName(currentUser.getUsername());
        //model.addAttribute("currentUser", user);
        List<Favorite> allFavorite = favoritesService.findByUser(user.getId());
        System.out.println(allFavorite);
        model.addAttribute("allFavorite", allFavorite);
        return "profilefav";
    }

    //delete place from favorite
    @GetMapping("/profilefav/{id}/delete")
    public String deleteFav(@PathVariable(value = "id") long id, RedirectAttributes redirectAttributes, Model model, Principal principal) {
        Place place = placesService.getPlaceById(id);
        AppUser user = appUserServiceImp.findByUserName(principal.getName());
        favoritesService.removeFavorite(place, user);
        List<Favorite> allFavorite = favoritesService.findByUser(user.getId());
        model.addAttribute("allFavorite", allFavorite);
        redirectAttributes.addFlashAttribute("message", "Favorite was deleted");

        return "redirect:/profilefav";
    }


    @GetMapping("/adminplace")
    public String showAdminPlace(Model model, String keyword) {
        if (keyword != null) {
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
        model.addAttribute("place", place);
        model.addAttribute("hasThumbnail", placesService.hasPic(placesService.getPlaceById(id).getThumbnailFileName()));
        model.addAttribute("hasG1", placesService.hasPic(placesService.getPlaceById(id).getGalleryImage1FileName()));
        model.addAttribute("hasG2", placesService.hasPic(placesService.getPlaceById(id).getGalleryImage2FileName()));
        model.addAttribute("hasG3", placesService.hasPic(placesService.getPlaceById(id).getGalleryImage3FileName()));
        model.addAttribute("hasG4", placesService.hasPic(placesService.getPlaceById(id).getGalleryImage4FileName()));
        model.addAttribute("hasG5", placesService.hasPic(placesService.getPlaceById(id).getGalleryImage5FileName()));

        return "editplaceadmin";
    }

    @PostMapping("/editplaceadmin/{id}")
    public String changePlace(@PathVariable("id") long id, @Valid Place place,
                              BindingResult result, Model model, Principal principal, RedirectAttributes redirectAttributes,
                              @RequestParam(value = "thumbnail", required = false) MultipartFile multipartFile,
                              @RequestParam(value = "galleryImage1", required = false) MultipartFile galleryImageFiles1,
                              @RequestParam(value = "galleryImage2", required = false) MultipartFile galleryImageFiles2,
                              @RequestParam(value = "galleryImage3", required = false) MultipartFile galleryImageFiles3,
                              @RequestParam(value = "galleryImage4", required = false) MultipartFile galleryImageFiles4,
                              @RequestParam(value = "galleryImage5", required = false) MultipartFile galleryImageFiles5) throws IOException {
        if (result.hasErrors()) {
            place.setId(id);
            return "editplaceadmin";
        }

        Place savedPlace = placesService.getPlaceById(id);
        String fileNameT = multipartFile.getOriginalFilename();
        if (!"".equals(fileNameT)) {
            uploadService.uploadImageFile(savedPlace, multipartFile, StringUtils.cleanPath(multipartFile.getOriginalFilename()));
        }
        String fileNameG1 = galleryImageFiles1.getOriginalFilename();
        if (!"".equals(fileNameG1)) {
            uploadService.uploadImageFile(savedPlace, galleryImageFiles1, StringUtils.cleanPath(galleryImageFiles1.getOriginalFilename()));
        }
        String fileNameG2 = galleryImageFiles2.getOriginalFilename();
        if (!"".equals(fileNameG2)) {
            uploadService.uploadImageFile(savedPlace, galleryImageFiles2, StringUtils.cleanPath(galleryImageFiles2.getOriginalFilename()));
        }
        String fileNameG3 = galleryImageFiles3.getOriginalFilename();
        if (!"".equals(fileNameG3)) {
            uploadService.uploadImageFile(savedPlace, galleryImageFiles3, StringUtils.cleanPath(galleryImageFiles3.getOriginalFilename()));
        }
        String fileNameG4 = galleryImageFiles4.getOriginalFilename();
        if (!"".equals(fileNameG4)) {
            uploadService.uploadImageFile(savedPlace, galleryImageFiles4, StringUtils.cleanPath(galleryImageFiles4.getOriginalFilename()));
        }
        String fileNameG5 = galleryImageFiles5.getOriginalFilename();
        if (!"".equals(fileNameG5)) {
            uploadService.uploadImageFile(savedPlace, galleryImageFiles5, StringUtils.cleanPath(galleryImageFiles5.getOriginalFilename()));
        }

        placesService.updatePlace(savedPlace, fileNameT, fileNameG1, fileNameG2, fileNameG3, fileNameG4, fileNameG5);
        model.addAttribute("places", placesService.getAllPlaces());
        redirectAttributes.addFlashAttribute("message", "Place was updated");
        logger.log(Level.INFO, "Updated place: ID " + id);

        return "adminplace";
    }

    /**
     * Method used to delete a place from the database
     *
     * @param id    - place ID
     * @param model
     * @return
     * @throws IllegalArgumentException
     */
    @GetMapping("/deleteplaceadmin/{id}")
    public String erasePlace(@PathVariable("id") long id, Model model, RedirectAttributes redirectAttributes) throws IllegalArgumentException {
        Place place = placesService.getPlaceById(id);
        placesService.deletePlace(id);
        model.addAttribute("places", placesService.getAllPlaces());
        redirectAttributes.addFlashAttribute("message", "Place was deleted");
        logger.log(Level.INFO, "Place was deleted " + id);
        return "adminplace";
    }

    @GetMapping("/adminuser")
    public String showAdminUser(Model model, String keyword) {
        if (keyword != null) {
            model.addAttribute("users", appUserServiceImp.findUsersByKeyword(keyword));
        } else {
            model.addAttribute("users", appUserServiceImp.findAllUsers());
        }
        return "adminuser";
    }


    @GetMapping("/edituseradmin/{id}")
    public String showUpdateUserForm(@PathVariable("id") long id, Model model) {
        AppUser user = appUserServiceImp.findById(id);
        model.addAttribute("user", user);
        return "edituseradmin";
    }

    @PostMapping("/edituseradmin/{id}")
    public String changeUser(@PathVariable("id") long id, @Valid AppUser user,
                             BindingResult result, Model model,RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {

            user.setId(id);
            redirectAttributes.addFlashAttribute("message", "Could not update user");
            return "edituseradmin";
        }

        appUserServiceImp.save(user);
        model.addAttribute("users", appUserServiceImp.findAllUsers());
        redirectAttributes.addFlashAttribute("message", "User was updated");
        logger.log(Level.INFO, "User was updated " + user);
        return "adminuser";
    }

    @GetMapping("/deleteuseradmin/{id}")
    public String deleteAdminUser(@PathVariable("id") long id, Model model, RedirectAttributes redirectAttributes) {
        AppUser user = appUserServiceImp.findById(id);
        appUserServiceImp.deleteUser(user);
        model.addAttribute("users", appUserServiceImp.findAllUsers());
        return "adminuser";
    }

    @GetMapping("/editratings/{id}")
    public String newRating(@PathVariable("id") long id, Model model, RedirectAttributes redirectAttributes) {
        Place place = placesService.getPlaceById(id);
        Rating rating = new Rating();
        rating.setPlace(place);
        model.addAttribute("place", place);
        List<Rating> ratingList = ratingService.getAllRatingsOfPlaceById(id);
        model.addAttribute("ratingList", ratingList);
        double placeAverageRating = (ratingList.stream().mapToDouble(Rating::getStarRating).sum() / ratingList.stream().count());
        model.addAttribute("placeAverageRating", placeAverageRating);
        return "editratings";
    }

    @GetMapping("/editratings/delete/{id}")
    public String deleteAdminRatings(@PathVariable("id") long id, RedirectAttributes redirectAttributes, Model model) throws IllegalArgumentException {

        long placeId = ratingService.findById(id).getPlace().getId();
        ratingService.deleteRating(id);
        redirectAttributes.addFlashAttribute("message", "Rating was deleted");
        logger.log(Level.INFO, "Rating was deleted" + id);
        return "redirect:/editratings/" + placeId;
    }
}
