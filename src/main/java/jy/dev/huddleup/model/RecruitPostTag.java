package jy.dev.huddleup.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Entity
@Builder
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RecruitPostTag {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn()
    private RecruitPost recruitPost;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn()
    private Tag tag;

    void setRecruitPost(RecruitPost post) {
        this.recruitPost = post;
    }

    public RecruitPostTag(Long recruitPostId, Long tagId) {
        this.recruitPost = new RecruitPost(recruitPostId);
        this.tag = new Tag(tagId);
    }


}
