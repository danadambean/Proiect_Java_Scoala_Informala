package sci.travel_app.Model;

import javax.persistence.*;
import java.util.Date;

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Entity
@DiscriminatorColumn(name = "CATEGORY")
public class Places {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    private String city;
    private String county;
    private String address;
    private String coordinates;
    // private int rating;
    private String phoneNumber;
    private String email;
    private String description;
    //Places mainPic;
    //Places galleryPic;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_created")
    private Date created;
    @OneToOne
    @Column(name = "created_by")
    private AppUser user;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

   /* public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    } */

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
