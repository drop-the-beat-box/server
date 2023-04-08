package store.dropthebeatbox.app.converter;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import store.dropthebeatbox.app.domain.Member;
import store.dropthebeatbox.app.domain.enums.AuthProviderType;
import store.dropthebeatbox.app.exception.MemberException;
import store.dropthebeatbox.app.exception.common.ErrorCode;
import store.dropthebeatbox.app.repository.MemberRepository;

import javax.annotation.PostConstruct;

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
}
