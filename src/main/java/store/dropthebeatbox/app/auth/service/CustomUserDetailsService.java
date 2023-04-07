package store.dropthebeatbox.app.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import store.dropthebeatbox.app.auth.dto.OAuthAttributes;
import store.dropthebeatbox.app.domain.Member;
import store.dropthebeatbox.app.repository.MemberRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails()
                .getUserInfoEndpoint().getUserNameAttributeName();

        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());
        Map<String, Object> modifiableAttributes = new HashMap<>(attributes.getAttributes());
        modifiableAttributes.put("registrationId", registrationId);
        modifiableAttributes.put("userNameAttributeName", userNameAttributeName);

        Member user = saveOrUpdate(attributes);
        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(user.getMemberRole().getAuthority()));


        String email = (String) modifiableAttributes.get("email");
        return new DefaultOAuth2User(
                authorities,
                modifiableAttributes,
                attributes.getNameAttributeKey());
    }

    private Member newMember(OAuthAttributes attributes) {
        Member newMember = attributes.toEntity();
        return newMember;
    }

    private Member saveOrUpdate(OAuthAttributes attributes) {
        Member member = memberRepository.findByEmailAndAuthProviderType(attributes.getEmail(), attributes.getAuthProviderType())
                .map(entity -> entity.update(attributes.getName()))
                .orElseGet(() -> newMember(attributes));

        return memberRepository.save(member);
    }
}
