package esprit.tunisiacamp.services;

import esprit.tunisiacamp.entities.User;
import esprit.tunisiacamp.entities.camping.CampingGround;
import esprit.tunisiacamp.entities.camping.Reservation;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ICampingGroundService {
    CampingGround ajouterCamping(CampingGround campingGrounds,String captcha);
    List<CampingGround> getAllCampingGrounds();
    Optional<CampingGround> getCampingGroundById(long id);
    void deleteCampingGroundById(long id);
    public String affecterCampingAuAdvantages(Long IdCamping,Long IdAdvantages);
    List<CampingGround> search(String genre,String location,String name);
    String GenCaptcha();

}
