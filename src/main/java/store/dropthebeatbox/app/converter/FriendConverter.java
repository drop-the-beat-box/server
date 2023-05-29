package store.dropthebeatbox.app.converter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import store.dropthebeatbox.app.domain.Member;
import store.dropthebeatbox.app.domain.mapping.Friend;
import store.dropthebeatbox.app.domain.mapping.FriendRequest;
import store.dropthebeatbox.app.repository.FriendRepository;
import store.dropthebeatbox.app.repository.FriendRequestRepository;
import store.dropthebeatbox.app.web.dto.FriendResponseDto;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class FriendConverter {

    private final FriendRepository friendRepository;

    private static FriendRepository staticFiendRepository;

    private final FriendRequestRepository friendRequestRepository;

    private static FriendRequestRepository staticFriendRequestRepository;

    @PostConstruct
    public void init(){staticFiendRepository = this.friendRepository;}

    public static FriendResponseDto.FriendDto toFriendDto(Member member){
        return FriendResponseDto.FriendDto.builder()
                .friendId(member.getId())
                .email(member.getEmail())
                .name(member.getName())
                .profileUrl(member.getProfileUrl())
                .build();
    }

    public static FriendResponseDto.FriendListDto toFriendListDto (List<Member> friendList){
        List<FriendResponseDto.FriendDto> frinedDtoList = friendList.stream()
                .map(friend -> toFriendDto(friend))
                .collect(Collectors.toList());

        return FriendResponseDto.FriendListDto.builder()
                .friendDtoList(frinedDtoList)
                .size(frinedDtoList.size())
                .build();
    }

    public static FriendResponseDto.FriendRequestDto toFriendRequestDto (FriendRequest friendRequest){
        return FriendResponseDto.FriendRequestDto.builder()
                .friendRequestId(friendRequest.getId())
                    .name(friendRequest.getTo().getName())
                .profileUrl(friendRequest.getTo().getProfileUrl())
                .build();
    }

    public static FriendResponseDto.FriendRequestListDto toFriendRequestListDto (List<FriendRequest> friendRequestList){
        List<FriendResponseDto.FriendRequestDto> friendRequestDtoList = friendRequestList.stream()
                .map(friendRequest -> toFriendRequestDto(friendRequest))
                .collect(Collectors.toList());

        return FriendResponseDto.FriendRequestListDto.builder()
                .friendRequestDtoList(friendRequestDtoList)
                .size(friendRequestDtoList.size())
                .build();
    }

    public static FriendResponseDto.CreateFriendRequestDto toCreateFriendRequestDto(FriendRequest friendRequest ){
        return FriendResponseDto.CreateFriendRequestDto.builder()
                .friendRequestId(friendRequest.getId())
                .fromId(friendRequest.getFrom().getId())
                .toId(friendRequest.getTo().getId())
                .createdAt(friendRequest.getCreatedAt())
                .build();
    }

    public static FriendResponseDto.AcceptFriendRequestDto toAcceptFriendRequestDto(Friend newFriend){
        return FriendResponseDto.AcceptFriendRequestDto.builder()
                .friendId(newFriend.getId())
                .ownerId(newFriend.getOwner().getId())
                .targetId(newFriend.getTarget().getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static FriendRequest newFriendRequest(Member from, Member to){
        return FriendRequest.builder()
                .from(from)
                .to(to)
                .build();
    }

    public static Friend newFriend(Member owner, Member target){
        return Friend.builder()
                .owner(owner)
                .target(target)
                .build();
    }
    public static FriendResponseDto.DeleteFriendDto toDeleteFriendDto(){
        return FriendResponseDto.DeleteFriendDto.builder()
                .deletedAt(LocalDateTime.now())
                .build();
    }
}
