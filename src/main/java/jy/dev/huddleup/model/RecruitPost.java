package jy.dev.huddleup.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import jy.dev.huddleup.dto.recruitpost.RecruitPostRequestDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@Entity
@Builder
@DynamicInsert
@AllArgsConstructor
@Table(name = "recruit_post")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RecruitPost extends TimeStamp {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @BatchSize(size = 100)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @Builder.Default
    @BatchSize(size = 100)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "recruitPost", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<RecruitPostTag> recruitPostTags = new ArrayList<>();

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "recruitPost", orphanRemoval = true)
//    private List<ApplicantEntity> applicants = new ArrayList<>();


    @Column(nullable = false)
    private String title;

    @Lob
    @Column(nullable = false)
    private String body;

    private Integer requiredDevelopers;

    private Integer requiredDesigners;

    private Integer requiredProjectManagers;

    private LocalDate projectStartTime;

    private LocalDate projectEndTime;

    private LocalDate recruitDueTime;

    //    @ColumnDefault("")
    private String imageUrl;

    //    @ColumnDefault("true")
    private Boolean isOpened;

    public RecruitPost(Long id) {
        this.id = id;
    }

    public List<RecruitPostTag> addTag(Long tagId) {
        RecruitPostTag recruitPostTag = new RecruitPostTag(id, tagId);
        recruitPostTags.add(recruitPostTag);

        return recruitPostTags;
    }

    public void update(RecruitPostRequestDto dto) {

        this.title = Optional.ofNullable(dto.getTitle()).orElse(this.title);
        this.body = Optional.ofNullable(dto.getBody()).orElse(this.body);
        this.recruitDueTime = Optional.ofNullable(dto.getRecruitDueTime()).orElse(this.recruitDueTime);
//        this.imageUrl = Optional.ofNullable(dto.()).orElse(this.imageUrl);
        this.title = Optional.ofNullable(dto.getTitle()).orElse(this.title);
        this.title = Optional.ofNullable(dto.getTitle()).orElse(this.title);
        this.title = Optional.ofNullable(dto.getTitle()).orElse(this.title);
        this.title = Optional.ofNullable(dto.getTitle()).orElse(this.title);
        this.title = Optional.ofNullable(dto.getTitle()).orElse(this.title);

    }
}
