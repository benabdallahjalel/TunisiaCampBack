package esprit.tunisiacamp.restcontrollers;

import esprit.tunisiacamp.entities.shopping.Critique;
import esprit.tunisiacamp.services.CritiqueIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CritiqueRestController {
    @Autowired
    CritiqueIService critiqueservice;

    @PostMapping("/critique/addcritique")
    public Critique addCritique(@RequestBody Critique critique, @RequestParam long tool_id){return critiqueservice.addCritique(critique,tool_id);}
    @DeleteMapping("/critique/deletecritique")
    public void deleteCritique(@RequestBody Critique critique){critiqueservice.deleteCritique(critique);}
    @PutMapping("/critique/updatecritique")
    public Critique updateCritique(@RequestBody Critique critique){return critiqueservice.updateCritique(critique);}
    @GetMapping("/critique/getcritiqueoftool")
    public List<Critique> getCritiqueOfTool(@RequestParam long tool_id){return critiqueservice.getCritiquesOfTool(tool_id);}
}
