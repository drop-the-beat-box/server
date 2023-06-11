package store.dropthebeatbox.app.web.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

public class SharedFileResponseDto {

    @Builder
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    public static class SharedMemberDto {
        private Long memberId;
        private String email;
        private String name;
        private String profileUrl;
    }

    @Builder
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    public static class SharedMemberListDto{
        private List<SharedMemberDto> sharedMemberDtoList;
        private Integer size;
    }

    @Builder
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    public static class DeleteSharedMemberDto{
        private LocalDateTime deletedAt;
    }

    @Builder
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    public static class CreateSharedFileDto {
        private Long sharedFileId;
        private Long fileId;
        private Long teamId;
        private LocalDateTime createdAt;
    }

    @Builder
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    public static class SharedFileDto {
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
    public static class SharedFileListDto {
        private List<SharedFileDto> fileDtoList;
        private Integer size;
    }
}
