package jy.dev.huddleup.model;

import java.util.Optional;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import jy.dev.huddleup.dto.ProfileRequestDto;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Column()
    private String position;

    private String jobGroup;

    private String skills;

    public Profile(User user) {
        this.user = user;
    }

    public Profile update(ProfileRequestDto dto) {
        this.position = Optional.ofNullable(dto.getPosition()).orElse(this.position);
        this.phoneNumber = Optional.ofNullable(dto.getPhone_number()).orElse(this.phoneNumber);
        this.portfolioUrl = Optional.ofNullable(dto.getPortfolio_url()).orElse(this.portfolioUrl);
        this.jobGroup = Optional.ofNullable(dto.getFields()).orElse(this.jobGroup);
        this.skills = Optional.ofNullable(dto.getSkills()).orElse(this.skills);
        this.availablePeriod = Optional.ofNullable(dto.getAvailable_period()).orElse(this.availablePeriod);
        this.availableTime = Optional.ofNullable(dto.getAvailable_time()).orElse(this.availableTime);
        this.faceToFace = Optional.ofNullable(dto.getFace_to_face()).orElse(this.faceToFace);
        this.careerPeriod = Optional.ofNullable(dto.getCareer_period()).orElse(this.careerPeriod);
        this.residence = Optional.ofNullable(dto.getResidence()).orElse(this.residence);

        return this;
    }
}
