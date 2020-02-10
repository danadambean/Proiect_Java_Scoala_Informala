package sci.travel_app.WalkTheBear.Repository;
import org.springframework.stereotype.Repository;
import sci.travel_app.WalkTheBear.Model.Entities.AppUser;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@Repository
public interface AppUserRepository extends CrudRepository<AppUser, Long> {
    List<AppUser> findByUserName(String userName);

    AppUser findById(long id);

}