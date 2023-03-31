package store.dropthebeatbox.app.web.dto;

import lombok.*;

import java.util.List;

public class MemberResponseDto {

    @Builder
    @Getter
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class MemberDto {
        private String field;
    }

    @Builder
    @Getter
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class MemberListDto {
        private List<MemberDto> memberDtoList;
    }
}
