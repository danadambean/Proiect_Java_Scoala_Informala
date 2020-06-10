package sci.travel_app.walkthebear.controller;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sci.travel_app.walkthebear.model.entities.*;
import sci.travel_app.walkthebear.service.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TripController {

    @Autowired
    private ItineraryService itineraryService;
    @Autowired
    private DailyScheduleService dailyScheduleService;
    @Autowired
    private HourMappingService hourMappingService;
    @Autowired
    private PlacesServiceImp placesService;
    @Autowired
    private UnplannedPlacesListService unplannedPlacesListService;

    private static org.apache.logging.log4j.Logger logger = LogManager.getLogger(TripController.class);

//Methods for trip manager page

    //switch to find by user after user login is implemented

    //show all itineraries


    //add Principal principal to argument list
    @GetMapping("/tripmanager")
    public String getAllTrips(Model model) {

        //AppUser user = principal;
        model.addAttribute("allTrips", itineraryService.findAll());
        return "tripmanager";
    }


    //delete itinerary
    @GetMapping("/tripmanager/delete/{id}")
    public String deleteTrip(@PathVariable(value = "id") long id, RedirectAttributes redirectAttributes, Model model) {
        List<DailySchedule> allDaysForItinerary = dailyScheduleService.getAllDays(itineraryService.findById(id));
        for (DailySchedule day : allDaysForItinerary) {
            hourMappingService.deleteAll(day);
        }
        dailyScheduleService.deleteAll(itineraryService.findById(id));
        itineraryService.deleteItinerary(itineraryService.findById(id));
        model.addAttribute("allTrips", itineraryService.findAll());
        redirectAttributes.addFlashAttribute("message", "Trip was deleted");
        logger.log(Level.INFO, "Deleted itinerary: ID "+id);
        return "redirect:/tripmanager";
    }

//Methods for planner page

    //create new itinerary
    @GetMapping("/planner/new")
    public String createTrip(Model model) {
        model.addAttribute("itinerary", new Itinerary());
        return "planneradd";
    }

    //save itinerary
    @PostMapping("/planner/save")
    public String saveTrip(@ModelAttribute("trip") Itinerary trip,  BindingResult result, RedirectAttributes redirectAttributes) {
        Itinerary savedItinerary = itineraryService.saveItinerary(trip);
        logger.log(Level.INFO, "Created new itinerary: ID "+ savedItinerary.getItineraryId());
        redirectAttributes.addFlashAttribute("message", "Trip saved!");
        return "redirect:/planner/" + savedItinerary.getItineraryId();
    }

    //view + edit itinerary
    @GetMapping("/planner/{id}")
    public String editTrip(@PathVariable(value = "id") long id, Model model) {
        model.addAttribute("itinerary" , itineraryService.findById(id));
        model.addAttribute("allDaysForItinerary", dailyScheduleService.getAllDays(itineraryService.findById(id)));
        List<Place> unplannedPlaces = unplannedPlacesListService.findByUser(null).getUnplannedPlacesTemp();
        model.addAttribute("unplannedPlaces", unplannedPlaces);
        return "planneredit";
    }

    @PostMapping("/planner/{id}/update")
    public String updateTrip (@PathVariable(value = "id") long id, @Valid Itinerary itinerary, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("message", "Could not update");
            return "planneredit";
        }
        model.addAttribute("itinerary" , itineraryService.findById(id));
        itineraryService.update(itinerary, id);
        redirectAttributes.addFlashAttribute("message", "Trip updated!");
        return "redirect:/planner/"+id;
    }

    //view + print + download pdf
    @GetMapping("/planner/view/{id}")
    public String showTrip(@PathVariable(value = "id") long id, Model model) {
        model.addAttribute("itinerary" , itineraryService.findById(id));
        List<DailySchedule> allDaysForItinerary = dailyScheduleService.getAllDays(itineraryService.findById(id));
        model.addAttribute("allDaysForItinerary", allDaysForItinerary);
        Map<String, List<HourMapping>> timeTable = new HashMap<>();
        for (DailySchedule day : allDaysForItinerary) {
            timeTable.put(day.getName(),hourMappingService.getFullDay(day));
        }
        model.addAttribute("timetable", timeTable);
        return "plannerview";
    }



