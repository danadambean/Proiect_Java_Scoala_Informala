package sci.travel_app.WalkTheBear.Model.Entities;

import sci.travel_app.WalkTheBear.Model.Misc.AttractionsSubCategory;

import javax.persistence.*;

@Entity
@DiscriminatorValue("Attractions")
public class Attractions extends Place {
    @Column(name = "SUBCATEGORY", nullable = false)
    @Enumerated(EnumType.STRING)
    private AttractionsSubCategory Subcategory;
    @Column(name = "WORK_HOURS")
    private String workingHours;

    public Attractions(AttractionsSubCategory subcategory) {
        Subcategory = subcategory;
    }

    public Attractions(String name, String city, String county, String address, String phoneNumber, String email, String description, AppUser user, AttractionsSubCategory subcategory) {
        super(name, city, county, address, phoneNumber, email, description, user);
        Subcategory = subcategory;
    }

    public String getWorkingHours() {
        return workingHours;
    }
    public void setWorkingHours(String workingHours) {
        this.workingHours = workingHours;
    }
    public AttractionsSubCategory getSubcategory() {
        return Subcategory;
    }
    public void setSubcategory(AttractionsSubCategory subcategory) {
        Subcategory = subcategory;
    }
}
