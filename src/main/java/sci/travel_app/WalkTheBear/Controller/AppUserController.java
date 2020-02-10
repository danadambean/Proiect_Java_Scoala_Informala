package sci.travel_app.WalkTheBear.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sci.travel_app.WalkTheBear.Model.Entities.AppUser;
import sci.travel_app.WalkTheBear.Model.Misc.AppUserRole;
import sci.travel_app.WalkTheBear.Repository.AppUserRepository;

public class AppUserController {

//    @Controller // This means that this class is a Controller
//    @RequestMapping(path = "/demo") // This means URL's start with /demo (after Application path)
//    public class MainController {
//        @Autowired // This means to get the bean called userRepository
//        // Which is auto-generated by Spring, we will use it to handle the data
//        private AppUserRepository userRepository;
//
//        @PostMapping(path = "/add") // Map ONLY POST Requests
//        public @ResponseBody
//        String addNewUser(@RequestParam String username, @RequestParam String password, @RequestParam String email, @RequestParam AppUserRole role) {
//            // @ResponseBody means the returned String is the response, not a view name
//            // @RequestParam means it is a parameter from the GET or POST request
//
//            AppUser n = new AppUser();
//            n.setUserName(username);
//            n.setPassword(password);
//            n.setEmail(email);
//            n.setRole(role);
//
//
//            userRepository.save(n);
//            return "Saved";
//        }
//
//        @GetMapping(path = "/all")
//        public @ResponseBody
//        Iterable<AppUser> getAllUsers() {
//            // This returns a JSON or XML with the users
//            return userRepository.findAll();
//        }
//    }
}