package esprit.tunisiacamp.entities.camping;

import com.fasterxml.jackson.annotation.JsonIgnore;
import esprit.tunisiacamp.entities.Activity;
import esprit.tunisiacamp.entities.User;
import esprit.tunisiacamp.entities.enums.Genre;
import esprit.tunisiacamp.entities.forum.Rating;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Cascade;

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

public class CampingGround implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    long idCampingGround;
    String name;
    String image;
    String location;
    String description;
    int capacity;
    int prix;
    int nbreservation;
    @Enumerated(EnumType.STRING)
    Genre genre;
    @OneToMany(mappedBy = "campingGround")
    @JsonIgnore
    List<Reservation> reservations;
    @OneToMany(mappedBy = "managed_ground")
    @JsonIgnore
    List<User> managers;
    @ManyToMany
    @JsonIgnore
    List<Activity> activities;
    @ManyToMany
    @JsonIgnore
    List<Advantage> advantages;
    @OneToMany(mappedBy = "review_Ground")
    @JsonIgnore
    List<Review> reviews;
    @OneToMany(mappedBy = "campingG_fav")
    @JsonIgnore
    List<Favorite> favorites;
    @OneToMany(mappedBy = "camp_rate")
    @JsonIgnore
    List<Rate> campingGrounds;

}
