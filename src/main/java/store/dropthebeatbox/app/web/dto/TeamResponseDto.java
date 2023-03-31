package store.dropthebeatbox.app.web.dto;

import lombok.*;

public class TeamResponseDto {

    @Builder
    @Getter
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class TeamDto {
        private String field;
    }
}
