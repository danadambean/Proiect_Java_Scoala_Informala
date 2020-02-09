package sci.travel_app.Model.Entities;

import sci.travel_app.Model.Misc.AttractionsSubCategory;

import javax.persistence.*;

@Entity
@DiscriminatorValue("Attractions")
public class Attractions extends Place {
    @Column(name = "SUBCATEGORY", nullable = false)
    @Enumerated(EnumType.STRING)
    private AttractionsSubCategory Subcategory;
    @Column(name = "WORK_HOURS")
    private String workingHours;

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
