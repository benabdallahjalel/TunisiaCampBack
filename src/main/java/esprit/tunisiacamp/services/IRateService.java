package esprit.tunisiacamp.services;

import esprit.tunisiacamp.entities.camping.Rate;

import java.util.List;

public interface IRateService {
     void likerCamp(Rate r, long idCamp, long idUser);

     List<Rate> getLikes();

    void deletelike(long idlike);

     void modifierrate(Rate rating);

    boolean chercheuser(Integer iduser,Integer idcamp);
}
