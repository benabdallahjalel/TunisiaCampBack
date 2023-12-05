package esprit.tunisiacamp.restControllers;

import esprit.tunisiacamp.entities.Claim;
import esprit.tunisiacamp.entities.enums.Category;
import esprit.tunisiacamp.repositories.ClaimRepository;
import esprit.tunisiacamp.services.IClaimsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@Tag(name="Manager of Claim")
@RequestMapping("/api/claims")
public class ClaimRestController {
    @Autowired
    IClaimsService iClaimsService;
//@Autowired
    //ClaimRepository claimRepository;

    @PostMapping("/addclaim")
    public void addclaim(@RequestBody Claim c){
        iClaimsService.addclaim(c);
    }
    @PostMapping("/addclaimtouser")
    public void addclaimtouser(@RequestBody Claim c,@RequestParam long idUser){

        iClaimsService.addclaimsandaffecterUser(c,idUser);
    }
    @PutMapping("/updateclaim")
    public  void modifyclaim(long idClaim,@RequestBody Claim c1){

        iClaimsService.modifyclaim(idClaim, c1);
    }
    @PutMapping("/modifyetat")
    public void modifyetatclaimsbyadmin(@RequestParam long idUser,long idClaim) {
        iClaimsService.modifyetatclaimsbyadmin(idUser, idClaim);
    }
    @DeleteMapping("/deleteclaimbyId")
    public void supprimerclaimAvecId(@RequestParam long idClaim){

        iClaimsService.deleteclaim(idClaim);
    }
    @GetMapping("/retriveclaimbyuser")
    public List<Claim> retrieveclaimByUser(long idUser){

        return iClaimsService.retrieveclaimByUser(idUser);
    }
    @GetMapping("/sentiment")
    public List<Claim> getClaimsSortedBySentiment() {
        //List<Claim> claim = (List<Claim>) claimRepository.findAll();
        List<Claim> claim=iClaimsService.getAllClaims();
        return iClaimsService.sortedComplaintsBySentiment(claim);
    }
    @GetMapping("/retriveallclaims")
    public List<Claim>  retriveclaims(){
        return iClaimsService.getAllClaims();
    }
    @PutMapping("/resolveclaims")
    public void processUnresolvedClaims(long adminId) {
        iClaimsService.processUnresolvedClaims(adminId);
    }
    @GetMapping("/claims/count-by-category")
    public List<Object[]> countByCategory() {
        return iClaimsService.countClaimsByCategory();
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/category")
    public Map<String, Long> getClaimsByCategory() {
        return iClaimsService.getClaimsByCategory();
    }
   /* @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/NumberBetweenDate")
    public Map<Category, Long> countClaimsByCategoryBetweenDates(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd")Date startDate, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd")Date endDate) {
    return iClaimsService.countClaimsByCategoryBetweenDates(startDate,endDate);
    }*/
}
