package sci.travel_app.walkthebear.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import sci.travel_app.walkthebear.model.entities.AppUser;

public interface AppUserService extends UserDetailsService {

    AppUser save(AppUser user);
    void update(AppUser user, long id);

    AppUser findByEmail(String email);

//    AppUser findByUsername(String userName);


}
