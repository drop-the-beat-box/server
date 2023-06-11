package store.dropthebeatbox.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import store.dropthebeatbox.app.domain.File;
import store.dropthebeatbox.app.domain.mapping.SharedFile;

import java.util.List;

public interface SharedFileRepository extends JpaRepository<SharedFile, Long> {
    void deleteByFile_IdAndTeam_Id(Long fileId, Long teamId);

    @Query("SELECT sf.file FROM SharedFile sf WHERE sf.team.id = :teamId")
    List<File> findFilesByTeam_Id(Long teamId);
}
