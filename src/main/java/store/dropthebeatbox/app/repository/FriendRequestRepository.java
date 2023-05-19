package store.dropthebeatbox.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import store.dropthebeatbox.app.domain.Member;
import store.dropthebeatbox.app.domain.mapping.FriendRequest;

import java.util.List;

public interface FriendRequestRepository extends JpaRepository<FriendRequest, Long> {

    @Query("select fr.id, fr.from from FriendRequest fr where fr.to.id = :toMemberId")
    List<Object[]> findAllByToMemberId(@Param("toMemberId") Long toMemberId);

    @Query("select fr.from from FriendRequest fr where fr.id =:requestId")
    Member findRequestById(@Param("requestId") Long requestId);
}
