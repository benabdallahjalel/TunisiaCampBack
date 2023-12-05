package esprit.tunisiacamp.Camping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CampingRestController {
    @Autowired
    CampingIService campingIService;
    @CrossOrigin("http://localhost:4200")
    @GetMapping("/AllCamp")
    public List<Camping> listCamping(){
        List<Camping> camp = campingIService.AllCamp();
        return  camp;
    }

    @PreAuthorize("hasAnyAuthority('CAMPER')")
    @PostMapping("/addCamping")
    public  void  addCamping(@RequestBody Camping c){
        campingIService.addCamping(c);
        //return null;
    }
    @PreAuthorize("hasAnyAuthority('CAMPER')")
    @GetMapping("/searchCamp")
    public List<Camping> searsh(){
        return campingIService.searchCamping();
    }
    //@PreAuthorize("hasAnyAuthority('CAMPER')")
    @PutMapping("/reserveCamp/{idcrea}/{idCam}")
    public String reserveCamp(@PathVariable("idcrea") Integer idCreateur,@PathVariable("idCam") Integer IdgroupCamp){
        return campingIService.reserveCamp(idCreateur, IdgroupCamp);
    }
}
