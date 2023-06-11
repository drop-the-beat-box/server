package store.dropthebeatbox.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import store.dropthebeatbox.app.domain.File;
import store.dropthebeatbox.app.domain.Member;
import store.dropthebeatbox.app.domain.enums.AuthProviderType;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    boolean existsByEmail(String email);
    Optional<Member> findByEmailAndAuthProviderType(String email, AuthProviderType authProviderType);

    Optional<Member> findById(Long id);

//    @Query("SELECT m FROM Member m WHERE m.id IN (SELECT sf.member.id FROM SharedFile sf WHERE sf.file.id = :fileId)")
//    List<Member> findSharedMembersByFile_Id(@Param("fileId") Long fileId);

    List<Member> findByIdIn (List<Long> memberList);
    List<Member> findByNameContains(String keyword);
}
