package sci.travel_app.WalkTheBear.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import sci.travel_app.WalkTheBear.model.entities.AppUser;
import sci.travel_app.WalkTheBear.repository.AppUserRepository;
import sci.travel_app.WalkTheBear.service.AppUserService;

@Controller
public class AppUserController {
    @Autowired
    private AppUserRepository userRepository;
    @Autowired
    private AppUserService userService;


//    @PostMapping(path = "/add") // Map ONLY POST Requests
//    public @ResponseBody
//    String addNewUser(@RequestParam String userName, @RequestParam String password, @RequestParam String email, @RequestParam AppUserRole role) {
//        // @ResponseBody means the returned String is the response, not a view name
//        // @RequestParam means it is a parameter from the GET or POST request
//
//        AppUser n = new AppUser();
//        n.setUserName(userName);
//        n.setPassword(password);
//        n.setEmail(email);
//        n.setRole(role);
//        userRepository.save(n);
//        return "Saved";
//    }
//
//
//    @GetMapping(value = "/greeting")
//    public ModelAndView captureDetails() {
//        ModelAndView mav = new ModelAndView("adduser");
//
//        AppUser theuser = new AppUser();
//        theuser.setUserName("fffff");
//        theuser.setPassword("ffff");
//        theuser.setEmail("ffff");
//        theuser.setRole(AppUserRole.HOST);
//
//        mav.addObject("theuser", theuser);
//        return mav;
//    }

//    @GetMapping("/register")
//    public String registration(Model model) {
//        model.addAttribute("userForm", new AppUser());
//
//        return "register";
//    }
//
//    @PostMapping("/register")
//    public String registration(@ModelAttribute("userForm") AppUser userForm, BindingResult bindingResult) {
//        UserValidator.validate(userForm, bindingResult);
//
//        if (bindingResult.hasErrors()) {
//            return "register";
//        }
//
//        AppUserService.save(userForm);
//       SecurityService.autoLogin(userForm.getUserName(), userForm.getPassword());
//
//        return "redirect:/welcome";
//    }
//
//    @GetMapping("/login")
//    public String login(Model model, String error, String logout) {
//        if (error != null)
//            model.addAttribute("error", "Your username and password is invalid.");
//
//        if (logout != null)
//            model.addAttribute("message", "You have been logged out successfully.");
//
//        return "login";
//    }
//
//    @GetMapping({"/", "/welcome"})
//    public String welcome(Model model) {
//        return "welcome";
//    }
//}

}