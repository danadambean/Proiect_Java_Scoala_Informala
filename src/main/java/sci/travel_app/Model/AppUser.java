package sci.travel_app.Model;

public class AppUser {
    private String userName;
    private String password;
    private String email;
    //private int age;
    private AppUserRole role;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public AppUserRole getRole() {
        return role;
    }

    public void setRole(AppUserRole role) {
        this.role = role;
    }

    /* public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }*/
}
