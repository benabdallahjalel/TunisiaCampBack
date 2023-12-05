package esprit.tunisiacamp.services;

import esprit.tunisiacamp.entities.User;
import esprit.tunisiacamp.entities.camping.CampingGround;
import esprit.tunisiacamp.entities.camping.Favorite;
import esprit.tunisiacamp.repositories.CampingGroundRepositories;
import esprit.tunisiacamp.repositories.FavoriteRepositories;
import esprit.tunisiacamp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteService implements IFavoriteService{
    @Autowired
    private FavoriteRepositories favoriteRepositorie;
    @Autowired
    private CampingGroundRepositories campingGroundRepositories;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Favorite saveFavorite(Favorite favorite) {
        return favoriteRepositorie.save(favorite);      }

    @Override
    public List<Favorite> getAllFavorite() {
        return (List<Favorite>) favoriteRepositorie.findAll();
    }

    @Override
    public List<CampingGround> getMyFavorite(Long userId) {
        List<CampingGround> favoriteCampings = favoriteRepositorie.findFavoriteCampingsByUserId(userId);
        return favoriteCampings;
    }

    @Override
    public void deleteFavoriteById(long id) {
        favoriteRepositorie.deleteById(id);

    }

    @Override
    public void affecterFavorite(Integer favoriteId, Integer campingId, Integer userId) {
        Favorite fa = favoriteRepositorie.findById(Long.valueOf(favoriteId)).get();
        CampingGround cp = campingGroundRepositories.findById(Long.valueOf(campingId)).get();
        User us = userRepository.findById(userId).get();
        fa.setUser_fav(us);
        fa.setCampingG_fav(cp);
        favoriteRepositorie.save(fa);
    }
}
