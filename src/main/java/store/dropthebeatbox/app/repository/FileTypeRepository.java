package store.dropthebeatbox.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import store.dropthebeatbox.app.domain.FileType;

import java.util.Optional;

public interface FileTypeRepository extends JpaRepository<FileType, Long> {
    boolean existsByName(String name);
    Optional<FileType> findByName(String name);
}
