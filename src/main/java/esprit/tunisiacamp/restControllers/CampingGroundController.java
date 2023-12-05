package esprit.tunisiacamp.restControllers;

import esprit.tunisiacamp.entities.camping.CampingGround;
import esprit.tunisiacamp.services.ICampingGroundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CampingGroundController {
    @Autowired
    private ICampingGroundService campingGroundService;

    @PostMapping("/addCamping")
    public CampingGround ajouterCamping(@org.springframework.web.bind.annotation.RequestBody CampingGround c,@RequestParam String captcha) {

        return campingGroundService.ajouterCamping(c,captcha);
    }
    @PutMapping("updateCamp/{id}")
    public ResponseEntity<CampingGround> updateCampingGround(@PathVariable long id, @RequestBody CampingGround campingGround,@RequestParam String captcha) {
        Optional<CampingGround> existingCampingGround = campingGroundService.getCampingGroundById(id);
        if (existingCampingGround.isPresent()) {
            campingGround.setIdCampingGround(id);
            return ResponseEntity.ok(campingGroundService.ajouterCamping(campingGround,captcha));
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("getCamp")
    public List<CampingGround> getAllCampingGrounds() {
        return campingGroundService.getAllCampingGrounds();
    }

    @DeleteMapping("deleteCamp/{id}")
    public void  deleteCampingGround(@RequestParam long idCampingGround) {

        campingGroundService.deleteCampingGroundById(idCampingGround);

    }
    @PutMapping("AffecterCampingAdvantages")
    public String affecterCampingAuAdvantages(Long IdCamping, Long IdAdvantages){
        return campingGroundService.affecterCampingAuAdvantages(IdCamping,IdAdvantages);
    }
    @GetMapping("SerachCamp")
    public List<CampingGround> search(@RequestParam(required = false) String genre,@RequestParam(required = false) String location,@RequestParam(required = false) String name){
        return campingGroundService.search(genre,location,name);
    }
    @GetMapping("Captcha")
    public  String GenCaptcha() {
        return campingGroundService.GenCaptcha();
    }

}
