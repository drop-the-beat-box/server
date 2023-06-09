package store.dropthebeatbox.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import store.dropthebeatbox.app.domain.Member;
import store.dropthebeatbox.app.domain.mapping.FriendRequest;

import java.util.List;

public interface FriendRequestRepository extends JpaRepository<FriendRequest, Long> {

    List<FriendRequest> findByTo_Id(Long toId);

    Member findFromById(Long id);
}
