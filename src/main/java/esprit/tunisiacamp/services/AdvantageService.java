package esprit.tunisiacamp.services;

import esprit.tunisiacamp.entities.camping.Advantage;
import esprit.tunisiacamp.repositories.AdvantagesRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdvantageService implements IAdvantageService{
    @Autowired
    private AdvantagesRepositories advantagesRepositories;

    @Override
    public Advantage saveAdvantage(Advantage advantage) {
        return advantagesRepositories.save(advantage);
    }

    @Override
    public List<Advantage> getAllAdvantage(String type) {
        return advantagesRepositories.getAdvantagesByType(type);
    }

    @Override
    public void deleteAdvantageById(long id) {
        advantagesRepositories.deleteById(id);
    }
}
