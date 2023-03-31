package store.dropthebeatbox.app.domain.enums;

import lombok.Getter;

@Getter
public enum MemberRole {
    USER("ROLE_USER", "유저");

    private String authority;
    private String description;

    MemberRole(String authority, String description) {
        this.authority = authority;
        this.description = description;
    }
}
