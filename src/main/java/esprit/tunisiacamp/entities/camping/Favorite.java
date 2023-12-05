package esprit.tunisiacamp.entities.camping;

import esprit.tunisiacamp.entities.Activity;
import esprit.tunisiacamp.entities.User;
import lombok.*;
import lombok.experimental.FieldDefaults;
import javax. persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity

public class Favorite implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    long idFavorite;
    @ManyToOne
    User user_fav;
    @ManyToOne
    CampingGround campingG_fav;
    @ManyToOne
    Activity activity_fav;
}
