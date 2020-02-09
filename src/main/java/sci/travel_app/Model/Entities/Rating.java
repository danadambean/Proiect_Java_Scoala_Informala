package sci.travel_app.Model.Entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "RATING")
public class Rating {
    @Id
    @Column(name = "RATING_ID")
    @GeneratedValue(strategy= GenerationType.AUTO)
    long id;
    @Column(name = "STAR_RATING", nullable = false)
    private int starRating;
    @Column(name = "COMMENT", nullable = false)
    private String comment;
    @OneToOne
    @JoinColumn(name = "CREATED_BY")
    private AppUser user;
    @OneToOne
    @JoinColumn(name = "RATED_PLACE")
    private Place place;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATE_CREATED")
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

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }


}
