package sci.travel_app.Model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@DiscriminatorValue("Attractions")
public class Attractions extends Places{
    @Enumerated(EnumType.STRING)
    Attractions attractionsSubCategory;
    private String workingHours;

    public String getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(String workingHours) {
        this.workingHours = workingHours;
    }

    public Attractions getAttractionsSubCategory() {
        return attractionsSubCategory;
    }

    public void setAttractionsSubCategory(Attractions attractionsSubCategory) {
        this.attractionsSubCategory = attractionsSubCategory;
    }
}
