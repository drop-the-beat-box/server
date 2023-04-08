package store.dropthebeatbox.app.auth.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;

import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import org.springframework.web.util.UriComponentsBuilder;
import store.dropthebeatbox.app.auth.dto.OAuthAttributes;
import store.dropthebeatbox.app.auth.provider.TokenProvider;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final TokenProvider tokenProvider;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {

        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        OAuthAttributes attributes = OAuthAttributes.of("kakao", null, oAuth2User.getAttributes());

        String email = attributes.getEmail();
        String accessToken = tokenProvider.createAccessToken(email, authentication.getAuthorities());

        // TODO : 클라이언트가 localhost인 경우와 배포 서버인 경우 분리해서 리다이렉트 시켜주기
        UriComponentsBuilder builder = UriComponentsBuilder.newInstance();
        URI redirectUri = builder
                .scheme("http")
                .host("localhost")
                .port(3000)
                .path("/token")
                .queryParam("accessToken", accessToken)
                .build()
                .toUri();

        getRedirectStrategy().sendRedirect(request, response, redirectUri.toString());
    }
}
