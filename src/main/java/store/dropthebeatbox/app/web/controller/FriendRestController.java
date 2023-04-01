package store.dropthebeatbox.app.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import store.dropthebeatbox.app.web.dto.FriendResponseDto;

@RestController
public class FriendRestController {

    @GetMapping("/member/friends")
    public ResponseEntity<FriendResponseDto.FriendListDto> getFriendList() {
        return null;
    }

    @GetMapping("/member/friend/requests")
    public ResponseEntity<FriendResponseDto.FriendRequestListDto> getFriendRequestList() {
        return null;
    }

    @PostMapping("/member/friend/{friendId}/request")
    public ResponseEntity<FriendResponseDto.CreateFriendRequestDto> createFriendRequest(@PathVariable(name = "friendId") Long friendId) {
        return null;
    }

    @PostMapping("/member/friend/request/{requestId}")
    public ResponseEntity<FriendResponseDto.AcceptFriendRequestDto> acceptFriendRequest(@PathVariable(name = "requestId") Long requsetId) {
        return null;
    }

    @DeleteMapping("/member/friend/{friendId}")
    public ResponseEntity<FriendResponseDto.DeleteFriendDto> deleteFriend(@PathVariable(name = "friendId") Long friendId) {
        return null;
    }
}
