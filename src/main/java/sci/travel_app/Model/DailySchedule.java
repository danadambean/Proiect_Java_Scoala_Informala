package sci.travel_app.Model;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
public class DailySchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    @OneToOne
    @Column(name = "created_by")
    private AppUser user;
    private String name;
    @ElementCollection
    @MapKeyColumn(name = "hour")
    @Column(name = "place")
    @CollectionTable(name = "DAY_MAPPING")
    public Map<String, Places> day = new HashMap<>();

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

    public Map<String, Places> getDay() {
        return day;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDay(Map<String, Places> day) {
        this.day = day;
    }
}