//Methods for day page


    @GetMapping("/planner/{id}/addnewday")
    public String addNewDay(@PathVariable(value = "id") long id, RedirectAttributes redirectAttributes, Model model) {
        DailySchedule newDay = dailyScheduleService.addNewDay(itineraryService.findById(id));
        model.addAttribute("itinerary" , itineraryService.findById(id));
        List<DailySchedule> allDaysForItinerary = dailyScheduleService.getAllDays(itineraryService.findById(id));
        model.addAttribute("allDaysForItinerary", allDaysForItinerary);
        redirectAttributes.addFlashAttribute("message", "New day successfully added");
        logger.log(Level.INFO, "Created new day: ID "+ newDay.getDayId());
        return "redirect:/planner/" + id;
    }

    @GetMapping("/planner/{id}/day/{dayID}")
    public String displayDay(@PathVariable(value = "id") long id, @PathVariable(value = "dayID") long dayId, Model model) {
        model.addAttribute("itinerary", itineraryService.findById(id));
        model.addAttribute("day", dailyScheduleService.getDay(dayId));
        List<HourMapping> allHours= hourMappingService.getFullDay(dailyScheduleService.getDay(dayId));
        model.addAttribute("allHours", allHours);
        HourMapping objective = new HourMapping();
        objective.setDailySchedule(dailyScheduleService.getDay(dayId));
        model.addAttribute("objective", objective);
//        //replace with method implementation to include current user
        List<Place> unplannedPlaces = unplannedPlacesListService.findByUser(null).getUnplannedPlacesTemp();
        model.addAttribute("unplannedPlaces", unplannedPlaces);

        return "day_add";
    }



    @PostMapping("/planner/{id}/day/{dayID}/save")
    public String saveDay(@PathVariable(value = "id") long itineraryID, @PathVariable(value = "dayID") long dayId, @ModelAttribute("objective") HourMapping hourMapping, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("message", "Could not save");
            return "day_add";
        }
        model.addAttribute("itinerary", itineraryService.findById(itineraryID));
        model.addAttribute("day", dailyScheduleService.getDay(dayId));
        dailyScheduleService.saveDay(dailyScheduleService.getDay(dayId));
        hourMappingService.saveMapping(hourMapping, dailyScheduleService.getDay(dayId));
        redirectAttributes.addFlashAttribute("message", "Day was successfully updated");
        logger.log(Level.INFO, "Saved hourmapping: ID "+dayId);
        return "redirect:/planner/"+itineraryID+"/day/"+ dayId;
    }

    //edit hour mapping wip
    @GetMapping("/planner/{id}/day/{dayID}/edit/{hourId}")
    public String editObjective(@PathVariable(value = "id") long id, @PathVariable(value = "dayID") long dayId, @PathVariable(value = "hourId") long hourId, Model model) {
        model.addAttribute("itinerary", itineraryService.findById(id));
        model.addAttribute("day", dailyScheduleService.getDay(dayId));
        List<HourMapping> allHours= hourMappingService.getFullDay(dailyScheduleService.getDay(dayId));
        model.addAttribute("allHours", allHours);
        HourMapping objective = hourMappingService.getHour(hourId);
        model.addAttribute("objective", objective);
        model.addAttribute("hour",hourMappingService.getHour(hourId));

        //replace with an actual implementation; from here

        List<Place> unplannedPlaces = unplannedPlacesListService.findByUser(null).getUnplannedPlacesTemp();
        model.addAttribute("unplannedPlaces", unplannedPlaces);
        return "day_update";
    }
    //update hour mapping wip
    @PostMapping("/planner/{id}/day/{dayID}/update/{hoursId}")
    public String updateHour(@PathVariable(value = "id") long itineraryID, @PathVariable(value = "dayID") long dayId, @PathVariable(value = "hoursId") long hourId, @Valid HourMapping hourMapping, BindingResult result, Model model, RedirectAttributes redirectAttributes){
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("message", "Could not update");
            return "day_update";
        }
        model.addAttribute("itinerary", itineraryService.findById(itineraryID));
        model.addAttribute("day", dailyScheduleService.getDay(dayId));
        model.addAttribute("hour",hourMappingService.getHour(hourId));
        hourMappingService.updateMapping(hourMapping, dailyScheduleService.getDay(dayId), hourId);
        redirectAttributes.addFlashAttribute("message", "Day was successfully updated");
        logger.log(Level.INFO, "Updated hourmapping: ID "+dayId);
        return "redirect:/planner/"+itineraryID+"/day/"+ dayId;
    }

    //delete hour mapping wip
    @GetMapping("/planner/{id}/day/{dayID}/delete/{hourId}")
    public String deleteObjective(@PathVariable(value = "id") long itineraryID, @PathVariable(value = "dayID") long dayId, @PathVariable(value = "hourId") long hourId, RedirectAttributes redirectAttributes, Model model) {
        hourMappingService.deleteOne(hourMappingService.getHour(hourId));
        List<HourMapping> allHours= hourMappingService.getFullDay(dailyScheduleService.getDay(dayId));
        model.addAttribute("allHours", allHours);
        redirectAttributes.addFlashAttribute("message", "Objective removed");
        logger.log(Level.INFO, "Deleted : ID "+hourId);

        return "redirect:/planner/"+itineraryID+"/day/"+ dayId;
    }


    //delete day + all hour mappings for that day
    @GetMapping("/planner/{id}/day/{dayID}/delete")
    public String removeDay(@PathVariable(value = "id") long id, @PathVariable(value = "dayID") long dayId, RedirectAttributes redirectAttributes, Model model) {

        hourMappingService.deleteAll(dailyScheduleService.getDay(dayId));
        dailyScheduleService.removeDay(dailyScheduleService.getDay(dayId));
        redirectAttributes.addFlashAttribute("message", "Day was successfully removed");
        logger.log(Level.INFO, "Deleted day: ID "+id);
        return "redirect:/planner/" + id;
    }
}
