package store.dropthebeatbox.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import store.dropthebeatbox.app.domain.Team;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
