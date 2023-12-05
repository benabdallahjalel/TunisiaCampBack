package esprit.tunisiacamp.repositories;

import esprit.tunisiacamp.entities.camping.Advantage;
import esprit.tunisiacamp.entities.camping.CampingGround;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CampingGroundRepositories extends CrudRepository<CampingGround,Long> {
    @Query(value = "select * from camping_ground c where lower(c.genre)=lower(?1) or LOWER(c.location)=LOWER(?2) or LOWER(c.name)=LOWER(?3)",nativeQuery=true)
    List<CampingGround> searcCamp(String genre, String location, String name);
}
