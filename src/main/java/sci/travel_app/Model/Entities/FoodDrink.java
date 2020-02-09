package sci.travel_app.Model.Entities;

import sci.travel_app.Model.Misc.FoodDrinkSubCategory;

import javax.persistence.*;

@Entity
@DiscriminatorValue("Food and Drink")
public class FoodDrink extends Place {
    @Column(name = "SUBCATEGORY", nullable = false)
    @Enumerated(EnumType.STRING)
    private FoodDrinkSubCategory Subcategory;
    @Column(name = "WORK_HOURS")
    private String workingHours;

    public String getWorkingHours() {
        return workingHours;
    }
    public void setWorkingHours(String workingHours) {
        this.workingHours = workingHours;
    }

    public FoodDrinkSubCategory getSubcategory() {
        return Subcategory;
    }

    public void setSubcategory(FoodDrinkSubCategory subcategory) {
        Subcategory = subcategory;
    }
}
