package esprit.tunisiacamp.Camping;

import java.util.Date;
import java.util.List;

public interface CampingIService {

    public List<Camping> AllCamp();
    public void addCamping(Camping c);
    public List<Camping> searchCamping();
    public String reserveCamp(Integer idCreateur,Integer idCamp);
}
