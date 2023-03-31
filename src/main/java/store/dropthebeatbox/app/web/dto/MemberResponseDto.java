package store.dropthebeatbox.app.web.dto;

import lombok.*;

public class MemberResponseDto {

    @Builder
    @Getter
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class MemberDto {
        private String field;
    }
}
