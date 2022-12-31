package jy.dev.huddleup.model;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Getter
@Setter
@Entity
@Builder
@DynamicInsert
@DynamicUpdate
@AllArgsConstructor
@Table(name = "recruit_post")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RecruitPost {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @BatchSize(size = 100)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "recruitPost", orphanRemoval = true, cascade = CascadeType.ALL)
//    private List<RecruitPostTagEntity> recruitPostTag = new ArrayList<>();

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "recruitPost", orphanRemoval = true)
//    private List<ApplicantEntity> applicants = new ArrayList<>();


    @Column(nullable = false)
    private String title;

    @Lob
    @Column(nullable = false)
    private String body;


    //    @ColumnDefault("0")
    private Integer requiredDevelopers;

    //    @ColumnDefault("0")
    private Integer requiredDesigners;

    //    @ColumnDefault("0")
    private Integer requiredProjectManagers;

    private LocalDate projectStartTime;

    private LocalDate projectEndTime;

    private LocalDate recruitDueTime;

    //    @ColumnDefault("")
    private String imageUrl;

    //    @ColumnDefault("true")
    private Boolean isOpened;


}
