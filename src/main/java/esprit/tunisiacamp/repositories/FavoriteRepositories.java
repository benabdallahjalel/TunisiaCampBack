package esprit.tunisiacamp.repositories;

import esprit.tunisiacamp.entities.camping.CampingGround;
import esprit.tunisiacamp.entities.camping.Favorite;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteRepositories extends CrudRepository<Favorite,Long> {
    @Query(value = "SELECT f.campingg_fav_id_camping_ground FROM favorite f WHERE f.user_fav_id_user = ?1", nativeQuery = true)
    List<CampingGround> findFavoriteCampingsByUserId(Long userId);
}
