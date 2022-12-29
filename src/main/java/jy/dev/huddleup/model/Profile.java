package jy.dev.huddleup.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Entity
@Builder
@Table(name = "profile")
@DynamicUpdate
@DynamicInsert
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Profile {

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID")
    private User user;

    @Column()
    private String phoneNumber;

    @Column()
    private String imageUrl;

    @Column()
    private String portfolioUrl;

    @Column()
    private String availablePeriod;

    @Column()
    private String availableTime;

    @Column()
    private Boolean faceToFace;

    @Column()
    private String careerPeriod;

    @Column()
    private String residence;

    public Profile(Long id) {
        this.id = id;
    }

}
