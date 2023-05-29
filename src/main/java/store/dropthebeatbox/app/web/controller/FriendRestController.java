package store.dropthebeatbox.app.web.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import store.dropthebeatbox.app.auth.annotation.AuthUser;
import store.dropthebeatbox.app.converter.FriendConverter;
import store.dropthebeatbox.app.domain.Member;
import store.dropthebeatbox.app.domain.mapping.Friend;
import store.dropthebeatbox.app.domain.mapping.FriendRequest;
import store.dropthebeatbox.app.service.FriendService;
import store.dropthebeatbox.app.web.dto.FriendResponseDto;

import java.util.List;

@Tag(name = "Friend API", description = "친구 요청, 수락, 조회, 삭제")
@RestController
@RequiredArgsConstructor
public class FriendRestController {

    private final FriendService friendService;

    @GetMapping("/member/friends")
    public ResponseEntity<FriendResponseDto.FriendListDto> getFriendList(@AuthUser Member member) {
        List<Member> memberList = friendService.findAllByMemberId(member.getId());
        return ResponseEntity.ok(FriendConverter.toFriendListDto(memberList));
    }

    @GetMapping("/member/friend/requests")
    public ResponseEntity<FriendResponseDto.FriendRequestListDto> getFriendRequestList(@AuthUser Member member) {
        List<FriendRequest> fromMemberList = friendService.findAllByToMemberId(member.getId());
        return ResponseEntity.ok(FriendConverter.toFriendRequestListDto(fromMemberList));
    }

    @PostMapping("/member/friend/{friendId}/request")
    public ResponseEntity<FriendResponseDto.CreateFriendRequestDto> createFriendRequest(@PathVariable(name = "friendId") Long friendId, @AuthUser Member member) {
        FriendRequest createdFriendRequest = friendService.createRequest(member, friendId);
        return ResponseEntity.ok(FriendConverter.toCreateFriendRequestDto(createdFriendRequest));
    }

    @PostMapping("/member/friend/request/{requestId}")
    public ResponseEntity<FriendResponseDto.AcceptFriendRequestDto> acceptFriendRequest(@PathVariable(name = "requestId") Long requestId, @AuthUser Member member) {
        Friend newFriend = friendService.createFriend(member, requestId);
        return ResponseEntity.ok(FriendConverter.toAcceptFriendRequestDto(newFriend));
    }

    @DeleteMapping("/member/friend/{friendId}")
    public ResponseEntity<FriendResponseDto.DeleteFriendDto> deleteFriend(@PathVariable(name = "friendId") Long friendId) {
        friendService.delete(friendId);
        return ResponseEntity.ok(FriendConverter.toDeleteFriendDto());
    }
}
