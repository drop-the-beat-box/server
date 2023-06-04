package store.dropthebeatbox.app.converter;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import store.dropthebeatbox.app.domain.Member;
import store.dropthebeatbox.app.domain.enums.AuthProviderType;
import store.dropthebeatbox.app.exception.MemberException;
import store.dropthebeatbox.app.exception.common.ErrorCode;
import store.dropthebeatbox.app.repository.FriendRepository;
import store.dropthebeatbox.app.repository.MemberRepository;
import store.dropthebeatbox.app.web.dto.MemberResponseDto;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MemberConverter {

    private final MemberRepository memberRepository;

    private final FriendRepository friendRepository;
    private static MemberRepository staticMemberRepository;

    private static FriendRepository staticFriendRepository;
    @PostConstruct
    public void init() {
        this.staticMemberRepository = this.memberRepository;
        this.staticFriendRepository = this.friendRepository;
    }

    public static Member toMember(String email) {
        return staticMemberRepository.findByEmailAndAuthProviderType(email, AuthProviderType.KAKAO).orElseThrow(() -> new MemberException(ErrorCode.MEMBER_NOT_FOUND));
    }

    public static MemberResponseDto.MemberDto toMemberDto(Member member, Member currentMember){
        Boolean following = staticFriendRepository.existsByOwnerIdAndTargetId(currentMember.getId(), member.getId());
        return MemberResponseDto.MemberDto.builder()
                .memberId(member.getId())
                .name(member.getName())
                .email(member.getEmail())
                .profileUrl(member.getProfileUrl())
                .following(following)
                .build();
    }

    public static MemberResponseDto.MemberListDto toMemberListDto(List<Member> memberList, Member currentMember){
        List<MemberResponseDto.MemberDto> memberDtoList = memberList.stream()
                .map(member -> toMemberDto(member, currentMember))
                .collect(Collectors.toList());
        return MemberResponseDto.MemberListDto.builder()
                .memberDtoList(memberDtoList)
                .size(memberDtoList.size())
                .build();
    }

    public static MemberResponseDto.JoinMemberDto toJoinMemberDto(Long memberId, Long teamId){
        return MemberResponseDto.JoinMemberDto.builder()
                .memberId(memberId)
                .teamId(teamId)
                .build();
    }

    public static MemberResponseDto.UpdateMemberDto toUpdateMemberDto(Member member){
        return MemberResponseDto.UpdateMemberDto.builder()
                .memberId(member.getId())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    public static MemberResponseDto.DeleteMemberDto toDeleteMemberDto(){
        return MemberResponseDto.DeleteMemberDto.builder()
                .deletedAt(LocalDateTime.now())
                .build();
    }

    public static MemberResponseDto.JoinMemberDto toJoinMemberDto(Member member, Long teamId){
        return  MemberResponseDto.JoinMemberDto.builder()
                .memberId(member.getId())
                .teamId(teamId)
                .build();
    }

    public static MemberResponseDto.JoinMemberListDto toJoinMemberListDto(List<Member> memberList, Long teamId){
        List<MemberResponseDto.JoinMemberDto> joinMemberDtoList = memberList.stream()
                .map(member -> toJoinMemberDto(member, teamId))
                .collect(Collectors.toList());

        return MemberResponseDto.JoinMemberListDto.builder()
                .joinMemberList(joinMemberDtoList)
                .size(joinMemberDtoList.size())
                .build();
    }
}
