package esprit.tunisiacamp.restController;

import esprit.tunisiacamp.entities.User;
import esprit.tunisiacamp.entities.forum.*;
import esprit.tunisiacamp.services.Iservices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class Restcontroller {
    @Autowired
    Iservices iservices ;

    @DeleteMapping("Supprimeruser")
    void suppuser (@RequestParam long iduser){
        iservices.supprimeruser(iduser);
    }


@PostMapping("AjouterMotsInterdit")
    void ajouter(@RequestBody List<Mots>mots){
        iservices.ajoutermots(mots);
}






}
