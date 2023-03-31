package store.dropthebeatbox.app.web.dto;

import lombok.*;

public class FileResponseDto {

    @Builder
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    public static class FileDto{
        private String filed;
    }
}
