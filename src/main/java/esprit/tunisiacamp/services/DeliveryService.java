package esprit.tunisiacamp.services;

import esprit.tunisiacamp.entities.Role;
import esprit.tunisiacamp.entities.User;
import esprit.tunisiacamp.entities.enums.role;
import esprit.tunisiacamp.entities.shopping.Delivery;
import esprit.tunisiacamp.repositories.DeliveryRepository;
import esprit.tunisiacamp.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.apache.lucene.util.SloppyMath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@AllArgsConstructor
@Service
public class DeliveryService implements IDeliveryService {
    @Autowired
    DeliveryRepository deliveryRepository;
    @Autowired
    UserRepository userRepository;
    @Override
    public void addDelivery(Delivery del) {
        deliveryRepository.save(del);
    }

    @Override
    public List<Delivery> findDeliveriesByUser(long idUser) {

        return deliveryRepository.findDeliveriesByUser(idUser) ;
    }
    public double latitude(String city) {
        double lat=0;
        String[] Cities = new String[24];
        double[] Latitudes = new double[24];
        Latitudes[0]=36.8665;
        Latitudes[1]=36.7333;
        Latitudes[2]=36.7435;
        Latitudes[3]=37.2768;
        Latitudes[4]=33.8881;
        Latitudes[5]=34.4311;
        Latitudes[6]=36.5072;
        Latitudes[7]=35.6712;
        Latitudes[8]=35.1723;
        Latitudes[9]=33.7072;
        Latitudes[10]=36.1680;
        Latitudes[11]=35.5024;
        Latitudes[12]=36.8093;
        Latitudes[13]=33.3399;
        Latitudes[14]=35.7643;
        Latitudes[15]=36.4513;
        Latitudes[16]=34.7398;
        Latitudes[17]=35.0354;
        Latitudes[18]=36.0887;
        Latitudes[19]=35.8245;
        Latitudes[20]=32.9211;
        Latitudes[21]=33.9185;
        Latitudes[22]=36.8065;
        Latitudes[23]=36.4091;
        Cities[0] = "Ariana";
        Cities[1] = "Beja";
        Cities[2] = "Ben Arous";
        Cities[3] = "Bizerte";
        Cities[4] = "Gabes";
        Cities[5] = "Gafsa";
        Cities[6] = "Jendouba";
        Cities[7] = "Kairouan";
        Cities[8] = "Kassrine";
        Cities[9] = "Kebili";
        Cities[10] = "Kef";
        Cities[11] = "Mahdia";
        Cities[12] = "Manouba";
        Cities[13] = "Mednine";
        Cities[14] = "Monastir";
        Cities[15] = "Nabeul";
        Cities[16] = "Sfax";
        Cities[17] = "Sidi Bouzid";
        Cities[18] = "Siliana";
        Cities[19] = "Sousse";
        Cities[20] = "Tataouine";
        Cities[21] = "Tozeur";
        Cities[22] = "Tunis";
        Cities[23] = "Zaghouan";
        for (int i = 0; i < 24; i++) {
            if (Cities[i].equals(city))
                lat = Latitudes[i];
        }
        return lat;
    }
    public double longitude(String city){
        double lon = 0;
        String[] Cities = new String[24];
        Cities[0] = "Ariana";
        Cities[1] = "Beja";
        Cities[2] = "Ben Arous";
        Cities[3] = "Bizerte";
        Cities[4] = "Gabes";
        Cities[5] = "Gafsa";
        Cities[6] = "Jendouba";
        Cities[7] = "Kairouan";
        Cities[8] = "Kassrine";
        Cities[9] = "Kebili";
        Cities[10] = "Kef";
        Cities[11] = "Mahdia";
        Cities[12] = "Manouba";
        Cities[13] = "Mednine";
        Cities[14] = "Monastir";
        Cities[15] = "Nabeul";
        Cities[16] = "Sfax";
        Cities[17] = "Sidi Bouzid";
        Cities[18] = "Siliana";
        Cities[19] = "Sousse";
        Cities[20] = "Tataouine";
        Cities[21] = "Tozeur";
        Cities[22] = "Tunis";
        Cities[23] = "Zaghouan";
        double[] longitudes = new double[24];
        longitudes[0]=10.1647;
        longitudes[1]=9.1844;
        longitudes[2]=10.2320;
        longitudes[3]=9.8642;
        longitudes[4]=10.0975;
        longitudes[5]=8.7757;
        longitudes[6]=8.7757;
        longitudes[7]=10.1005;
        longitudes[8]=8.8308;
        longitudes[9]=8.9715;
        longitudes[10]=8.7096;
        longitudes[11]=11.0457;
        longitudes[12]=10.0863;
        longitudes[13]=10.4959;
        longitudes[14]=10.8113;
        longitudes[15]=10.7357;
        longitudes[16]=10.7600;
        longitudes[17]=9.4839;
        longitudes[18]=9.3645;
        longitudes[19]=10.6346;
        longitudes[20]=10.4509;
        longitudes[21]=8.1229;
        longitudes[22]=10.1815;
        longitudes[23]=10.1423;
        for (int i = 0; i < 24; i++) {
            if (Cities[i].equals(city))
                lon = longitudes[i];
        }
        return lon;
    }
    public double calculateDistanceCity(String dep,String des) {
        double lat1=Math.toRadians(latitude(dep));
        double long1=longitude(dep);
        double lat2=latitude(des);
        double long2=longitude(des);
        double dist = SloppyMath.haversinKilometers(lat1, long1, lat2, long2);
        return dist;
    }

