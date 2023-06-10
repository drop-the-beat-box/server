package store.dropthebeatbox.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import store.dropthebeatbox.app.domain.File;
import store.dropthebeatbox.app.domain.Member;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface FileRepository extends JpaRepository<File, Long> {
    Optional<File> findById(Long id);

    @Query("select f FROM File f where f.member = :member and f.isDeleted = false ")
    List<File> findByMember(@Param("member") Member member);

    @Query("SELECT f FROM File f WHERE f.id IN (SELECT ff.file.id FROM FavoriteFile ff WHERE ff.member = :member)")
    List<File> findFavoriteFilesByMember(@Param("member") Member member);

    @Query("select f from File f where f.isDeleted = true and f.member = :member")
    List<File> findTrashFiles(@Param("member") Member member);
}
