package sci.travel_app.WalkTheBear.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sci.travel_app.WalkTheBear.model.entities.Place;
import sci.travel_app.WalkTheBear.model.misc.Category;

import java.util.List;

@Repository
public interface PlacesRepository extends CrudRepository<Place, Long> {
    List<Place> findByName(String name);
    List<Place> findByCategory(Category category);
    Place findById(long id);

   /* @Query(value = "SELECT p FROM Place p WHERE p.PLACE_ID LIKE '%' || :keyword || '%'"
            + " OR p.NAME LIKE '%' || :keyword || '%'"
            + " OR c.COUNTY LIKE '%' || :keyword || '%'"
            + " OR p.CITY LIKE '%' || :keyword || '%'"
            + " OR c.ADDRESS LIKE '%' || :keyword || '%'"
            + " OR p.COORDINATES LIKE '%' || :keyword || '%'"
            + " OR c.PHONE_NUMBER LIKE '%' || :keyword || '%'"
            + " OR c.EMAIL LIKE '%' || :keyword || '%'"
            + " OR p.CATEGORY LIKE '%' || :keyword || '%'"
            + " OR c.SUBCATEGORY LIKE '%' || :keyword || '%'")
    public List<Place> search(@Param("keyword") String keyword); */

}