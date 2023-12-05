package esprit.tunisiacamp.repositories;

import esprit.tunisiacamp.entities.camping.Advantage;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdvantagesRepositories extends CrudRepository<Advantage,Long> {
    @Query(value = "select * from advantage a where a.type=?1",nativeQuery=true)
    List<Advantage> getAdvantagesByType(String type);
}
