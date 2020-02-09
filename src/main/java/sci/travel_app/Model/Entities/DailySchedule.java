package sci.travel_app.Model.Entities;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
public class DailySchedule {
    @Id
    @Column(name = "SCHEDULE_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    @OneToOne
    @JoinColumn(name = "CREATED_BY")
    private AppUser user;
    @OneToOne
    @JoinColumn(name = "ITINERARY_ID")
    private Itinerary itinerary;
    @Column(name = "NAME")
    private String name;
    @ElementCollection
    @MapKeyColumn(name = "TIME_SLOT")
    @Column(name = "PLACE")
    @CollectionTable(name = "DAY_MAPPING")
    public Map<String, Place> day = new HashMap<>();

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public AppUser getUser() {
        return user;
    }
    public void setUser(AppUser user) {
        this.user = user;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

}
