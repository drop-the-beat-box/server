package store.dropthebeatbox.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import store.dropthebeatbox.app.domain.mapping.SharedFile;

public interface SharedFileRepository extends JpaRepository<SharedFile, Long> {
}
