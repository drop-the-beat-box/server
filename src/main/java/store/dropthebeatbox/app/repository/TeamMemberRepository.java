package store.dropthebeatbox.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import store.dropthebeatbox.app.domain.Member;
import store.dropthebeatbox.app.domain.mapping.TeamMember;

import java.util.List;

public interface TeamMemberRepository extends JpaRepository<TeamMember, Long> {
    @Query("select tm.team.id from TeamMember tm where tm.member = :member")
    List<Long> findTeamIdByMember(@Param("member") Member member);
}
