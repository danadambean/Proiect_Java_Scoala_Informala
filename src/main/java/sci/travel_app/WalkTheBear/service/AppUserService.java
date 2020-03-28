package sci.travel_app.WalkTheBear.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import sci.travel_app.WalkTheBear.model.entities.AppUser;

import java.util.List;

public interface AppUserService extends UserDetailsService {

    void save(AppUser user);

    AppUser findByEmail(String email);
    //List<AppUser> findByUserName(String userName);
}
