package esprit.tunisiacamp.controller;

import esprit.tunisiacamp.model.Userr;
import esprit.tunisiacamp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/getall")
  public List<Userr> getall()  {
        List<Userr> userrs ;
     userrs =  userService.getall();
return userrs ;
    }

    @PostMapping("/add")
    void addUser(@RequestBody Userr userr){
        userService.addUser(userr);

    }

    @GetMapping("/getbyusername/{username}")
   void getUserByUserName(@PathVariable String username) {

            userService.getUserByUserName(username);

    }

}
