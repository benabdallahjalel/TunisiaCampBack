package esprit.tunisiacamp.services;

import esprit.tunisiacamp.entities.Activity;
import esprit.tunisiacamp.entities.User;
import esprit.tunisiacamp.entities.camping.Reservation;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Date;
import java.util.List;

public interface IReservationService {
    RedirectView saveReservation(Reservation reservation, Integer campingId, Integer userId);
    List<Reservation> getAllReservations();
    void deleteReservationById(long id);
    List<Reservation> getAllReservation(Integer userId);
    void affecterReservationToCamping (Integer reservationId , Integer campingId,Integer userId);
    public List<User> matchCampers(Integer reservationId);
    void sendSms();
    void sendReminderSms();
}
