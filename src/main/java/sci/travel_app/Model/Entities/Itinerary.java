package sci.travel_app.Model.Entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ITINERARY")
public class Itinerary {
    @Id
    @Column(name = "ITINERARY_ID")
    @GeneratedValue(strategy= GenerationType.AUTO)
    long id;
    @Column(name = "NAME", nullable = false)
    private String name ;
    @Column(name = "DESCRIPTION")
    private String description;
    @OneToOne
    @JoinColumn(name = "CREATED_BY")
    private AppUser user;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATE_CREATED")
    private Date created;
    @Temporal(TemporalType.DATE)
    @Column(name = "DATE_START")
    private Date startDate;
    @Temporal(TemporalType.DATE)
    @Column(name = "DATE_END")
    private Date endDate;
    public  List<Place> unplannedPlaces = new ArrayList<>();
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
