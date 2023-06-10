package store.dropthebeatbox.app.web.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

public class FileResponseDto {

    @Builder
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    public static class FileDto {
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
    public static class FileListDto{
        private List<FileDto> fileDtoList;
        private Integer size;
    }

    @Builder
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    public static class DeleteFileDto{
        private Long fileId;
        private LocalDateTime deletedAt;
    }

    @Builder
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    public static class CreateFileDto {
        private Long fileId;
        private LocalDateTime createdAt;
    }

    @Builder
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    public static class UpdateFileDto {
        private Long fileId;
        private LocalDateTime updatedAt;
    }

    @Builder
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    public static class trashFileDto {
        private Long fileId;
        private Long remainDay;
    }

    @Builder
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    public static class trashFileListDto{
        private List<trashFileDto> trashFileDtoList;
        private Integer size;
    }
}
