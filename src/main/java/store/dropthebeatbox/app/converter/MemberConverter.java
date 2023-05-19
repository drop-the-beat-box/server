package store.dropthebeatbox.app.converter;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import store.dropthebeatbox.app.domain.Member;
import store.dropthebeatbox.app.domain.enums.AuthProviderType;
import store.dropthebeatbox.app.exception.MemberException;
import store.dropthebeatbox.app.exception.common.ErrorCode;
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
    private static MemberRepository staticMemberRepository;

    @PostConstruct
    public void init() {
        this.staticMemberRepository = this.memberRepository;
    }

    public static Member toMember(String email) {
        return staticMemberRepository.findByEmailAndAuthProviderType(email, AuthProviderType.KAKAO).orElseThrow(() -> new MemberException(ErrorCode.MEMBER_NOT_FOUND));
    }

    public static MemberResponseDto.MemberDto toMemberDto(Member member){
        return MemberResponseDto.MemberDto.builder()
                .memberId(member.getId())
                .name(member.getName())
                .email(member.getEmail())
                .profileUrl(member.getProfileUrl())
                .build();
    }

    public static MemberResponseDto.MemberListDto toMemberListDto(List<Member> memberList){
        List<MemberResponseDto.MemberDto> memberDtoList = memberList.stream()
                .map(member -> toMemberDto(member))
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
}
