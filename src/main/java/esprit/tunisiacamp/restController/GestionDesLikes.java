package esprit.tunisiacamp.restController;

import esprit.tunisiacamp.entities.forum.Rating;
import esprit.tunisiacamp.services.IserviceLike;
import esprit.tunisiacamp.services.Iservices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GestionDesLikes {

@Autowired
    IserviceLike iserviceLike ;

    @PutMapping("LikerPost")
    void  likerpost (@RequestBody Rating l, @RequestParam long idPost, @RequestParam long idUser){
        iserviceLike.likerPost(l,idPost,idUser);
    }
    @GetMapping("AfficheLikes")
    public List<Rating> getlikess (){
        return iserviceLike.getLikes();
    }

    @DeleteMapping("SupprimerLike")
    void supprimerlike(@RequestParam long idlike ){
        iserviceLike.supprimerlike(idlike);
    }

    @PostMapping("ModifierRating")
    void modifrate(@RequestBody Rating rating){
        iserviceLike.modifrate(rating);
    }

    @GetMapping("AfficheLikesParposte")
    public List<Rating> getlikesss (@RequestParam  long  idPoste){
        return  iserviceLike.listelike(idPoste);
    }

}
