package store.dropthebeatbox.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import store.dropthebeatbox.app.domain.Member;
import store.dropthebeatbox.app.domain.mapping.FavoriteFile;

public interface FavoriteFileRepository extends JpaRepository<FavoriteFile, Long> {
    void deleteByFile_IdAndMember(Long fileId, Member member);
}
