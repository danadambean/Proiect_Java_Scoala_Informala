package sci.travel_app.WalkTheBear.repository;

import org.springframework.data.repository.CrudRepository;
import sci.travel_app.WalkTheBear.model.entities.AppUser;
import sci.travel_app.WalkTheBear.model.entities.DailySchedule;
import sci.travel_app.WalkTheBear.model.entities.Itinerary;

import java.util.List;


public interface DailyScheduleRepository extends CrudRepository<DailySchedule, Long> {
    List<DailySchedule> findByUser(AppUser createdBy);
    DailySchedule findById(long id);

}
