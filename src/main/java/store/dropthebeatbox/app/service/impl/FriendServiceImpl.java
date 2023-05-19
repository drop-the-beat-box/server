package store.dropthebeatbox.app.service.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import store.dropthebeatbox.app.converter.FriendConverter;
import store.dropthebeatbox.app.domain.Member;
import store.dropthebeatbox.app.domain.mapping.Friend;
import store.dropthebeatbox.app.domain.mapping.FriendRequest;
import store.dropthebeatbox.app.repository.FriendRepository;
import store.dropthebeatbox.app.repository.FriendRequestRepository;
import store.dropthebeatbox.app.repository.MemberRepository;
import store.dropthebeatbox.app.service.FriendService;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FriendServiceImpl implements FriendService {

    private final FriendRepository friendRepository;

    private final FriendRequestRepository friendRequestRepository;

    private final MemberRepository memberRepository;

    @Override
    public List<Member> findAllByMemberId(Long memberId) {
        List<Member> memberList = friendRepository.findAllByMemberId(memberId);
        return memberList;
    }

    @Override
    public List<Object[]> findAllByToMemberId(Long meberId) {
        List<Object[]> fromMemberList = friendRequestRepository.findAllByToMemberId(meberId);
        return fromMemberList;
    }

    @Override
    @Transactional
    public Long createRequest(Member member, Long friendId) {
        Member targetMember = memberRepository.findById(friendId).get();
        FriendRequest friendRequest = FriendConverter.newFriendRequest(member, targetMember);
         FriendRequest createdRequest = friendRequestRepository.save(friendRequest);
        return createdRequest.getId();
    }

    @Override
    @Transactional
    public Long createFirend(Member member, Long requestId) {
        Member targetFriend = friendRequestRepository.findRequestById(requestId);
        friendRequestRepository.deleteById(requestId);
        Friend newFriend = FriendConverter.newFriend(member, targetFriend);
        friendRepository.save(newFriend);
        return targetFriend.getId();
    }

    @Override
    @Transactional
    public void delete(Long friendId){
        friendRepository.deleteByFriendId(friendId);
        return;
    }
}
