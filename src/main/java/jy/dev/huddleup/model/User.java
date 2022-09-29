package jy.dev.huddleup.model;

import jy.dev.huddleup.security.oauth2.CustomOAuth2User;
import jy.dev.huddleup.util.UserRole;
import jy.dev.huddleup.util.Social;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Getter
@Entity
@Builder
@Table(name = "_user")
@DynamicUpdate
@ToString(of = {"id", "email", "username", "social"})
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String socialProviderKey;

    @NonNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Social social;

    @NonNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

//    @BatchSize(size = 100)
//    @OneToOne(mappedBy = "user")
//    private ProfileEntity profile;

    public User update(CustomOAuth2User oAuth2User){
        this.email = oAuth2User.getEmail();
        this.username = oAuth2User.getName();

        return this;
    }


    public User(Long id){this.id = id;}
}
