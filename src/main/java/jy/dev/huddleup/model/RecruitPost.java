package jy.dev.huddleup.model;

import lombok.*;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;

@Getter
@Setter
@Entity
@Builder
@DynamicInsert
@DynamicUpdate
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RecruitPost {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @BatchSize(size = 100)
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "USER_ID")
//    private UserEntity user;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "PROFILE_ID")
//    private ProfileEntity profile;

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "recruitPost", orphanRemoval = true, cascade = CascadeType.ALL)
//    private List<RecruitPostTagEntity> recruitPostTag = new ArrayList<>();

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "recruitPost", orphanRemoval = true)
//    private List<ApplicantEntity> applicants = new ArrayList<>();


    @Column(nullable = false)
    private String title;

    @Lob
    @Column(nullable = false)
    private String body;


    @ColumnDefault("0")
    private Integer requiredDevelopers;

    @ColumnDefault("0")
    private Integer requiredDesigners;

    @ColumnDefault("0")
    private Integer requiredProjectManagers;

    private LocalDate projectStartTime;

    private LocalDate projectEndTime;

    private LocalDate recruitDueTime;

    @ColumnDefault("")
    private String imageUrl;

    @ColumnDefault("true")
    private Boolean isOpened;


}
