package store.dropthebeatbox.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import store.dropthebeatbox.app.domain.File;
import store.dropthebeatbox.app.domain.Member;

import java.util.List;
import java.util.Optional;

public interface FileRepository extends JpaRepository<File, Long> {
    Optional<File> findById(Long id);
    List<File> findByMember(Member member);
}
