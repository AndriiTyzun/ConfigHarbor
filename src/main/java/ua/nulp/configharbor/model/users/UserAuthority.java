package ua.nulp.configharbor.model.users;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "user_authority")
@NoArgsConstructor
@Getter
@Setter
public class UserAuthority implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userAuthorityId;

    private String authority;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User userId;

    public UserAuthority(String authority, User userId) {
        this.authority = authority;
        this.userId = userId;
    }

    public UserAuthority (String authority){
        this.authority = authority;
    }
}
