package store.dropthebeatbox.app.web.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class FileRequestDto {

    @Getter
    public static class CreateFileDto{
        String filed;
    }

    @Getter
    public static class UpdateFileDto{
        String filed;
    }
}
