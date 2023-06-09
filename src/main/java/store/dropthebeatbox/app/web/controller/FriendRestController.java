package store.dropthebeatbox.app.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import store.dropthebeatbox.app.auth.annotation.AuthUser;
import store.dropthebeatbox.app.converter.FriendConverter;
import store.dropthebeatbox.app.domain.Member;
import store.dropthebeatbox.app.domain.mapping.Friend;
import store.dropthebeatbox.app.domain.mapping.FriendRequest;
import store.dropthebeatbox.app.service.FriendService;
import store.dropthebeatbox.app.validation.annotation.ExistFriend;
import store.dropthebeatbox.app.validation.annotation.ExistFriendRequest;
import store.dropthebeatbox.app.validation.annotation.ExistMember;
import store.dropthebeatbox.app.web.dto.FriendResponseDto;

import java.util.List;

@Tag(name = "Friend API", description = "친구 요청, 수락, 조회, 삭제")
@Validated
@RestController
@RequiredArgsConstructor
public class FriendRestController {

    private final FriendService friendService;

    @Operation(summary = "친구목록 조회", description = "로그인 한 사용자의 친구 목록 조회입니다.")
    @Parameters({
            @Parameter(name = "member", hidden = true)
        }
    )
    @GetMapping("/member/friends")
    public ResponseEntity<FriendResponseDto.FriendListDto> getFriendList(@AuthUser Member member) {
        List<Member> memberList = friendService.findAllByMemberId(member.getId());
        return ResponseEntity.ok(FriendConverter.toFriendListDto(memberList));
    }

    @Deprecated
    @GetMapping("/member/friend/requests")
    public ResponseEntity<FriendResponseDto.FriendRequestListDto> getFriendRequestList(@AuthUser Member member) {
        List<FriendRequest> fromMemberList = friendService.findAllByToMemberId(member.getId());
        return ResponseEntity.ok(FriendConverter.toFriendRequestListDto(fromMemberList));
    }

    @Deprecated
    @PostMapping("/member/friend/{friendId}/request")
    public ResponseEntity<FriendResponseDto.CreateFriendRequestDto> createFriendRequest(@PathVariable(name = "friendId") @ExistMember Long friendId, @AuthUser Member member) {
        System.out.println("in friend Controller");
        System.out.println(friendId);
        FriendRequest createdFriendRequest = friendService.createRequest(member, friendId);
        return ResponseEntity.ok(FriendConverter.toCreateFriendRequestDto(createdFriendRequest));
    }

//    @Deprecated
//    @PostMapping("/member/friend/request/{requestId}")
//    public ResponseEntity<FriendResponseDto.AcceptFriendRequestDto> acceptFriendRequest(@PathVariable(name = "requestId") @ExistFriendRequest Long requestId, @AuthUser Member member) {
//        Friend newFriend = friendService.createFriend(member, requestId);
//        return ResponseEntity.ok(FriendConverter.toAcceptFriendRequestDto(newFriend));
//    }

    @Operation(summary = "친구 삭제입니다.", description = "삭제 하려는 친구 아이디로 삭제합니다.")
    @Parameters(
            {
                    @Parameter(name = "friendId", description = "삭제 하려는 친구 아이디"),
                    @Parameter(name = "member", hidden = true)
            }
    )
    @DeleteMapping("/member/friend/{friendId}")
    public ResponseEntity<FriendResponseDto.DeleteFriendDto> deleteFriend(@PathVariable(name = "friendId") @ExistMember Long friendId, @AuthUser Member member) {
        friendService.delete(friendId);
        return ResponseEntity.ok(FriendConverter.toDeleteFriendDto());
    }

    @Operation(summary = "친구 신청입니다.", description = "친구를 맺으려는 대상의 아이디로 친구를 맺습니다.")
    @Parameters(
            {
                    @Parameter(name = "friendId", description = "친구를 맺고 싶은 멤버 아이디입니다."),
                    @Parameter(name = "member", hidden = true)
            }
    )
    @PostMapping("/member/friend/{friendId}")
    public ResponseEntity<FriendResponseDto.AcceptFriendDto> createFriend(@PathVariable(name = "friendId") @ExistMember Long friendId, @AuthUser Member member){
        Friend friend = friendService.makeFriend(member, friendId);
        return ResponseEntity.ok(FriendConverter.toAcceptFriendDto(friend));
    }
}
