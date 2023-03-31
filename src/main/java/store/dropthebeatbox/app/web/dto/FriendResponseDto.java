package store.dropthebeatbox.app.web.dto;

import lombok.*;

import java.util.List;

public class FriendResponseDto {

    @Builder
    @Getter
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class FriendDto {
        private String field;
    }

    @Builder
    @Getter
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class FriendRequestDto {
        private String field;
    }

    @Builder
    @Getter
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class FriendListDto {
        private List<FriendDto> friendDtoList;
    }

    @Builder
    @Getter
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class FriendRequestListDto {
        private List<FriendRequestDto> friendRequestDtoList;
    }
}
