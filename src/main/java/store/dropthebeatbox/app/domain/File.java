package store.dropthebeatbox.app.domain;

import lombok.*;
import store.dropthebeatbox.app.domain.common.BaseEntity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class File extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(columnDefinition = "TEXT")
    private String url;

    @Column(length = 300)
    private String description;

    private Boolean isDeleted;

    private LocalDateTime deletedAt;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "uuid_id")
    private Uuid uuid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "file_type_id")
    private FileType fileType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    // setter
    public void setDescription(String description) {
        this.description = description;
    }

    public void setDeleted(LocalDateTime deletedAt){
        this.deletedAt = deletedAt;
        this.isDeleted = true;
    }

    public void setRollback(){
        this.isDeleted = false;
        this.deletedAt = null;
    }
}
