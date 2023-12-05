package esprit.tunisiacamp.repositories;

import esprit.tunisiacamp.entities.Role;
import esprit.tunisiacamp.entities.enums.role;
import esprit.tunisiacamp.entities.shopping.Delivery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeliveryRepository extends CrudRepository<Delivery,Long> {
    @Query("SELECT t.delivery FROM Transaction t WHERE t.shopper.idUser = :idUser")
    List<Delivery> findDeliveriesByUser(long idUser);

   @Query("SELECT t.price FROM Transaction t INNER JOIN t.delivery d WHERE d.idDelivery = :idDelivery")
   Float findPricesByIdDelivery(@Param("idDelivery") Long idDelivery);
   /* @Query("SELECT d.location FROM Delivery d JOIN d.shipments t JOIN t.shopper u WHERE u.role.role = :role")
    List<String> findDeliveryLocationsForDrivers(@Param("role") role driverRole);*/
   @Query("SELECT DISTINCT u.address FROM User u WHERE u.role.role = 'DRIVER'")
   List<String> findDriverLocations();
}
