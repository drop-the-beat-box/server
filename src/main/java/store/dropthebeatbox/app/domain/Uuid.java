package store.dropthebeatbox.app.domain;

import lombok.*;
import store.dropthebeatbox.app.domain.common.BaseEntity;

import javax.persistence.*;

@Entity
@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Uuid extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String uuid;

    @OneToOne
    @JoinColumn(name = "file_id")
    private File file;
}