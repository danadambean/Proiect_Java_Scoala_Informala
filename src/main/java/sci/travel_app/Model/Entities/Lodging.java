package sci.travel_app.Model.Entities;

import sci.travel_app.Model.Misc.LodgingSubCategory;

import javax.persistence.*;

@Entity
@DiscriminatorValue("Lodging")
public class Lodging extends Place {
    @Column(name = "SUBCATEGORY", nullable = false)
    @Enumerated(EnumType.STRING)
    private LodgingSubCategory Subcategory;
    @Column(name = "WORK_HOURS")
    private String workingHours;

    public String getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(String workingHours) {
        this.workingHours = workingHours;
    }

    public LodgingSubCategory getSubcategory() {
        return Subcategory;
    }

    public void setSubcategory(LodgingSubCategory subcategory) {
        Subcategory = subcategory;
    }

}
