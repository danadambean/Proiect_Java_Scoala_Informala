package sci.travel_app.Model;

public class FoodDrink extends Places{
    FoodDrink foodDrinkSubCategory;
    private String workingHours;

    public String getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(String workingHours) {
        this.workingHours = workingHours;
    }
}
