package sci.travel_app.Model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Rating {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    long id;
    private int starRating;
    private String comment;
    @OneToOne
    @Column(name = "created_by")
    private AppUser user;
    @OneToOne
    @Column(name = "rated_place")
    private Places place;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_created")
    private Date created;

    public int getStarRating() {
        return starRating;
    }

    public void setStarRating(int starRating) {
        this.starRating = starRating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

    public Places getPlace() {
        return place;
    }

    public void setPlace(Places place) {
        this.place = place;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