    @Override
    public Float findPricesByIdDelivery(long idDelivery) {
        return deliveryRepository.findPricesByIdDelivery(idDelivery);
    }

    @Override
    public double calculatePrice(String dep,String des,long idDelivery){
        double price =deliveryRepository.findPricesByIdDelivery(idDelivery);
        double lat1=latitude(dep);
        double long1=longitude(dep);
        double lat2=latitude(des);
        double long2=longitude(des);
        double dist = SloppyMath.haversinKilometers(lat1, long1, lat2, long2);
        if(dist>10 && dist<150) {
            price+=dist*0.086;}
        else if (dist>150) {
            price+=dist*0.071;
        }
        else
            price+=0.850;
        return price;
    }
    @Override
    public List<String> findDeliveryLocationsForDrivers() {
        return deliveryRepository.findDriverLocations();
    }
    @Override
    public void assignDriverToDelivery(Long deliveryId, Long driverId) {
        User driver = userRepository.findById(Math.toIntExact(driverId)).get();
        if (driver != null && driver.getRole().getRole() == role.DRIVER) {
            Delivery delivery = deliveryRepository.findById(deliveryId).get();
            driver.getDeliveryList().add(delivery);
            deliveryRepository.save(delivery);
        }
    }
    @Override
    public void assignDriverToDeliverybydistance(long idDelivery) {
        List<String> driverLocations = deliveryRepository.findDriverLocations();
        Delivery delivery1=deliveryRepository.findById(idDelivery).get();
        String deliveryLocation = delivery1.getLocation();
        double minDistance = Double.MAX_VALUE;
        User closestDriver = null;
        for (String driverLocation : driverLocations) {
            double distance = calculateDistanceCity(driverLocation, deliveryLocation);
            if (distance < minDistance) {
                minDistance = distance;
                //closestDriver = (User) userRepository.findByAddressAndRoleRole(driverLocation, role.DRIVER);
                List<User> drivers = userRepository.findByAddressAndRoleRole(driverLocation, role.DRIVER);
                if (!drivers.isEmpty()) {
                    closestDriver = drivers.get(0);
                }
            }
        }
        if (closestDriver != null) {
            // delivery.g(closestDriver);
            driverLocations.add(String.valueOf(delivery1));
            deliveryRepository.save(delivery1);
        }
    }
}


