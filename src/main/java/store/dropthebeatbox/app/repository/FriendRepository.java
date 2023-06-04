package store.dropthebeatbox.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import store.dropthebeatbox.app.domain.Member;
import store.dropthebeatbox.app.domain.mapping.Friend;

import java.util.List;
import java.util.Optional;

public interface FriendRepository extends JpaRepository<Friend, Long> {

    @Query("select f.target from Friend f where f.owner.id = :memberId")
    List<Member> findAllByMemberId(@Param("memberId") Long memberId);

    @Modifying
    @Query("delete from Friend f where f.target.id = :friendId")
    void deleteByFriendId(@Param("friendId") Long friendId);

    Boolean existsByTargetId(Long targetId);

    Boolean existsByOwnerIdAndTargetId(Long ownerId, Long targetId);
}
