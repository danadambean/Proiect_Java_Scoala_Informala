package sci.travel_app.WalkTheBear.Model.Misc;

public enum AppUserRole {
    ADMIN(1), TRAVELER(2), HOST(3);

    private int order;
    private AppUserRole(int order) {
    this.order=order;
    }

    @Override
    public String toString() {
        return this.toString();
    }
}
