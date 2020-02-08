package sci.travel_app.Model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@DiscriminatorValue("Food and Drink")
public class FoodDrink extends Places {
    @Enumerated(EnumType.STRING)
    FoodDrink foodDrinkSubCategory;
    private String workingHours;

    public String getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(String workingHours) {
        this.workingHours = workingHours;
    }

    public FoodDrink getFoodDrinkSubCategory() {
        return foodDrinkSubCategory;
    }

    public void setFoodDrinkSubCategory(FoodDrink foodDrinkSubCategory) {
        this.foodDrinkSubCategory = foodDrinkSubCategory;
    }
}
