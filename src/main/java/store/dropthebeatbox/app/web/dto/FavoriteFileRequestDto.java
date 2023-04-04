package store.dropthebeatbox.app.web.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class FavoriteFileRequestDto {

    @Getter
    public static class CreateFavoriteFileDto{
        String filed;
    }
}
