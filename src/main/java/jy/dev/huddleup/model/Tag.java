package jy.dev.huddleup.model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Entity
@Builder
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @BatchSize(size = 100)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "tag", orphanRemoval = true)
    private List<RecruitPostTag> recruitPostTag;

    @Column(nullable = false, unique = true)
    private String Name;

    public Tag(Long id) {
        this.id = id;
    }
}
