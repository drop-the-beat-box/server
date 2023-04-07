package store.dropthebeatbox.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import store.dropthebeatbox.app.domain.Member;
import store.dropthebeatbox.app.domain.enums.AuthProviderType;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    boolean existsByEmail(String email);
    Optional<Member> findByEmailAndAuthProviderType(String email, AuthProviderType authProviderType);
}
