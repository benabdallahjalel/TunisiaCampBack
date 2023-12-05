package esprit.tunisiacamp.services;

import esprit.tunisiacamp.entities.camping.Advantage;

import java.util.List;

public interface IAdvantageService {
    Advantage saveAdvantage(Advantage advantage);
    List<Advantage> getAllAdvantage(String type);
    void deleteAdvantageById(long id);
}
