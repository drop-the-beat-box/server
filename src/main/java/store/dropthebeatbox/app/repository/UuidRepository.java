package store.dropthebeatbox.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import store.dropthebeatbox.app.domain.Uuid;

public interface UuidRepository extends JpaRepository<Uuid, Long> {
    boolean existsByUuid(String uuid);
}
