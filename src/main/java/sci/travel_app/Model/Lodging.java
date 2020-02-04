package sci.travel_app.Model;

public class Lodging extends Places{
    Lodging lodgingSubCategory;
    private String workingHours;

    public String getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(String workingHours) {
        this.workingHours = workingHours;
    }
}
