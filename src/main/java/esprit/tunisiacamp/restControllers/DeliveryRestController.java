package esprit.tunisiacamp.restControllers;

import esprit.tunisiacamp.entities.User;
import esprit.tunisiacamp.entities.shopping.Delivery;
import esprit.tunisiacamp.services.IDeliveryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name="Manager of Delivery")
public class DeliveryRestController {
    @Autowired
    IDeliveryService iDeliveryService;

    @PostMapping("/addDelivery")
    public void addDelivery(@RequestBody Delivery del) {
        iDeliveryService.addDelivery(del);
    }
    @GetMapping("/getdelivery")
    public List<Delivery> findDeliveriesByUser(long idUser){
        return iDeliveryService.findDeliveriesByUser(idUser);
    }
    @GetMapping("/retriveprice")
    public Float findPricesByIdDelivery(long idDelivery) {
        return iDeliveryService.findPricesByIdDelivery(idDelivery);
    }

    @PostMapping("/calculatePrice")
    public double calculatePrice(String dep,String des,long idDelivery){
        double P=iDeliveryService.calculatePrice(dep,des,idDelivery);
        return P;
    }
    @PutMapping("/affecter")
    public void assignDriverToDelivery(Long deliveryId, Long driverId){
         iDeliveryService.assignDriverToDelivery(deliveryId,driverId);
    }
    @GetMapping("/listdriverlocation")
    public List<String> findDeliveryLocationsForDrivers() {
        return iDeliveryService.findDeliveryLocationsForDrivers();
    }
    @PutMapping("/affecterdriver")
    public void assignDriverToDelivery(long idDelivery) {
        iDeliveryService.assignDriverToDeliverybydistance(idDelivery);
    }

}
