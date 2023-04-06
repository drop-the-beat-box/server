package store.dropthebeatbox.app.web.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

public class FavoriteFileResponseDto {

    @Builder
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    public static class FavoriteFileDto {
        private Long fileId;
        private String name;
        private String url;
        private String description;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }

    @Builder
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    public static class FavoriteFileListDto{
        private List<FavoriteFileDto> favoriteFileDtoList;
        private Integer size;
    }

    @Builder
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    public static class DeleteFavoriteFile{
        private LocalDateTime deletedAt;
    }

    @Builder
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    public static class CreateFavoriteFileDto {
        private Long favoriteFileId;
        private LocalDateTime createdAt;
    }
}
