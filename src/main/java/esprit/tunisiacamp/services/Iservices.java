package esprit.tunisiacamp.services;

import esprit.tunisiacamp.entities.User;
import esprit.tunisiacamp.entities.forum.*;
import org.springframework.stereotype.Service;

import java.util.List;


public interface Iservices {

    void supprimeruser(long iduser);



    void ajoutermots(List<Mots> mots);

    boolean contientMotInterdit(String ContentPost);
}