package esprit.tunisiacamp.repositories;

import esprit.tunisiacamp.entities.camping.Review;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepositories extends CrudRepository<Review,Long> {
}
