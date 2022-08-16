package jy.dev.huddleup.model;

import lombok.*;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Getter
@Entity
@DynamicUpdate
@ToString(of = {"id", "email", "username", "socialType"})
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
    private String socialType;

//    @BatchSize(size = 100)
//    @OneToOne(mappedBy = "user")
//    private ProfileEntity profile;

    @Builder
    public User(String email, String username, String socialType) {
        this.email = email;
        this.username = username;
        this.socialType = socialType;
    }

    public User(Long id){this.id = id;}
}
