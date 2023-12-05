package esprit.tunisiacamp.restControllers;

import esprit.tunisiacamp.entities.camping.CampingGround;
import esprit.tunisiacamp.entities.camping.Favorite;
import esprit.tunisiacamp.services.IFavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class FavoriteController {
    @Autowired
    private IFavoriteService iFavoriteService;
    @PostMapping("savefavorite")
    public Favorite saveFavorite(Favorite favorite) {
        return iFavoriteService.saveFavorite(favorite);      }
    @PreAuthorize("hasAnyAuthority('CAMPER','MANAGER','SHOP','ADMIN')")
    @GetMapping("getAllfavorite")
    public List<Favorite> getAllFavorite() {
        return iFavoriteService.getAllFavorite();
    }

    @PutMapping("affecterfavorite")
    public void affecterFavorite(@RequestParam Integer favoriteId, @RequestParam Integer campingId, @RequestParam Integer userId){
        iFavoriteService.affecterFavorite(favoriteId,campingId,userId);
    }
    @PreAuthorize("hasAnyAuthority('CAMPER','MANAGER','SHOP','ADMIN')")
    @GetMapping("/MyFavorite/{id}")
    public List<CampingGround> getMyFavorite(@PathVariable("id") Long userId, HttpServletRequest request){
        HttpSession session = request.getSession();
        Long iduser = (Long) session.getAttribute("iduser");
        System.out.println(iduser);
        return iFavoriteService.getMyFavorite(iduser);

    }

    @DeleteMapping("deletFavorite/{id}")
    public void deleteFavoriteById(@RequestParam long id) {
        iFavoriteService.deleteFavoriteById(id);

    }
}
