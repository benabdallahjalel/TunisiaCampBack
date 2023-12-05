package esprit.tunisiacamp.restControllers;

import esprit.tunisiacamp.services.IGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GroupController {
    @Autowired
    IGroupService iGroupService;
    @PutMapping
    public void acceptgroupe(@RequestParam Integer groupId){
        iGroupService.acceptgroupe(groupId);
    }
}
