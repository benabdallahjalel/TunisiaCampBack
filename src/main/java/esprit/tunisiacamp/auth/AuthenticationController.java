package esprit.tunisiacamp.auth;


import esprit.tunisiacamp.entities.Autority;
import esprit.tunisiacamp.entities.Role;
import esprit.tunisiacamp.entities.User;
import esprit.tunisiacamp.entities.enums.Provider;
import esprit.tunisiacamp.entities.enums.role;
import esprit.tunisiacamp.repositories.AutorityRepository;
import esprit.tunisiacamp.repositories.RoleRepository;
import esprit.tunisiacamp.repositories.UserRepository;
import esprit.tunisiacamp.services.UserIService;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Date;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuthenticationController {
  @Autowired
  UserRepository userRepository;
  private final AuthenticationService service;
  private final PasswordEncoder passwordEncoder;
  @Autowired
  RoleRepository roleRepository;
  @Autowired
  UserIService userIService;
  @Autowired
  AutorityRepository autorityRepository;

  @PostMapping("/register")
  public ResponseEntity<?> register(@RequestBody RegisterRequest request,HttpServletRequest req) throws MessagingException, UnsupportedEncodingException {
    if(userRepository.existsByEmail(request.getEmail())){
      return ResponseEntity.badRequest().body(new MessageResponse("Error: email is use!!!"));
    }
      User user = new User();
    user.setLastname(request.getLastname());
    user.setFirstname(request.getFirstname());
    user.setEmail(request.getEmail());
    user.setPassword(passwordEncoder.encode(request.getPassword()));
    user.setProdiver(Provider.Local);
    user.setLastC(false);
    user.setLastCnx(new Date());
    String rol = request.getRole();
    Autority a = new Autority();
    //role setRole;
    if(rol==null){
      return ResponseEntity.badRequest().body(new MessageResponse("Role IS NULL"));
    } else if(rol.equals("ADMIN")){
      Role r = roleRepository.getRole(role.ADMIN);
      if(r==null){
        return ResponseEntity.badRequest().body(new MessageResponse("Role ADMIN NOT FOUND"));
      }
              //.orElseThrow(()->new RuntimeException("Error: Role is not found."));
      user.setRole(r);
      a.setName("ADMIN");
    } else if (rol.equals("CAMPER")) {
      Role r = roleRepository.getRole(role.CAMPER);
      if(r==null){
        return ResponseEntity.badRequest().body(new MessageResponse("Role CAMPER NOT FOUND"));
      }
              //.orElseThrow(()->new RuntimeException("Error: Role is not found."));
      user.setRole(r);
      a.setName("CAMPER");
    }else if (rol.equals("MANAGER")) {
      Role r = roleRepository.getRole(role.MANAGER);
      if(r==null){
        return ResponseEntity.badRequest().body(new MessageResponse("Role MANAGER NOT FOUND"));
      }
              //.orElseThrow(()->new RuntimeException("Error: Role is not found."));
      user.setRole(r);
      a.setName("MANAGER");
    }else if (rol.equals("SHOP")) {
      Role r = roleRepository.getRole(role.SHOP);
      if(r==null){
        return ResponseEntity.badRequest().body(new MessageResponse("Role SHOP NOT FOUND"));
      }
             // .orElseThrow(()->new RuntimeException("Error: Role is not found."));
      user.setRole(r);
      a.setName("SHOP");
    }else if (rol.equals("DRIVER")) {
      Role r = roleRepository.getRole(role.DRIVER);
      if(r==null){

        return ResponseEntity.badRequest().body(new MessageResponse("Role DRINVER NOT FOUND"));
      }
             // .orElseThrow(()->new RuntimeException("Error: Role is not found."));
      user.setRole(r);
      a.setName("DRIVER");
    }else{
      Role r = roleRepository.getRole(role.CAMPER);
      /*if(r==null){
        return ResponseEntity.badRequest().body(new MessageResponse("Verifie le role"));
      }
              //.orElseThrow(()->new RuntimeException("Error: Role is not found."));
      user.setRole(r);
      a.setName("CAMPER");*/
      return ResponseEntity.badRequest().body(new MessageResponse("VERIFY THE ROLE "));
    }
    String randomCode = RandomString.make(64);
    user.setVerificationCode(randomCode);
    userRepository.save(user);
    //System.out.println(user.getRole().toString());
//    a.setName(user.getRole().toString());
    a.setUserAuth(user);
    autorityRepository.save(a);
    userIService.sendVerificationEmail(user,getSiteURL(req));
    return ResponseEntity.ok((new MessageResponse("registration successfully")));

  }
  private String getSiteURL(HttpServletRequest request) {
    String siteURL = request.getRequestURL().toString();
    return siteURL.replace(request.getServletPath(), "");
  }
  /*
  public ResponseEntity<AuthenticationResponse> register(@RequestBody User request) {
    return ResponseEntity.ok(service.register(request));
  }
*/

    @PostMapping("/authenticate")
    @CrossOrigin("http://localhost:4200")
  public Authentication authenticate(
      @RequestBody AuthenticationRequest request
  ) {
    User user = userRepository.getUserByUsername(request.getEmail());
    if(user!=null){
    user.setLastCnx(new Date());
    user.setLastC(false);
    userRepository.save(user);}
    return service.authenticate(request);
  }


}
