package sci.travel_app.Repository;
import sci.travel_app.Model.AppUser;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface AppUserRepository extends CrudRepository<AppUser, Long> {
    List<AppUser> findByLastName(String lastName);

    AppUser findById(long id);

}