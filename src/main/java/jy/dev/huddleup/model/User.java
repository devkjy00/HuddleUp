package jy.dev.huddleup.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import jy.dev.huddleup.security.oauth2.CustomOAuth2User;
import jy.dev.huddleup.util.Social;
import jy.dev.huddleup.util.UserRole;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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


    @BatchSize(size = 100)
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Profile profile;

    public User update(CustomOAuth2User oAuth2User) {
        this.email = oAuth2User.getEmail();
        this.username = oAuth2User.getName();

        return this;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public User(Long id) {
        this.id = id;
    }
}
