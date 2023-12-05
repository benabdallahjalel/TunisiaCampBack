package esprit.tunisiacamp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
<<<<<<< HEAD
import esprit.tunisiacamp.Camping.Camping;
=======
>>>>>>> Iskandar
import esprit.tunisiacamp.entities.camping.CampingGround;
import esprit.tunisiacamp.entities.enums.role;
import esprit.tunisiacamp.entities.forum.*;
import esprit.tunisiacamp.entities.forum.Post;
<<<<<<< HEAD
import esprit.tunisiacamp.entities.shopping.Delivery;
=======
import esprit.tunisiacamp.entities.shopping.Tool;
>>>>>>> Iskandar
import esprit.tunisiacamp.entities.shopping.Transaction;
import lombok.*;
import lombok.experimental.FieldDefaults;
import javax. persistence.*;
import java.io.Serializable;
import java.util.*;

import esprit.tunisiacamp.entities.enums.Provider;
import esprit.tunisiacamp.entities.enums.State;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "_user")
public class User implements Serializable, UserDetails {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
<<<<<<< HEAD
    Integer idUser;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
=======
    long idUser;
    @JsonIgnore
    @ManyToOne
>>>>>>> Iskandar
    Role role;

    String username;
    String password;
    String firstname;
    String lastname;
    @Temporal(TemporalType.DATE)
    Date birthday;
    String email;
    int telephone;
    int cin;
    String  picture;
    //region shop_part
    String shopname;
    int shop_phone;
    int postal_code;
    String shop_address;
    String  shop_picture;
    //endregion
    @Enumerated(EnumType.STRING)
    Provider  prodiver;
    @Enumerated(EnumType.STRING)
    State state;
    String verificationCode;
    boolean enable;
<<<<<<< HEAD

    String verifiepwd;
    @Temporal(TemporalType.DATE)
    Date lastCnx;
    Boolean lastC;

    Boolean availability;
    @JsonIgnore
    @ManyToMany
    List<ChatRoom> chatRooms;

    @OneToMany (mappedBy = "user", cascade = CascadeType.REMOVE)

=======
    @JsonIgnore
    @ManyToMany
    List<ChatRoom> chatRooms;
>>>>>>> Iskandar
    @JsonIgnore
    @OneToMany

    List<Post> posts;
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    List<Claim> my_claims;
    @JsonIgnore
    @OneToMany(mappedBy = "admin")
    List<Claim> admin_claims;
    @JsonIgnore
    @ManyToOne
    CampingGround managed_ground;
    @JsonIgnore
    @OneToMany(mappedBy = "shopper")
    List<Transaction> transactions;
    @JsonIgnore
    @ManyToMany
    List<Tool> my_tools;


    @OneToMany(mappedBy ="userreponse")
    List<Reponse> reponseListuser ;

    @ManyToOne()
    User shopper;
    @OneToMany(mappedBy = "shopper")
    List<User> drivers;
    @OneToMany
    List<Delivery> deliveryList;
    @OneToMany(mappedBy = "UserAuth" ,fetch = FetchType.EAGER)


    private Set<Autority> autority;
    @JsonIgnore
    @OneToMany(mappedBy = "createur")
    private List<Camping> campingList;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Camping campingGroupe;

    public Set<Autority> getAuthFromBase(){
        return this.autority;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>() ;
        for (Autority authority : autority) {
            if (authority !=null)
                authorities.add(new SimpleGrantedAuthority(authority.getName()));
            else
                System.out.println("----- U have no AUtority Bro ----");
        }
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    @Override
    public String getUsername() {
        return email;
    }
    @Override
    public String getPassword() {
        return password;
    }


    public void addChatroom(ChatRoom chatroom) {
    }
}
