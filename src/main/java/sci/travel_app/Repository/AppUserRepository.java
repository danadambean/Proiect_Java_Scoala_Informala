package sci.travel_app.Repository;
import org.springframework.stereotype.Repository;
import sci.travel_app.Model.Entities.AppUser;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@Repository
public interface AppUserRepository extends CrudRepository<AppUser, Long> {
    List<AppUser> findByLastName(String lastName);

    AppUser findById(long id);

}