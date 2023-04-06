package store.dropthebeatbox.app.web.dto;

import lombok.Getter;

public class TeamRequestDto {
    @Getter
    public static class CreateTeamDto {
        private String name;
    }

    @Getter
    public static class UpdateTeamDto {
        private String name;
    }
}
