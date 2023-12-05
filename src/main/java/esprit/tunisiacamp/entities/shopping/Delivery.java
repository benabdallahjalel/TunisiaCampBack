package esprit.tunisiacamp.entities.shopping;

import lombok.*;
import lombok.experimental.FieldDefaults;
import javax. persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity

public class Delivery implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    long idDelivery;
    String location;
    @Temporal(TemporalType.DATE)
    Date delivery_date;
    float weight;
    Boolean done;
    @OneToMany(mappedBy = "delivery")
    List<Transaction> shipments;
    @ManyToOne
    Driver driver;
}
