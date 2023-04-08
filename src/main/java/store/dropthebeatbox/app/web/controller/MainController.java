package store.dropthebeatbox.app.web.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import store.dropthebeatbox.app.auth.annotation.AuthUser;
import store.dropthebeatbox.app.domain.Member;

@RestController
public class MainController {

    @GetMapping("/")
    public String home() {
        return "DROP THE BEATBOX API Server";
    }

    @GetMapping("/health")
    public String health() {
        return "I'm healthy";
    }

    @GetMapping("/test")
    public String oauthProfile(@AuthUser Member member) {
        return "email : " + member.getEmail() + "\n" + "name : " + member.getName() + "\n" + "profileUrl : " + member.getProfileUrl();
    }
}
