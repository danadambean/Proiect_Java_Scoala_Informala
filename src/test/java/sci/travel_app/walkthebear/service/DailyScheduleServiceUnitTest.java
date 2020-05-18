package sci.travel_app.walkthebear.service;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import sci.travel_app.walkthebear.model.entities.DailySchedule;
import sci.travel_app.walkthebear.repository.DailyScheduleRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)

public class DailyScheduleServiceUnitTest {

    @Mock
    private DailyScheduleRepository scheduleRepository;

    @InjectMocks
    private DailyScheduleService scheduleService;

    @Test
    public void addANewDay() {

        // create a dailySchedule
        DailySchedule aMockSchedule = new DailySchedule();
        aMockSchedule.setName("Salina Turda");

        // test adding the info
        when(scheduleRepository.save(any(DailySchedule.class))).thenReturn(aMockSchedule);

        DailySchedule newPlace = scheduleService.addNewDay(null);

        // verify if created
        assertEquals("Salina Turda", newPlace.getName());

    }

}
