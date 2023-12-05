package esprit.tunisiacamp.services;

import esprit.tunisiacamp.entities.Claim;
import esprit.tunisiacamp.entities.enums.Category;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface IClaimsService {
    void addclaim(Claim c);
    void addclaimsandaffecterUser(Claim c,long idUser);
    void modifyclaim(long idClaim,Claim c1);
    //void modifyetatclaimsbyadmin(long idUser,Claim cla1);
    void modifyetatclaimsbyadmin(long idUser,long idClaim);
    void deleteclaim(long idClaim);
    public List<Claim> retrieveclaimByUser(long idUser);
    // public  List<Claim> sortedClaimbysentiment(List<Claim> claims);
    List<Claim> sortedComplaintsBySentiment(List<Claim> complaints);
    public List<Claim> getAllClaims();
    List<Claim> getUnresolvedClaims();
    void processUnresolvedClaims(long adminId) ;
    //long ClaimProcessor(long adminId);
    List<Object[]> countClaimsByCategory();
    Map<String, Long> getClaimsByCategory();
    Map<Category, Long> countClaimsByCategoryBetweenDates(Date startDate, Date endDate);
}
