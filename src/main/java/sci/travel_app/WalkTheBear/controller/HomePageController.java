package sci.travel_app.WalkTheBear.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import sci.travel_app.WalkTheBear.model.entities.Place;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomePageController {

@RequestMapping("/")
public List<Place> populateWidget1(){
//add code to get places based on criteria
    List<Place> widget1 = new ArrayList<>();

        return widget1;
    }
//repeat for other 2 lists

}
