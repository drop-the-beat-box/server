package store.dropthebeatbox.app.web.dto;

import lombok.Getter;

public class TeamRequestDto {
    @Getter
    public static class CreateTeamDto {
        private String field;
    }

    @Getter
    public static class UpdateTeamDto {
    }
}
