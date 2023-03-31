package store.dropthebeatbox.app.web.dto;

import lombok.Getter;

public class MemberRequestDto {
    @Getter
    public static class UpdateMemberDto {
        private String field;
    }
}
