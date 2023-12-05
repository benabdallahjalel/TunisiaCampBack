package esprit.tunisiacamp.repositories;

import esprit.tunisiacamp.entities.camping.GroupCamp;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepositories extends CrudRepository<GroupCamp,Long> {
}
