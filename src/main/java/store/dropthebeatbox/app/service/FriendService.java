package store.dropthebeatbox.app.service;

import store.dropthebeatbox.app.domain.Member;

import java.util.List;

public interface FriendService {
    List<Member> findAllByMemberId(Long memberId);

    List<Object[]> findAllByToMemberId(Long memberId);

    Long createRequest(Member member, Long friendId);

    Long createFirend(Member member, Long requestId);

    void delete(Long friendId);
}
