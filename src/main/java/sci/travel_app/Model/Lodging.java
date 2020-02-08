package sci.travel_app.Model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
@Entity
@DiscriminatorValue("Lodging")
public class Lodging extends Places{
    @Enumerated(EnumType.STRING)
    Lodging lodgingSubCategory;
    private String workingHours;

    public String getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(String workingHours) {
        this.workingHours = workingHours;
    }
}
