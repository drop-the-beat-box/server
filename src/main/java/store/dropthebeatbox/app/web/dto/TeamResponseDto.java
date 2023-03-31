package store.dropthebeatbox.app.web.dto;

import lombok.*;

import java.util.List;

public class TeamResponseDto {

    @Builder
    @Getter
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class TeamDto {
        private String field;
    }

    @Builder
    @Getter
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class TeamListDto {
        private List<TeamDto> teamDtoList;
    }
}
