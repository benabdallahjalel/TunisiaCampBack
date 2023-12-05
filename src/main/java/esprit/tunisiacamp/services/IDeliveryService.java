package esprit.tunisiacamp.services;

import esprit.tunisiacamp.entities.User;
import esprit.tunisiacamp.entities.shopping.Delivery;

import java.util.List;

public interface IDeliveryService {
    void addDelivery(Delivery del);
    List<Delivery> findDeliveriesByUser(long idUser);
    public double latitude(String city);
    public double longitude(String city);
    public double calculateDistanceCity(String dep,String des);
    Float findPricesByIdDelivery(long idDelivery);
    public double calculatePrice(String dep,String des,long idDelivery);
    public void assignDriverToDelivery(Long deliveryId, Long driverId);

    List<String> findDeliveryLocationsForDrivers();
    public void assignDriverToDeliverybydistance(long idDelivery) ;
}
