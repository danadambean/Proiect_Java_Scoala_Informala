package sci.travel_app.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Itinerary {

    private String name;
    private String description;
    @OneToOne
    @Column(name = "created_by")
    private AppUser user;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_created")
    private Date created;
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Temporal(TemporalType.DATE)
    private Date endDate;
    public  List<Places> unplannedPlaces = new ArrayList<>();
    public  List<DailySchedule> Schedule = new ArrayList<>();
    

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreated() {
        return created;
    }
    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getStartDate() {
        return startDate;
    }
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }





}
