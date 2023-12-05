package esprit.tunisiacamp.Camping;

import esprit.tunisiacamp.entities.User;
import esprit.tunisiacamp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

@Service
public class CampingService implements CampingIService{

    @Autowired
    CampingRepository campingRepository;
    @Autowired
    UserRepository userRepository;
    @Override
    public List<Camping> AllCamp() {
        return (List<Camping>) campingRepository.findAll();
    }

    @Override
    public void addCamping(Camping c) {
        campingRepository.save(c);

    }

    @Override
    public List<Camping> searchCamping() {
        List<Camping> c = (List<Camping>) campingRepository.findAll();
        List<Camping> c1 =  new ArrayList<>();
        for (Camping c2 : c){
            if(c2.getCheckInDate().after(new Date())){
                c1.add(c2);
            }
        }
        return c1;
    }

    @Override
    public String reserveCamp(Integer idCreateur, Integer idCamp) {
        Camping c = campingRepository.findById(idCamp).get();
        User u = userRepository.findById(idCreateur).get();
        {
            if (c.getCheckInDate().before(new Date())) {
                return "the date is expired";
            } else if (c.getGroupSize() == 0) {
                return "the groupe full";
            }
            c.setCreateur(u);
            c.setGroupSize(c.getGroupSize() - 1);
            campingRepository.save(c);
            u.setCampingGroupe(c);
            userRepository.save(u);

            return "successfully reservation";
        }
    }
}
