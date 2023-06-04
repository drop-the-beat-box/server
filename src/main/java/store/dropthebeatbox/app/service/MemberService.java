package store.dropthebeatbox.app.service;

import org.springframework.transaction.annotation.Transactional;
import store.dropthebeatbox.app.domain.Member;
import store.dropthebeatbox.app.web.dto.MemberRequestDto;

import java.util.List;

public interface MemberService {
    Member findById(Long memberId);

    List<Member> findAllByTeamId(Long teamId);

    List<Member> insertToTeam(Long teamId, MemberRequestDto.AddTeamMemberDto request);

    Member update(Long memberId, MemberRequestDto.UpdateMemberDto request);

    void delete(Long memberId);

    List<Member> search(String keyword);
}