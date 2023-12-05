package esprit.tunisiacamp.restControllers;

import esprit.tunisiacamp.entities.camping.Rate;
import esprit.tunisiacamp.services.IRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RateController {
    @Autowired
    IRateService iRateService;

    @PutMapping("LikerCamp")
    void  likerpost (@RequestBody Rate r, @RequestParam long idCamp, @RequestParam long idUser){
        iRateService.likerCamp(r,idCamp,idUser);
    }
    @GetMapping("AfficheRate")
    public List<Rate> getlikess (){
        return iRateService.getLikes();
    }

    @DeleteMapping("SupprimerLike")
    void supprimerlike(@RequestParam long idlike ){
        iRateService.deletelike(idlike);
    }
}
