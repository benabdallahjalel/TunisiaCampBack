package esprit.tunisiacamp.restControllers;

import esprit.tunisiacamp.entities.Activity;
import esprit.tunisiacamp.entities.User;
import esprit.tunisiacamp.entities.camping.Reservation;
import esprit.tunisiacamp.services.IReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@RestController
public class ReservationController {
    @Autowired
    private IReservationService reservationService;
    @PostMapping("AddReservation")
    public RedirectView createReservation(@RequestBody Reservation reservation, @RequestParam int campingId, @RequestParam int userId) {
        return reservationService.saveReservation(reservation,campingId,userId);}
    @PutMapping("affecterReservationToCamping")
    public void affecterReservationToCamping(@RequestParam int idr, @RequestParam int idc, @RequestParam int idu){
        // System.out.println("job");
        reservationService.affecterReservationToCamping(idr,idc,idu);
    }
    @CrossOrigin("http://localhost:4200")
    @GetMapping("getReservation")
    public List<Reservation> getAllReservation(Integer userId) {
        return reservationService.getAllReservation(userId);}
    @DeleteMapping("deletereservation/{id}")
    public void deleteReservationById(@RequestParam long id){
        reservationService.deleteReservationById(id);
    }

    @GetMapping("MatchGroup")
    public List<User> matchCampers(Integer reservationId){
        return reservationService.matchCampers(reservationId);
    }
    //@Scheduled(cron = "5 * * * * *")
    @PostMapping("sms")
    public void sendSms(){
        reservationService.sendReminderSms();
    }
}
