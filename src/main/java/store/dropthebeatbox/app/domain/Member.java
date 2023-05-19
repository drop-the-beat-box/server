package store.dropthebeatbox.app.domain;

import lombok.*;
import store.dropthebeatbox.app.domain.common.BaseEntity;
import store.dropthebeatbox.app.domain.enums.AuthProviderType;
import store.dropthebeatbox.app.domain.enums.MemberRole;

import javax.persistence.*;

@Entity
@Builder
@Getter @Setter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String name;

    @Enumerated(EnumType.STRING)
    private AuthProviderType authProviderType;

    @Enumerated(EnumType.STRING)
    private MemberRole memberRole;

    @Column(columnDefinition = "TEXT")
    private String profileUrl;

    public Member update(String name) {
        this.name = name;
        return this;
    }
}