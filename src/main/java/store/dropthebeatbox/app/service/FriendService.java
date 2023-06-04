package store.dropthebeatbox.app.service;

import store.dropthebeatbox.app.domain.Member;
import store.dropthebeatbox.app.domain.mapping.Friend;
import store.dropthebeatbox.app.domain.mapping.FriendRequest;

import java.util.List;

public interface FriendService {
    List<Member> findAllByMemberId(Long memberId);

    List<FriendRequest> findAllByToMemberId(Long memberId);

    FriendRequest createRequest(Member member, Long friendId);

    Friend createFriend(Member member, Long requestId);

    void delete(Long friendId);

    Friend makeFriend(Member owner, Long targetId);
}
