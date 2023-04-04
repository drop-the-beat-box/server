package store.dropthebeatbox.app.web.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class SharedFileRequestDto {

    @Getter
    public static class CreateSharedFileDto{
        String filed;
    }
}
