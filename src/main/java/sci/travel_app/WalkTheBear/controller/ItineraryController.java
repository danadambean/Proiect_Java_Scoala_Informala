package sci.travel_app.WalkTheBear.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sci.travel_app.WalkTheBear.model.entities.AppUser;
import sci.travel_app.WalkTheBear.model.entities.Itinerary;
import sci.travel_app.WalkTheBear.repository.ItineraryRepository;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class ItineraryController {

    @Autowired
    private ItineraryRepository itineraryRepository;

//    @PostMapping("addItinerary")
//public String addNewItinerary (Itinerary itinerary, Model model) {
//        ItineraryService.createItinerary();
//        return "add-itinerary";
//    }

    @GetMapping("/tripmanager")
    public List<Itinerary> getAllTrips(AppUser user) {
        return itineraryRepository.findByUser(user);
    }
    @PostMapping("/planner")
    public Itinerary createTrip(Itinerary trip) {
        return itineraryRepository.save(trip);
    }
    @GetMapping("/planner/{id}")
    public Optional<Itinerary> showTrip(@PathVariable(value = "id") Long id) {
        return itineraryRepository.findById(id);
                //.orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));
    }
//    @PutMapping("/planner/{id}")
//    public Itinerary updateTrip(@PathVariable(value = "id") Long id,
//                           @Valid @RequestBody Itinerary trip) {
//
//        Itinerary itinerary = itineraryRepository.findById(id);
//                //.orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));
//
//        itinerary.setName(trip.getName());
//        itinerary.setDescription(trip.getDescription());
//
//        Itinerary updatedTrip = itineraryRepository.save(itinerary);
//        return updatedTrip;
//    }
//    @DeleteMapping("/planner/{id}")
//    public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Long id) {
//        Itinerary itinerary = itineraryRepository.findById(id);
////                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));
//
//        itineraryRepository.delete(itinerary);
//
//        return ResponseEntity.ok().build();
//    }

}