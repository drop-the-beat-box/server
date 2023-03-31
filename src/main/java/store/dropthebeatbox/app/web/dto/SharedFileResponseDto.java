package store.dropthebeatbox.app.web.dto;

import lombok.*;

public class SharedFileResponseDto {

    @Builder
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    public static class sharedMemberListDto{
        String filed;
    }
}
