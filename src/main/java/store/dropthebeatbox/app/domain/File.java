package store.dropthebeatbox.app.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class File {

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

    @OneToOne(mappedBy = "file", cascade = CascadeType.ALL)
    private Uuid uuid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "file_type_id")
    private FileType fileType;
}
