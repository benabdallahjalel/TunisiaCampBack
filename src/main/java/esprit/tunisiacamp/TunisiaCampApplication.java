package esprit.tunisiacamp;

<<<<<<< HEAD
import esprit.tunisiacamp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
=======
import esprit.tunisiacamp.entities.shopping.Tool;
>>>>>>> Iskandar
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@SpringBootApplication
@EnableScheduling
public class TunisiaCampApplication {
    public static void main(String[] args) {
        SpringApplication.run(TunisiaCampApplication.class, args);


    }


}
