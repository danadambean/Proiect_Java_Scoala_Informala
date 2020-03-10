package sci.travel_app.WalkTheBear.data_utils.dto;

import sci.travel_app.WalkTheBear.model.entities.AppUser;
import sci.travel_app.WalkTheBear.model.entities.DailySchedule;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TripDTO {

    private AppUser currentUser;
    private String tripName;
    private String tripDescription;
    private Date dateCreated;
    private Date firstDay;
//    private Date startDate;
    private Date lastDay;
//    private Date endDate;
private List<DailySchedule> ScheduleList = new ArrayList<>();

    public AppUser getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(AppUser currentUser) {
        this.currentUser = currentUser;
    }

    public String getTripName() {
        return tripName;
    }

    public void setTripName(String tripName) {
        this.tripName = tripName;
    }

    public String getTripDescription() {
        return tripDescription;
    }

    public void setTripDescription(String tripDescription) {
        this.tripDescription = tripDescription;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getFirstDay() {
        return firstDay;
    }

    public void setFirstDay(Date firstDay) {
        this.firstDay = firstDay;
    }

    public Date getLastDay() {
        return lastDay;
    }

    public void setLastDay(Date lastDay) {
        this.lastDay = lastDay;
    }

    public List<DailySchedule> getScheduleList() {
        return ScheduleList;
    }

    public void setScheduleList(List<DailySchedule> scheduleList) {
        ScheduleList = scheduleList;
    }
}
